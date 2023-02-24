import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChartModule } from 'primeng/chart';

@Component({
  selector: 'frontend-active-pull-requests-statistics',
  standalone: true,
  imports: [CommonModule, ChartModule],
  templateUrl: './active-pull-requests-statistics.component.html',
  styleUrls: ['./active-pull-requests-statistics.component.scss'],
})
export class ActivePullRequestsStatisticsComponent {
  stackedOptions: any;
  stackedData: any;

  ngOnInit() {
    this.stackedData = {
      labels: ['Commits', 'File Changes', 'Reviewers', 'Comments'],
      datasets: [
        {
          label: 'PR 1',
          backgroundColor: '#42A5F5',
          data: [50, 25, 12, 48, 90, 76, 42],
        },
        {
          label: 'PR 2',
          backgroundColor: '#66BB6A',
          data: [21, 84, 24, 75, 37, 65, 34],
        },
        {
          label: 'PR 3',
          backgroundColor: '#FFA726',
          data: [41, 52, 24, 74, 23, 21, 32],
        },
      ],
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
