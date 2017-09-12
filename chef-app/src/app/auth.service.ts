import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, URLSearchParams} from '@angular/http';
import { Router } from '@angular/router';
import { MdSnackBar } from '@angular/material';

@Injectable()
export class AuthService {
  
  data: any;
  loginURL = 'https://foodiekiddiee.000webhostapp.com/post_tester.php';
  headers: Headers;
  redirectURL: string;

  constructor(private http: Http, private router: Router, public snackBar: MdSnackBar){
  	this.headers = new Headers();
    this.headers.append("Content-Type", "application/x-www-form-urlencoded");
  }

  login(email:string,password:string){
  	let body = {"email": email, "password": password, "role": "chef"};
    this.http.post(this.loginURL, body,{ headers: this.headers}).subscribe(
        result => this.data = result,
    	  () => console.log("Failed..."),
    	  () => { 
          console.log(this.data.json()); 
          if(this.data.json()=="success") this.router.navigate(['/home']);
          else { 
            console.log(this.data.json()); 
            let snackBarRef = this.snackBar.open('Invalid Credentials',' Try Again',
              { duration: 3000 });
          }
        }
    );
  }

  logout(): void {
	  this.data = null;
	  this.router.navigate(["/login"]);
  }

}
