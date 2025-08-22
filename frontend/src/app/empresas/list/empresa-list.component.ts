import { Component, OnInit, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Empresa } from '../../models/empresa';
import { EmpresaService } from '../../services/empresa.service';

@Component({
  selector: 'app-empresa-list',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './empresa-list.component.html',
})
export class EmpresaListComponent implements OnInit {
  empresas: Empresa[] = [];
  private svc = inject(EmpresaService);
  ngOnInit() {
    this.load();
  }
  load() {
    this.svc.list().subscribe((r) => (this.empresas = r));
  }
  borrar(id?: number) {
    if (id && confirm('¿Borrar empresa?'))
      this.svc.delete(id).subscribe(() => this.load());
  }
}
