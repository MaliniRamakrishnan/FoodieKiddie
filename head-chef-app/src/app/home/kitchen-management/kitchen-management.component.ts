import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'kitchen',
  templateUrl: './kitchen-management.component.html',
  styleUrls: ['./kitchen-management.component.css']
})

export class KitchenManagementComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }

}
