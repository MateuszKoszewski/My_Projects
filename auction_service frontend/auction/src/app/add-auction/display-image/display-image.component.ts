
import { Component, OnInit, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-display-image',
  templateUrl: './display-image.component.html',
  styleUrls: ['./display-image.component.css']
})
export class DisplayImageComponent implements OnInit {

  constructor() { }

  // changed: boolean = false;
  array = [];

  ngOnInit(): void {
  }
  message: String;
  url: any;
  @Output() exprotingArray = new EventEmitter();
  @Output() changeValue = new EventEmitter();
  selectFile(event: any) {
    // this.changed = true;
    // console.log(event)
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
      // if (event.target.files === undefined) {
      //   this.exprotingArray.emit(null);
      // }
      this.array = [event.target.files[0]]
      this.exprotingArray.emit(this.array)
      // this.emitChange();
    }
  }
  // emitChange() {
  //   this.changeValue.emit(this.changed)
  // }

}
