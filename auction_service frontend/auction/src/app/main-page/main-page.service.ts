import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuctionRequest } from './AuctionRequest';

@Injectable({
  providedIn: 'root'
})
export class MainPageService {

  constructor(private http: HttpClient) { }

  getCategories(): Observable<any> {
    return this.http.get<Observable<any>>('http://localhost:8080/category');
  }
  getLocalizations(): Observable<any> {
    return this.http.get<Observable<any>>('http://localhost:8080/localization')
  }
  getAuctionsBySearchTag(searchingTag, county, city, category): Observable<any> {
    let params = new HttpParams();
    params = params.append('searchingTag', searchingTag)
    params = params.append('county', county)
    params = params.append('city', city)
    params = params.append('category', category)
    console.log(params)
    return this.http.get<Observable<any>>('http://localhost:8080/auction/', { params: params });
  }
}
