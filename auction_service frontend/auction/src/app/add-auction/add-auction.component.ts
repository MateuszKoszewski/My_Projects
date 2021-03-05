import { Component, OnInit } from '@angular/core';
import { AddAuctionServiceService } from './add-auction-service.service';

@Component({
  selector: 'app-add-auction',
  templateUrl: './add-auction.component.html',
  styleUrls: ['./add-auction.component.css']
})
export class AddAuctionComponent implements OnInit {

  constructor(private service: AddAuctionServiceService) { }

  message: String;
  shouldDisplayMessage: boolean = false;
  url: any;

  ngOnInit(): void {
  }
  button() {
    this.service.getAllAuctions().subscribe(auction => console.log(auction));
  }

  selectFile(event: any) {

    if (!event.target.files[0] || event.target.files[0].length == 0) {
      this.message = 'You must select an image';
      return;
    }

    const mimeType = event.target.files[0].type;

    if (mimeType.match(/image\/*/) == null) {
      this.message = "Only images are supported";
      this.url = "";
      return;
    }

    const reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);

    reader.onload = (_event) => {
      this.message = "";
      this.url = reader.result;
    }
  }


}

