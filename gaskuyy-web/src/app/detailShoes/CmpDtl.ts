import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Chart, PrvHome, Shoes } from '../home/PrvHome';
import { filter } from 'rxjs';

@Component({
	selector: 'cmp-dtl',
	templateUrl: './CmpDtl.html',
	styleUrls: ['./CmpDtl.scss']
})
export class CmpDtl implements OnInit {

	chart: Chart = {
		id: 0,
		qty: 0,
		productId: {
			id: 0,
			title: '',
			price: 0,
			qty: 0,
			imageUrl: '',
			category: '',
			discount: 0
		}
	}
	selectedShoes: Shoes = {
		id: 0,
		title: '',
		price: 0,
		qty: 0,
		imageUrl: '',
		category: '',
		discount: 0
	};
	lengthLstPrd: any;
	qty = 1;


	constructor(
		private activatedRoute: ActivatedRoute,
		private prvHome: PrvHome
	) { }

	ngOnInit(): void {
		this.getId()
	}

	getId() {
		this.activatedRoute.params.subscribe(params => {
			let id: number = +params['id'];
			console.log('id = ' + id);
			this.getDataById(id);
		});
	}

	getDataById(id: number) {
		this.prvHome.getDetailProduct(id).subscribe(
			(data: any) => {
				this.selectedShoes = data
			}
		)
	}

	// add product to chart
	addToChart() {
		this.chart.productId = this.selectedShoes
		this.prvHome.addProductToChart(this.chart).subscribe(
			(data: any) => {
				console.log('Berhasil Ditambahkan')
			}
		)
	}

	plus() {
		this.qty = this.qty + 1
		this.chart.qty = this.qty
	}
	minus() {
		this.qty = this.qty - 1
		this.chart.qty = this.qty
	}

	openAlert() {
		this.prvHome.openSnackbar('Add to Chart Success')
	}

}
