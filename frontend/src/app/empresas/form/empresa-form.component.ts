import { DatePipe } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Empresa } from '../../models/empresa';
import { EmpresaService } from '../../services/empresa.service';
import { UppercaseDirective } from '../../shared/directives/ uppercase.directive';

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
      return;
    }

    // Evitar edición del código (seguridad extra en modo editar)
    if (this.form.value.id) {
      this.form.get('codigo')?.disable();
    }

    this.cargando = true;

    // Incluye controles deshabilitados (p. ej., 'codigo' cuando está deshabilitado)
    const raw = this.form.getRawValue();

    // Excluir metadatos de la petición
    const { fechaAlta, fechaMod, ...empresa } = raw as any;

    const req = empresa.id
      ? this.svc.update(empresa as Empresa)
      : this.svc.create(empresa as Empresa);

    req.subscribe({
      next: () => this.router.navigateByUrl('/empresas'),
      error: () => (this.cargando = false),
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
