import { Observable } from 'rxjs/Rx';
import { DetailsDialogComponent } from './order.component';
import { MdDialogRef, MdDialog, MdDialogConfig } from '@angular/material';
import { Injectable } from '@angular/core';

@Injectable()
export class DetailsDialogService {
	
	constructor(private dialog: MdDialog) {}

	setDetails(id: string) {
	    let dialogRef: MdDialogRef<DetailsDialogComponent>;
	    dialogRef = this.dialog.open(DetailsDialogComponent);
	    dialogRef.componentInstance.id = id;
	    return dialogRef.afterClosed();
	}
}