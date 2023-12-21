import { _isNumberValue } from '@angular/cdk/coercion';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PrvHome, Shoes } from 'src/app/home/PrvHome';

@Component({
	selector: 'cmp-detail',
	templateUrl: './CmpDetail.html',
	styleUrls: ['./CmpDetail.scss']
})
export class CmpDetail implements OnInit {

	@Input() id!: number;
	shoes = {
		id: 0,
		title: '',
		price: '',
		qty: 0,
		category: '',
		imageUrl: 'assets/item1.png',
		discount: 0
	}
	lstShoes: any[] = []

	constructor(
		private prvHome: PrvHome

	) {

	}

	ngOnInit(): void {
		this.getData(this.id)
		console.log('id = ' + this.id)
	}

	getData(id: number) {
		if (id != 0) {
			this.prvHome.getDetailProduct(id).subscribe(
				(data: any) => {
					this.shoes = data
				}
			)
		}
		// if (id != 0) {
		// 	console.log('get data by id')
		// 	let selectedShoes = this.lstShoes.find(p => p.id === id); // Use find instead of filter
		// 	this.shoes.id = selectedShoes.id
		// 	this.shoes.title = selectedShoes.title
		// 	this.shoes.price = selectedShoes.price
		// 	this.shoes.stock = selectedShoes.stock
		// 	this.shoes.imageUrl = selectedShoes.imageUrl
		// 	this.shoes.category = selectedShoes.category
		// } else {
		// 	this.shoes = this.shoes
		// }

	}

	save(shoes: any) {
		console.log(shoes.id)
		if (shoes.id != 0) {
			this.prvHome.addProduct(shoes).subscribe(
				(data: any) => {
					this.prvHome.openSnackbar('Edit Product Success')
				}
			)
		} else {
			this.prvHome.addProduct(shoes).subscribe(
				(data: any) => {
					this.prvHome.openSnackbar('Add New Product Success')
				}
			)

		}
	}

}
