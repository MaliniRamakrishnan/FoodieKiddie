import { Component, OnInit, ElementRef, ViewChild, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';
import { AuthService } from '../../auth.service';
import { Http, Response, Headers, RequestOptions, URLSearchParams} from '@angular/http';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import 'rxjs/add/operator/startWith';
import 'rxjs/add/observable/merge';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/observable/fromEvent';
import 'rxjs/add/observable/of';
import { DeleteDialogService } from './delete-dialog.service';

export interface Chefs {
  email: string;
  expertise: string;
  id: string;
  name: string;
  phone: string;
}

export interface DeliveryGuys{
  email: string;
  id: string;
  name: string;
  phone: string;
  currentLocationLatitude: string;
  currentLocationLongitude: string;
  orderAssignment: string;
}

let chefsList: Chefs[];
let deliveryGuysList: DeliveryGuys[];

@Component({
  selector: 'people',
  templateUrl: './people-management.component.html',
  styleUrls: ['./people-management.component.css']
})

export class PeopleManagementComponent implements OnInit {

  headchefID; username; phone; expertise; email;
  kitchenID; nameOfLocation; typeOfLocation; locationDoorNumber;
  locationLandmark; locationStreet; locationCity;
  locationState; locationCountry; locationPIN;
  latitude; longitude;

  headers: Headers;
  chefsRaw; dgRaw;
  chefURL='https://foodiekiddiee.000webhostapp.com/chefs-details_headchef.php';
  deliveryGuyURL='https://foodiekiddiee.000webhostapp.com/deliveryguys-details_headchef.php';

  deleteFlag = false; whichCDelete; whichDGDelete;

  cdataSource; cdisplayedColumns;
  ctableReady=false; caddFormFlag = false; ceditFlag = false;
  dgdataSource; dgdisplayedColumns;
  dgtableReady=false; dgaddFormFlag = false; dgeditFlag = false;

  c1; c2; c3; c4; c5; tc2; tc3; tc4; tc5;
  dg1; dg2; dg3; dg4; tdg2; tdg3; tdg4;

  constructor(private router:Router, private authService:AuthService, private httpCDet: Http, private httpDGDet: Http, private httpcadd: Http, private httpdgadd: Http, private httpcedit: Http, private httpdgedit: Http, private httpcdgdelete: Http, private delDiaSer: DeleteDialogService) {
  	this.headchefID=this.authService.userID;
  	this.email=this.authService.email;
  	this.kitchenID=this.authService.kitchenID;
    this.username=this.authService.username;
    this.phone=this.authService.phone;
    this.expertise=this.authService.expertise;
    this.nameOfLocation=this.authService.nameOfLocation;
    this.typeOfLocation=this.authService.typeOfLocation;
    this.locationDoorNumber=this.authService.locationDoorNumber;
    this.locationLandmark=this.authService.locationLandmark;
    this.locationStreet=this.authService.locationStreet;
    this.locationCity=this.authService.locationCity;
    this.locationState=this.authService.locationState;
    this.locationCountry=this.authService.locationCountry;
    this.locationPIN=this.authService.locationPIN;
    this.latitude=this.authService.latitude;
    this.longitude=this.authService.longitude;
    this.headers = new Headers();
    this.headers.append("Content-Type", "application/x-www-form-urlencoded");
    this.chefDetailsFetcher();
    this.cdisplayedColumns = ['id', 'name', 'email', 'expertise', 'phone','edit','delete'];
    this.deliveryGuyDetailsFetcher();
    this.dgdisplayedColumns=['id', 'name', 'email', 'currentLocation', 'orderAssignment', 'phone','edit','delete'];
  }
  
  @ViewChild('cfilter') cfilter: ElementRef;
  @ViewChild('dgfilter') dgfilter: ElementRef;

  ngOnInit() {
    this.cdataSource = new ChefDataSource();
    Observable.fromEvent(this.cfilter.nativeElement, 'keyup')
        .debounceTime(150)
        .distinctUntilChanged()
        .subscribe(() => {
          if (!this.cdataSource) { return; }
          this.cdataSource.filter = this.cfilter.nativeElement.value;
        });
    this.dgdataSource = new DeliveryGuyDataSource();
    Observable.fromEvent(this.dgfilter.nativeElement, 'keyup')
        .debounceTime(150)
        .distinctUntilChanged()
        .subscribe(() => {
          if (!this.dgdataSource) { return; }
          this.dgdataSource.filter = this.dgfilter.nativeElement.value;
        });
  }

  chefDetailsFetcher(){
    // console.log("cdetfet");
    let body = {"kitchenID": this.kitchenID};
    this.httpCDet.post(this.chefURL, body,{ headers: this.headers}).subscribe(
        result => this.chefsRaw = result.json(),
        () => console.log("Failed..."),
        () => {
          console.log(this.chefsRaw);
          chefsList=this.chefsRaw;
          this.ctableReady=true; this.ceditFlag = false; this.caddFormFlag = false;
        }
    );
  }

  deliveryGuyDetailsFetcher(){
    // console.log("dgdetfet");
    let body = {"kitchenID": this.kitchenID};
    this.httpDGDet.post(this.deliveryGuyURL, body,{ headers: this.headers}).subscribe(
        result => this.dgRaw = result.json(),
        () => console.log("Failed..."),
        () => { 
          console.log(this.dgRaw);
          deliveryGuysList=this.dgRaw;
          this.dgtableReady=true; this.dgeditFlag = false; this.dgaddFormFlag = false;
        }
    );
  }

  showAddForm(whichPerson:string){
    if(whichPerson=="chef") { 
      this.ctableReady=false; this.caddFormFlag = true; this.ceditFlag = false; }
    else { this.dgtableReady=false; this.dgaddFormFlag = true; this.dgeditFlag = false; }
  }

  addChef(name:string,email:string,phone:string,expertise:string){
    let pswd = "chef-" + name.toLowerCase();
    let body = {"role":"chef", "name":name, "email":email, "kitchenID":this.kitchenID, "phone":phone, "expertise":expertise, "password":pswd};
    let addURL = 'https://foodiekiddiee.000webhostapp.com/chef_registration.php';
    let answer;
    let cheaders: Headers;
    cheaders = new Headers();
    cheaders.append("Content-Type", "application/x-www-form-urlencoded");
    this.httpcadd.post(addURL, body,{ headers: cheaders}).subscribe(
        result => answer = result.json(),
        () => console.log("Failed..."),
        () => { 
          console.log(answer);
          if(answer=="inserted") { this.chefDetailsFetcher(); }
        }
    );
  }

  addDG(name:string,email:string,phone:string){
    let pswd = "delivery-guy-" + name.toLowerCase();
    let body = {"role":"delivery-guy", "name":name, "email":email, "kitchenID":this.kitchenID, "phone":phone, "password":pswd};
    let addURL = 'https://foodiekiddiee.000webhostapp.com/deliveryGuy_registration.php';
    let answer;
    let dgheaders: Headers;
    dgheaders = new Headers();
    dgheaders.append("Content-Type", "application/x-www-form-urlencoded");
    this.httpdgadd.post(addURL, body,{ headers: dgheaders}).subscribe(
        result => answer = result.json(),
        () => console.log("Failed..."),
        () => { 
          console.log(answer);
          if(answer=="inserted") { this.deliveryGuyDetailsFetcher(); }
        }
    );
  }

  editChefSet(c1:string,c2:string,c3:string,c4:string,c5:string){
    this.ceditFlag = true; this.ctableReady = false; this.caddFormFlag = false;
    this.c1 = c1; this.c2 = c2; this.c3 = c3; this.c4 = c4; this.c5 = c5;
    this.tc2 = c2; this.tc3 = c3; this.tc4 = c4; this.tc5 = c5;
  }

  editDGSet(dg1:string,dg2:string,dg3:string,dg4:string){
    this.dgeditFlag = true; this.dgtableReady = false; this.dgaddFormFlag = false;
    this.dg1 = dg1; this.dg2 = dg2; this.dg3 = dg3; this.dg4 = dg4;
    this.tdg2 = dg2; this.tdg3 = dg3; this.tdg4 = dg4;
  }

  editChef(){
    let body = {"name":this.tc2,"email":this.tc3,"phone":this.tc4,"expertise":this.tc5,"id":this.c1};
    let editHeaders: Headers = new Headers();
    let editURL = 'https://foodiekiddiee.000webhostapp.com/editchef.php';
    let answer;
    editHeaders.append("Content-Type", "application/x-www-form-urlencoded");
    this.httpcedit.post(editURL, body,{ headers: editHeaders}).subscribe(
        result => answer = result.json(),
        () => console.log("Failed..."),
        () => {
          console.log(answer);
          if(answer=="updated") { this.chefDetailsFetcher(); }
        }
    );
  }

  editDG(){
    let body = {"name":this.tdg2,"email":this.tdg3,"phone":this.tdg4,"id":this.dg1};
    let editHeaders: Headers = new Headers();
    let editURL = 'https://foodiekiddiee.000webhostapp.com/editdeliveryguy.php';
    let answer;
    editHeaders.append("Content-Type", "application/x-www-form-urlencoded");
    this.httpdgedit.post(editURL, body,{ headers: editHeaders}).subscribe(
        result => answer = result.json(),
        () => console.log("Failed..."),
        () => {
          console.log(answer);
          if(answer=="updated") { this.deliveryGuyDetailsFetcher(); }
        }
    );
  }

  deletePerson(id:string,role:string){
    this.delDiaSer.permission(id).subscribe(
      res => this.deleteFlag = res,
      () => console.log("failed"+this.deleteFlag),
      () => {
        if(this.deleteFlag==true){
          let body = {"role":role,"id":id};
          let deleteHeaders: Headers = new Headers();
          let deleteURL = 'https://foodiekiddiee.000webhostapp.com/deleteCDG.php';
          let answer;
          deleteHeaders.append("Content-Type", "application/x-www-form-urlencoded");
          this.httpcdgdelete.post(deleteURL, body,{ headers: deleteHeaders}).subscribe(
            result => answer = result.json(),
            () => console.log("Failed..."),
            () => {
              console.log(answer);
              if(answer=="deleted") {
                if(role=="chef") { this.whichCDelete = id; this.chefDetailsFetcher(); }
                else { this.whichDGDelete = id; this.deliveryGuyDetailsFetcher(); }
              }
            }
          );
        }
      }
    );
  }

  cancel(whichPerson:string){
    if(whichPerson=="chef") {
      this.ctableReady = true; this.ceditFlag = false; this.caddFormFlag = false;
    }
    else  {
      this.dgtableReady = true; this.dgaddFormFlag = false; this.dgeditFlag = false;
    }
  }

}

export class ChefDataSource extends DataSource<any> {

  cdataChange: BehaviorSubject<Chefs[]> = new BehaviorSubject<Chefs[]>([]);
  
  _filterChange = new BehaviorSubject('');
  get filter(): string { return this._filterChange.value; }
  set filter(filter: string) { this._filterChange.next(filter); }

  connect(): Observable<Chefs[]> {
    const cdisplayDataChanges = [this.cdataChange,this._filterChange];
    return Observable.merge(...cdisplayDataChanges).map(() => {
      return chefsList.slice().filter((item: Chefs) => {
        let searchStr = (item.name).toLowerCase();
        return searchStr.indexOf(this.filter.toLowerCase()) != -1;
      });
    });
  }

  disconnect() {}
}

export class DeliveryGuyDataSource extends DataSource<any> {

  dgdataChange: BehaviorSubject<DeliveryGuys[]> = new BehaviorSubject<DeliveryGuys[]>([]);
  
  _dgfilterChange = new BehaviorSubject('');
  get filter(): string { return this._dgfilterChange.value; }
  set filter(filter: string) { this._dgfilterChange.next(filter); }

  connect(): Observable<DeliveryGuys[]> {
    const dgdisplayDataChanges = [this.dgdataChange,this._dgfilterChange];
    return Observable.merge(...dgdisplayDataChanges).map(() => {
      return deliveryGuysList.slice().filter((item: DeliveryGuys) => {
        let searchStr = (item.name).toLowerCase();
        return searchStr.indexOf(this.filter.toLowerCase()) != -1;
      });
    });
  }

  disconnect() {}
}

@Component({
  selector: 'delete-dialog',
  templateUrl: 'delete-dialog.html',
  styleUrls: ['delete-dialog.css']
})

export class DeleteDialogComponent{

  id:string;

  constructor(public dialogRef: MdDialogRef<DeleteDialogComponent>) {}

}