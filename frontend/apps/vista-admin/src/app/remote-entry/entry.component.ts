import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NxWelcomeComponent } from './nx-welcome.component';

@Component({
  standalone: true,
  imports: [CommonModule, NxWelcomeComponent],
  selector: 'frontend-vista-admin-entry',
  template: `<frontend-nx-welcome></frontend-nx-welcome>`,
})
export class RemoteEntryComponent {}
