import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { UiOverlayComponent } from './shared/ui/ui-overlay.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, UiOverlayComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {
  protected readonly title = signal('crono-frontend');
}
