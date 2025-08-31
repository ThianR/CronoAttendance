import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Empresa } from '../models/empresa';

export interface PageResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number; // tamaño de página
  number: number; // página actual (0-based)
}

@Injectable({ providedIn: 'root' })
export class EmpresaService {
  private http = inject(HttpClient);
  private base = '/api/empresas';

  // NUEVO: listado paginado + filtros
  listPaged(opts: {
    page?: number;
    size?: number;
    sort?: string;
    codigo?: string;
    descripcion?: string;
    estado?: '' | 'ACTIVO' | 'INACTIVO';
  }): Observable<PageResponse<Empresa>> {
    let p = new HttpParams()
      .set('page', String(opts.page ?? 0))
      .set('size', String(opts.size ?? 20))
      .set('sort', opts.sort ?? 'id,desc');

    if (opts.codigo) p = p.set('codigo', opts.codigo);
    if (opts.descripcion) p = p.set('descripcion', opts.descripcion);
    if (opts.estado) p = p.set('estado', opts.estado);

    return this.http.get<PageResponse<Empresa>>(this.base, { params: p });
  }

  // (opcional) métodos CRUD ya existentes
  get(id: number) {
    return this.http.get<Empresa>(`${this.base}/${id}`);
  }
  create(e: Empresa) {
    return this.http.post<Empresa>(this.base, e);
  }
  update(e: Empresa) {
    return this.http.put<Empresa>(`${this.base}/${e.id}`, e);
  }
  delete(id: number) {
    return this.http.delete<void>(`${this.base}/${id}`);
  }
}
