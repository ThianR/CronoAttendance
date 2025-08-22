import { Routes } from '@angular/router';

export const ROUTES: Routes = [
  {
    path: 'empresas',
    children: [
      {
        path: '',
        loadComponent: () =>
          import('./empresas/list/empresa-list.component').then(
            (m) => m.EmpresaListComponent
          ),
      },
      {
        path: 'nuevo',
        loadComponent: () =>
          import('./empresas/form/empresa-form.component').then(
            (m) => m.EmpresaFormComponent
          ),
      },
      {
        path: 'editar/:id',
        loadComponent: () =>
          import('./empresas/form/empresa-form.component').then(
            (m) => m.EmpresaFormComponent
          ),
      },
    ],
  },
  { path: '', redirectTo: 'empresas', pathMatch: 'full' },
  { path: '**', redirectTo: 'empresas' },
];
