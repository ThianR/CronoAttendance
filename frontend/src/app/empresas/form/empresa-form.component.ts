import { DatePipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { Empresa } from '../../models/empresa';
import { EmpresaService } from '../../services/empresa.service';
import { UppercaseDirective } from '../../shared/directives/ uppercase.directive';
import { UiService } from '../../shared/ui/ui.service';

@Component({
  selector: 'app-empresa-form',
  standalone: true,
  imports: [ReactiveFormsModule, DatePipe, UppercaseDirective],
  templateUrl: './empresa-form.component.html',
})
export class EmpresaFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private svc = inject(EmpresaService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);
  private ui = inject(UiService);

  form = this.fb.group({
    id: this.fb.control<number | undefined>(undefined),
    codigo: this.fb.control('', {
      nonNullable: true,
      validators: [Validators.required, Validators.maxLength(20)],
    }),
    descripcion: this.fb.control('', {
      nonNullable: true,
      validators: [Validators.required, Validators.maxLength(120)],
    }),
    estado: this.fb.control<'ACTIVO' | 'INACTIVO'>('ACTIVO', {
      nonNullable: true,
    }),
    // Metadatos (solo lectura, vienen del entity)
    fechaAlta: this.fb.control<Date | string | null>({
      value: null,
      disabled: true,
    }),
    fechaMod: this.fb.control<Date | string | null>({
      value: null,
      disabled: true,
    }),
  });

  cargando = false;

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      const id = +idParam;
      this.cargando = true;
      this.svc.get(id).subscribe({
        next: (e: Empresa) => {
          this.form.patchValue(e);
          this.form.get('codigo')?.disable(); // evitar edición del código
          this.cargando = false;
        },
        error: () => (this.cargando = false),
      });
    }
  }

  guardar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      this.ui.toast({
        type: 'warning',
        title: 'Formulario',
        message: 'Revisa los campos requeridos.',
      });
      return;
    }

    const isEdit = !!this.form.value.id;
    const codigoCtrl = this.form.get('codigo');

    // En edición bloqueamos el código, pero lo incluimos con getRawValue()
    if (isEdit) codigoCtrl?.disable({ emitEvent: false });

    this.cargando = true;

    // Incluye controles deshabilitados
    const raw = this.form.getRawValue();

    // No mandamos metadatos manejados por el backend
    const { fechaAlta, fechaMod, ...empresa } = raw as any;

    const req$ = isEdit
      ? this.svc.update(empresa as Empresa)
      : this.svc.create(empresa as Empresa);

    req$
      .pipe(
        finalize(() => {
          this.cargando = false;
          // Si te quedas en la misma pantalla ante error, re–habilita el control
          codigoCtrl?.enable({ emitEvent: false });
        })
      )
      .subscribe({
        next: () => {
          this.ui.toast({
            type: 'success',
            title: isEdit ? 'Actualizado' : 'Creado',
            message: 'Empresa guardada correctamente',
          });
          this.router.navigate(['/empresas']);
        },
        error: (err: unknown) => {
          // El interceptor ya muestra un toast genérico; aquí afinamos algunos casos
          if (err instanceof HttpErrorResponse) {
            if (err.status === 409) {
              this.ui.toast({
                type: 'error',
                title: 'Duplicado',
                message: 'El código ya existe.',
              });
            } else if (err.status === 400) {
              this.ui.toast({
                type: 'error',
                title: 'Validación',
                message: 'Datos inválidos. Revisa el formulario.',
              });
            }
          }
        },
      });
  }

  cancelar(): void {
    this.router.navigateByUrl('/empresas');
  }

  // helpers para template
  get f() {
    return this.form.controls;
  }
}
