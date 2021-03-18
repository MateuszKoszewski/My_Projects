import { DOCUMENT } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserEntity } from './UserEntity';

@Injectable({
  providedIn: 'root'
})
export class GlobalService {

  constructor(private router: Router, @Inject(DOCUMENT) private _document: Document, private http: HttpClient) { }
  loggedInUser: UserEntity = null;
  subscribedUser: boolean = false;
  actualSearchingResult;
  actualSearchingQuery;
  notifications;
  lastSearchAuctionRequest;
  logOut() {
    this.loggedInUser = null;
    sessionStorage.removeItem('user')
    // this.actualSearchingResult = null;
    // this._document.defaultView.location.reload();
    this.router.navigateByUrl('/logg', { skipLocationChange: true }).then(() => {
      this.router.navigate(['/mainpage']);
    });
    // this.router.navigateByUrl('/logg')
  }

  getNotifications(): Observable<any> {
    console.log(this.loggedInUser)
    if (this.loggedInUser != null) {
      return this.http.get(`http://localhost:8080/notifications/${this.loggedInUser.emailAddress}`)
    }
  }
}
