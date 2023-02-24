import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivePullRequestsStatisticsComponent } from './active-pull-requests-statistics.component';

describe('ActivePullRequestsStatisticsComponent', () => {
  let component: ActivePullRequestsStatisticsComponent;
  let fixture: ComponentFixture<ActivePullRequestsStatisticsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActivePullRequestsStatisticsComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ActivePullRequestsStatisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
