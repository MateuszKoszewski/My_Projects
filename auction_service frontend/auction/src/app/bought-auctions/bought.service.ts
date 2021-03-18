import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BoughtService {

  constructor(private http: HttpClient) { }



  getBoughtAuctions(userEmail: String): Observable<any> {
    return this.http.get(`http://localhost:8080/auction/bought/${userEmail}`)
  }
  getAuctionById(auctionId: number): Observable<any> {
    return this.http.get(`http://localhost:8080/auction/${auctionId}`)
  }

}