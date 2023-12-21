import { Component, OnInit } from '@angular/core';
import { PrvHome } from '../PrvHome';

@Component({
	selector: 'cmp-run',
	templateUrl: './CmpRun.html',
	styleUrls: ['./CmpRun.scss']
})
export class CmpRun implements OnInit {
	run!: any[]

	constructor(
		private prvHome: PrvHome
	) { }

	ngOnInit() {
		// this.getData()
	}

	// get run shoes
	// getData() {
	// 	this.prvHome.getData().subscribe(
	// 		(data: any) => {
	// 			this.run = data
	// 			this.run = this.run.filter(shoes => {
	// 				return shoes.category === 'Running'
	// 			})


	// 		}
	// 	)
	// }
}
