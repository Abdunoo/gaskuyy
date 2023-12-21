import { NgModule } from '@angular/core';
import { CmpHome } from './CmpHome';
import { RoutHome } from './RoutHome';
import { DatePipe } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { CmpAllShoes } from './allShoes/CmpAllShoes';
import { CmpOutdoor } from './outdoor/CmpOutdoor';
import { CmpTennis } from './tennis/CmpTennis';
import { CmpRun } from './running/CmpRun';
import { CmpDtl } from '../detailShoes/CmpDtl';
import { CmpCard } from '../templete/card/CmpCard';
import { CmpChart } from '../keranjang/CmpChart';
import { CmpCardBest } from '../templete/card/CmpCardBest';
import { CmpAdmin } from '../admin/CmpAdmin';
import { MatTableModule } from '@angular/material/table';
import { CmpPrdAdmin } from '../templete/admin/CmpPrdAdmin';
import { CmpList } from '../admin/list/CmpList';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { CmpDetail } from '../admin/detail/CmpDetail';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatMenuModule } from '@angular/material/menu';
import { MatDialogModule } from '@angular/material/dialog';
import { CmpDialog } from '../confirm-dialog/CmpDialog';
import { CmpLogin } from '../login/CmpLogin';
import { HttpClientModule } from '@angular/common/http';
import { AuthGuard } from './AuthGuard';




@NgModule({
	declarations: [
		CmpHome,
		CmpAllShoes,
		CmpOutdoor,
		CmpTennis,
		CmpRun,
		CmpDtl,
		CmpCard,
		CmpChart,
		CmpCardBest,
		CmpAdmin,
		CmpPrdAdmin,
		CmpList,
		CmpDetail,
		CmpDialog,
		CmpLogin

	],
	imports: [BrowserModule, HttpClientModule, RoutHome, MatDialogModule, MatTableModule, MatSnackBarModule, FormsModule, ReactiveFormsModule, MatMenuModule],
	exports: [],
	providers: [DatePipe, AuthGuard],
})
export class MdlHome { }
