import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DynamicDialogRef } from 'primeng/dynamicdialog';
import { DynamicDialogConfig } from 'primeng/dynamicdialog';
import { PullRequest } from '../../dtos/pull-request.data';

@Component({
  selector: 'frontend-pull-request-dialog',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pull-request-dialog.component.html',
  styleUrls: ['./pull-request-dialog.component.scss'],
})
export class PullRequestDialogComponent implements OnInit {
  data: PullRequest | undefined;

  constructor(
    public ref: DynamicDialogRef,
    public config: DynamicDialogConfig
  ) {}

  ngOnInit() {
    this.data = this.config.data;
  }

  getUpdatedClosedString() {
    if (this.data?.state === 'OPEN') {
      return `Last Updated: ${this.data.updateDate}`;
    } else {
      return `Closed: ${this.data?.closedDate}`;
    }
  }

  getAliveTime(): string {
    if (this.data?.state === 'OPEN') {
      return this.dhmFormat(Date.now() - new Date(this.data.createDate).getTime());
    } else if (this.data) {
      return this.dhmFormat(
        new Date(this.data.closedDate).getTime() - new Date(this.data.createDate).getTime()
      );
    }

    return NaN.toString();
  }

  dhmFormat(ms: number) {
    const days = Math.floor(ms / (24 * 60 * 60 * 1000));
    const daysms = ms % (24 * 60 * 60 * 1000);
    const hours = Math.floor(daysms / (60 * 60 * 1000));
    const hoursms = ms % (60 * 60 * 1000);
    const minutes = Math.floor(hoursms / (60 * 1000));
    return `${days} days, ${hours} hours, ${minutes} minutes`;
  }
}
