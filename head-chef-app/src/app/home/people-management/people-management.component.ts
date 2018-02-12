import { Component, OnInit, ElementRef, ViewChild, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog, MatDialogRef, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
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
  expertiseCategory: string;
  expertiseCuisine: string;
  id: string;
  name: string;
  phone: string;
}

let chefsList: Chefs[];

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
  chefsRaw; expertiseList = [];
  chefURL='https://foodiekiddiee.000webhostapp.com/headchef/chefs-details_headchef.php';

  deleteFlag = false; whichCDelete;

  cdataSource; cdisplayedColumns;
  ctableReady=false; caddFormFlag = false; ceditFlag = false;
  
  c1; c2; c3; c4; c5; tc2; tc3; tc4; tc5;
  
  constructor(private router:Router, private authService:AuthService, private httpCDet: Http,private httpcadd: Http, private httpcedit: Http, private httpcdgdelete: Http, private expListHttp: Http, private delDiaSer: DeleteDialogService, public snackBar: MatSnackBar) {
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
    let expheaders = new Headers();
    expheaders.append("Content-Type", "application/x-www-form-urlencoded");
    let expURL = 'https://foodiekiddiee.000webhostapp.com/headchef/expertiseList.php';
    this.expListHttp.get(expURL).subscribe(
      expdata => {
        console.log(expdata);
        if(expdata.json()=="failed"){
          console.log(expdata.json());
        }
        else{
          let cuisines = expdata.json();
          for(let exp in cuisines){
            let temp = {
              value: cuisines[exp]["cuisineName"], 
              viewValue: cuisines[exp]["cuisineName"]
            };
            this.expertiseList.push(temp);
          }
        }
      },
      err => { console.log('Failed...'); }
    );
  }
  
  @ViewChild('cfilter') cfilter: ElementRef;
  
  ngOnInit() {
    this.cdataSource = new ChefDataSource();
    Observable.fromEvent(this.cfilter.nativeElement, 'keyup')
        .debounceTime(150)
        .distinctUntilChanged()
        .subscribe(() => {
          if (!this.cdataSource) { return; }
          this.cdataSource.filter = this.cfilter.nativeElement.value;
        });
  }
  
  goto(which:string){
    switch(which){
      case 'kitchen': this.router.navigate(['home/kitchen']); break;
      case 'people': this.router.navigate(['home/people']); break;
      case 'stock': this.router.navigate(['home/stock']); break;
      case 'profile': this.router.navigate(['home/profile']); break;
      default: this.router.navigate(['page-not-found']);
    }
  }

  chefDetailsFetcher(){
    let body = {"kitchenID": this.kitchenID};
    this.httpCDet.post(this.chefURL, body,{ headers: this.headers}).subscribe(
        result => this.chefsRaw = result.json(),
        () => console.log("Failed..."),
        () => {
          if(this.chefsRaw=="Zero results"){
            let snackBarRef = this.snackBar.open('No chefs','',
              { duration: 3000 });
          }
          else{
          console.log(this.chefsRaw);
          chefsList=this.chefsRaw;
          this.ctableReady=true; this.ceditFlag = false; this.caddFormFlag = false;
        }}
    );
  }

  showAddForm(whichPerson:string){
    if(whichPerson=="chef") { 
      this.ctableReady=false; this.caddFormFlag = true; this.ceditFlag = false; }
  }

  addChef(name:string,email:string,phone:string,expertise:string){
    let pswd = "chef-" + name.toLowerCase();
    let body = {"role":"chef", "name":name, "email":email, "kitchenID":this.kitchenID, "phone":phone, "expertise":expertise, "password":pswd};
    let addURL = 'https://foodiekiddiee.000webhostapp.com/headchef/chef_registration.php';
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

  editChefSet(c1:string,c2:string,c3:string,c4:string,c5:string){
    this.ceditFlag = true; this.ctableReady = false; this.caddFormFlag = false;
    this.c1 = c1; this.c2 = c2; this.c3 = c3; this.c4 = c4; this.c5 = c5;
    this.tc2 = c2; this.tc3 = c3; this.tc4 = c4; this.tc5 = c5;
  }

  editChef(){
    let body = {"name":this.tc2,"email":this.tc3,"phone":this.tc4,"expertise":this.tc5,"id":this.c1};
    let editHeaders: Headers = new Headers();
    let editURL = 'https://foodiekiddiee.000webhostapp.com/headchef/editchef.php';
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

  deletePerson(id:string,role:string){
    this.delDiaSer.permission(id).subscribe(
      res => this.deleteFlag = res,
      () => console.log("failed"+this.deleteFlag),
      () => {
        if(this.deleteFlag==true){
          let body = {"role":role,"id":id};
          let deleteHeaders: Headers = new Headers();
          let deleteURL = 'https://foodiekiddiee.000webhostapp.com/headchef/deleteCDG.php';
          let answer;
          deleteHeaders.append("Content-Type", "application/x-www-form-urlencoded");
          this.httpcdgdelete.post(deleteURL, body,{ headers: deleteHeaders}).subscribe(
            result => answer = result.json(),
            () => console.log("Failed..."),
            () => {
              console.log(answer);
              if(answer=="deleted") {
                if(role=="chef") { this.whichCDelete = id; this.chefDetailsFetcher(); }
              }
              else if(answer.includes("a foreign key constraint fails") || answer.includes("orderDetails")){
                let snackBarRef = this.snackBar.open('Chef is assigned a task:','Cannot delete chef.',
                  { duration: 3000 });
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

@Component({
  selector: 'delete-dialog',
  templateUrl: 'delete-dialog.html',
  styleUrls: ['delete-dialog.css']
})

export class DeleteDialogComponent{

  id:string;

  constructor(public dialogRef: MatDialogRef<DeleteDialogComponent>) {}

}