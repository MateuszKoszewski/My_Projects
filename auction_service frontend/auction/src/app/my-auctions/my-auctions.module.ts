import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MyAuctionsComponent } from './my-auctions.component';
import { MyAuctionsService } from './my-auctions.service';
import { DisplaySingleAuctionComponent } from './display-single-auction/display-single-auction.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MaterialsModule } from '../materials/materials.module';



@NgModule({
  declarations: [MyAuctionsComponent, DisplaySingleAuctionComponent],
  imports: [
    CommonModule, HttpClientModule, FormsModule, MaterialsModule
  ],
  providers: [MyAuctionsService]
})
export class MyAuctionsModule { }
