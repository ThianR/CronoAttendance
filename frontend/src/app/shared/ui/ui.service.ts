import { Injectable, signal } from '@angular/core';

export type ToastType = 'success' | 'error' | 'info' | 'warning';

export interface Toast {
  id: number;
  type: ToastType;
  title?: string;
  message: string;
  timeout?: number; // ms
}

export interface ConfirmOptions {
  title?: string;
  message: string;
  acceptText?: string;
  cancelText?: string;
}

@Injectable({ providedIn: 'root' })
export class UiService {
  // ---- TOASTS ----
  private _toasts = signal<Toast[]>([]);
  toasts = this._toasts.asReadonly();
  private nextId = 1;

  toast(input: Omit<Toast, 'id'>) {
    const t: Toast = { id: this.nextId++, timeout: 4000, ...input };
    this._toasts.update((list) => [...list, t]);
    if (t.timeout && t.timeout > 0) {
      setTimeout(() => this.dismiss(t.id), t.timeout);
    }
  }
  dismiss(id: number) {
    this._toasts.update((list) => list.filter((t) => t.id !== id));
  }

  // ---- CONFIRM ----
  confirmOpen = signal(false);
  confirmOpts = signal<ConfirmOptions | null>(null);
  private confirmResolver?: (v: boolean) => void;

  confirm(opts: ConfirmOptions): Promise<boolean> {
    this.confirmOpts.set({
      acceptText: 'Sí, borrar',
      cancelText: 'Cancelar',
      title: 'Confirmar',
      ...opts,
    });
    this.confirmOpen.set(true);
    return new Promise<boolean>((resolve) => {
      this.confirmResolver = resolve;
    });
  }

  confirmAccept() {
    this.confirmOpen.set(false);
    this.confirmOpts.set(null);
    this.confirmResolver?.(true);
    this.confirmResolver = undefined;
  }
  confirmCancel() {
    this.confirmOpen.set(false);
    this.confirmOpts.set(null);
    this.confirmResolver?.(false);
    this.confirmResolver = undefined;
  }
}
