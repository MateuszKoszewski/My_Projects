import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GlobalService {

  constructor() { }
  loggedIn: boolean = false;
  loggedInUserEmail: String = null;
  loggedInUserPassword: String = null;
}
