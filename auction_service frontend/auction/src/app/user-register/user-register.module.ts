import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserRegisterComponent } from './user-register.component';
import { FormsModule } from '@angular/forms';
import { UserRegisterService } from './user-register.service';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [UserRegisterComponent],
  imports: [
    CommonModule, FormsModule, HttpClientModule
  ],
  providers: [UserRegisterService]
})
export class UserRegisterModule { }
