import { Component, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { GlobalService } from 'src/app/global.service';
import { AddToObserveRequest } from '../AddToObserveRequest';
import { MainPageService } from '../main-page.service';
import { AddLicytation } from '../single-auction/AddLicytation';
import { AddPurchaseRequest } from './AddPurchaseRequest';

@Component({
  selector: 'app-display-auction',
  templateUrl: './display-auction.component.html',
  styleUrls: ['./display-auction.component.css']
})
export class DisplayAuctionComponent implements OnInit {

  constructor(private service: MainPageService, private globalService: GlobalService, private sanitizer: DomSanitizer) { }
  @ViewChild('input') inputPrice
  displayedAuctionId
  displayedAuction
  arrayOfImages = []
  differenceInTime;
  buyNowPrice
  auctionOwner
  actualPrice
  auctionTitle
  localizationCity
  localizationCounty
  description
  lastLicytationOwner
  displayBuyNowButton = true;
  displayLicytationButton = true;
  displaySubscribeButton = true;
  numberOfObservers
  displayLicytationMenu
  licytationMessage
  loggedIn = false;
  auctionFinished

  ngOnInit(): void {
    this.displayedAuctionId = this.service.displayedAuction.id
    this.service.getAuctionById(this.displayedAuctionId).subscribe(data => {
      this.displayedAuction = data;
      for (let i = 0; i < this.displayedAuction.pictures.length; i++) {
        this.arrayOfImages[i] = this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/jpg;base64, ${this.displayedAuction.pictures[i].base64Image}`)
      }
      this.buyNowPrice = this.displayedAuction.buyNowPrice;
      if (this.buyNowPrice == undefined || this.buyNowPrice == null || this.buyNowPrice === 0) {
        this.displayBuyNowButton = false;
      }

      console.log(this.buyNowPrice)
      this.differenceInTime = this.displayedAuction.duration;
      this.auctionOwner = this.displayedAuction.user.name;
      this.auctionTitle = this.displayedAuction.title;
      this.localizationCity = this.displayedAuction.localization.city
      this.localizationCounty = this.displayedAuction.localization.county
      this.description = this.displayedAuction.description
      this.numberOfObservers = this.displayedAuction.observations.length
      if (this.globalService.loggedInUser != null) {
        this.loggedIn = true;

        for (let i = 0; i < this.displayedAuction.observations.length; i++) {
          if (this.displayedAuction.observations[i].user.emailAddress == this.globalService.loggedInUser.emailAddress) {
            this.displaySubscribeButton = false;
          }
        }


        if (this.globalService.loggedInUser.emailAddress === this.displayedAuction.user.emailAddress) {
          this.auctionOwner = "Ciebie"
          this.displayBuyNowButton = false;
          this.displayLicytationButton = false;
          this.displaySubscribeButton = false;
        }
      }
      else {
        this.displayBuyNowButton = false;
        this.displayLicytationButton = false;
        this.displaySubscribeButton = false;
      }
      if (this.displayedAuction.licytations.length > 0) {
        this.actualPrice = this.displayedAuction.licytations[this.displayedAuction.licytations.length - 1].price
        this.lastLicytationOwner = this.displayedAuction.licytations[this.displayedAuction.licytations.length - 1].user.name
      }
      else {
        this.actualPrice = this.displayedAuction.minPrice;
        this.lastLicytationOwner = null;
      }

    })


  }
  buyNow() {
    this.auctionFinished = true;
    this.displayBuyNowButton = false;
    this.displayLicytationButton = false;
    console.log(this.displayedAuction);
    this.actualPrice = this.buyNowPrice;
    this.lastLicytationOwner = this.globalService.loggedInUser.name
    const purchaseRequest = new AddPurchaseRequest(this.globalService.loggedInUser.emailAddress, this.displayedAuctionId)
    this.service.addPurchaseBuyNow(purchaseRequest).subscribe(data => console.log(data));
  }

  addToObservable() {
    const userEmail = this.globalService.loggedInUser.emailAddress;
    const auctionId = this.displayedAuction.id;
    const objectToSend = new AddToObserveRequest(auctionId, userEmail);
    console.log(objectToSend)
    this.service.postObservationToAuction(objectToSend).subscribe(data => {
      console.log(data.body)
      this.numberOfObservers = this.numberOfObservers + 1;
      this.displaySubscribeButton = false;
    })
  }
  showLicytationMenu() {
    this.displayLicytationMenu = !this.displayLicytationMenu
  }
  addLicytation() {
    const id = this.displayedAuction.id
    const userEmail = this.globalService.loggedInUser.emailAddress
    const price = this.inputPrice.nativeElement.value
    const licytationToSend = new AddLicytation(id, userEmail, price);

    this.service.addLicytation(licytationToSend).subscribe(data => {
      if (data.message == 'licytation added') {
        this.licytationMessage = "Twoja oferta jest najwyższa";
        this.displayLicytationMenu = false;
        this.actualPrice = price;
      }
    }, error => {
      this.licytationMessage = error.error.message
    })

  }

  moveToImage() {

  }
  klik() {

    this.displayLicytationMenu = !this.displayLicytationMenu

  }
}
