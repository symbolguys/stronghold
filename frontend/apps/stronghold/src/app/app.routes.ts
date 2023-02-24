import { NxWelcomeComponent } from './nx-welcome.component';
import { Route } from '@angular/router';

export const appRoutes: Route[] = [
  {
    path: 'continuous-integration',
    loadChildren: () =>
      import('continuous-integration/Routes').then((m) => m.remoteRoutes),
  },
  {
    path: '',
    component: NxWelcomeComponent,
  },
];
