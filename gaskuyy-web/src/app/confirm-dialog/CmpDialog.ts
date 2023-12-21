import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
	selector: 'cmp-dialog',
	templateUrl: './CmpDialog.html',
	styleUrls: ['./CmpDialog.scss']
})
export class CmpDialog {

	constructor(
		public dialogRef: MatDialogRef<CmpDialog>,
		@Inject(MAT_DIALOG_DATA) public data: any
	) { }

	onCancelClick(): void {
		this.dialogRef.close(false);
	}

	onConfirmClick(): void {
		this.dialogRef.close(true)
	}
}
