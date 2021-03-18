import { Component, OnInit } from '@angular/core';
import { GlobalService } from '../global.service';
import { BoughtService } from './bought.service';

@Component({
  selector: 'app-bought-auctions',
  templateUrl: './bought-auctions.component.html',
  styleUrls: ['./bought-auctions.component.css']
})
export class BoughtAuctionsComponent implements OnInit {

  constructor(private service: BoughtService, private globalService: GlobalService) { }

  arrayOfAllAuctions = [];
  arrayOfMainImages = [];
  displayLittleAuction = false;
  userName: String;
  displayNoAuctionsMessage = false;
  finalPrices = [];
  datesOfFinish = [];


  ngOnInit(): void {
    console.log(this.globalService.loggedInUser.emailAddress)

    this.service.getBoughtAuctions(this.globalService.loggedInUser.emailAddress).subscribe(result => {
      console.log(result)
      if (result != null) {
        for (let i = 0; i < result.length; i++) {
          this.arrayOfAllAuctions.push(result[i].auction)
          this.finalPrices.push(result[i].price)
          this.datesOfFinish.push(result[i].date)
        }
        console.log(this.arrayOfAllAuctions)
        if (this.arrayOfAllAuctions.length == 0) {
          this.displayNoAuctionsMessage = true;
        }
        else {

          this.userName = this.globalService.loggedInUser.name
          this.displayLittleAuction = true;
        }
      }
    })



  }
}