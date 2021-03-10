import { Injectable } from '@angular/core';
import { UserEntity } from './UserEntity';

@Injectable({
  providedIn: 'root'
})
export class GlobalService {

  constructor() { }
  loggedIn: boolean = false;
  loggedInUser: UserEntity = null;
  loggedInUserEmail: String = null;
  loggedInUserPassword: String = null;
}
