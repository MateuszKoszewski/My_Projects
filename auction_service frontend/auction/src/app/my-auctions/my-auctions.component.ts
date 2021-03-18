import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { GlobalService } from '../global.service';
import { MyAuctionsService } from './my-auctions.service';

@Component({
  selector: 'app-my-auctions',
  templateUrl: './my-auctions.component.html',
  styleUrls: ['./my-auctions.component.css']
})
export class MyAuctionsComponent implements OnInit {

  constructor(private service: MyAuctionsService, private globalService: GlobalService, private sanitizer: DomSanitizer) { }


  arrayOfAllAuctions = [];
  arrayOfMainImages = [];
  displayLittleAuction = false;
  userName: String;
  displayNoAuctionsMessage = false;

  ngOnInit(): void {
    this.service.getAuctions(this.globalService.loggedInUser.emailAddress, true).subscribe(result => {
      this.arrayOfAllAuctions = result;
      console.log(this.arrayOfAllAuctions)
      if (this.arrayOfAllAuctions.length == 0) {
        this.displayNoAuctionsMessage = true;
      }
      else {
        // this.arrayOfAllAuctions.forEach(element => {
        //   element.pictures.forEach(singlePicture => {
        //     if (singlePicture.main == true) {
        //       this.arrayOfMainImages.push(singlePicture.base64Image);
        //     }
        //   })
        //   console.log(this.arrayOfMainImages)
        // })
        this.userName = this.arrayOfAllAuctions[0].user.name;
        this.displayLittleAuction = true;
      }
    })

    // console.log(this.arrayOfImages);
    // this.imageSource = this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/jpg;base64, ${image}`);

  }

  showArray() {
    // console.log(this.arrayOfAllAuctions)
    // console.log(this.arrayOfAllAuctions.length)
  }

}
