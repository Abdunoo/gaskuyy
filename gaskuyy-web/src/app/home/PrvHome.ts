import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { delay, of } from 'rxjs';
import { CmpDialog } from '../confirm-dialog/CmpDialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpClient } from '@angular/common/http';

export interface Shoes {
	id: number
	title: string
	price: number
	qty: number
	imageUrl: string
	category: string
	discount: number
}

export interface Chart {
	id: number
	qty: number
	productId: Shoes
}

@Injectable({ providedIn: 'root' })
export class PrvHome {
	private storageKey = 'allShoes';
	private popularKey = 'popShoes';
	private chartKey = 'chart';

	constructor(private dialog: MatDialog, private snackBar: MatSnackBar, private http: HttpClient) { }

	// products
	getAllProducts(page: number, limit: number) {
		return this.http.get<Shoes[]>('http://localhost:8080/api/' + `products?page=` + page + '&limit=' + limit) // mengakses API
	}

	getPopularProducts(page: number, limit: number) {
		return this.http.get<Shoes[]>('http://localhost:8080/api/' + `products?page=` + page + '&limit=' + limit) // mengakses API
	}

	addProduct(newData: any) {
		return this.http.put<Shoes>('http://localhost:8080/api/' + 'products', newData) // mengakses API
	}

	getDetailProduct(id: any) {
		return this.http.get<Shoes>('http://localhost:8080/api/' + 'products/' + id) // mengakses API
	}

	getProductByCategory(category: any) {
		return this.http.get<Shoes>('http://localhost:8080/api/' + 'products/cat/' + category) // mengakses API
	}
	removeProduct(id: any) {
		return this.http.delete<Shoes>('http://localhost:8080/api/' + 'products/' + id) // mengakses API
	}

	// chart
	getAllChart() {
		return this.http.get<Chart[]>('http://localhost:8080/api/' + 'chart') // mengakses API
	}

	addProductToChart(newData: any) {
		return this.http.put<Chart>('http://localhost:8080/api/' + 'chart', newData) // mengakses API
	}

	getChartById(id: any) {
		return this.http.get<Shoes>('http://localhost:8080/api/' + 'chart/' + id) // mengakses API
	}

	removeChart(id: any) {
		return this.http.delete('http://localhost:8080/api/' + 'chart/' + id) // mengakses API
	}








	// get all product from local storage


	// get popular product from local storage
	// getPopular(): any[] {
	// 	const data = localStorage.getItem(this.popularKey);
	// 	return data ? JSON.parse(data) : [];
	// }

	// getChart(): any[] {
	// 	const data = localStorage.getItem(this.chartKey);
	// 	return data ? JSON.parse(data) : [];
	// }

	// // save data to local storage
	// saveData(data: any[]): void {
	// 	localStorage.setItem(this.storageKey, JSON.stringify(data));
	// }

	// // save popular product to local storage
	// savePopShoes() {
	// 	let dataPopular = [
	// 		{
	// 			id: 1,
	// 			name: 'Nike Jordan',
	// 			price: '1.729.000',
	// 			stock: 10,
	// 			imageUrl: 'assets/item1.png',
	// 			category: 'Outdoor',
	// 		},
	// 		{
	// 			id: 3,
	// 			name: 'Nike Jordan',
	// 			price: '1.729.000',
	// 			stock: 10,
	// 			imageUrl: 'assets/item3.png',
	// 			category: 'Outdoor',
	// 		},
	// 		// {
	// 		// 	id: 4,
	// 		// 	name: 'Nike Air Max',
	// 		// 	price: '1.729.000',
	// 		// 	stock: 10,
	// 		// 	imageUrl: 'assets/item4.png',
	// 		// 	category: 'Outdoor',
	// 		// }
	// 	]
	// 	localStorage.setItem(this.popularKey, JSON.stringify(dataPopular));
	// }

	// // save data to local storage
	// saveChart(data: any[]): void {
	// 	localStorage.setItem(this.chartKey, JSON.stringify(data));
	// }


	// // add product to chart
	// addToChart(newData: any) {
	// 	const existingData = this.getChart();
	// 	existingData.push(newData);
	// 	this.saveChart(existingData);
	// }

	// // add product to local storage
	// // addData(newData: any): void {
	// // 	const existingData = this.getAll();
	// // 	existingData.push(newData);
	// // 	this.saveData(existingData);
	// // }

	// // remove product from storage
	// removeData(index: number): void {
	// 	const existingData = this.getAll(); /home/abdun / antre - app / antre - quarkus / src / main / java / com / abdun / ws / ApplicationConfig.java
	// 	existingData.splice(index, 1);
	// 	this.saveData(existingData);
	// }

	// UpdateData(newData: any): void {
	// 	const existingData = this.getAll();
	// 	const index = existingData.findIndex((p: any) => p.id === newData.id);
	// 	console.log('index = ' + index)
	// 	if (index !== -1) {
	// 		existingData[index] = newData
	// 		// existingData[index].price = newData.price;
	// 		// existingData[index].stock = newData.stock;
	// 		// existingData[index].category = newData.category;
	// 	}
	// 	this.saveData(existingData);
	// }

	deleteProduct(id: number) {
		// Tampilkan dialog konfirmasi
		const dialogRef = this.dialog.open(CmpDialog, {
			width: '250px',
			data: {
				title: 'Confirm Deletion',
				message: 'Are you sure you want to delete this product?'
			}
		});

		dialogRef.afterClosed().subscribe(result => {
			this.removeProduct(id).subscribe(data => {
				this.openSnackbar('Remove Product Success')
			}, error => {
				this.openSnackbar('Remove Product Failed')
			})

		});
	}

	openSnackbar(message: string, action: string = 'Close', duration: number = 3000): void {
		this.snackBar.open(message, action, {
			duration: duration
		});
	}



}