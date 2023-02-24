import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivePullRequestsComponent } from './active-pull-requests.component';

describe('ActivePullRequestsComponent', () => {
  let component: ActivePullRequestsComponent;
  let fixture: ComponentFixture<ActivePullRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActivePullRequestsComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ActivePullRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
