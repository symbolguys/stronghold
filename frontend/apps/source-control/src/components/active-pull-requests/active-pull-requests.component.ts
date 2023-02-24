import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PullRequest } from '../../dtos/pull-request.data';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';

@Component({
  selector: 'frontend-active-pull-requests',
  standalone: true,
  imports: [
    CommonModule,
    TableModule,
    DialogModule,
  ],
  templateUrl: './active-pull-requests.component.html',
  styleUrls: ['./active-pull-requests.component.scss'],
})
export class ActivePullRequestsComponent implements OnInit {
  pullRequests: PullRequest[] = [];
  display = false;

  ngOnInit() {
    this.pullRequests = [
      new PullRequest(
        'ID 1',
        'Test 1',
        'HIGH',
        'ACTIVE',
        new Date(),
        new Date(),
        new Date()
      ),
      new PullRequest(
        'ID 2',
        'Test 2',
        'HIGH',
        'INACTIVE',
        new Date(),
        new Date(),
        new Date()
      ),
      new PullRequest(
        'ID 3',
        'Test 3',
        'MEDIUM',
        'ACTIVE',
        new Date(),
        new Date(),
        new Date()
      ),
      new PullRequest(
        'ID 4',
        'Test 4',
        'HIGH',
        'MERGED',
        new Date(),
        new Date(),
        new Date()
      ),
      new PullRequest(
        'ID 5',
        'Test 5',
        'LOW',
        'DECLINED',
        new Date(),
        new Date(),
        new Date()
      ),
      new PullRequest(
        'ID 6',
        'Test 6',
        'MEDIUM',
        'FAILED',
        new Date(),
        new Date(),
        new Date()
      ),
      new PullRequest(
        'ID 7',
        'Test 7',
        'MEDIUM',
        'FAILED',
        new Date(),
        new Date(),
        new Date()
      ),
    ];
  }

  showSelectedPullRequest(rowData: PullRequest) {
    this.display = true;
    console.log(rowData);
  }
}
