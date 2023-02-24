import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PullRequest } from '../../dtos/pull-request.data';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { DynamicDialogModule } from 'primeng/dynamicdialog';
import { DialogService } from 'primeng/dynamicdialog';
import { PullRequestDialogComponent } from '../pull-request-dialog/pull-request-dialog.component';
import { PullRequestDummyData } from '../../dtos/pull-request.dummy';
import {
  HttpClient,
  HttpClientModule,
  HttpHeaders,
} from '@angular/common/http';

@Component({
  selector: 'frontend-active-pull-requests',
  standalone: true,
  providers: [DialogService, PullRequestDummyData],
  imports: [
    CommonModule,
    TableModule,
    DialogModule,
    DynamicDialogModule,
    PullRequestDialogComponent,
    HttpClientModule,
  ],
  templateUrl: './active-pull-requests.component.html',
  styleUrls: ['./active-pull-requests.component.scss'],
})
export class ActivePullRequestsComponent implements OnInit {
  pullRequests: PullRequest[] = [];
  display = false;
  baseUrl = 'http://localhost:4202';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(
    public dialogService: DialogService,
    private dummyData: PullRequestDummyData,
    private httpClient: HttpClient
  ) {}

  ngOnInit() {
    this.getPullRequests();
    this.pullRequests = this.pullRequests.filter(
      (value) => value.state === 'OPEN'
    );
  }

  getPullRequests() {
    this.httpClient
      .get(`${this.baseUrl}/pullRequests`, this.httpOptions)
      .subscribe((pullRequests) => {
        this.pullRequests = pullRequests as PullRequest[];
        console.log(pullRequests);
      });
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
