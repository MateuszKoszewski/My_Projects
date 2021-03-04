import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddAuctionComponent } from './add-auction.component';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [AddAuctionComponent],
  imports: [
    CommonModule, FormsModule
  ]
})
export class AddAuctionModule { }
