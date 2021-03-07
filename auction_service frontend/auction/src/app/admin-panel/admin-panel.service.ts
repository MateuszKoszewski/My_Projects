import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CategoryEntity } from '../CategoryEntity';

@Injectable({
  providedIn: 'root'
})
export class AdminPanelService {

  constructor(private httpClient: HttpClient) { }


  postCategory(category: CategoryEntity): Observable<any> {
    return this.httpClient.post("http://localhost:8080/admin/category", category);
  }

}
