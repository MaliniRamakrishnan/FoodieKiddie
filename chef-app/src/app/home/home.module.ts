import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { 
  MdInputModule, 
  MdToolbar,
  MaterialModule 
} from '@angular/material';
import 'hammerjs';

import { HomeRoutingModule } from './home-routing.module';
import { ChefProfileComponent } from './chef-profile/chef-profile.component';
import { OrderComponent } from './order/order.component';
import { StockManagementComponent } from './stock-management/stock-management.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HomeRoutingModule,
    BrowserAnimationsModule,
    MaterialModule
  ],
  declarations: [ChefProfileComponent, OrderComponent, StockManagementComponent],
  entryComponents: []
})

export class HomeModule{}