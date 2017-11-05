import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth.service';
import { Http, Response, Headers, RequestOptions, URLSearchParams} from '@angular/http';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/observable/of';
import { DetailsDialogService } from './details-dialog.service';

@Component({
  selector: 'order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})

export class OrderComponent implements OnInit {

  headers: Headers;
  selectedValue = "all";
  ordersSubscription;
  all_orders; message;
  ordersAdded: boolean;
  info: boolean; all: boolean; accepted: boolean; allc: boolean;
  ordersURL = 'https://foodiekiddiee.000webhostapp.com/chef/order-fetcher.php';
  infoURL = 'https://foodiekiddiee.000webhostapp.com/chef/order-info.php';
  
  oi = [];
  tiles = [];

  constructor(private router: Router, private authService: AuthService, private http: Http, private accepthttp: Http/*, private detDiaSer: DetailsDialogService*/) {
    this.headers = new Headers();
    this.headers.append("Content-Type", "application/x-www-form-urlencoded");
    this.info = false; this.allc = false;
    this.accepted = false; this.all = true;
    this.getOrders();
    this.ordersSubscription = Observable.interval(1000 * 60 * 5).subscribe(x => {
      //Even after 9am, if some order isn't accepted by any chef,
      //UI of 'all orders under chef's expertise' has to be updated.
      let yetToAssignChef: boolean = false;
      for(let order in this.all_orders["data"]){
        if(this.all_orders["data"][order]["chefID"]==null){
          yetToAssignChef=true;
          break;
        }
      }
      let tempData = this.all_orders["data"];
      var d = new Date();
      if(d.getHours()<9||yetToAssignChef) {
        this.getOrders();
        if(this.all_orders["data"].length>tempData.length) this.ordersAdded = true;
        else this.ordersAdded = false;
      }
    });
  }
  
  options = [
    {value: 'all', viewValue: 'All'},
    {value: 'chef-cuisine', viewValue: 'All ' + this.authService.expertise},
    {value: 'accepted', viewValue: 'Accepted'}
  ];

  ngOnInit() {
  }

  ngOnDestroy(){
    this.ordersSubscription.unsubscribe();
  }

  goto(which:string){
    switch(which){
      case 'order': this.router.navigate(['home/order']); break;
      case 'stock': this.router.navigate(['home/stock']); break;
      case 'profile': this.router.navigate(['home/profile']); break;
      default: this.router.navigate(['page-not-found']);
    }
  }

  getOrders(){
    let date = new Date(); let datestr: string;
    let dd = date.getDate(); let dds: string;
    let mm = (date.getMonth()+1); let mms: string;
    let yyyy = date.getFullYear();
    if(dd<10) dds = '0' + dd;
    else dds = dd.toString();
    if(mm<10) mms = '0' + mm;
    else mms = mm.toString();
    datestr = yyyy + '-' + mms + '-' + dds;
    let body = {"date":datestr,"kitchenID":this.authService.kitchenID};
    this.http.post(this.ordersURL, body,{ headers: this.headers}).subscribe(
        result => this.all_orders = result.json(),
        () => console.log("Failed..."),
        () => { 
          console.log(this.all_orders);
          this.optionChooser();
        });
  }

  orderInfo(tile){
    this.oi["items"] = [];
    this.oi["orderID"] = tile["orderID"];
    this.oi["deliveryTime"] = tile["deliveryTime"];
    for(let row in this.all_orders["data"]){
      if(this.all_orders["data"][row]["orderID"]==tile["orderID"]){
        this.oi["items"].push(this.all_orders["data"][row]);
      }
    }
    this.all = false; this.accepted = false; this.allc = false; this.info = true;
  }

  orderComposer(){
    let orders = []; let oldRow = false;
    for(let row in this.all_orders["data"]){
      let temp = {};
      temp["orderID"] = this.all_orders["data"][row]["orderID"];
      temp["deliveryTime"] = this.all_orders["data"][row]["deliveryTime"];
      for(let x in orders){
        if(orders[x]["orderID"]==temp["orderID"]) { oldRow = true; break; }
      }
      if(!oldRow) orders.push(temp);
      oldRow = false;
    }
    this.tiles = orders;
    if(this.tiles.length == 0) this.message = "None to display. Kindly refresh.";
    else this.message = null;
    if(!this.info) this.all = true;
    this.accepted = false; this.allc = false;
  }

  acceptedOrders(){
    this.tiles = [];
    for(let row in this.all_orders["data"]){
      if(this.all_orders["data"][row]["chefID"]==this.authService.userID){
        let temp = {};
        temp["orderID"] = this.all_orders["data"][row]["orderID"];
        temp["deliveryTime"] = this.all_orders["data"][row]["deliveryTime"];
        temp["item"] = this.all_orders["data"][row]["name"];
        temp["qty"] = this.all_orders["data"][row]["qty"];
        temp["itemID"] = this.all_orders["data"][row]["id"];
        this.tiles.push(temp);
      }
    }
    console.log(this.tiles);
    if(this.tiles.length == 0) this.message = "None to display. Kindly refresh.";
    else this.message = null;
    this.info = false; this.all = false;
    this.allc = false; this.accepted = true;
  }

  cuisineOrders(){
    this.tiles = [];
    for(let row in this.all_orders["data"]){
      let cc = this.all_orders["data"][row]["cuisine"] + " " + this.all_orders["data"][row]["category"];
      if((cc==this.authService.expertise) && 
          (this.all_orders["data"][row]["chefID"]==null)){
        let temp = {};
        temp["orderID"] = this.all_orders["data"][row]["orderID"];
        temp["deliveryTime"] = this.all_orders["data"][row]["deliveryTime"];
        temp["item"] = this.all_orders["data"][row]["name"];
        temp["qty"] = this.all_orders["data"][row]["qty"];
        temp["itemID"] = this.all_orders["data"][row]["id"];
        this.tiles.push(temp);
      }
    }
    console.log(this.tiles);
    if(this.tiles.length == 0) this.message = "None to display. Kindly refresh.";
    else this.message = null;
    this.info = false; this.all = false;
    this.accepted = false; this.allc = true;
  }

  acceptOrder(tile){
    let url = "https://foodiekiddiee.000webhostapp.com/orders/direct-to-chefs/accept-orders.php";
    let body = {
      "orderID":tile["orderID"],
      "itemID":tile["itemID"],
      "chefID":this.authService.userID
    };
    let acceptheaders = new Headers();
    let acceptRes;
    acceptheaders.append("Content-Type", "application/x-www-form-urlencoded");
    this.accepthttp.post(url, body,{ headers: acceptheaders}).subscribe(
        result => acceptRes = result.json(),
        () => console.log("Failed..."),
        () => {
          console.log(acceptRes);
          if(acceptRes["data"]=="success"){
            this.getOrders();
          }
        });
  }

  viewDetails(tile){
    //this.detDiaSer.setDetails(tile.id);
  }

  optionChooser(){
    switch(this.selectedValue){
      case 'accepted':
        this.acceptedOrders();
        break;
      case 'chef-cuisine':
        this.cuisineOrders();
        break;
      default:
        if(!this.info) this.orderComposer();
      }
    }
}

@Component({
  selector: 'details-dialog',
  templateUrl: 'details-dialog.html',
  styleUrls: ['details-dialog.css']
})

export class DetailsDialogComponent{

  id:string;

  constructor(public dialogRef: MdDialogRef<DetailsDialogComponent>) {}

}