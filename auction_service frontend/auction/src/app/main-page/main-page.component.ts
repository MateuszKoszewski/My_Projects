import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { GlobalService } from '../global.service';
import { AuctionRequest } from './AuctionRequest';


import { MainPageService } from './main-page.service';


@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor(private service: MainPageService, private globalService: GlobalService) { }
  @ViewChild('firstOption') countySelected: HTMLInputElement
  @ViewChild('cityOption') citySelected: HTMLInputElement
  @ViewChild('categoryOption') categorySelected: HTMLInputElement
  @ViewChild('searchingTag') tagSelected: HTMLInputElement
  localization: String;
  arrayOfCategoryObject = [];
  arrayOfCategories = [];
  text: String;
  setOfLocalization
  arrayOfCities
  arrayOfAuctions
  auctionRequest
  secondAuctionRequest
  loggedUserEmailAddress = ""
  showSearchingResult = true;



  arrayOfCounties
  ngOnInit(): void {

    if (this.globalService.loggedInUser != null) {
      this.loggedUserEmailAddress = this.globalService.loggedInUser.emailAddress
    }
    this.service.getCategories().subscribe(data => {
      this.arrayOfCategoryObject = data;
      this.arrayOfCategoryObject.forEach(element => this.arrayOfCategories.push(element.name))
      console.log(this.arrayOfCategories)
    })
    this.service.getLocalizations().subscribe(data => {
      this.setOfLocalization = data;
      this.arrayOfCounties = Object.keys(this.setOfLocalization)
      console.log(this.arrayOfCounties)
    })
    console.log(this.globalService.actualSearchingQuery)
    console.log(this.globalService.actualSearchingResult)
    if (this.globalService.actualSearchingQuery != null) {
      this.getSearchingResult(this.globalService.actualSearchingQuery)
      console.log("searching sie wykonal")
    }
    this.arrayOfAuctions = this.globalService.actualSearchingResult
    // if (this.globalService.actualSearchingQuery != null) {
    //   this.service.getAuctionsBySearchTag(this.globalService.actualSearchingQuery).subscribe(data => this.arrayOfAuctions = data)
    // }
    console.log("component loaded")
  }

  click(el) {
    console.log(el)
    let searchingEl = el.getAttribute('data-index')
    console.log(this.arrayOfCategories[searchingEl])
  }

  sendData() {

  }
  isChanging() {
    console.log(this.tagSelected.value)
  }

  getResult() {
    if (this.tagSelected.value == undefined) {
      this.tagSelected.value = ''
    }
    this.secondAuctionRequest = new AuctionRequest(this.tagSelected.value, this.countySelected.value, this.citySelected.value, this.categorySelected.value, this.loggedUserEmailAddress)
    this.globalService.actualSearchingQuery = this.secondAuctionRequest;
    this.getSearchingResult(this.secondAuctionRequest)
  }

  getSearchingResult(request: AuctionRequest) {
    this.showSearchingResult = true;
    // this.auctionRequest = new AuctionRequest(form.value.searchingTag, form.value.county, form.value.city, form.value.category,
    //   this.loggedUserEmailAddress)



    // let email
    // if (this.globalService.loggedInUser != null) {
    //   email = this.globalService.loggedInUser.emailAddress
    // }
    // else {
    //   email = ""
    // }

    this.service.getAuctionsBySearchTag(request).subscribe(data => {
      this.arrayOfAuctions = data

      this.globalService.actualSearchingResult = this.arrayOfAuctions;

    })

  }

  showCities() {
    let value = this.countySelected.value
    this.arrayOfCities = this.setOfLocalization[value]

  }
  hideSearchingResult() {
    this.showSearchingResult = false;
    this.countySelected.value = '';
    this.citySelected.value = '';
    this.categorySelected.value = '';
  }

  resetValue(element: HTMLInputElement) {
    element.value = ''
  }
  checkCounty(element) {
    if (element != this.countySelected) {
      this.citySelected.value = ''
    }
  }

}
