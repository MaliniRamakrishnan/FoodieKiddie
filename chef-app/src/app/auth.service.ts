import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, URLSearchParams} from '@angular/http';
import { Router } from '@angular/router';
import { MdSnackBar } from '@angular/material';

@Injectable()
export class AuthService {
  
  data: any;
  userID: string = null; kitchenID: string; expertise: string;
  loginURL = 'https://foodiekiddiee.000webhostapp.com/login.php';
  headers: Headers;
  redirectURL: string;

  constructor(private http: Http, private khttp: Http, private router: Router, public snackBar: MdSnackBar){
  	this.headers = new Headers();
    this.headers.append("Content-Type", "application/x-www-form-urlencoded");
  }

  login(email:string,password:string){
  	let body = {"email": email, "password": password, "role": "chef"};
    this.http.post(this.loginURL, body,{ headers: this.headers}).subscribe(
        result => this.data = result,
    	  () => console.log("Failed..."),
    	  () => { 
          // console.log(this.data.json()["data"]); 
          if(this.data.json()["data"]=="failed"){
            let snackBarRef = this.snackBar.open('Invalid Credentials',' Try Again',
              { duration: 3000 });
            }
          else {
            this.userID = this.data.json()["data"];
            this.fetchChefDetails();
          }
        }
    );
  }

  fetchChefDetails(){
    let kheaders = new Headers();
    kheaders.append("Content-Type", "application/x-www-form-urlencoded");
    let body = {"userID":this.userID}; let data;
    let kitchenURL = 'https://foodiekiddiee.000webhostapp.com/chef/chefDetail.php';
    this.khttp.post(kitchenURL, body, { headers: kheaders}).subscribe(
      result=> data = result.json(),
      ()=> console.log("Failed..."),
      ()=> {
        // console.log(data["0"]["kitchenID"] + data["0"]["expertiseCuisine"] + data["0"]["expertiseCategory"]);
        this.kitchenID=data["0"]["kitchenID"];
        this.expertise=data["0"]["expertiseCuisine"]+" "+data["0"]["expertiseCategory"];
        this.router.navigate(['/home']);
      }
    );
  }

  logout(): void {
	  this.data = null;
    this.userID = null;
	  this.router.navigate(["/login"]);
  }

}
