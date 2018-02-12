import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Http, Response, Headers, RequestOptions, URLSearchParams} from '@angular/http';
import { AuthService } from '../../auth.service';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Rx';
import { MatTabChangeEvent } from '@angular/material';

export interface ChefTask{
  orderID: string;
  name: string;
  cuisine: string;
  chefID: string;
  preparation_status: Number;
}

let chefTaskList: ChefTask[];

export interface OrderAssign{
  orderID: string;
  name: string;
  cuisine: string;
  chefID: string;
}

let orderAssignList: OrderAssign[];

@Component({
  selector: 'kitchen',
  templateUrl: './kitchen-management.component.html',
  styleUrls: ['./kitchen-management.component.css']
})

export class KitchenManagementComponent implements OnInit {

  dataRaw; datestr; chefs; hint;
  chefTaskSource; displayedColumns; table2Ready: boolean = false;
  orderSource; assignDisplayedColumns; table1Ready: boolean = false;

  constructor(private router:Router , /*private expListHttp: Http, private chefsHttp: Http,*/ private authService: AuthService, private assignHttp: Http, private taskHttp: Http) {
    
    let chefheaders = new Headers();
    chefheaders.append("Content-Type", "application/x-www-form-urlencoded");
    let chefURL = 'https://foodiekiddiee.000webhostapp.com/headchef/chefs-details_headchef.php';
    let chefbody = {"kitchenID":this.authService.kitchenID};
    this.taskHttp.post(chefURL, chefbody,{ headers: chefheaders}).subscribe(
        result => this.chefs = result.json(),
        () => console.log("Failed..."),
        () => {
          console.log(this.chefs);
        }
    );

    let date = new Date();
    let dd = date.getDate(); let dds: string;
    let mm = (date.getMonth()+1); let mms: string;
    let yyyy = date.getFullYear();
    if(dd<10) dds = '0' + dd;
    else dds = dd.toString();
    if(mm<10) mms = '0' + mm;
    else mms = mm.toString();
    this.datestr = yyyy + '-' + mms + '-' + dds;
    
    this.assignDisplayedColumns = ['orderID','name','cuisine','chefID'];
    this.orderSource = new OrderSource();
    this.getOrdersList();

    this.displayedColumns = ['orderID','name','cuisine','chefID','preparation_status'];
    this.chefTaskSource = new ChefTaskSource();
    this.getAssignedList();
  }

  ngOnInit() {
  }

  onTabChange(event: MatTabChangeEvent){
    //console.log(event.tab);
    //console.log(event.tab["textLabel"]);
    if(event.tab["textLabel"]=="CHEF TASKS") this.getAssignedList();
    else this.getOrdersList();
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

  getList(which: string){
    let headers = new Headers();
    headers.append("Content-Type", "application/x-www-form-urlencoded");
    let olURL = 'https://foodiekiddiee.000webhostapp.com/orders/direct-to-chefs/hc-list.php';
    this.table1Ready = false;
    let body = {"date":this.datestr,"kitchenID":this.authService.kitchenID};
    this.taskHttp.post(olURL, body,{ headers: headers}).subscribe(
        result => this.dataRaw = result.json(),
        () => console.log("Failed..."),
        () => {
          console.log(this.dataRaw);
          if(which=="table1"){
            orderAssignList = this.dataRaw["data"];
            this.table1Ready = true;
            this.orderSource.connect();
          }
          else{
            chefTaskList = this.dataRaw["data"];
            this.table2Ready = true;
          }
        }
    );
  }

  getOrdersList(){
    this.table1Ready = false;
    this.getList("table1");
  }

  getAssignedList(){
    this.table2Ready = false;
    this.getList("table2");
  }

  assignChef(od:string, c:string, o:string, n:string){
    this.hint = "Assigning #" + o + " " + n + " to chef #" + c + ". Kindly wait.";
    let answer;
    let headers = new Headers();
    headers.append("Content-Type", "application/x-www-form-urlencoded");
    let caURL = 'https://foodiekiddiee.000webhostapp.com/orders/direct-to-chefs/hc-assignchef.php';
    let body = {"odID":od,"chefID":c};
    this.assignHttp.post(caURL, body,{ headers: headers}).subscribe(
        result => answer = result.json(),
        () => console.log("Failed..."),
        () => {
          console.log(answer["data"]);
          if(answer["data"]!="success"){
            this.hint="FAILED! Assigning #" + o + " " + n + " to chef #" + c + ".";
          }
          else { this.hint = ""; }
        }
    );
  }
  
}

export class ChefTaskSource extends DataSource<any> {
  /** Connect function called by the table to retrieve one stream containing the data to render. */
  connect(): Observable<ChefTask[]> {
    console.log("Connecting table2");
    return Observable.of(chefTaskList);
  }

  disconnect() {}
}

export class OrderSource extends DataSource<any> {
  /** Connect function called by the table to retrieve one stream containing the data to render. */
  connect(): Observable<OrderAssign[]> {
    console.log("Connecting table1");
    return Observable.of(orderAssignList);
  }

  disconnect() {}
}