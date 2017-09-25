import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '../auth-guard.service';

import { HomeComponent } from './home.component';
import { StockManagementComponent } from './stock-management/stock-management.component';
import { OrderComponent } from './order/order.component';
import { ChefProfileComponent } from './chef-profile/chef-profile.component';

const homeRoutes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    children: [
      { path: 'stock', component: StockManagementComponent, canActivate: [AuthGuard] },
      { path: 'order', component: OrderComponent, canActivate: [AuthGuard] },
      { path: 'profile', component: ChefProfileComponent, canActivate: [AuthGuard] }
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