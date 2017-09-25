import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import 'hammerjs';
import {
  MdInputModule,
  MdButtonModule,
  MdIconModule,
  MdToolbarModule,
  MdSnackBarModule,
  MdDialogModule,
  MdTableModule
} from '@angular/material';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HomeComponent } from './home/home.component';

import { AuthGuard } from './auth-guard.service';
import { AuthService } from './auth.service';

import { HomeModule } from './home/home.module';

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
    HomeModule,
    RouterModule.forRoot(appRoutes),
    MdInputModule,
    MdButtonModule,
    MdIconModule,
    MdToolbarModule,
    MdSnackBarModule,
    MdDialogModule,
    MdTableModule
  ],
  providers: [AuthGuard, AuthService],
  bootstrap: [AppComponent]
})

export class AppModule { }