import { Component } from '@angular/core';

@Component({
  selector: 'app-student-profile',
  imports: [],
  templateUrl: './student-profile.html',
  styleUrl: './student-profile.css',
})
export class StudentProfile {
  student = { name: 'Lokesh', id: 'SRM2027', gpa: 3.8, enrolledCourses: 3 };
}
