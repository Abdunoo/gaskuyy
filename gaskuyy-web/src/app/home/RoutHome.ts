import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CmpHome } from './CmpHome';
import { CmpAllShoes } from './allShoes/CmpAllShoes';
import { CmpOutdoor } from './outdoor/CmpOutdoor';
import { CmpTennis } from './tennis/CmpTennis';
import { CmpRun } from './running/CmpRun';
import { CmpDtl } from '../detailShoes/CmpDtl';
import { CmpChart } from '../keranjang/CmpChart';
import { CmpAdmin } from '../admin/CmpAdmin';
import { CmpList } from '../admin/list/CmpList';
import { CmpLogin } from '../login/CmpLogin';
import { AuthGuard } from './AuthGuard';

@NgModule({
	imports: [
		RouterModule.forChild([
			{ path: 'login', component: CmpLogin },
			{
				path: 'dashboard', component: CmpHome, children: [
					{ path: 'all_shoes', component: CmpAllShoes },
					{ path: 'outdoor', component: CmpOutdoor },
					{ path: 'tennis', component: CmpTennis },
					{ path: 'running', component: CmpRun },
					// { path: 'chart', component: CmpChart },
				], canActivate: [AuthGuard]
			},
			{ path: 'dashboard/detail/:id', component: CmpDtl, canActivate: [AuthGuard] },
			{ path: 'dashboard/chart', component: CmpChart, canActivate: [AuthGuard] },
			{
				path: 'dashboard/admin', component: CmpAdmin, children: [
					{ path: ':category', component: CmpList },
				], canActivate: [AuthGuard]
			},
		])
	],
	exports: [RouterModule],

})
export class RoutHome { }