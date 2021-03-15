import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { GlobalService } from 'src/app/global.service';

@Component({
  selector: 'app-single-auction',
  templateUrl: './single-auction.component.html',
  styleUrls: ['./single-auction.component.css']
})
export class SingleAuctionComponent implements OnInit {

  constructor(private sanitizer: DomSanitizer, private globalService: GlobalService) { }

  @Input() auction: any

  localizationCity: String;
  mainImage: any;
  dateOfStart: String;
  minPrice: number;
  buyNowPrice: number;
  title: String;
  localizationCounty: String;
  auctionOwner: String
  ngOnInit(): void {

    console.log(this.auction)
    this.mainImage = this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/jpg;base64, ${this.auction.pictures[0].base64Image}`)

    this.localizationCity = this.auction.localization.city;
    this.dateOfStart = this.auction.dateOfStart;
    this.buyNowPrice = this.auction.buyNowPrice;
    this.minPrice = this.auction.minPrice;
    this.title = this.auction.title;
    this.localizationCounty = this.auction.county;
    this.auctionOwner = this.auction.user.name;
    if (this.auction.user.emailAddress === this.globalService.loggedInUser.emailAddress) {
      this.auctionOwner = "Ciebie"
    }
  }

}

