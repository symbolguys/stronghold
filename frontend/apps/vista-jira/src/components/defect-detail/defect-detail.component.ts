import { Component, Input, ViewEncapsulation } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import { DefectService } from '../../services/defect.service'
import { ActivatedRoute } from '@angular/router';
import { DefectIssue } from '../../models/defectIssue';

@Component({
  selector: 'frontend-defect-detail',
  standalone: true,
  imports: [CommonModule, TableModule],
  templateUrl: './defect-detail.component.html',
  styleUrls: ['./defect-detail.component.scss'],
  encapsulation: ViewEncapsulation.ShadowDom,
})
export class DefectDetailComponent {
  @Input() defect!: DefectIssue;
  newComment: string = "";

  constructor(private route: ActivatedRoute, private defectsService: DefectService) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.defectsService.getDefect(id!).subscribe((defect) => (this.defect = defect));
  }

  submitComment() {
    // Implement the
  }
}
