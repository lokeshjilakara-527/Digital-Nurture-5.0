import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreditLabelPipe } from '../../pipes/credit-label-pipe';

interface Course {
  id: number; name: string; code: string; credits: number;
  gradeStatus?: string; enrolled?: boolean;
}

@Component({
  selector: 'app-course-card',
  imports: [CommonModule, CreditLabelPipe], // CommonModule → ngSwitch/ngClass/ngStyle/ngIf
  templateUrl: './course-card.html',
  styleUrl: './course-card.css',
})
export class CourseCard implements OnChanges {
  @Input() course!: Course;                              // data flows DOWN from parent
  @Output() enrollRequested = new EventEmitter<number>(); // events bubble UP to parent
  isExpanded = false;

  // ngOnChanges fires whenever an @Input reference changes
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['course']) {
      console.log('course changed:', changes['course'].previousValue, '→', changes['course'].currentValue);
    }
  }

  // getters keep the template clean — the class holds the logic
  get cardClasses() {
    return {
      'card--enrolled': !!this.course?.enrolled,
      'card--full': (this.course?.credits ?? 0) >= 4,
      'expanded': this.isExpanded,
    };
  }
  get borderColor() {
    switch (this.course?.gradeStatus) {
      case 'passed': return 'green';
      case 'failed': return 'red';
      default: return 'grey';
    }
  }
}
