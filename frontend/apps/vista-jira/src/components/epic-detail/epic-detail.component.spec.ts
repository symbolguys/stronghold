import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EpicDetailComponent } from './epic-detail.component';

describe('EpicDetailComponent', () => {
  let component: EpicDetailComponent;
  let fixture: ComponentFixture<EpicDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EpicDetailComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(EpicDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
