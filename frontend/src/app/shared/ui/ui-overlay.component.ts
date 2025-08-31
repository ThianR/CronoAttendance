import { NgClass, NgIf } from '@angular/common';
import { Component, inject } from '@angular/core';
import { UiService } from './ui.service';

@Component({
  selector: 'app-ui-overlay',
  standalone: true,
  imports: [NgIf, NgClass],
  template: `
    <!-- MODAL CONFIRM -->
    @if (ui.confirmOpen()) {
    <div class="overlay">
      <div class="modal">
        <h3>{{ ui.confirmOpts()?.title }}</h3>
        <p class="msg">{{ ui.confirmOpts()?.message }}</p>
        <div class="actions">
          <button class="btn danger" (click)="ui.confirmAccept()">
            {{ ui.confirmOpts()?.acceptText }}
          </button>
          <button class="btn ghost" (click)="ui.confirmCancel()">
            {{ ui.confirmOpts()?.cancelText }}
          </button>
        </div>
      </div>
    </div>
    }

    <!-- TOASTS -->
    <div class="toasts">
      @for (t of ui.toasts(); track t.id) {
      <div class="toast" [ngClass]="t.type" (click)="ui.dismiss(t.id)">
        <strong *ngIf="t.title">{{ t.title }}</strong>
        <span>{{ t.message }}</span>
      </div>
      }
    </div>
  `,
  styles: [
    `
      .overlay {
        position: fixed;
        inset: 0;
        background: rgba(0, 0, 0, 0.35);
        display: grid;
        place-items: center;
        z-index: 1100;
      }
      .modal {
        width: min(92vw, 480px);
        background: #fff;
        border: 1px solid #e5e7eb;
        border-radius: 14px;
        padding: 1rem;
        box-shadow: 0 20px 50px rgba(0, 0, 0, 0.2);
      }
      .modal h3 {
        margin: 0.25rem 0 0.25rem 0;
        font-weight: 800;
      }
      .modal .msg {
        color: #334155;
        margin: 0.25rem 0 1rem;
      }
      .modal .actions {
        display: flex;
        gap: 0.5rem;
        justify-content: flex-end;
      }
      .btn {
        border: 1px solid #e5e7eb;
        border-radius: 999px;
        padding: 0.6rem 1rem;
        cursor: pointer;
        background: #fff;
      }
      .btn.ghost:hover {
        box-shadow: 0 0 0 3px rgba(0, 0, 0, 0.06);
      }
      .btn.danger {
        background: #ef4444;
        color: #fff;
        border: 0;
      }
      .btn.danger:hover {
        background: #dc2626;
      }
      .toasts {
        position: fixed;
        right: 12px;
        bottom: 12px;
        display: grid;
        gap: 0.5rem;
        z-index: 1200;
      }
      .toast {
        min-width: 220px;
        max-width: 360px;
        border-radius: 12px;
        border: 1px solid #e5e7eb;
        background: #fff;
        padding: 0.5rem 0.75rem;
        box-shadow: 0 6px 16px rgba(0, 0, 0, 0.12);
        cursor: pointer;
      }
      .toast.success {
        border-color: #16a34a;
      }
      .toast.error {
        border-color: #ef4444;
      }
      .toast.info {
        border-color: #0ea5e9;
      }
      .toast.warning {
        border-color: #f59e0b;
      }
      .toast strong {
        display: block;
      }
    `,
  ],
})
export class UiOverlayComponent {
  ui = inject(UiService);
}
