import { Component } from '@angular/core';
import { PrvHome, Shoes } from '../PrvHome';

@Component({
	selector: 'cmp-all-shoes',
	templateUrl: './CmpAllShoes.html',
	styleUrls: ['./CmpAllShoes.scss']
})
export class CmpAllShoes {

	constructor(
		private prvHome: PrvHome
	) { }

	ngOnInit() {
	}



}
