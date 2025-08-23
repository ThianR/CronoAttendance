import { Directive, ElementRef, HostListener, Optional } from '@angular/core';
import { NgControl } from '@angular/forms';

@Directive({
  selector: '[appUppercase]',
  standalone: true,
})
export class UppercaseDirective {
  constructor(
    private el: ElementRef<HTMLInputElement>,
    @Optional() private ngControl?: NgControl
  ) {}

  @HostListener('input')
  onInput() {
    const next = (this.el.nativeElement.value || '').toUpperCase();

    // 1) Actualiza el control (si existe) sin bucle de eventos
    this.ngControl?.control?.setValue(next, { emitEvent: false });

    // 2) Mantiene sincronizada la UI (por si no hay reactive forms)
    if (this.el.nativeElement.value !== next) {
      this.el.nativeElement.value = next;
    }
  }
}
