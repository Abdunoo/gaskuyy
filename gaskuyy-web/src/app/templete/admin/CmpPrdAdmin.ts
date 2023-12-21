import { Component, Input, OnInit } from '@angular/core';
import { Chart, PrvHome, Shoes } from '../../home/PrvHome';

@Component({
	selector: 'cmp-prd-admin',
	template: `
	<div class="relative flex flex-grow-0 w-full h-full py-4">
			<div class="absolute inset-0 w-full h-full overflow-auto rounded-lg" tabindex="0">
				<table mat-table [dataSource]="shoes">
					<ng-container matColumnDef="name">
						<th mat-header-cell *matHeaderCellDef> Name </th>
						<td mat-cell *matCellDef="let element"> {{element.title}} </td>
					</ng-container>

					<ng-container matColumnDef="stock">
						<th mat-header-cell *matHeaderCellDef> Quantity </th>
						<td mat-cell *matCellDef="let element"> {{element.qty}} </td>
					</ng-container>

					<ng-container matColumnDef="category">
						<th mat-header-cell *matHeaderCellDef> Category </th>
						<td mat-cell *matCellDef="let element"> {{element.category}} </td>
					</ng-container>

					<ng-container matColumnDef="action">
						<th mat-header-cell *matHeaderCellDef> Action </th>
						<td mat-cell *matCellDef="let element">
							<span class="icon-cog"></span>
						</td>
					</ng-container>
					<tr mat-header-row *matHeaderRowDef="displayedColumns; sticky: true"></tr>
					<tr mat-row *matRowDef="let row; columns: displayedColumns;"></tr>
				</table>
				<div class="hidden sm:flex" style="height: 180px;"></div>
				<div class="md:hidden" style="height: 230px;"></div>
			</div>
		</div>
 `})
export class CmpPrdAdmin implements OnInit {

	@Input() category!: string;
	shoes!: Shoes[]
	displayedColumns: string[] = ['name', 'stock', 'category', 'action'];
	dataSource = this.shoes;
	selectedShoes: any;



	constructor(
		private prvHome: PrvHome
	) { }

	ngOnInit() {
		this.getData()
	}

	getData() {
		console.log(this.category)
		this.prvHome.getAllProducts(1, 20).subscribe(
			(data: any) => {
				this.shoes = data
			}
		)
		if (this.category != null) {
			this.shoes = this.shoes.filter(shoes => {
				return shoes.category === this.category
			})
		}
	}

	// // add product to chart
	// addToChart(id: any) {
	// 	this.selectedShoes = this.shoes.find(s => s.id === id)
	// 	this.chart = this.prvHome.getChart();
	// 	this.lengthLstPrd = this.chart.length
	// 	const newPrd = {
	// 		id: this.lengthLstPrd + 1,
	// 		qty: 1,
	// 		shoesId: this.selectedShoes
	// 	}
	// 	this.prvHome.addToChart(newPrd);
	// 	console.log('Berhasil Ditambahkan')
	// }

}
