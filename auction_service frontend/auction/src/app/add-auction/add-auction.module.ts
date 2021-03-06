import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddAuctionComponent } from './add-auction.component';
import { FormsModule } from '@angular/forms';
import { DisplayImageComponent } from './display-image/display-image.component';
import { AddAuctionServiceService } from './add-auction-service.service';
import { HttpClientModule } from '@angular/common/http';




@NgModule({
  declarations: [AddAuctionComponent, DisplayImageComponent],
  imports: [
    CommonModule, FormsModule, HttpClientModule
  ],
  providers: [AddAuctionServiceService]
})
export class AddAuctionModule { }
