import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth.service';
import { Http, Response, Headers, RequestOptions, URLSearchParams} from '@angular/http';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/observable/of';

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
  info: boolean; all: boolean;
  ordersURL = 'https://foodiekiddiee.000webhostapp.com/orders/direct-to-chefs/c-tasklist.php';
  infoURL = 'https://foodiekiddiee.000webhostapp.com/orders/direct-to-chefs/c-taskinfo.php';

  tiles = [];
  infoData = [];
  ingData;
  update_odID;

  constructor(private router: Router, private authService: AuthService, private http: Http, private infohttp: Http, private ingHttp: Http) {
    this.headers = new Headers();
    this.headers.append("Content-Type", "application/x-www-form-urlencoded");
    this.info = false; this.all = true;
    this.getOrders();
    this.ordersSubscription = Observable.interval(1000 * 60 * 5).subscribe(x => {
      //Even after 9am, if some order is newly assigned by head chef,
      //the list has to be updated.
      let tempData = this.all_orders["data"];
      var d = new Date();
      if(d.getHours()<10) {
        this.getOrders();
        if(this.all_orders["data"].length>tempData.length) this.ordersAdded = true;
        else this.ordersAdded = false;
      }
    });
    let ingURL = 'https://foodiekiddiee.000webhostapp.com/stock/ingredients.php';
    this.ingHttp.get(ingURL).subscribe(
      data => {
        console.log(data.json());
        this.ingData = data.json();
      },
      err => { console.log('Failed...'); }
    );
    }

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
    let body = {
      "date": datestr,
      "kitchenID": this.authService.kitchenID,
      "chefID": this.authService.userID
    };
    this.http.post(this.ordersURL, body,{ headers: this.headers}).subscribe(
        result => this.all_orders = result.json(),
        () => console.log("Failed..."),
        () => { 
          console.log(this.all_orders);
          if(this.all_orders["data"]=="Zero results") { this.message = "None to display."; }
          else { this.tiles = this.all_orders["data"]; }
        });
  }

  orderInfo(tile){
    let taskdetails;
    let infoheaders = new Headers();
    infoheaders.append("Content-Type", "application/x-www-form-urlencoded");
    let body = { "odID": tile.odID };
    this.infohttp.post(this.infoURL, body,{ headers: infoheaders}).subscribe(
      result => taskdetails = result.json()["data"],
      () => console.log("Failed..."),
      () => { 
        console.log(taskdetails);
        this.infoData = [];
        for(let row in taskdetails){
          let temp = [];
          for(let ing in this.ingData){
            if(this.ingData[ing]['id']==taskdetails[row]['ingID']){
              temp['ing'] = this.ingData[ing]['name'];
            }
            else if(this.ingData[ing]['id']==taskdetails[row]['chosenIngID']){
              temp['chosening'] = this.ingData[ing]['name'];
            }
            else {}
          }
          if(taskdetails[row]['ingQty']==""){ temp['ingQty'] = "Chef's Choice"; }
          else { temp['ingQty'] = taskdetails[row]['ingQty']; }
          temp['odID'] = taskdetails[row]['odID'];
          this.infoData.push(temp);
        }
        console.log(this.infoData);
        this.update_odID = tile.odID;
        this.all = false; this.info = true;
      });
    }

    updateStatus(){
      let status;
      let statusURL = 'https://foodiekiddiee.000webhostapp.com/orders/direct-to-chefs/c-updatestatus.php';
      let headers = new Headers();
      headers.append("Content-Type", "application/x-www-form-urlencoded");
      let body = { "odID": this.update_odID, "chefID": this.authService.userID };
      this.infohttp.post(statusURL, body,{ headers: headers}).subscribe(
        result => status = result,
        () => console.log("Failed..."),
        () => { 
          console.log(status);
          if(status.json()["data"]=="updated")
            this.all = true; this.info = false;
        }
        );
    }
}