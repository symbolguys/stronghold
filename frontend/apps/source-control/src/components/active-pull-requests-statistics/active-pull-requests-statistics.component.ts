import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChartModule } from 'primeng/chart';
import { PullRequestDummyData } from '../../dtos/pull-request.dummy';

@Component({
  selector: 'frontend-active-pull-requests-statistics',
  standalone: true,
  providers: [PullRequestDummyData],
  imports: [CommonModule, ChartModule],
  templateUrl: './active-pull-requests-statistics.component.html',
  styleUrls: ['./active-pull-requests-statistics.component.scss'],
})
export class ActivePullRequestsStatisticsComponent implements OnInit {
  stackedOptions: any;
  stackedData: any;

  constructor(private dummyData: PullRequestDummyData) {}

  ngOnInit() {
    this.stackedData = {
      labels: ['Commits', 'File Changes', 'Reviewers', 'Comments'],
      datasets: this.dummyData.get().filter(value => value.state === "MERGED").map(value => {
        return {
          label: value.title,
          data: [
            value.commits,
            value.fileChanges,
            value.reviewers,
            value.comments
          ]
        }
      })
    };

    this.stackedOptions = {
      tooltips: {
        mode: 'index',
        intersect: false,
      },
      responsive: true,
      scales: {
        x: {
          stacked: true,
        },
        y: {
          stacked: true,
        },
      },
    };
  }
}
