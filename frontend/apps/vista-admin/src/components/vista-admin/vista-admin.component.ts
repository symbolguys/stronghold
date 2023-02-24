/* eslint-disable @typescript-eslint/no-non-null-assertion */
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Manor } from '../../models/manor';
import { Household } from '../../models/household';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Member } from '../../models/member';

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
  members: Member[] = [];
  apiManors: Manor[] = [];

  displayAddManor = false;
  displayDeleteManor = false;
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

  public deleteManor(manor: any) {
    this.selectedManor = manor;
    this.httpClient.delete(this.manorsBaseUrl + '/manors/' + this.selectedManor!.id, this.httpOptions).subscribe(response => {
      console.log(response)
      this.getManors();
    });
  };

  public addManor() {
    if (this.manorname == undefined) {
      this.displayAddManor = false;
    } else {
      const manor = new Manor(this.manorname!, undefined as any, []);
      console.log('test');
      this.httpClient.post(this.manorsBaseUrl + '/manors', manor, this.httpOptions).subscribe(response => {
        console.log(response)
        this.getManors();
      });
      this.displayAddManor = false;
    };
  }

  public showAddHousehold(manor: any) {
    this.selectedManor = manor;
    this.displayAddHousehold = true;
    console.log(this.selectedManor?.name);
  };

  public showAddMember(manor: any, household: any) {
    console.log('add member')
    console.log(manor)
    console.log(household)
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
        id: undefined as any,
        name: this.householdname!,
      });
      this.httpClient.put(this.manorsBaseUrl + '/manors/' + this.selectedManor?.id, this.selectedManor, this.httpOptions).subscribe(response => {
        this.getManors();
        console.log(response)
      });
      this.displayAddHousehold = false;
    };
  }

  public deleteHousehold(manor: any, household: any) {
    this.selectedManor = manor;
    this.selectedHousehold = household;
    const filteredHouseholds = this.selectedManor?.households.filter(household => household.id != this.selectedHousehold?.id);
    this.selectedManor = {
      households: filteredHouseholds ? filteredHouseholds : [],
      name: this.selectedManor!.name,
      id: this.selectedManor!.id,
    };

    this.httpClient.put(this.manorsBaseUrl + '/manors/' + this.selectedManor!.id, this.selectedManor, this.httpOptions).subscribe(response => {
      console.log(response)
      this.getManors();
    });
  }

  public addMember() {
    console.log('hello from addmember');
    if (this.membername == undefined || this.memberinitials == undefined) {
      this.displayAddMember = false;
    } else {
      this.selectedManor?.households.find(household => household.id == this.selectedHousehold?.id)?.members.push({
        initials: this.memberinitials,
        id: undefined as any,
        name: this.membername,
      });
      this.httpClient.put(this.manorsBaseUrl + '/manors/' + this.selectedManor?.id, this.selectedManor, this.httpOptions).subscribe(response => {
        this.getManors();
        console.log(response)
      });
      this.displayAddMember = false;
    };
  }
  
  // public deleteMember(manor: any, household: any, selectedMember: any) {
  //   this.selectedManor = manor;
  //   this.selectedHousehold = household;
  //   const householdToEdit = this.selectedManor?.households.find(household => household.id == this.selectedHousehold?.id);
  //   const filteredMembers = householdToEdit?.members.filter(members => members.id != selectedMember.id);
    
  //   this.selectedManor = {
  //     households: filteredHouseholds ? filteredHouseholds : [],
  //     name: this.selectedManor!.name,
  //     id: this.selectedManor!.id,
  //   };

  //   this.httpClient.put(this.manorsBaseUrl + '/manors/' + this.selectedManor!.id, this.selectedManor, this.httpOptions).subscribe(response => {
  //     console.log(response)
  //     this.getManors();
  //   });
  // }
}
