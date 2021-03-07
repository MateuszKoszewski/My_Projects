import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MainPageService {

  constructor(private http: HttpClient) { }

  getCategories(): Observable<any> {
    return this.http.get<Observable<any>>('http://localhost:8080/category');
  }
}
