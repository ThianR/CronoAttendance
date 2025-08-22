import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { ROUTES_SERVER } from './app.routes.server';

export const appConfigServer: ApplicationConfig = {
  providers: [provideRouter(ROUTES_SERVER)],
};
