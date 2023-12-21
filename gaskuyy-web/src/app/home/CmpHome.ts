import { Component, OnInit } from '@angular/core';
import { PrvHome } from './PrvHome';
import { filter } from 'rxjs';

@Component({
	selector: 'cmp-home',
	templateUrl: './CmpHome.html',
	styleUrls: ['./CmpHome.scss']
})
export class CmpHome implements OnInit {

	showSidemenu = true
	dropdown = true
	admin = false

	menuAll = true
	menuOut = false
	menuRun = false
	menuTen = false



	constructor(
		private prvHome: PrvHome
	) { }

	ngOnInit() {
		this.getAllData();
		// this.savePopShoes();
		this.getRole()
	}

	getAllData() {
		// let data1 = localStorage.getItem('allShoes')
		// if (data1 === null) {
		// 	let data = [
		// 		{
		// 			id: 1,
		// 			name: 'Nike Jordan',
		// 			price: '1.729.000',
		// 			stock: 10,
		// 			imageUrl: 'assets/item1.png',
		// 			category: 'Outdoor',
		// 		},
		// 		{
		// 			id: 2,
		// 			name: 'Nike Air Max',
		// 			price: '1.729.000',
		// 			stock: 20,
		// 			imageUrl: 'assets/item2.png',
		// 			category: 'Outdoor'
		// 		},
		// 		{
		// 			id: 3,
		// 			name: 'Nike Jordan',
		// 			price: '1.729.000',
		// 			stock: 10,
		// 			imageUrl: 'assets/item3.png',
		// 			category: 'Tennis'
		// 		},
		// 		{
		// 			id: 4,
		// 			name: 'Nike Air Max',
		// 			price: '1.729.000',
		// 			stock: 20,
		// 			imageUrl: 'assets/item4.png',
		// 			category: 'Tennis'
		// 		},
		// 		{
		// 			id: 5,
		// 			name: 'Nike Jordan',
		// 			price: '1.729.000',
		// 			stock: 10,
		// 			imageUrl: 'assets/item1.png',
		// 			category: 'Outdoor'
		// 		},
		// 		{
		// 			id: 6,
		// 			name: 'Nike Air Max',
		// 			price: '1.729.000',
		// 			stock: 20,
		// 			imageUrl: 'assets/item2.png',
		// 			category: 'Running'
		// 		},
		// 		{
		// 			id: 7,
		// 			name: 'Nike Jordan',
		// 			price: '1.729.000',
		// 			stock: 10,
		// 			imageUrl: 'assets/item3.png',
		// 			category: 'Running'
		// 		},
		// 		{
		// 			id: 8,
		// 			name: 'Nike Air Max',
		// 			price: '1.729.000',
		// 			stock: 20,
		// 			imageUrl: 'assets/item4.png',
		// 			category: 'Running'
		// 		}
		// 		// ,
		// 		// {
		// 		// 	id: 9,
		// 		// 	name: 'Nike Air Max',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 20,
		// 		// 	imageUrl: 'assets/item2.png',
		// 		// 	category: 'Tennis'
		// 		// },
		// 		// {
		// 		// 	id: 10,
		// 		// 	name: 'Nike Jordan',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 10,
		// 		// 	imageUrl: 'assets/item1.png',
		// 		// 	category: 'Outdoor'
		// 		// },
		// 		// {
		// 		// 	id: 11,
		// 		// 	name: 'Nike Air Max',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 20,
		// 		// 	imageUrl: 'assets/item2.png',
		// 		// 	category: 'Outdoor'
		// 		// },
		// 		// {
		// 		// 	id: 12,
		// 		// 	name: 'Nike Jordan',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 10,
		// 		// 	imageUrl: 'assets/item3.png',
		// 		// 	category: 'Tennis'
		// 		// },
		// 		// {
		// 		// 	id: 13,
		// 		// 	name: 'Nike Air Max',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 20,
		// 		// 	imageUrl: 'assets/item4.png',
		// 		// 	category: 'Tennis'
		// 		// },
		// 		// {
		// 		// 	id: 14,
		// 		// 	name: 'Nike Jordan',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 10,
		// 		// 	imageUrl: 'assets/item1.png',
		// 		// 	category: 'Outdoor'
		// 		// },
		// 		// {
		// 		// 	id: 15,
		// 		// 	name: 'Nike Air Max',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 20,
		// 		// 	imageUrl: 'assets/item2.png',
		// 		// 	category: 'Running'
		// 		// },
		// 		// {
		// 		// 	id: 16,
		// 		// 	name: 'Nike Jordan',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 10,
		// 		// 	imageUrl: 'assets/item3.png',
		// 		// 	category: 'Running'
		// 		// },
		// 		// {
		// 		// 	id: 17,
		// 		// 	name: 'Nike Air Max',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 20,
		// 		// 	imageUrl: 'assets/item4.png',
		// 		// 	category: 'Running'
		// 		// },
		// 		// {
		// 		// 	id: 18,
		// 		// 	name: 'Nike Air Max',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 20,
		// 		// 	imageUrl: 'assets/item2.png',
		// 		// 	category: 'Tennis'
		// 		// },
		// 		// {
		// 		// 	id: 19,
		// 		// 	name: 'Nike Jordan',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 10,
		// 		// 	imageUrl: 'assets/item1.png',
		// 		// 	category: 'Outdoor',
		// 		// },
		// 		// {
		// 		// 	id: 20,
		// 		// 	name: 'Nike Air Max',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 20,
		// 		// 	imageUrl: 'assets/item2.png',
		// 		// 	category: 'Outdoor'
		// 		// },
		// 		// {
		// 		// 	id: 21,
		// 		// 	name: 'Nike Jordan',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 10,
		// 		// 	imageUrl: 'assets/item3.png',
		// 		// 	category: 'Tennis'
		// 		// },
		// 		// {
		// 		// 	id: 22,
		// 		// 	name: 'Nike Air Max',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 20,
		// 		// 	imageUrl: 'assets/item4.png',
		// 		// 	category: 'Tennis'
		// 		// },
		// 		// {
		// 		// 	id: 23,
		// 		// 	name: 'Nike Jordan',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 10,
		// 		// 	imageUrl: 'assets/item1.png',
		// 		// 	category: 'Outdoor'
		// 		// },
		// 		// {
		// 		// 	id: 24,
		// 		// 	name: 'Nike Air Max',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 20,
		// 		// 	imageUrl: 'assets/item2.png',
		// 		// 	category: 'Running'
		// 		// },
		// 		// {
		// 		// 	id: 25,
		// 		// 	name: 'Nike Jordan',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 10,
		// 		// 	imageUrl: 'assets/item3.png',
		// 		// 	category: 'Running'
		// 		// },
		// 		// {
		// 		// 	id: 26,
		// 		// 	name: 'Nike Air Max',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 20,
		// 		// 	imageUrl: 'assets/item4.png',
		// 		// 	category: 'Running'
		// 		// },
		// 		// {
		// 		// 	id: 27,
		// 		// 	name: 'Nike Air Max',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 20,
		// 		// 	imageUrl: 'assets/item2.png',
		// 		// 	category: 'Tennis'
		// 		// },
		// 		// {
		// 		// 	id: 28,
		// 		// 	name: 'Nike Jordan',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 10,
		// 		// 	imageUrl: 'assets/item1.png',
		// 		// 	category: 'Outdoor'
		// 		// },
		// 		// {
		// 		// 	id: 29,
		// 		// 	name: 'Nike Air Max',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 20,
		// 		// 	imageUrl: 'assets/item2.png',
		// 		// 	category: 'Outdoor'
		// 		// },
		// 		// {
		// 		// 	id: 30,
		// 		// 	name: 'Nike Jordan',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 10,
		// 		// 	imageUrl: 'assets/item3.png',
		// 		// 	category: 'Tennis'
		// 		// },
		// 		// {
		// 		// 	id: 31,
		// 		// 	name: 'Nike Air Max',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 20,
		// 		// 	imageUrl: 'assets/item4.png',
		// 		// 	category: 'Tennis'
		// 		// },
		// 		// {
		// 		// 	id: 32,
		// 		// 	name: 'Nike Jordan',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 10,
		// 		// 	imageUrl: 'assets/item1.png',
		// 		// 	category: 'Outdoor'
		// 		// },
		// 		// {
		// 		// 	id: 33,
		// 		// 	name: 'Nike Air Max',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 20,
		// 		// 	imageUrl: 'assets/item2.png',
		// 		// 	category: 'Running'
		// 		// },
		// 		// {
		// 		// 	id: 34,
		// 		// 	name: 'Nike Jordan',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 10,
		// 		// 	imageUrl: 'assets/item3.png',
		// 		// 	category: 'Running'
		// 		// },
		// 		// {
		// 		// 	id: 35,
		// 		// 	name: 'Nike Air Max',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 20,
		// 		// 	imageUrl: 'assets/item4.png',
		// 		// 	category: 'Running'
		// 		// },
		// 		// {
		// 		// 	id: 36,
		// 		// 	name: 'Nike Air Max',
		// 		// 	price: '1.729.000',
		// 		// 	stock: 20,
		// 		// 	imageUrl: 'assets/item2.png',
		// 		// 	category: 'Tennis'
		// 		// }
		// 	]
		// 	this.prvHome.saveData(data);
		// }
		console.log('success save product to local storage')
	}

	// savePopShoes() {
	// 	this.prvHome.savePopShoes();
	// 	console.log('success save popular product to local storage')
	// }

	// saveChart() {
	// 	let data: any
	// 	this.prvHome.saveChart(data);
	// }

	toAll() {
		this.menuAll = true
		this.menuOut = false
		this.menuRun = false
		this.menuTen = false
	}

	toOutdoor() {
		this.menuAll = false
		this.menuOut = true
		this.menuRun = false
		this.menuTen = false
	}

	toRunning() {
		this.menuAll = false
		this.menuOut = false
		this.menuRun = true
		this.menuTen = false
	}

	toTennis() {
		this.menuAll = false
		this.menuOut = false
		this.menuRun = false
		this.menuTen = true
	}

	getRole() {
		let role = localStorage.getItem('role')
		if (role === 'admin') {
			console.log('role = ' + role)
			this.admin = true
		}
	}

	logout() {
		localStorage.removeItem('role')

	}


}
