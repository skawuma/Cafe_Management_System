import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { SignupComponent } from '../signup/signup.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private dialog: MatDialog,
    private router: Router,
    private userServices: UserService) { }

    handleSignupAction() {
      const dialogConfig = new MatDialogConfig();
      dialogConfig.width = "550px";
      this.dialog.open(SignupComponent, dialogConfig);
    }
  ngOnInit(): void {
  }

}
