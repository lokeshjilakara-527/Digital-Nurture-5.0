import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CourseCard } from '../../components/course-card/course-card';
import { Highlight } from '../../directives/highlight';

@Component({
  selector: 'app-course-list',
  imports: [CommonModule, CourseCard, Highlight],
  templateUrl: './course-list.html',
  styleUrl: './course-list.css',
})
export class CourseList implements OnInit {
  isLoading = true;
  selectedCourseId?: number;

  courses = [
    { id: 1, name: 'Intro to Programming', code: 'CS101', credits: 3, gradeStatus: 'passed',  enrolled: true },
    { id: 2, name: 'Data Structures',      code: 'CS201', credits: 4, gradeStatus: 'pending', enrolled: false },
    { id: 3, name: 'Web Development',       code: 'CS210', credits: 3, gradeStatus: 'failed',  enrolled: false },
    { id: 4, name: 'Databases',            code: 'CS220', credits: 4, gradeStatus: 'passed',  enrolled: true },
    { id: 5, name: 'Operating Systems',    code: 'CS301', credits: 3, gradeStatus: 'pending', enrolled: false },
  ];

  ngOnInit(): void {
    setTimeout(() => (this.isLoading = false), 1500); // simulate an API load
  }

  // trackBy lets Angular update only changed rows instead of re-rendering the whole list
  trackByCourseId(index: number, course: { id: number }) { return course.id; }

  onEnroll(courseId: number) {
    console.log('Enrolling in course: ' + courseId);
    this.selectedCourseId = courseId;
  }
}
