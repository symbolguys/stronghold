import { Component, ViewEncapsulation } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'frontend-feature-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './feature-detail.component.html',
  styleUrls: ['./feature-detail.component.scss'],
  encapsulation: ViewEncapsulation.ShadowDom,
})
export class FeatureDetailComponent {}
