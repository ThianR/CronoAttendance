import { Component, OnInit, computed, inject, signal } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { startWith } from 'rxjs/operators';

import { DatePipe } from '@angular/common';
import { Empresa } from '../../models/empresa';
import { EmpresaService } from '../../services/empresa.service';

@Component({
  selector: 'app-empresa-list',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, DatePipe],
  templateUrl: './empresa-list.component.html',
})
export class EmpresaListComponent implements OnInit {
  private svc: EmpresaService = inject(EmpresaService);
  private router: Router = inject(Router);
  private fb: FormBuilder = inject(FormBuilder);

  empresas = signal<Empresa[]>([]);

  // Filtros (form)
  filtroForm = this.fb.nonNullable.group({
    codigo: [''],
    descripcion: [''],
    estado: ['' as '' | 'ACTIVO' | 'INACTIVO'],
  });

  // Filtros como SIGNAL (clave para que el computed reaccione)
  filtros = toSignal(
    this.filtroForm.valueChanges.pipe(startWith(this.filtroForm.getRawValue())),
    { initialValue: this.filtroForm.getRawValue() }
  );

  // Paginación
  page = signal<number>(1);
  pageSize = signal<number>(10);
  pageSizeOptions: number[] = [10, 20, 50];

  // Lista filtrada (reacciona a filtros + datos)
  filtradas = computed<Empresa[]>(() => {
    const f = this.filtros();
    const cod = (f.codigo || '').toLowerCase();
    const des = (f.descripcion || '').toLowerCase();
    const est = (f.estado || '').toUpperCase();

    return this.empresas().filter((e) => {
      const c = String(e.codigo ?? '').toLowerCase();
      const d = String(e.descripcion ?? '').toLowerCase();
      const s = String(e.estado ?? '').toUpperCase();
      return (
        (!cod || c.includes(cod)) &&
        (!des || d.includes(des)) &&
        (!est || s === est)
      );
    });
  });

  // Página actual
  pageData = computed<Empresa[]>(() => {
    const p = this.page();
    const size = this.pageSize();
    const start = (p - 1) * size;
    return this.filtradas().slice(start, start + size);
  });

  totalPages = computed<number>(() =>
    Math.max(1, Math.ceil(this.filtradas().length / this.pageSize()))
  );

  ngOnInit() {
    this.load();
  }

  load() {
    this.svc.list().subscribe((data) => {
      this.empresas.set((data || []).sort((a, b) => a.id! - b.id!));
      this.page.set(1);
    });
  }

  create() {
    this.router.navigate(['/empresas/nuevo']);
  }
  edit(e: Empresa) {
    this.router.navigate(['/empresas/editar', e.id]);
  }
  remove(e: Empresa) {
    if (!e.id) return;
    if (confirm(`¿Borrar empresa "${e.descripcion}"?`)) {
      this.svc.delete(e.id).subscribe(() => this.load());
    }
  }

  // Paginación
  goTo(p: number) {
    this.page.set(Math.min(Math.max(1, p), this.totalPages()));
  }
  next() {
    this.goTo(this.page() + 1);
  }
  prev() {
    this.goTo(this.page() - 1);
  }

  trackById = (_: number, e: Empresa) => e.id;
}
