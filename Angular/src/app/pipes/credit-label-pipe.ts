import { Pipe, PipeTransform } from '@angular/core';

// creditLabel: 1 → "1 Credit", 2+ → "N Credits", null/0 → "No Credits"
@Pipe({ name: 'creditLabel' })
export class CreditLabelPipe implements PipeTransform {
  transform(value: number | null | undefined): string {
    if (value === null || value === undefined || value === 0) return 'No Credits';
    return value === 1 ? '1 Credit' : `${value} Credits`;
  }
}
