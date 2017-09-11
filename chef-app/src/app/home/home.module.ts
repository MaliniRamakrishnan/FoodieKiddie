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

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HomeRoutingModule,
    BrowserAnimationsModule,
    MaterialModule
  ],
  declarations: [],
  entryComponents: []
})

export class ParentModule {}