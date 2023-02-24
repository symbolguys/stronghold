import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefectDetailComponent } from './defect-detail.component';

describe('DefectDetailComponent', () => {
  let component: DefectDetailComponent;
  let fixture: ComponentFixture<DefectDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DefectDetailComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(DefectDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
