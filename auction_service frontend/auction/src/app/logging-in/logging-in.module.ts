import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoggingInComponent } from './logging-in.component';
import { FormsModule } from '@angular/forms';
import { LoggingService } from './logging.service';
import { GlobalService } from '../global.service';
import { UserGuardGuard } from './user-guard.guard';





@NgModule({
  declarations: [LoggingInComponent],
  imports: [
    CommonModule, FormsModule
  ],
  providers: [LoggingService, GlobalService, UserGuardGuard]


})
export class LoggingInModule { }
