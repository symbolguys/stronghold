import { Component, ViewEncapsulation } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'frontend-epic-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './epic-detail.component.html',
  styleUrls: ['./epic-detail.component.scss'],
  encapsulation: ViewEncapsulation.ShadowDom,
})
export class EpicDetailComponent {}
