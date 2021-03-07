import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AddAuctionServiceService {

  private getAllAuctionsUrl = 'http://localhost:8080/api/getAllAuctions';
  constructor(private http: HttpClient) { }

  getAllAuctions() {
    return this.http.get(this.getAllAuctionsUrl);
  }
  uploadImages(uploadImageData: FormData) {
    return this.http.post('http://localhost:8080/image/upload', uploadImageData, { observe: 'response' })
  }

}
