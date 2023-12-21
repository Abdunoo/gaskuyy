import { Component, OnInit } from '@angular/core';
import { Chart, PrvHome, Shoes } from '../home/PrvHome';

@Component({
	selector: 'cmp-chart',
	templateUrl: './CmpChart.html',
	styleUrls: ['./CmpChart.scss']
})
export class CmpChart implements OnInit {

	chartprd: Chart[] = []
	qty = 1
	selected: any

	totalPrice = 0
	discount = 20000
	ongkir = 15000
	totalPay = 0

	constructor(
		private prvHome: PrvHome
	) { }

	ngOnInit() {
		this.getChartPrd();
	}

	//get Product From Chart
	getChartPrd() {
		this.prvHome.getAllChart().subscribe(
			(data: any) => {
				this.chartprd = data

				this.totalPrice = 0
				this.discount = 20000
				this.ongkir = 15000
				this.totalPay = 0

				for (let index = 0; index < this.chartprd.length; index++) {
					let total = this.chartprd[index].productId.price * this.chartprd[index].qty;
					this.totalPrice += total
					this.discount += this.chartprd[index].productId.discount * this.chartprd[index].qty;
				}
				console.log(this.totalPrice)
				this.totalPay = this.ongkir + this.totalPrice - this.discount
			}
		)
	}

	plus(id: any) {
		this.selected = this.chartprd.find(p => p.id === id)
		if (this.selected.qty != 0) {
			this.selected.qty = this.selected.qty + 1
			this.update(this.selected)
		}
	}
	minus(id: any) {
		this.selected = this.chartprd.find(p => p.id === id)
		if (this.selected.qty != 0) {
			this.selected.qty = this.selected.qty - 1
			this.update(this.selected)
		}
	}

	update(selected: any) {
		this.prvHome.addProductToChart(selected).subscribe(
			(data: any) => {
				console.log('success update chart')
				this.getChartPrd()
			}
		)
	}

	deleteProductFromChart(id: any) {
		this.prvHome.removeChart(id).subscribe(
			(data: any) => {
				this.getChartPrd()
			}
		)
	}




}
