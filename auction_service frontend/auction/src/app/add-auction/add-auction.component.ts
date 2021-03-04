import { Component, OnInit } from '@angular/core';
import { AddAuctionServiceService } from './add-auction-service.service';

@Component({
  selector: 'app-add-auction',
  templateUrl: './add-auction.component.html',
  styleUrls: ['./add-auction.component.css']
})
export class AddAuctionComponent implements OnInit {

  constructor(private service: AddAuctionServiceService) { }

  ngOnInit(): void {
  }
  button() {
    this.service.getAllAuctions().subscribe(auction => console.log(auction));
  }
}
