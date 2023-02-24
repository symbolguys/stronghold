import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VistaAdminComponent } from '../../components/vista-admin/vista-admin.component';

@Component({
  standalone: true,
  imports: [CommonModule, VistaAdminComponent],
  selector: 'frontend-vista-admin-entry',
  template: `<frontend-vista-admin></frontend-vista-admin>`,
})
export class RemoteEntryComponent {}
