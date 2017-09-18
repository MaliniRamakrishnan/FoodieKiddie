import { Observable } from 'rxjs/Rx';
import { DeleteDialogComponent } from './people-management.component';
import { MdDialogRef, MdDialog, MdDialogConfig } from '@angular/material';
import { Injectable } from '@angular/core';

@Injectable()
export class DeleteDialogService {

    constructor(private dialog: MdDialog) { }

    permission(id: string): Observable<boolean> {
    let dialogRef: MdDialogRef<DeleteDialogComponent>;
    dialogRef = this.dialog.open(DeleteDialogComponent);
    dialogRef.componentInstance.id = id;
    return dialogRef.afterClosed();}
}