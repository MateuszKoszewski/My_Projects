import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BoughtAuctionsComponent } from './bought-auctions.component';
import { BoughtService } from './bought.service';
import { DisplaySingleAuctionComponent } from './display-single-auction/display-single-auction.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [BoughtAuctionsComponent, DisplaySingleAuctionComponent],
  imports: [
    CommonModule, FormsModule, HttpClientModule
  ],
  providers: [BoughtService]
})
export class BoughtAuctionsModule { }
