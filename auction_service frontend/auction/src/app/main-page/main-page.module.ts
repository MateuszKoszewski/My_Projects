import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainPageComponent } from './main-page.component';
import { FormsModule } from '@angular/forms';
import { MainPageService } from './main-page.service';
import { HttpClientModule } from '@angular/common/http';
import { MaterialsModule } from '../materials/materials.module';
import { SingleAuctionComponent } from './single-auction/single-auction.component';
import { DisplayAuctionComponent } from './display-auction/display-auction.component';

@NgModule({
  declarations: [MainPageComponent, SingleAuctionComponent, DisplayAuctionComponent],
  imports: [
    CommonModule, FormsModule, HttpClientModule, MaterialsModule
  ],
  exports: [],
  providers: [MainPageService]
})
export class MainPageModule { }
