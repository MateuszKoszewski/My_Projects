import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoggingService } from './logging.service';

@Component({
  selector: 'app-logging-in',
  templateUrl: './logging-in.component.html',
  styleUrls: ['./logging-in.component.css']
})
export class LoggingInComponent implements OnInit {

  constructor(private service: LoggingService) { }

  ngOnInit(): void {
  }
  isLoginMode = false;
  userEmail: String;
  userPassword: String;
  loggedIn: boolean = false;
  message: String = ""
  onSwitchMode() {
    this.isLoginMode = !this.isLoginMode;
  }
  onSubmit(form: NgForm) {
    console.log(form.value);
    console.log(form);
    console.log("useremail = " + this.userEmail + " and password: " + this.userPassword)
  }

  authorize() {
    this.service.getAuth(this.userEmail, this.userPassword).subscribe(data => {
      console.log(data)
      if (data === "authenticated succesfully") {
        this.message = "authenticated succesfully"
      }
    }, error => {
      if (error.status !== 200) {
        this.message = "worng email or password"
      }
    }
    )

  }


}