import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { HomeRoutingModule } from './home-routing.module';

import { StockManagementComponent } from './stock-management/stock-management.component';
import { PeopleManagementComponent } from './people-management/people-management.component';
import { KitchenManagementComponent } from './kitchen-management/kitchen-management.component';
import { DeleteDialogComponent } from './people-management/people-management.component';
import { HcProfileComponent } from './hc-profile/hc-profile.component';

import 'hammerjs';
import {
  MatInputModule,
  MatButtonModule,
  MatIconModule,
  MatToolbarModule,
  MatSnackBarModule,
  MatDialogModule,
  MatTabsModule,
  MatTableModule,
  MatSelectModule,
  MatListModule,
  MatSlideToggleModule
} from '@angular/material';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HomeRoutingModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MatSnackBarModule,
    MatDialogModule,
    MatTabsModule,
    MatTableModule,
    MatSelectModule,
    MatListModule,
    MatSlideToggleModule
  ],
  declarations: [
    StockManagementComponent, 
    PeopleManagementComponent, 
    KitchenManagementComponent,
    DeleteDialogComponent,
    HcProfileComponent
  ],
  entryComponents: [ DeleteDialogComponent ]
})

export class HomeModule{}