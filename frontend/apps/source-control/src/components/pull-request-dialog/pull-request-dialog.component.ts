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
}
