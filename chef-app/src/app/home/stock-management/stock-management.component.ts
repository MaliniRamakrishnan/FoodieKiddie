import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth.service';
import { MdSnackBar } from '@angular/material';
import { Http, Response, Headers, RequestOptions, URLSearchParams} from '@angular/http';
import { DataSource } from '@angular/cdk/collections';
//import { Observable } from 'rxjs/Observable';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/observable/of';

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

  ingredientsTableReady: boolean = false;
  ingDataSource; ingDisplayedColumns;
  ingredientsRaw; stock; stockSubscription;

  constructor(private router:Router, public snackBar: MdSnackBar, private authService: AuthService, private ingredientsHttp: Http, private stockHttp: Http, private refillHttp: Http) {
    this.ingDisplayedColumns = ['ingID', 'name', 'refill'];
    this.ingDataSource = new IngredientsSource();
    this.getAllIngredients();
    this.stockSubscription = Observable.interval(1000 * 60).subscribe(x => { 
      this.ingredientsTableReady = false;
      this.notInStockOnly(); 
    });
  }

  ngOnInit() {}

  ngOnDestroy(){
    this.stockSubscription.unsubscribe();
  }

  checkStock(ingID: string): boolean{
    for(let x in this.stock) if(ingID==this.stock[x]["ingID"]) return true;
  }

  goto(which:string){
    switch(which){
      case 'order': this.router.navigate(['home/order']); break;
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
        /*let z=-1; let nisChumma=[];
        for(let x in this.ingredientsRaw){
          for(let y in this.stock){
            if(this.ingredientsRaw[x]["id"]==this.stock[y]["ingID"]){
              z++; nisChumma[z]=this.ingredientsRaw[x]; 
            }
          }
        }*/
        if(this.stock=="Zero results"){
          let snackBarRef = this.snackBar.open('All in stock.','',
              { duration: 3000 });
        }
        ingredients = this.ingredientsRaw;
        this.ingredientsTableReady = true;
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
    let refillURL = 'https://foodiekiddiee.000webhostapp.com/stock/requestrefill.php';
    let body = {"kitchenID": this.authService.kitchenID,"ingID":ingID};
    let reHead: Headers = new Headers(); let data;
    reHead.append("Content-Type", "application/x-www-form-urlencoded");
    this.refillHttp.post(refillURL, body,{ headers: reHead}).subscribe(
        result => data = result.json(),
        () => console.log("Failed..."),
        () => { 
          console.log(data);
          if(data=="requested"){
            this.ingredientsTableReady=false;
            this.getAllIngredients();
          }
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