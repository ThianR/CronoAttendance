import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { ApplicationConfig, LOCALE_ID } from '@angular/core';
import {
  provideClientHydration,
  withEventReplay,
} from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { ROUTES } from './app.routes';
import { httpErrorInterceptor } from './core/http-error.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(ROUTES),
    provideHttpClient(withInterceptors([httpErrorInterceptor])),
    provideClientHydration(withEventReplay()),
    { provide: LOCALE_ID, useValue: 'es-ES' }, // <-- clave
    //provideBrowserGlobalErrorListeners(),
    //provideZonelessChangeDetection(),
  ],
};
