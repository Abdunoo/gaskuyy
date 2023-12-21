import { Component, Input, OnInit } from '@angular/core';
import { Chart, PrvHome, Shoes } from '../../home/PrvHome';

@Component({
	selector: 'cmp-card',
	template: `
	<div class="grid w-full grid-cols-2 gap-4 lg:grid-cols-4">
	<div *ngFor="let item of shoes">
		<div class="pl-1 space-y-1 bg-white rounded-lg" >
			<img [src]="item.imageUrl" alt="" [routerLink]="'/dashboard/detail/' + item.id">
			<p class="text-base font-semibold text-gray-500 " [routerLink]="'/dashboard/detail/' + item.id">{{item.title}}</p>
			<p class="flex justify-between font-sans text-sm font-medium text-gray-600" >Rp {{item.price}}
				<span class="p-2 text-white bg-blue-400 rounded-tl-lg rounded-br-lg icon-plus" (click)="openAlert()" (click)="addToChart(item.id)">
				</span>
			</p>
		</div>
	</div>
</div>
 `})
export class CmpCard implements OnInit {

	@Input() category!: string;
	shoes!: Shoes[]
	showAlert: boolean = false;
	alertMessage: string | null = null;
	chart!: Chart[];
	selectedShoes!: Shoes;
	lengthLstPrd: any;


	constructor(
		private prvHome: PrvHome
	) { }

	ngOnInit() {
		this.getData()
	}

	getData() {

		// this.shoes.reverse()
		if (this.category != null) {
			this.prvHome.getProductByCategory(this.category).subscribe(
				(data: any) => {
					this.shoes = data
					this.shoes.reverse()
				}
			)
		} else {
			this.prvHome.getAllProducts(1, 20).subscribe(
				(data: any) => {
					this.shoes = data
					this.shoes.reverse()
				}
			)
		}
	}

	// add product to chart
	addToChart(id: any) {
		this.prvHome.getDetailProduct(id).subscribe(
			(data: any) => {
				this.selectedShoes = data
			}
		)
		if (this.selectedShoes != null) {
			const newPrd = {
				qty: 1,
				productId: this.selectedShoes
			}
			this.prvHome.addProductToChart(newPrd).subscribe(
				(data: any) => {
					console.log('Berhasil Ditambahkan')
				}
			)
		}
	}

	openAlert() {
		this.prvHome.openSnackbar('Add to Chart Success')
	}

}
