import { Component, OnInit } from '@angular/core';
import { PrvHome } from '../PrvHome';
import { filter } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

@Component({
	selector: 'cmp-outdoor',
	templateUrl: './CmpOutdoor.html',
	styleUrls: ['./CmpOutdoor.scss']
})
export class CmpOutdoor implements OnInit {
	outdoor!: any[]

	constructor(
		private prvHome: PrvHome,
		private activatedRoute: ActivatedRoute,
	) { }

	ngOnInit() {
		// this.getData()
	}

	// get outdoor shoes
	// getData() {
	// 	this.prvHome.getData().subscribe(
	// 		(data: any) => {
	// 			this.outdoor = data
	// 			this.outdoor = this.outdoor.filter(shoes => {
	// 				return shoes.category === 'Outdoor'
	// 			})


	// 		}
	// 	)
	// }
}
