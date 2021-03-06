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
  array = [];
  uploadMessage: String[];
  displaySomething: boolean = false;

  ngOnInit(): void {
  }

  shouldDisplaySomething() {
    this.displaySomething = !this.displaySomething;
  }

  button() {
    this.service.getAllAuctions().subscribe(auction => console.log(auction));
  }
  import(odebranaZmienna: any) {
    // console.log(odebranaZmienna);
    this.array.push(odebranaZmienna);
  }
  selectFile(event: any) {
    // console.log(this.array);
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
  kliknij() {
    console.log(this.array)
    this.uploadImages();

  }
  uploadImages() {

    for (let i = 0; i < this.array.length; i++) {
      let uploadImageData = new FormData();

      let newFile: File = this.array[i];
      uploadImageData.append('imageFile', newFile, newFile.name);
      this.service.uploadImages(uploadImageData).subscribe((response) => {
        if (response.status === 200) {
          this.uploadMessage[i] = 'Image uploaded successfully';
        } else {
          this.uploadMessage[i] = 'Image not uploaded successfully';
        }
      })
    }
  }



}

