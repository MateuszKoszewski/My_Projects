import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { GlobalService } from 'src/app/global.service';
import { AddToObserveRequest } from '../AddToObserveRequest';
import { MainPageService } from '../main-page.service';
import { AddLicytation } from './AddLicytation';

@Component({
  selector: 'app-single-auction',
  templateUrl: './single-auction.component.html',
  styleUrls: ['./single-auction.component.css']
})
export class SingleAuctionComponent implements OnInit {

  constructor(private sanitizer: DomSanitizer, private globalService: GlobalService, private router: Router, private service: MainPageService) { }

  @Input() auction: any
  @ViewChild('input') inputPrice: ElementRef
  localizationCity: String;
  mainImage: any;
  dateOfStart: String;
  minPrice: number;
  buyNowPrice: number;
  title: String;
  localizationCounty: String;
  auctionOwner: String
  displaySubscribeButton = true;
  numberOfObservers: number
  displayLicytationMenu = false;
  loggedIn = false;
  licytationMessage;
  actualPrice;
  displayBuyNowButton = false;
  displayLicytationButton = true;
  ngOnInit(): void {


    this.mainImage = this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/jpg;base64, ${this.auction.pictures[0].base64Image}`)

    this.localizationCity = this.auction.localization.city;
    this.dateOfStart = this.auction.dateOfStart;
    this.buyNowPrice = this.auction.buyNowPrice;
    if (this.buyNowPrice) {
      this.displayBuyNowButton = true;
    }

    this.title = this.auction.title;
    this.localizationCounty = this.auction.county;
    this.auctionOwner = this.auction.user.name;
    this.numberOfObservers = this.auction.observations.length

    if (this.auction.licytations.length === 0) {
      this.minPrice = this.auction.minPrice;
    }
    else {
      this.minPrice = this.auction.licytations[this.auction.licytations.length - 1].price;
    }
    if (this.auction != undefined && this.globalService.loggedInUser != null) {
      this.loggedIn = true;
      for (let i = 0; i < this.auction.observations.length; i++) {
        if (this.auction.observations[i].user.emailAddress == this.globalService.loggedInUser.emailAddress) {
          this.displaySubscribeButton = false;
        }
      }
      if (this.auction.user.emailAddress === this.globalService.loggedInUser.emailAddress) {
        this.auctionOwner = "Ciebie"
        this.displaySubscribeButton = false;
        this.displayBuyNowButton = false;
        this.displayLicytationButton = false;
      }

      // this.displaySubscribeButton = !this.auction.loggedInUserSubscribedAuction

    }
    else {
      this.displaySubscribeButton = false;
      this.displayBuyNowButton = false;
      this.displayLicytationButton = false;
    }



  }
  moveToAuction() {
    this.router.navigateByUrl('displayAuction')
    this.service.displayedAuction = this.auction;

  }
  addToObservable() {
    const userEmail = this.globalService.loggedInUser.emailAddress;
    const auctionId = this.auction.id;
    const objectToSend = new AddToObserveRequest(auctionId, userEmail);

    this.service.postObservationToAuction(objectToSend).subscribe(data => {

      this.numberOfObservers = this.numberOfObservers + 1;
      this.displaySubscribeButton = false;
    })
  }
  showLicytationMenu() {
    this.displayLicytationMenu = !this.displayLicytationMenu
  }
  addLicytation() {
    const id = this.auction.id
    const userEmail = this.globalService.loggedInUser.emailAddress
    const price = this.inputPrice.nativeElement.value
    const licytationToSend = new AddLicytation(id, userEmail, price);

    this.service.addLicytation(licytationToSend).subscribe(data => {
      if (data.message == 'licytation added') {
        this.licytationMessage = "Twoja oferta jest najwyższa";
        this.displayLicytationMenu = false;
        this.minPrice = price;
      }
    }, error => {
      this.licytationMessage = error.error.message
    })
  }
  tetete(event: KeyboardEvent) {
    console.log(event)

  }
}

