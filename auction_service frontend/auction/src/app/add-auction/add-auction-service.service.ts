import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AddAuctionServiceService {

  private getAllAuctionsUrl = 'http://localhost:8080/api/getAllAuctions';
  constructor(private http: HttpClient) { }

  getAllAuctions() {
    return this.http.get(this.getAllAuctionsUrl);
  }
}
