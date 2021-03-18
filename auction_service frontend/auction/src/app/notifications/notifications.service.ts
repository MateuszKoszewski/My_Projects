import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GlobalService } from '../global.service';

@Injectable({
  providedIn: 'root'
})
export class NotificationsService {

  constructor(private http: HttpClient, private globalService: GlobalService) {

  }

  getNotificationsByUser(): Observable<any> {
    return this.http.get(`http://localhost:8080/notifications/${this.globalService.loggedInUser.emailAddress}`)
  }
  deleteNotificationsAfterReading(): Observable<any> {
    return this.http.delete(`http://localhost:8080/notifications/${this.globalService.loggedInUser.emailAddress}`)
  }
}
