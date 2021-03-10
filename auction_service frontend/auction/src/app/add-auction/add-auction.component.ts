import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuctionEntity } from '../AuctionEntity';
import { GlobalService } from '../global.service';
import { LocalizationEntity } from '../LocalizationEntity';
import { MainPageService } from '../main-page/main-page.service';
import { UserEntity } from '../UserEntity';
import { AddAuctionServiceService } from './add-auction-service.service';

@Component({
  selector: 'app-add-auction',
  templateUrl: './add-auction.component.html',
  styleUrls: ['./add-auction.component.css']
})
export class AddAuctionComponent implements OnInit {

  constructor(private service: AddAuctionServiceService, private globalService: GlobalService, private mainPageService: MainPageService) { }

  message: String;
  shouldDisplayMessage: boolean = false;
  url: any;
  array = [];
  uploadMessage = "";
  displaySomething: boolean = false;
  howManyImages = [1]
  nastepnyArray = [false]
  optionsArray = []
  newOptionsArray = []
  loggedInUser: UserEntity;
  county: String = ""
  city: String = ""
  postCode: String = ""
  arrayOfImageNames = [];
  auction: AuctionEntity;
  arrayOfFormData = [];

  ngOnInit(): void {
    this.mainPageService.getCategories().subscribe(data => {
      this.newOptionsArray = data;
      this.newOptionsArray.forEach(element =>
        this.optionsArray.push(element.name))
    });
    this.service.getUser(this.globalService.loggedInUserEmail).subscribe(data => {
      this.loggedInUser = data;
      console.log(this.loggedInUser)
      if (this.loggedInUser.address != null) {
        this.county = this.loggedInUser.address.county;
        this.city = this.loggedInUser.address.city;
        this.postCode = this.loggedInUser.address.postCode;
      }
    });
  }

  shouldDisplaySomething() {
    this.displaySomething = !this.displaySomething;
  }

  button() {
    this.service.getAllAuctions().subscribe(auction => console.log(auction));
  }

  import(takenValue: any, el) {
    let index = el.getAttribute('data-index')
    // console.log(takenValue)
    if (takenValue.length === 2) {
      this.nastepnyArray[index] = true;
    }
    else {
      this.nastepnyArray[index] = false;
    }

    this.array[index] = takenValue[0];
    this.arrayOfImageNames.push(takenValue[0].name)
    if (index == this.howManyImages.length - 1) {
      this.howManyImages.push(1)
      this.nastepnyArray.push(false)
    }
    let newData = new FormData()
    newData.append('imageFile', takenValue[0], takenValue[0].name)
    this.arrayOfFormData.push(newData)
  }

  kliknij() {

    this.uploadImages();
    console.log(this.arrayOfImageNames)
  }

  uploadImages() {

    for (let i = 0; i < this.array.length; i++) {

      let uploadImageData = new FormData();
      let newFile: File = this.array[i];
      uploadImageData.append('imageFile', newFile, newFile.name);
      console.log(uploadImageData)

      // this.service.uploadImages(uploadImageData).subscribe((response) => {
      //   console.log(response.status)
      //   if (response.status === 200) {
      //     this.uploadMessage = 'Image uploaded successfully';
      //   } else {
      //     this.uploadMessage = 'Image not uploaded successfully';
      //   }
      // })
    }
    // console.log(this.array)
    // this.arrayRoboczy[0] = 5;
    // this.arrayRoboczy.push(null);
    // this.arrayRoboczy[2] = 6;
    // console.log(this.arrayRoboczy);

  }
  onSubmit(form: NgForm) {
    console.log(this.array)
    this.auction = new AuctionEntity(form.value.title, form.value.category, form.value.description, form.value.minPrice, form.value.buyNowPrice, new LocalizationEntity(form.value.county, form.value.city, form.value.postCode), form.value.promoted, this.arrayOfImageNames, this.loggedInUser.emailAddress)
    let formData = new FormData;
    formData.append('auction', JSON.stringify(this.auction))
    for (let i = 0; i < this.array.length; i++) {
      formData.append('imageFile', this.array[i], this.array[i].name)
    }



    console.log(formData)
    this.service.addAuction(formData).subscribe(response => {
      console.log(response.status);
      if (response.status === 200) {
        this.uploadMessage = 'Image uploaded successfully';
      } else {
        this.uploadMessage = 'Image not uploaded successfully';
      }
    });
  }





}

