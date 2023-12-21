import { Component } from '@angular/core';

@Component({
	selector: 'cmp-login',
	templateUrl: './CmpLogin.html',
	styleUrls: ['./CmpLogin.scss']
})
export class CmpLogin {


	loginAdmin() {
		localStorage.setItem('role', 'admin')
	}

	loginUser() {
		localStorage.setItem('role', 'user')
	}


}
