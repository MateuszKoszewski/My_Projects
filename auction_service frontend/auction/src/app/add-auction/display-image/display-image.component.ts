
import { Component, ElementRef, Input, OnInit, Output, ViewChild } from '@angular/core';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-display-image',
  templateUrl: './display-image.component.html',
  styleUrls: ['./display-image.component.css']
})
export class DisplayImageComponent implements OnInit {

  constructor() { }

  // changed: boolean = false;
  array = []
  inputMessage = "dodaj"
  ngOnInit(): void {
  }
  message: String;
  url: any;
  @Output() exportingArray = new EventEmitter();
  @Output() changeValue = new EventEmitter();
  @ViewChild('input') input: ElementRef<HTMLInputElement>
  @ViewChild('toReplace') toReplace: ElementRef<HTMLInputElement>
  fileReader = new FileReader();
  @Input() displayCloseButton


  // tete() {
  //   console.log(this.input)
  //   console.log(this.input.nativeElement.files[0])
  //   this.input.nativeElement.files = this.toReplace.nativeElement.files
  //   this.url = ''
  //   this.array = []
  //   this.exportingArray.emit(this.array)
  // }

  selectFile(event: any) {
    console.log("select file worked")
    // this.changed = true;
    // console.log(event)
    if (!event.target.files[0] || event.target.files[0].length == 0) {
      // this.message = 'You must select an image';

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
      this.inputMessage = "zmień"
      // if (event.target.files === undefined) {
      //   this.exprotingArray.emit(null);
      // }
      this.array = [event.target.files[0]]
      this.exportingArray.emit(this.array)

      // this.emitChange();
    }
  }
  // emitChange() {
  //   this.changeValue.emit(this.changed)
  // }

}
