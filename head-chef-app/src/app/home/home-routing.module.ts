import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '../auth-guard.service';

import { HomeComponent } from './home.component';
import { StockManagementComponent } from './stock-management/stock-management.component';
import { PeopleManagementComponent } from './people-management/people-management.component';
import { KitchenManagementComponent } from './kitchen-management/kitchen-management.component';

const homeRoutes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    children: [
       { path: 'kitchen', component: KitchenManagementComponent, canActivate: [AuthGuard] },
       { path: 'people', component: PeopleManagementComponent, canActivate: [AuthGuard] },
       { path: 'stock', component: StockManagementComponent, canActivate: [AuthGuard] }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(homeRoutes)
  ],
  exports: [
    RouterModule
  ]
})

export class HomeRoutingModule{
}