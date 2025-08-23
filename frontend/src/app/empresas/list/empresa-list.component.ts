// src/app/empresas/list/empresa-list.component.ts
import { Component, OnInit, computed, inject, signal } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Empresa } from '../../models/empresa';
import { EmpresaService } from '../../services/empresa.service';

@Component({
  selector: 'app-empresa-list',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, RouterLink],
  templateUrl: './empresa-list.component.html',
})
export class EmpresaListComponent implements OnInit {
  // inyección tipada (evita "unknown")
  private svc: EmpresaService = inject(EmpresaService);
  private router: Router = inject(Router);
  private fb: FormBuilder = inject(FormBuilder);

  // datos
  empresas = signal<Empresa[]>([]);

  // filtros
  filtroForm = this.fb.nonNullable.group({
    codigo: [''],
    descripcion: [''],
    estado: ['' as '' | 'ACTIVO' | 'INACTIVO'],
  });

  // paginación
  page = signal<number>(1);
  pageSize = signal<number>(20);
  pageSizeOptions: number[] = [10, 20, 50];

  // lista filtrada
  filtradas = computed<Empresa[]>(() => {
    const { codigo, descripcion, estado } = this.filtroForm.getRawValue();
    const cod = (codigo || '').toLowerCase();
    const des = (descripcion || '').toLowerCase();
    const est = (estado || '').toUpperCase();
    return this.empresas().filter(
      (e) =>
        (!cod || e.codigo.toLowerCase().includes(cod)) &&
        (!des || e.descripcion.toLowerCase().includes(des)) &&
        (!est || e.estado === (est as 'ACTIVO' | 'INACTIVO'))
    );
  });

  // página actual
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
    this.filtroForm.valueChanges.subscribe(() => this.page.set(1));
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

  // paginación
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
