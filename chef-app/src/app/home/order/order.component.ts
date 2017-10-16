import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth.service';
import { Http, Response, Headers, RequestOptions, URLSearchParams} from '@angular/http';

@Component({
  selector: 'order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  constructor(private router: Router, private authService: AuthService, private http: Http) { }
  
  options = [
    {value: 'chef-cuisine', viewValue: this.authService.expertise},
    {value: 'all', viewValue: 'All'}
    ];
  tiles = [
    {text: 'One'},
    {text: 'Two'},
    {text: 'Three'},
    {text: 'Four'},
    {text: 'Five'},
    {text: 'Six'},
    {text: 'Seven'}
  ];
  
  ngOnInit() {
  }

  goto(which:string){
    switch(which){
      case 'order': this.router.navigate(['home/order']); break;
      case 'stock': this.router.navigate(['home/stock']); break;
      case 'profile': this.router.navigate(['home/profile']); break;
      default: this.router.navigate(['page-not-found']);
    }
  }

}
