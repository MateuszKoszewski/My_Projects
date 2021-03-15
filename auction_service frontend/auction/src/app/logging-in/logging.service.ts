import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserEntity } from '../UserEntity';


@Injectable({
  providedIn: 'root'
})
export class LoggingService {

  constructor(private HttpClient: HttpClient, private router: Router) { }

  encoder: string;
  getAuth(useremail: String, userPassword: String): Observable<UserEntity> {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(useremail + ":" + userPassword)
    })
    // console.log(headers)
    this.encoder = headers.get('Authorization')
    return this.HttpClient.get<UserEntity>(`http://localhost:8080/user/${useremail}`)
    // return this.HttpClient.get("http://localhost:8080/log", { headers, responseType: 'text' as 'json' })
  }

  // getUser(emailAddress: String): Observable<UserEntity> {
  //   return this.HttpClient.get<UserEntity>('http://localhost:8080/user/' + emailAddress)
  // }

  moveToRegister() {
    this.router.navigateByUrl('register');
  }

}






