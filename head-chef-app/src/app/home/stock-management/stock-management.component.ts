import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'stock',
  templateUrl: './stock-management.component.html',
  styleUrls: ['./stock-management.component.css']
})

export class StockManagementComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }

}
