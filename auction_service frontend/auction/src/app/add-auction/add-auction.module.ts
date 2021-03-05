import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddAuctionComponent } from './add-auction.component';
import { FormsModule } from '@angular/forms';
import { DisplayImageComponent } from './display-image/display-image.component';



@NgModule({
  declarations: [AddAuctionComponent, DisplayImageComponent],
  imports: [
    CommonModule, FormsModule
  ]
})
export class AddAuctionModule { }
