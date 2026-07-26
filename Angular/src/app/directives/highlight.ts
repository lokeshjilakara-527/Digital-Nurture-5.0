import { Directive, HostListener, Input, ElementRef, Renderer2 } from '@angular/core';

// appHighlight: adds a background colour on hover, removes it on leave.
@Directive({ selector: '[appHighlight]' })
export class Highlight {
  @Input() appHighlight = 'yellow'; // configurable colour (default yellow)

  constructor(private el: ElementRef, private renderer: Renderer2) {}

  @HostListener('mouseenter') onEnter() {
    this.renderer.setStyle(this.el.nativeElement, 'backgroundColor', this.appHighlight);
  }
  @HostListener('mouseleave') onLeave() {
    this.renderer.removeStyle(this.el.nativeElement, 'backgroundColor');
  }
}
