import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  imports: [CommonModule, FormsModule],   // CommonModule → *ngIf ; FormsModule → ngModel
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home implements OnInit, OnDestroy {
  portalName = 'Student Course Portal';   // interpolation source
  isPortalActive = true;                  // property binding source
  message = '';                           // set by event binding
  searchTerm = '';                        // two-way binding source
  coursesAvailable = 12;
  enrolled = 3;
  gpa = 3.8;

  // (click) handler
  onEnrollClick() { this.message = 'Enrollment opened!'; }

  // ngOnInit fires once after inputs are set — use it for data loading (not the constructor)
  ngOnInit(): void {
    this.coursesAvailable = 12; // simulate a data load
    console.log('HomeComponent initialised — courses loaded');
  }
  // ngOnDestroy runs on navigation away — used to unsubscribe / clear timers
  ngOnDestroy(): void { console.log('HomeComponent destroyed'); }
}
