import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AddToObserveRequest } from './AddToObserveRequest';
import { AuctionRequest } from './AuctionRequest';
import { AddPurchaseRequest } from './display-auction/AddPurchaseRequest';
import { AddLicytation } from './single-auction/AddLicytation';

@Injectable({
  providedIn: 'root'
})
export class MainPageService {
  displayedAuction;
  actualSearchingResult;
  constructor(private http: HttpClient) { }

  getCategories(): Observable<any> {
    return this.http.get<Observable<any>>('http://localhost:8080/category');
  }
  getLocalizations(): Observable<any> {
    return this.http.get<Observable<any>>('http://localhost:8080/localization')
  }
  getAuctionsBySearchTag(auctionRequest: AuctionRequest): Observable<any> {
    let params = new HttpParams();
    params = params.append('searchingTag', auctionRequest.searchingTag)
    params = params.append('county', auctionRequest.county)
    params = params.append('city', auctionRequest.city)
    params = params.append('category', auctionRequest.category)
    params = params.append('userEmail', auctionRequest.userEmail)
    console.log(params)
    return this.http.get<Observable<any>>('http://localhost:8080/auction/', { params: params });
  }

  postObservationToAuction(addToObserveRequest: AddToObserveRequest) {
    return this.http.post('http://localhost:8080/observe', addToObserveRequest, { observe: 'response' });
  }
  addLicytation(licytationToSend: AddLicytation): Observable<any> {
    return this.http.post('http://localhost:8080/licytation', licytationToSend);
  }
  getAuctionById(auctionId: number): Observable<any> {
    return this.http.get(`http://localhost:8080/auction/${auctionId}`)
  }
  addPurchaseBuyNow(purchaseData: AddPurchaseRequest): Observable<any> {
    return this.http.post('http://localhost:8080/buyauction', purchaseData);
  }
}
