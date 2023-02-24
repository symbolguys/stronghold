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
  selectedManor: Manor | undefined;
  householdname: string | undefined;
  manors: any[] = [];
  symbologyMembers: Member[] = [new Member(
    'Morten Lund',
    'MLD',
    'Knight'
    ),
    new Member(
      'Floor Manager',
      'ABC',
      'Knight'
      )
    ];

  households: Household[] = [];
  apiManors: Manor[] = [];

  displayAddManor = false;
  displayAddHousehold = false;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
    })
  };

  ngOnInit() {
    this.households = [
      new Household(
      'Symbology',
      this.symbologyMembers),
      new Household(
      'Symbology2',
      this.symbologyMembers),
    ];

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

  public addHousehold() {
    if (!this.householdname) {
    const manor = new Household(this.householdname!, []);
    console.log(manor.name);
  };
    this.displayAddHousehold = false;
  }

  public deleteManor(manor: Manor) {
    console.log(manor);
  }
}
