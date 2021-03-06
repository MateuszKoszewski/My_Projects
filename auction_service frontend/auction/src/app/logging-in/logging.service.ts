import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class LoggingService {

  constructor(private HttpClient: HttpClient) { }
  useremail: String;
  password: String;
  encoder: string;
  getAuth(useremail: String, userPassword: String) {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(useremail + ":" + userPassword)
    })
    console.log(headers)
    this.encoder = headers.get('Authorization')
    return this.HttpClient.get("http://localhost:8080/log", { headers, responseType: 'text' as 'json' })
  }

  getToken() {
    return localStorage.getItem('token');
  }

}






