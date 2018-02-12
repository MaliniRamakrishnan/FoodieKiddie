import { Component, OnInit, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { FormGroup } from '@angular/forms';
import { Http, Response, Headers, RequestOptions, URLSearchParams} from '@angular/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  error;

  constructor(public authService:AuthService, private router: Router,private http: Http) {
    this.authService.logout();
  }

  ngOnInit() {}

  checkUser(username:string, password:string){
    //username="gautham@gmail.com";password="headchef-gautham";
    this.error = "";
    if(username&&password) this.authService.login(username,password);
    else this.error = "Enter all credentials.";
  }

}