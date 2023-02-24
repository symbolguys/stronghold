import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RootComponent } from '../../components/root/root.component';

@Component({
  standalone: true,
  imports: [CommonModule, RootComponent],
  selector: 'frontend-source-control-entry',
  template: `<frontend-root></frontend-root>`,
})
export class RemoteEntryComponent {}
