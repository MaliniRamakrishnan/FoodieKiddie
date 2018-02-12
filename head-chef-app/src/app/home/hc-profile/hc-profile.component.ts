import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../auth.service';
import { Router } from '@angular/router';
import { Http, Response, Headers, RequestOptions, URLSearchParams} from '@angular/http';

@Component({
  selector: 'app-hc-profile',
  templateUrl: './hc-profile.component.html',
  styleUrls: ['./hc-profile.component.css']
})
export class HcProfileComponent implements OnInit {

	oldP; newP; cnewP; hint; data;

  constructor(public authService:AuthService, private router: Router, private http:Http) { }

  ngOnInit() {
  }

  cancel(){
    this.cnewP = this.newP = this.oldP = null;
    this.router.navigate(['/home/people']);
  }

  changePassword(){
    if(this.newP==this.cnewP){
  		this.hint=null;
  		let body = {"newPassword":this.newP,"oldPassword":this.oldP,"role":"headchef","userid":this.authService.userID};
  		let headers = new Headers();
  		headers.append("Content-Type", "application/x-www-form-urlencoded");
		  let changeURL = 'https://foodiekiddiee.000webhostapp.com/changepassword.php';
  		this.http.post(changeURL, body,{ headers: headers}).subscribe(
  			result => this.data = result.json(),
  			() => console.log("Failed..."),
  			() => {
		        console.log(this.data);
		        if(this.data["data"]=="updated"){ this.router.navigate(['/home/people']); }
		        else{ this.hint=this.data["data"]; }
  		});
  	}
  	else { this.hint="Passwords do not match"; }
  }

}