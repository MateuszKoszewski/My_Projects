import { stringify } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { GlobalService } from '../global.service';
import { UserEntity } from '../UserEntity';
import { LoggingService } from './logging.service';

@Component({
  selector: 'app-logging-in',
  templateUrl: './logging-in.component.html',
  styleUrls: ['./logging-in.component.css']
})
export class LoggingInComponent implements OnInit {

  constructor(private service: LoggingService, private globalService: GlobalService, private routing: Router, private route: ActivatedRoute) { }

  returnUrl: string;
  displayLoginPanel = true;
  loggedInUserName: String;
  loggedInUser: UserEntity;
  userEmail: string;
  userPassword: string;
  // loggedIn: boolean = false;
  message: String = ""

  ngOnInit(): void {

    this.returnUrl = this.route.snapshot.queryParams['returnUrl' || '/'];
    if (sessionStorage.length > 0) {
      this.displayLoginPanel = false;
      this.loggedInUser = this.globalService.loggedInUser;
      this.loggedInUserName = this.loggedInUser.name;
    }
  }

  onSubmit(form: NgForm) {
    // console.log(form.value);
    // console.log(form);
    // console.log("useremail = " + this.userEmail + " and password: " + this.userPassword)
  }

  authorize() {
    this.service.getAuth(this.userEmail, this.userPassword).subscribe(data => {
      this.loggedInUser = data;
      this.globalService.loggedInUser = data;
      if (this.loggedInUser.emailAddress != null) {
        console.log(this.loggedInUser)
        this.message = "authenticated succesfully"
        if (this.returnUrl != undefined) {
          this.routing.navigateByUrl(this.returnUrl);
        }
        else {
          this.routing.navigateByUrl('mainpage')
        }
        sessionStorage.setItem('user', JSON.stringify(this.loggedInUser))
      }
    }, error => {
      if (error.status !== 200) {
        this.message = "wrong email or password"
      }
    }
    )
  }
  moveToRegister() {
    this.service.moveToRegister()
  }


}