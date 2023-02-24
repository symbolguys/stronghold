import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompletedPullRequestsComponent } from './completed-pull-requests.component';

describe('CompletedPullRequestsComponent', () => {
  let component: CompletedPullRequestsComponent;
  let fixture: ComponentFixture<CompletedPullRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CompletedPullRequestsComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CompletedPullRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
