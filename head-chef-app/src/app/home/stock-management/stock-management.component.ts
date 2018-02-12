import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth.service';
import { MatSnackBar } from '@angular/material';
import { Http, Response, Headers, RequestOptions, URLSearchParams} from '@angular/http';
import { DataSource } from '@angular/cdk/collections';
// import { Observable } from 'rxjs/Observable';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/observable/of';
import { NotificationsService } from 'angular2-notifications';

export interface Ingredients{
	ingID: string;
	name: string;
}

let ingredients: Ingredients[];
let notinstock: Ingredients[];

@Component({
  selector: 'stock',
  templateUrl: './stock-management.component.html',
  styleUrls: ['./stock-management.component.css']
})

export class StockManagementComponent implements OnInit {

	ingredientsTableReady: boolean = false; nisTableReady: boolean = false;
	ingDataSource; ingDisplayedColumns;
	nisDataSource; nisDisplayedColumns;
	ingredientsRaw; stock;
	nis; all; audio;
  stockSubscription;
	selectedValue: string;
	options = [
		{value: 'notinstock', viewValue: 'Not in Stock Only'},
    {value: 'all', viewValue: 'All'}
  	];


  constructor(private router:Router, public snackBar: MatSnackBar, private authService: AuthService, private ingredientsHttp: Http, private stockHttp: Http, private refillHttp: Http, private _notifService: NotificationsService) {
    this.selectedValue = "Not In Stock"
  	this.ingDisplayedColumns = ['ingID', 'name','isInStock'];
  	this.nisDisplayedColumns = ['ingID','name','refill'];
  	this.ingDataSource = new IngredientsSource();
  	this.nisDataSource = new IngredientsSource();
  	this.getAllIngredients();
    this.stockSubscription = Observable.interval(1000 * 60 * 3).subscribe(x => { 
      this.ingredientsTableReady = false;
      let oldnis = this.stock;
      this.notInStockOnly();
      //if(this.stock!="Zero results" && oldnis!="Zero results"){
        for(let nis in this.stock){
          /*let flag = true;
          for(let old in oldnis){
            if(oldnis[old]["ingID"]==this.stock[nis]["ingID"]) flag = false;
          }
          if(flag)*/ this.notify("STOCK REQUEST",
            this.stock[nis]["ingID"] + " is requested by " + this.stock[nis]["chefID"]);
        //}
      } 
      /*else if(this.stock!="Zero results" && oldnis=="Zero results"){
        for(let ing in this.stock)
          this.notify("STOCK REQUEST", this.stock[ing]);
      }
      else{} */
    });
  }

  ngOnInit() {}

  notify(title, content){
    this._notifService.alert(title,content);
  }

  /*ngOnDestroy(){
    this.stockSubscription.unsubscribe();
  }*/

  checkStock(ingID: string): boolean{
  	for(let x in this.stock) if(ingID==this.stock[x]["ingID"]) return true;
  }

  optionChooser(option){
  	if(option=="notinstock") { this.notInStockOnly(); }
  	else { this.getAllIngredients(); }
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

  notInStockOnly(){
  	let stockURL = 'https://foodiekiddiee.000webhostapp.com/stock/stock_in_kitchen.php';
    let body = {"kitchenID": this.authService.kitchenID};
    let stoheaders = new Headers();
  	stoheaders.append("Content-Type", "application/x-www-form-urlencoded");
    this.stockHttp.post(stockURL, body,{ headers: stoheaders}).subscribe(
      result => this.stock = result.json(),
      () => console.log("Failed..."),
      () => {
        console.log(this.stock);
        let z=-1; let nisChumma=[];
        for(let x in this.ingredientsRaw){
        	for(let y in this.stock){
        		if(this.ingredientsRaw[x]["id"]==this.stock[y]["ingID"]){
        			z++; nisChumma[z]=this.ingredientsRaw[x]; 
        		}
        	}
        }
        if(this.selectedValue=="all"){
          if(this.stock=="Zero results"){
          let snackBarRef = this.snackBar.open('All in stock.','',
              { duration: 3000 });
          }
        	this.nis = false; this.all = true;
        	this.nisTableReady = false;
        	ingredients = this.ingredientsRaw; this.ingredientsTableReady = true;
        }
        else{
          if(this.stock=="Zero results"){
          let snackBarRef = this.snackBar.open('All in stock.','',
              { duration: 3000 });
          }
        	this.all = false; this.nis = true;
        	this.ingredientsTableReady = false;
        	ingredients = nisChumma; this.nisTableReady = true;
        }
      }
    );
  }
  
  getAllIngredients(){
  	let ingheaders = new Headers();
  	ingheaders.append("Content-Type", "application/x-www-form-urlencoded");
		let ingURL = 'https://foodiekiddiee.000webhostapp.com/stock/ingredients.php';
  	this.ingredientsHttp.get(ingURL).subscribe(
    	data => {
        console.log(data);
        if(data.json()=="Zero results"){
          let snackBarRef = this.snackBar.open('No ingredients available','',
              { duration: 3000 });
        }
        else{
    		this.ingredientsRaw = data.json();
    		this.notInStockOnly();
    	  }
      },
    	err => { console.log('Failed...'); }
    );
  }

  refill(ingID: string){
  	let refillURL = 'https://foodiekiddiee.000webhostapp.com/stock/refill.php';
  	let body = {"kitchenID": this.authService.kitchenID,"ingID":ingID};
  	let reHead: Headers = new Headers(); let data;
  	reHead.append("Content-Type", "application/x-www-form-urlencoded");
    this.refillHttp.post(refillURL, body,{ headers: reHead}).subscribe(
        result => data = result.json(),
        () => console.log("Failed..."),
        () => { 
          console.log(data);
          if(this.selectedValue==null || this.selectedValue=="notinstock")
            { this.notInStockOnly(); }
          else { this.getAllIngredients(); }
        }
    );
  }

}

export class IngredientsSource extends DataSource<any> {
  /** Connect function called by the table to retrieve one stream containing the data to render. */
  connect(): Observable<Ingredients[]> {
    console.log("Connecting");
    return Observable.of(ingredients);
  }

  disconnect() {}
}