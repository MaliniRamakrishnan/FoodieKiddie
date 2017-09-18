import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, URLSearchParams} from '@angular/http';
import { Router } from '@angular/router';
import { MdSnackBar } from '@angular/material';

@Injectable()
export class AuthService {
  
  data: any; rawKitchenData: any;
  redirectURL: string;
  loginURL = 'https://foodiekiddiee.000webhostapp.com/login.php';
  kitchenURL = 'https://foodiekiddiee.000webhostapp.com/kitchen_details.php';
  headers: Headers; detailsHeaders: Headers;

  userID; email; 
  kitchenID; username; phone; expertise;
  nameOfLocation; typeOfLocation; locationDoorNumber;
  locationLandmark; locationStreet; locationCity;
  locationState; locationCountry; locationPIN;
  latitude; longitude; locationID;

  constructor(private http: Http, private httpReq: Http, private router: Router, public snackBar: MdSnackBar){
  	this.headers = new Headers();
    this.headers.append("Content-Type", "application/x-www-form-urlencoded");
  }

  login(email:string,password:string){
    this.email = email;
  	let body = {"email": email, "password": password, "role": "headchef"};
    this.http.post(this.loginURL, body,{ headers: this.headers}).subscribe(
        result => this.data = result,
    	  () => console.log("Failed..."),
    	  () => { 
          console.log(this.data); 
          if(this.data.json()=="failed"||this.data.json()==null){
            let snackBarRef = this.snackBar.open('Invalid Credentials',' Try Again',
              { duration: 3000 });
            }
          else { 
            this.userID = this.data.json();
            // console.log(this.userID);
            this.getAllDetails();
          }
        }
    );
  }

  logout(): void {
	  this.data = null;
    this.userID = null;
	  this.router.navigate(["/login"]);
  }

  getAllDetails(){
    this.detailsHeaders = new Headers();
    this.detailsHeaders.append("Content-Type", "application/x-www-form-urlencoded");
    let body = {"id": this.userID};
    this.httpReq.post(this.kitchenURL, body,{ headers: this.detailsHeaders}).subscribe(
        result => this.rawKitchenData = result.json(),
        () => console.log("Oopsie"),
        () => {
          // console.log(this.rawKitchenData);
          this.parseDetails();
        }
    );
  }

  parseDetails(){
    this.username=this.rawKitchenData[0][0]["name"];
    this.phone=this.rawKitchenData[0][0]["phone"];
    this.expertise=this.rawKitchenData[0][0]["expertise"];
    this.kitchenID=this.rawKitchenData[0][0]["kitchenID"];
    this.nameOfLocation=this.rawKitchenData[1][0]["nameOfLocation"];
    this.typeOfLocation=this.rawKitchenData[1][0]["typeOfLocation"];
    this.locationDoorNumber=this.rawKitchenData[1][0]["locationDoorNumber"];
    this.locationLandmark=this.rawKitchenData[1][0]["locationLandmark"];
    this.locationStreet=this.rawKitchenData[1][0]["locationStreet"];
    this.locationCity=this.rawKitchenData[1][0]["locationCity"];
    this.locationState=this.rawKitchenData[1][0]["locationState"];
    this.locationCountry=this.rawKitchenData[1][0]["locationCountry"];
    this.locationPIN=this.rawKitchenData[1][0]["locationPIN"];
    this.latitude=this.rawKitchenData[1][0]["latitude"];
    this.longitude=this.rawKitchenData[1][0]["longitude"];
    this.locationID=this.rawKitchenData[1][0]["locationID"];
    this.router.navigate(['/home']);
  }

}