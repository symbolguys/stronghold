import { Route } from '@angular/router';
import { RemoteEntryComponent } from './entry.component';
import { FeatureDetailComponent } from '../../components/feature-detail/feature-detail.component';
import { EpicDetailComponent } from '../../components/epic-detail/epic-detail.component';
import { TaskDetailComponent } from '../../components/task-detail/task-detail.component';
import { DefectDetailComponent } from '../../components/defect-detail/defect-detail.component';

export const remoteRoutes: Route[] = [
  { path: '', component: RemoteEntryComponent },
  { path: 'feature/:id', component: FeatureDetailComponent},
  { path: 'epic/:id', component: EpicDetailComponent},
  { path: 'task/:id', component: TaskDetailComponent},
  { path: 'task/:id', component: TaskDetailComponent}
];