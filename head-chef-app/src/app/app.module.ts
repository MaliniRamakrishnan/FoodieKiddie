import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';
import { SimpleNotificationsModule } from 'angular2-notifications';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
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
  MatListModule
} from '@angular/material';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HomeComponent } from './home/home.component';
import { StockManagementComponent } from './home/stock-management/stock-management.component';
import {PeopleManagementComponent} from './home/people-management/people-management.component';
import { KitchenManagementComponent } from './home/kitchen-management/kitchen-management.component';

import { HomeModule } from './home/home.module';

import { AuthGuard } from './auth-guard.service';
import { AuthService } from './auth.service';
import { DeleteDialogService } from './home/people-management/delete-dialog.service';

const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: '', pathMatch: 'full', redirectTo: '/login'},
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PageNotFoundComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes),
    SimpleNotificationsModule.forRoot(),
    HomeModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MatSnackBarModule,
    MatDialogModule,
    MatTabsModule,
    MatTableModule,
    MatSelectModule,
    MatListModule
  ],
  providers: [AuthGuard, AuthService, DeleteDialogService],
  bootstrap: [AppComponent]
})

export class AppModule {}