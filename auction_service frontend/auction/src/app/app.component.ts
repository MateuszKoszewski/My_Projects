import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { GlobalService } from './global.service';
import { LoggingService } from './logging-in/logging.service';
import { UserEntity } from './UserEntity';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private globalService: GlobalService, private router: Router, private logginInService: LoggingService) {
    console.log(sessionStorage)
    console.log(sessionStorage.length)
    if (sessionStorage.length > 0) {
      this.loggedInUser = JSON.parse(sessionStorage.getItem('user'))
      console.log(this.loggedInUser)
      this.globalService.loggedInUser = this.loggedInUser;
      console.log(this.loggedInUser)
      logginInService.getAuth(this.loggedInUser.emailAddress, this.loggedInUser.password)
      console.log(this.loggedInUser)
    }

    // if (sessionStorage.getItem('userLogin') != null && sessionStorage.getItem('userPassword') != null) {
    //   console.log("sa dane")
    //   logginInService.getAuth(sessionStorage.getItem('userLogin'), sessionStorage.getItem('userPassword')).subscribe(data => {
    //     globalService.loggedInUser = data;
    //   }
    //   )
    // }
    // powyższa linijka sprawa, że po odświeżeniu strony, jesteśmy nadal zalogowani w kontekście springSecurity
    console.log(globalService.loggedInUser)
  }

  loggedInUser: UserEntity
  displayAdminPanel: boolean
  title = 'auction';
  selectRouter(event: any) {
    console.log(event)
  }
  showDisplayAdminPanel() {
    if (this.globalService.loggedInUser != null) {
      if (this.globalService.loggedInUser.emailAddress == "admin@admin")
        return true
    }
    else {
      return false
    }
  }
  logOut() {
    this.globalService.logOut();
  }
}
