import { Component, Input, OnInit } from '@angular/core';
import { PrvHome, Shoes } from '../home/PrvHome';

@Component({
	selector: 'cmp-admin',
	templateUrl: './CmpAdmin.html',
	styleUrls: ['./CmpAdmin.scss']
})
export class CmpAdmin implements OnInit {

	shoes: Shoes[] = []

	allShoes: any
	outdoor: any
	running: any
	tennis: any

	constructor(
		private prvHome: PrvHome
	) { }

	ngOnInit() {
		this.getData()
	}

	getData() {

		this.prvHome.getAllProducts(1, 20).subscribe(
			(data: any) => {
				this.shoes = data
				const out = this.shoes.filter(shoes => {
					return shoes.category === 'Outdoor'
				})
				const run = this.shoes.filter(shoes => {
					return shoes.category === 'Running'
				})
				const tennis = this.shoes.filter(shoes => {
					return shoes.category === 'Tennis'
				})

				this.allShoes = this.shoes.length
				this.running = run.length
				this.outdoor = out.length
				this.tennis = tennis.length
			}
		)
		// this.shoes = this.prvHome.getAllProducts();
		// console.log('shoes length = ' + this.shoes.length)


	}
}
