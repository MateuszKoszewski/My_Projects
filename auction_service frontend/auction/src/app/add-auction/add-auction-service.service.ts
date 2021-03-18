import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserEntity } from '../UserEntity';
import { AuctionEntity } from '../AuctionEntity';

@Injectable({
  providedIn: 'root'
})
export class AddAuctionServiceService {

  private getAllAuctionsUrl = 'http://localhost:8080/api/getAllAuctions';
  constructor(private http: HttpClient) { }

  getAllAuctions() {
    return this.http.get(this.getAllAuctionsUrl);
  }
  // uploadImages(uploadImageData: FormData) {
  //   return this.http.post('http://localhost:8080/image/upload', uploadImageData, { observe: 'response' })
  // }
  getUser(emailAddress: String): Observable<UserEntity> {
    return this.http.get<UserEntity>('http://localhost:8080/user/' + emailAddress)
  }
  addAuction(uploadImageData) {
    return this.http.post('http://localhost:8080/auction', uploadImageData, { observe: 'response' });
  }
  // addAuction2(uploadImageData) {
  //   return this.http.post('http://localhost:8080/auction2', uploadImageData, { observe: 'response' });
  // }

}
