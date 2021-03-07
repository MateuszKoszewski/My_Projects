import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainPageComponent } from './main-page.component';
import { FormsModule } from '@angular/forms';
import { MainPageService } from './main-page.service';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [MainPageComponent],
  imports: [
    CommonModule, FormsModule, HttpClientModule
  ],
  exports: [],
  providers: [MainPageService]
})
export class MainPageModule { }
