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
  localization: String;
  arrayOfCategoryObject = [];
  arrayOfCategories = [];
  text: String;
  setOfLocalization
  arrayOfCities
  arrayOfAuctions
  displaySearchedAuctions = false;


  arrayOfCounties
  ngOnInit(): void {
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
    this.displaySearchedAuctions = false;

  }

  click(el) {
    console.log(el)
    let searchingEl = el.getAttribute('data-index')
    console.log(this.arrayOfCategories[searchingEl])
  }
  onSubmit(form: NgForm) {
    const auctionRequest = new AuctionRequest(form.value.searchingTag, form.value.county, form.value.city, form.value.category)
    console.log(auctionRequest)
    this.service.getAuctionsBySearchTag(form.value.searchingTag, form.value.county, form.value.city, form.value.category).subscribe(data => {
      this.displaySearchedAuctions = true;
      this.arrayOfAuctions = data
      console.log(this.arrayOfAuctions)
    })

  }
  kliknij() {
    let value = this.countySelected.value
    console.log(this.countySelected.value)
    console.log(this.setOfLocalization[value])
  }
  showCities() {
    let value = this.countySelected.value
    this.arrayOfCities = this.setOfLocalization[value]

  }

}
