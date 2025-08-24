import { DatePipe, formatDate } from '@angular/common';
import { Component, OnInit, computed, inject, signal } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { startWith } from 'rxjs/operators';
import * as XLSX from 'xlsx';

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

  filtroForm = this.fb.nonNullable.group({
    codigo: [''],
    descripcion: [''],
    estado: ['' as '' | 'ACTIVO' | 'INACTIVO'],
  });

  filtros = toSignal(
    this.filtroForm.valueChanges.pipe(startWith(this.filtroForm.getRawValue())),
    { initialValue: this.filtroForm.getRawValue() }
  );

  page = signal<number>(1);
  pageSize = signal<number>(20);
  pageSizeOptions: number[] = [10, 20, 50];

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

  // --------- EXPORTS ---------
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
  private asRows(usePage = false): Array<Record<string, any>> {
    const src = usePage ? this.pageData() : this.filtradas();
    return src.map((e) => ({
      ID: e.id ?? '',
      Código: e.codigo ?? '',
      Descripción: e.descripcion ?? '',
      Estado: e.estado ?? '',
      'Fecha alta': this.fmt(e.fechaAlta),
    }));
  }

  exportCSV(usePage = false) {
    const rows = this.asRows(usePage);
    const headers = Object.keys(
      rows[0] ?? {
        ID: '',
        Código: '',
        Descripción: '',
        Estado: '',
        'Fecha alta': '',
      }
    );

    const escape = (v: any) => {
      const s = String(v ?? '');
      // siempre entre comillas y escapamos comillas dobles
      return `"${s.replace(/"/g, '""')}"`;
    };

    const csv = [
      headers.map(escape).join(';'),
      ...rows.map((r) => headers.map((h) => escape(r[h])).join(';')),
    ].join('\r\n');

    const blob = new Blob(['\uFEFF' + csv], {
      type: 'text/csv;charset=utf-8;',
    }); // BOM para Excel
    const a = document.createElement('a');
    a.href = URL.createObjectURL(blob);
    a.download = `empresas_${this.timestamp()}.csv`;
    a.click();
    URL.revokeObjectURL(a.href);
  }

  exportExcel(usePage = false) {
    const data = this.asRows(usePage);
    const ws = XLSX.utils.json_to_sheet(data);
    const wb = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, 'Empresas');
    XLSX.writeFile(wb, `empresas_${this.timestamp()}.xlsx`);
  }
  // ---------------------------

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
