import { Component, OnDestroy, OnInit } from '@angular/core';
import { NotificationsService } from './notifications.service';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit, OnDestroy {

  constructor(private service: NotificationsService) { }

  notificationsArray
  oneNotifications
  ngOnInit(): void {
    this.service.getNotificationsByUser().subscribe(data => {
      this.notificationsArray = data
      console.log(data)
      this.oneNotifications = this.notificationsArray[0].message;
    })

  }
  ngOnDestroy(): void {
    this.service.deleteNotificationsAfterReading().subscribe(data => console.log(data))
  }

}
