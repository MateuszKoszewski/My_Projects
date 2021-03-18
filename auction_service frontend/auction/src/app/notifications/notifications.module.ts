import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationsComponent } from './notifications.component';
import { NotificationsService } from './notifications.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [NotificationsComponent],
  imports: [
    CommonModule, HttpClientModule, FormsModule
  ],
  providers: [NotificationsService]
})
export class NotificationsModule { }
