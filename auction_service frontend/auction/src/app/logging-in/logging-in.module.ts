import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoggingInComponent } from './logging-in.component';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [LoggingInComponent],
  imports: [
    CommonModule, FormsModule
  ]
})
export class LoggingInModule { }
