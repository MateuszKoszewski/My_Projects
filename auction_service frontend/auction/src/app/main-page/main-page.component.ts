import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CategoryEntity } from '../CategoryEntity';
import { MainPageService } from './main-page.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor(private service: MainPageService) { }

  localization: String;
  newArray = [];
  arrayOfCategories = [];

  text: String;
  ngOnInit(): void {
    this.service.getCategories().subscribe(data => {
      this.newArray = data;
      this.newArray.forEach(element => this.arrayOfCategories.push(element.name))
      console.log(this.arrayOfCategories)
    })
  }
  // click() {
  //   this.service.getCategories().subscribe(data => {
  //     this.newArray = data;
  //     this.newArray.forEach(element => this.arrayOfCategories.push(element.name))
  //     console.log(this.arrayOfCategories)
  //   })
  // }
}
