import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home.component';

const homeRoutes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    children: [
      // { path: '', component: OrderComponent},
      // { path: 'order', component: OrderComponent },
      // { path: 'info', component: InfoComponent},
      // { path: 'inventory', component: InventoryComponent}
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