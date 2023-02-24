import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivePullRequestsComponent } from '../active-pull-requests/active-pull-requests.component';

@Component({
  selector: 'frontend-root',
  standalone: true,
  imports: [CommonModule, ActivePullRequestsComponent],
  templateUrl: './root.component.html',
  styleUrls: ['./root.component.scss'],
})
export class RootComponent {}
