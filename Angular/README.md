# Student Course Portal — Angular v20 (DN 5.0 Hands-on)

One unified Angular app built across the hands-on exercises. **Hands-On 1–3 are complete
and the app builds** (`ng build` succeeds). Standalone components (Angular v20).

## Done so far
- **HOL 1** — CLI scaffold, project structure (`notes.txt`), 4 components (Header, Home,
  CourseList, StudentProfile), `<app-header>` + `<router-outlet>`, routing.
- **HOL 2** — all four binding types (interpolation, `[property]`, `(event)`, `[(ngModel)]`),
  lifecycle hooks (`ngOnInit`/`ngOnDestroy`/`ngOnChanges`), `@Input`/`@Output` + `EventEmitter`
  (CourseCard ↔ CourseList).
- **HOL 3** — structural directives (`*ngIf`/`*ngFor` with `trackBy`/`*ngSwitch`, `*ngIf` else),
  attribute directives (`[ngClass]` getter, `[ngStyle]`), custom directive (`appHighlight` with
  `@HostListener` + configurable colour), custom pipe (`creditLabel`).

## Still to build (next increments)
HOL 4 template forms · HOL 5 reactive forms · HOL 6 services/DI · HOL 7 routing guards/lazy ·
HOL 8 HttpClient/interceptors · HOL 9 NgRx · HOL 10 Jasmine/Karma tests.

## Run
```bash
npm install
ng serve        # http://localhost:4200
ng build        # production build → dist/
```

## Key files
```
src/app/
├── app.ts, app.html, app.routes.ts
├── components/header/        (nav)
├── components/course-card/   (@Input/@Output, ngSwitch, ngClass/ngStyle, pipe)
├── pages/home/               (bindings + lifecycle)
├── pages/course-list/        (*ngFor/trackBy, *ngIf-else, appHighlight)
├── pages/student-profile/
├── directives/highlight.ts   (custom directive)
└── pipes/credit-label-pipe.ts (custom pipe)
```
