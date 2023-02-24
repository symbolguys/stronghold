import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivePullRequestsComponent } from '../active-pull-requests/active-pull-requests.component';
import { CompletedPullRequestsComponent } from '../completed-pull-requests/completed-pull-requests.component';
import { ActivePullRequestsStatisticsComponent } from '../active-pull-requests-statistics/active-pull-requests-statistics.component';

@Component({
  selector: 'frontend-root',
  standalone: true,
  imports: [
    CommonModule,
    ActivePullRequestsComponent,
    CompletedPullRequestsComponent,
    ActivePullRequestsStatisticsComponent,
  ],
  templateUrl: './root.component.html',
  styleUrls: ['./root.component.scss'],
})
export class RootComponent {}
