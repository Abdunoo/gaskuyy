import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PrvHome, Shoes } from 'src/app/home/PrvHome';

@Component({
	selector: 'cmp-list',
	templateUrl: './CmpList.html',
	styleUrls: ['./CmpList.scss']
})
export class CmpList {

	shoes!: Shoes[]
	displayedColumns: string[] = ['name', 'stock', 'category', 'action'];
	dataSource = this.shoes;
	selectedId: any;
	cat: any
	showDetail = false



	constructor(
		private prvHome: PrvHome,
		private activatedRoute: ActivatedRoute,
	) { }

	ngOnInit() {
		this.getCategory()
	}

	getCategory() {
		this.activatedRoute.params.subscribe(params => {
			let category: string = params['category'];
			console.log('category = ' + category);
			this.getData(category)
			this.cat = category
		});
	}

	getData(category: any) {
		console.log(category)
		if (category === 'All_shoes') {
			this.prvHome.getAllProducts(1, 20).subscribe(
				(data: any) => {
					this.shoes = data
				}
			)
		} else {
			this.prvHome.getProductByCategory(category).subscribe(
				(data: any) => {
					this.shoes = data
				}
			)
		}
		// if (this.shoes.length != undefined) {
		// 	this.shoes.reverse()
		// 	if (category != 'All_shoes') {
		// 		this.shoes = this.shoes.filter(shoes => {
		// 			return shoes.category === category
		// 		})
		// 	}
		// }
	}

	edit() {
		this.showDetail = true
	}

	delete(id: any) {
		this.prvHome.deleteProduct(id)
		console.log('success remove product')
		this.getCategory()
	}

}
