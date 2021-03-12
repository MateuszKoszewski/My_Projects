import { Component, Input, OnInit, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { ShowOnDirtyErrorStateMatcher } from '@angular/material/core';
import { DomSanitizer } from '@angular/platform-browser';


import { GlobalService } from 'src/app/global.service';
import { MyAuctionsService } from '../my-auctions.service';
import { image } from './image.const';


@Component({
  selector: 'app-display-single-auction',
  templateUrl: './display-single-auction.component.html',
  styleUrls: ['./display-single-auction.component.css']
})
export class DisplaySingleAuctionComponent implements OnInit {

  constructor(private sanitizer: DomSanitizer) { }

  @Input() auction: any

  localizationCity: String;
  mainImage: any;
  dateOfStart: String;
  minPrice: number;
  buyNowPrice: number;
  title: String;
  ngOnInit(): void {

    console.log(this.auction)
    this.mainImage = this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/jpg;base64, ${this.auction.pictures[0].base64Image}`)

    this.localizationCity = this.auction.localization.city;
    this.dateOfStart = this.auction.dateOfStart;
    this.buyNowPrice = this.auction.buyNowPrice;
    this.minPrice = this.auction.minPrice;
    this.title = this.auction.title;
  }

}

