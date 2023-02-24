/* eslint-disable @typescript-eslint/no-non-null-assertion */
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Manor } from '../../models/manor';
import { Household } from '../../models/household';
import { Member } from '../../models/member';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'frontend-vista-admin',
  standalone: true,
  imports: [
    CommonModule,
    TableModule,
    DialogModule,
    ButtonModule,
    InputTextModule,
    FormsModule,
    HttpClientModule,
    ],
  templateUrl: './vista-admin.component.html',
  styleUrls: ['./vista-admin.component.scss'],
})
export class VistaAdminComponent {
  constructor(private httpClient: HttpClient){}

  manorsBaseUrl = 'http://localhost:4205'
  manorname: string | undefined;
  householdname: string | undefined;
  membername: string | undefined;
  memberinitials: string | undefined;

  selectedManor: Manor | undefined;
  selectedHousehold: Household | undefined;

  manors: Manor[] = [];

  households: Household[] = [];
  apiManors: Manor[] = [];

  displayAddManor = false;
  displayAddHousehold = false;
  displayAddMember = false;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
    })
  };

  ngOnInit() {
    this.getManors();
  }
  
  public getManors() {
    this.httpClient.get(this.manorsBaseUrl + '/get-manors').subscribe(manors => {
      this.manors = manors as Manor[];
      console.log(manors);
    });
  }

  public showAddManor(manor: any) {
    this.selectedManor = manor;
    this.displayAddManor = true;
    console.log(this.selectedManor?.name);
  };

  public addManor() {
    if (this.manorname == undefined) {
      this.displayAddManor = false;
    } else {
      const manor = new Manor(this.manorname!, []);
      console.log('test');
      this.httpClient.post(this.manorsBaseUrl + '/manors', manor, this.httpOptions).subscribe(response => {
        console.log(response)
      });
      this.displayAddManor = false;
      this.getManors();
    };
  }

  public showAddHousehold(manor: any) {
    this.selectedManor = manor;
    this.displayAddHousehold = true;
    console.log(this.selectedManor?.name);
  };

  public showAddMember(manor: any, household: any) {
    this.selectedManor = manor;
    this.selectedHousehold = household;
    this.displayAddMember = true;
  };

  public addHousehold() {
    console.log('hello from addhousehold');
    if (this.householdname == undefined) {
      this.displayAddHousehold = false;
    } else {
      this.selectedManor?.households.push({
        members: [],
        name: this.householdname!,
      });
      this.httpClient.post(this.manorsBaseUrl + '/manors', this.selectedManor, this.httpOptions).subscribe(response => {
        console.log(response)
      });
      this.displayAddHousehold = false;
    };
  }

  public addMember() {
    console.log('hello from addmember');
    if (this.householdname == undefined) {
      this.displayAddHousehold = false;
    } else {
      this.selectedManor?.households.push({
        members: [],
        name: this.householdname!,
      });
      this.httpClient.post(this.manorsBaseUrl + '/manors', this.selectedManor, this.httpOptions).subscribe(response => {
        console.log(response)
      });
      this.displayAddHousehold = false;
    };
  }

  public deleteManor(manor: Manor) {
    console.log(manor);
  }
}
