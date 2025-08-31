import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { UiService } from '../shared/ui/ui.service';

export const httpErrorInterceptor: HttpInterceptorFn = (req, next) => {
  const ui = inject(UiService);
  return next(req).pipe(
    catchError((err: unknown) => {
      if (err instanceof HttpErrorResponse) {
        const status = err.status;
        const url = err.url ?? '';
        const detail =
          (err.error &&
            (err.error.message || err.error.error || err.error.detail)) ||
          err.message ||
          'Error desconocido';

        ui.toast({
          type: 'error',
          title: `Error ${status}`,
          message: `${detail}${url ? ` (${url})` : ''}`,
          timeout: 6000,
        });
      }
      return throwError(() => err);
    })
  );
};
