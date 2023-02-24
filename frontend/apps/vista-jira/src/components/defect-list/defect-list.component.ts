import { Component, ViewEncapsulation, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { DefectIssue } from '../../models/defectIssue';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { DefectDetailComponent } from '../defect-detail/defect-detail.component';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';


@Component({
  selector: 'frontend-defect-list',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatButtonModule, DefectDetailComponent, MatDialogModule],
  templateUrl: './defect-list.component.html',
  styleUrls: ['./defect-list.component.scss'],
  encapsulation: ViewEncapsulation.ShadowDom,
})
export class DefectListComponent {
  // @ts-ignore
  @Input() defects: DefectIssue[];

  defectColumns: string[] = ['key', 'summary', 'status', 'priority', 'assignee', 'reporter', 'created', 'updated', 'severity'];

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private dialog: MatDialog) {};

  onRowClicked(row: DefectIssue) {
    const dialogRef = this.dialog.open(DefectDetailComponent, {
      width: '600px',
      data: row
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('closing');
    })
  }
}
