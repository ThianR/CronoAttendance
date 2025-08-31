import { DatePipe, formatDate } from '@angular/common';
import { Component, OnInit, inject, signal } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { debounceTime, finalize } from 'rxjs/operators';
import * as XLSX from 'xlsx';

import { Empresa } from '../../models/empresa';
import { EmpresaService, PageResponse } from '../../services/empresa.service';

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

  // estado
  loading = signal(true);
  empresas = signal<Empresa[]>([]);
  total = signal<number>(0);

  // paginación (UI 1-based)
  page = signal<number>(1);
  pageSize = signal<number>(10);
  pageSizeOptions: number[] = [10, 20, 50];
  skeletonRows = signal(Array.from({ length: this.pageSize() }));

  // filtros (enviados al backend)
  filtroForm = this.fb.nonNullable.group({
    codigo: [''],
    descripcion: [''],
    estado: ['' as '' | 'ACTIVO' | 'INACTIVO'],
  });

  ngOnInit() {
    this.loadPage();
    this.filtroForm.valueChanges.pipe(debounceTime(250)).subscribe(() => {
      this.page.set(1);
      this.loadPage();
    });
  }

  loadPage() {
    this.loading.set(true);
    this.skeletonRows.set(Array.from({ length: this.pageSize() }));
    const f = this.filtroForm.getRawValue();

    this.svc
      .listPaged({
        page: this.page() - 1, // backend es 0-based
        size: this.pageSize(),
        sort: 'id,desc',
        codigo: f.codigo || undefined,
        descripcion: f.descripcion || undefined,
        estado: f.estado || undefined,
      })
      .pipe(finalize(() => this.loading.set(false)))
      .subscribe((resp: PageResponse<Empresa>) => {
        this.empresas.set(resp.content || []);
        this.total.set(resp.totalElements ?? 0);
      });
  }

  // acciones
  create() {
    this.router.navigate(['/empresas/nuevo']);
  }
  edit(e: Empresa) {
    this.router.navigate(['/empresas/editar', e.id]);
  }
  remove(e: Empresa) {
    if (!e.id) return;
    if (confirm(`¿Borrar empresa "${e.descripcion}"?`)) {
      this.svc.delete(e.id).subscribe(() => this.loadPage());
    }
  }

  // export (exporta la página visible)
  private fmt(d?: string | Date | null): string {
    if (!d) return '';
    try {
      const date = typeof d === 'string' ? new Date(d) : d;
      return formatDate(date, 'dd/MM/yyyy HH:mm:ss', 'es-ES');
    } catch {
      return '';
    }
  }
  private timestamp(): string {
    const now = new Date();
    return formatDate(now, 'yyyyMMdd_HHmmss', 'es-ES');
  }
  exportCSV() {
    const rows = (this.empresas() || []).map((e) => ({
      Código: e.codigo ?? '',
      Descripción: e.descripcion ?? '',
      Estado: e.estado ?? '',
      'Fecha alta': this.fmt(e.fechaAlta),
    }));
    const headers = Object.keys(
      rows[0] ?? {
        Código: '',
        Descripción: '',
        Estado: '',
        'Fecha alta': '',
      }
    );
    const esc = (v: any) => `"${String(v ?? '').replace(/"/g, '""')}"`;
    const csv = [
      headers.map(esc).join(';'),
      ...rows.map((r) =>
        headers.map((h) => esc((r as Record<string, unknown>)[h])).join(';')
      ),
    ].join('\r\n');
    const blob = new Blob(['\uFEFF' + csv], {
      type: 'text/csv;charset=utf-8;',
    });
    const a = document.createElement('a');
    a.href = URL.createObjectURL(blob);
    a.download = `empresas_${this.timestamp()}_page${this.page()}.csv`;
    a.click();
    URL.revokeObjectURL(a.href);
  }
  exportExcel() {
    const rows = (this.empresas() || []).map((e) => ({
      Código: e.codigo ?? '',
      Descripción: e.descripcion ?? '',
      Estado: e.estado ?? '',
      'Fecha alta': this.fmt(e.fechaAlta),
    }));
    const ws = XLSX.utils.json_to_sheet(rows);
    const wb = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, 'Empresas');
    XLSX.writeFile(wb, `empresas_${this.timestamp()}_page${this.page()}.xlsx`);
  }
  canExport = signal<boolean>(false);

  // paginación UI
  get totalPages(): number {
    const t = this.total();
    const size = this.pageSize();
    return Math.max(1, Math.ceil(t / size));
  }
  goTo(p: number) {
    const np = Math.min(Math.max(1, p), this.totalPages);
    this.page.set(np);
    this.loadPage();
  }
  next() {
    this.goTo(this.page() + 1);
  }
  prev() {
    this.goTo(this.page() - 1);
  }
}
