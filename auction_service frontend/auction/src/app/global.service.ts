import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { UserEntity } from './UserEntity';

@Injectable({
  providedIn: 'root'
})
export class GlobalService {

  constructor(private router: Router) { }
  loggedInUser: UserEntity = null;
  subscribedUser: boolean = false;


  logOut() {
    this.loggedInUser = null;
    this.router.navigateByUrl('/mainpage')
    sessionStorage.removeItem('user')

  }

}
