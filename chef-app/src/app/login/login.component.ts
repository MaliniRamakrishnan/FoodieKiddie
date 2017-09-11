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

  constructor(public authService:AuthService, private router: Router,private http: Http) {
    this.authService.logout();
  }

  ngOnInit() {}

  checkUser(username:string, password:string){
    this.authService.login(username,password);
  }

}