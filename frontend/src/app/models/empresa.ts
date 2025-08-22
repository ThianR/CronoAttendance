export interface Empresa {
  id?: number;
  codigo: string;
  descripcion: string;
  estado: 'ACTIVO' | 'INACTIVO';
  fechaAlta?: string;
  fechaMod?: string;
}
