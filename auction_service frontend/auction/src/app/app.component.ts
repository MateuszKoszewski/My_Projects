import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { GlobalService } from './global.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private globalService: GlobalService, private router: Router) {
  }
  displayAdminPanel: boolean = this.globalService.loggedInAdmin;
  title = 'auction';
  selectRouter(event: any) {
    console.log(event)

  }
  showDisplayAdminPanel() {
    return this.globalService.loggedInAdmin
  }
  logOut() {
    this.globalService.logOut();
  }
}
