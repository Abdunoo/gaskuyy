import { Component, OnInit } from '@angular/core';
import { PrvHome } from '../PrvHome';

@Component({
	selector: 'cmp-tennis',
	templateUrl: './CmpTennis.html',
	styleUrls: ['./CmpTennis.scss']
})
export class CmpTennis implements OnInit {
	tennis!: any[]

	constructor(
		private prvHome: PrvHome
	) { }

	ngOnInit() {
		// this.getData()
	}

	// get tennis shoes
	// getData() {
	// 	this.prvHome.getData().subscribe(
	// 		(data: any) => {
	// 			this.tennis = data
	// 			this.tennis = this.tennis.filter(shoes => {
	// 				return shoes.category === 'Tennis'
	// 			})


	// 		}
	// 	)
	// }
}
