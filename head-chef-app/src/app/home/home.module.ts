import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { HomeRoutingModule } from './home-routing.module';

import { StockManagementComponent } from './stock-management/stock-management.component';
import { PeopleManagementComponent } from './people-management/people-management.component';
import { KitchenManagementComponent } from './kitchen-management/kitchen-management.component';
import { DeleteDialogComponent } from './people-management/people-management.component';

import 'hammerjs';
import {
  MaterialModule,
  MdInputModule,
  MdButtonModule,
  MdIconModule,
  MdToolbarModule,
  MdSnackBarModule,
  MdDialogModule,
  MdTabsModule,
  MdTableModule
} from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HomeRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    MdInputModule,
    MdButtonModule,
    MdIconModule,
    MdToolbarModule,
    MdSnackBarModule,
    MdDialogModule,
    MdTabsModule,
    MdTableModule
  ],
  declarations: [
    StockManagementComponent, 
    PeopleManagementComponent, 
    KitchenManagementComponent,
    DeleteDialogComponent
  ],
  entryComponents: [ DeleteDialogComponent ]
})

export class HomeModule {}