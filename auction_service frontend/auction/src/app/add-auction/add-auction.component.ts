import { Component, OnInit } from '@angular/core';
import { GlobalService } from '../global.service';
import { AddAuctionServiceService } from './add-auction-service.service';

@Component({
  selector: 'app-add-auction',
  templateUrl: './add-auction.component.html',
  styleUrls: ['./add-auction.component.css']
})
export class AddAuctionComponent implements OnInit {

  constructor(private service: AddAuctionServiceService, private globalService: GlobalService) { }

  message: String;
  shouldDisplayMessage: boolean = false;
  url: any;
  array = [];
  uploadMessage = "";
  displaySomething: boolean = false;
  arrayRoboczy = [];
  ngOnInit(): void {
  }

  shouldDisplaySomething() {
    this.displaySomething = !this.displaySomething;
  }

  button() {
    this.service.getAllAuctions().subscribe(auction => console.log(auction));
  }


  import(odebranaZmienna: any) {
    // console.log(odebranaZmienna)
    if (odebranaZmienna === undefined) {
      this.array.push(null)
    }
    this.array.push(odebranaZmienna);
    // console.log(this.array)
  }

  kliknij() {
    // console.log(this.array)
    this.uploadImages();

  }
  uploadImages() {

    for (let i = 0; i < this.array.length; i++) {
      let uploadImageData = new FormData();

      let newFile: File = this.array[i];
      uploadImageData.append('imageFile', newFile, newFile.name);
      this.service.uploadImages(uploadImageData).subscribe((response) => {
        console.log(response.status)
        if (response.status === 200) {
          this.uploadMessage = 'Image uploaded successfully';
        } else {
          this.uploadMessage = 'Image not uploaded successfully';
        }
        // console.log(this.uploadMessage)
      })
    }
    // console.log(this.array)
    // this.arrayRoboczy[0] = 5;
    // this.arrayRoboczy.push(null);
    // this.arrayRoboczy[2] = 6;
    // console.log(this.arrayRoboczy);

  }




}

