import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { UserEntity } from './UserEntity';

@Injectable({
  providedIn: 'root'
})
export class GlobalService {

  constructor(private router: Router) { }
  loggedIn: boolean = false;
  loggedInUser: UserEntity = null;
  loggedInUserEmail: String = null;
  loggedInUserPassword: String = null;
  loggedInAdmin: boolean = false;

  logOut() {
    this.loggedIn = false;
    this.loggedInUserEmail = null;
    this.loggedInUserPassword = null;
    this.loggedInUser = null;
    this.loggedInAdmin = false;
    this.router.navigateByUrl('/mainpage')
  }

}
