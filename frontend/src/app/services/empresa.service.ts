// src/app/services/empresa.service.ts
import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Empresa } from '../models/empresa';

@Injectable({ providedIn: 'root' })
export class EmpresaService {
  private http = inject(HttpClient);
  private base = '/api/empresas';

  list(): Observable<Empresa[]> {
    return this.http.get<Empresa[]>(this.base);
  }
  get(id: number): Observable<Empresa> {
    return this.http.get<Empresa>(`${this.base}/${id}`);
  }
  create(e: Empresa): Observable<Empresa> {
    return this.http.post<Empresa>(this.base, e);
  }
  update(e: Empresa): Observable<Empresa> {
    return this.http.put<Empresa>(`${this.base}/${e.id}`, e);
  }
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.base}/${id}`);
  }
}
