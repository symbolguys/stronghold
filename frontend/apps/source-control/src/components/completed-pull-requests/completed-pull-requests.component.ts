import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PullRequest } from '../../dtos/pull-request.data';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { DynamicDialogModule } from 'primeng/dynamicdialog';
import { DialogService } from 'primeng/dynamicdialog';
import { PullRequestDialogComponent } from '../pull-request-dialog/pull-request-dialog.component';
import { PullRequestDummyData } from '../../dtos/pull-request.dummy';

@Component({
  selector: 'frontend-completed-pull-requests',
  standalone: true,
  providers: [DialogService, PullRequestDummyData],
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
export class CompletedPullRequestsComponent implements OnInit {
  pullRequests: PullRequest[] = [];
  display = false;

  constructor(
    private dialogService: DialogService,
    private dummyData: PullRequestDummyData
  ) {}

  ngOnInit() {
    this.pullRequests = this.dummyData.get().filter(value => value.state === "MERGED");
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
