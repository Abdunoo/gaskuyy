import { Injectable, OnInit } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { PrvHome } from './PrvHome';

@Injectable({
	providedIn: 'root',
})
export class AuthGuard implements CanActivate, OnInit {

	constructor(
		private router: Router,
		private prvHome: PrvHome
	) { }


	canActivate(): boolean {
		const role = localStorage.getItem('role')
		if (!role) {
			this.router.navigate(['/login'])
			console.log('no permission')
			return false;
		} else {
			return true;
		}
	}

	ngOnInit() {

	}

}
