import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../auth.service';
import { Router } from '@angular/router';
import { Http, Response, Headers, RequestOptions, URLSearchParams} from '@angular/http';

@Component({
  selector: 'profile',
  templateUrl: './chef-profile.component.html',
  styleUrls: ['./chef-profile.component.css']
})
export class ChefProfileComponent implements OnInit {

	oldP; newP; cnewP; hint; data;

  constructor(public authService:AuthService, private router: Router, private http:Http) { }

  ngOnInit() {
  }

  changePassword(){
    if(this.cnewP==this.oldP){ this.hint="New password cannot be the same as old password."; }
    else{
    	if(this.newP==this.cnewP){
    		this.hint=null;
    		let body = {"newPassword":this.newP,"oldPassword":this.oldP,"role":"chef","userid":this.authService.userID};
    		let headers = new Headers();
    		headers.append("Content-Type", "application/x-www-form-urlencoded");
  		  let changeURL = 'https://foodiekiddiee.000webhostapp.com/changepassword.php';
    		this.http.post(changeURL, body,{ headers: headers}).subscribe(
    			result => this.data = result.json(),
    			() => console.log("Failed..."),
    			() => {
  		        console.log(this.data);
  		        if(this.data["data"]=="updated"){ this.router.navigate(['home']); }
  		        else{ this.hint=this.data["data"]; }
    		});
    	}
    	else{ this.hint="Passwords do not match"; }
    }
  }

  cancel(){
    this.router.navigate(['/home/order']);
  }

}