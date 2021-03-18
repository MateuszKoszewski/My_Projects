import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { BoughtService } from '../bought.service';

@Component({
  selector: 'app-display-single-auction',
  templateUrl: './display-single-auction.component.html',
  styleUrls: ['./display-single-auction.component.css']
})
export class DisplaySingleAuctionComponent implements OnInit {

  constructor(private sanitizer: DomSanitizer, private service: BoughtService) { }

  @Input() auction: any
  @Input() price: number
  @Input() dateOfClosing: String;

  localizationCity: String;
  mainImage: any;
  dateOfStart: String;
  actualPrice: number;
  minPrice: number
  buyNowPrice: number;
  title: String;
  expandAuction;
  numberOfObservers;
  lastLicytationOwner;
  timeToFinish: String;

  ngOnInit(): void {


    this.mainImage = this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/jpg;base64, ${this.auction.pictures[0].base64Image}`)

    this.localizationCity = this.auction.localization.city;
    this.dateOfStart = this.auction.dateOfStart;
    this.buyNowPrice = this.auction.buyNowPrice;
    this.actualPrice = this.auction.minPrice;
    this.title = this.auction.title;
    this.minPrice = this.auction.minPrice;
    this.service.getAuctionById(this.auction.id).subscribe(data => {
      console.log(data)
      const auction = data;
      if (auction != null) {
        if (auction.licytations.length > 0) {
          this.actualPrice = auction.licytations[auction.licytations.length - 1].price;
          this.lastLicytationOwner = auction.licytations[auction.licytations.length - 1].user.name;
        }
        this.numberOfObservers = auction.observations.length;
        this.timeToFinish = auction.duration;
      }
    })

  }



}
