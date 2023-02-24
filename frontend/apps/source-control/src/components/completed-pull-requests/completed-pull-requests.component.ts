import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PullRequest } from '../../dtos/pull-request.data';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { DynamicDialogModule } from 'primeng/dynamicdialog';
import { DialogService } from 'primeng/dynamicdialog';
import { PullRequestDialogComponent } from '../pull-request-dialog/pull-request-dialog.component';

@Component({
  selector: 'frontend-completed-pull-requests',
  standalone: true,
  providers: [DialogService],
  imports: [
    CommonModule,
    TableModule,
    DialogModule,
    DynamicDialogModule,
    PullRequestDialogComponent,
  ],
  templateUrl: './completed-pull-requests.component.html',
  styleUrls: ['./completed-pull-requests.component.scss'],
})
export class CompletedPullRequestsComponent {
  pullRequests: PullRequest[] = [];
  display = false;

  constructor(public dialogService: DialogService) {}

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
    this.dialogService.open(PullRequestDialogComponent, {
      header: `Pull-Request: ${rowData.title}`,
      width: '70%',
      height: '50%',
      maximizable: true,
      data: rowData,
    });
  }
}
