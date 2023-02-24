import { NxWelcomeComponent } from './nx-welcome.component';
import { Route } from '@angular/router';

export const appRoutes: Route[] = [
  {
    path: 'vista-jira',
    loadChildren: () => import('vista-jira/Routes').then((m) => m.remoteRoutes),
  },
  {
    path: 'vista-viz',
    loadChildren: () =>
      import('vista-viz/Module').then((m) => m.RemoteEntryModule),
  },
  {
    path: 'vista-admin',
    loadChildren: () =>
      import('vista-admin/Routes').then((m) => m.remoteRoutes),
  },
  {
    path: 'source-control',
    loadChildren: () =>
      import('source-control/Routes').then((m) => m.remoteRoutes),
  },
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
