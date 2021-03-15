import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddAuctionComponent } from './add-auction.component';
import { FormsModule } from '@angular/forms';
import { DisplayImageComponent } from './display-image/display-image.component';
import { AddAuctionServiceService } from './add-auction-service.service';
import { HttpClientModule } from '@angular/common/http';
import { MainPageService } from '../main-page/main-page.service';
import { DisplayAuctionComponent } from './display-auction/display-auction.component';
import { MaterialsModule } from '../materials/materials.module';




@NgModule({
  declarations: [AddAuctionComponent, DisplayImageComponent, DisplayAuctionComponent],
  imports: [
    CommonModule, FormsModule, HttpClientModule, MaterialsModule
  ],
  providers: [AddAuctionServiceService, MainPageService]
})
export class AddAuctionModule { }
