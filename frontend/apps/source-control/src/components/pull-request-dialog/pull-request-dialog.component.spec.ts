import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PullRequestDialogComponent } from './pull-request-dialog.component';

describe('PullRequestDialogComponent', () => {
  let component: PullRequestDialogComponent;
  let fixture: ComponentFixture<PullRequestDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PullRequestDialogComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(PullRequestDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
