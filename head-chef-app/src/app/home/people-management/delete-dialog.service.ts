import { Observable } from 'rxjs/Rx';
import { DeleteDialogComponent } from './people-management.component';
import { MatDialogRef, MatDialog, MatDialogConfig } from '@angular/material';
import { Injectable } from '@angular/core';

@Injectable()
export class DeleteDialogService {

    constructor(private dialog: MatDialog) { }

    permission(id: string): Observable<boolean> {
	    let dialogRef: MatDialogRef<DeleteDialogComponent>;
	    dialogRef = this.dialog.open(DeleteDialogComponent);
	    dialogRef.componentInstance.id = id;
	    return dialogRef.afterClosed();
    }
}