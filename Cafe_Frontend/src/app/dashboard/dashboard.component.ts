import { AfterViewInit, Component, OnInit } from '@angular/core';
import { DashboardService } from '../services/dashboard.service';
import { SnachbarService } from '../services/snachbar.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { GlobalConstants } from 'src/shared/global-constants';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements AfterViewInit {

	responseMessage: any;
	data: any;
	ngAfterViewInit() { }

	constructor(private dashboardService: DashboardService,
		private ngxService: NgxUiLoaderService,
		private snachbarService: SnachbarService) {
		this.ngxService.start();
		this.dashboardData();
	}

	dashboardData() {
		this.dashboardService.getDetails().subscribe((response: any) => {
			this.ngxService.stop();
			this.data = response;
		}, (error: any) => {
			this.ngxService.stop();
			console.log(error);
			if (error.error?.message) {
				this.responseMessage = error.error?.message;
			}
			else {
				this.responseMessage = GlobalConstants.genericError;
			}
			this.snachbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
		})
	}
}
