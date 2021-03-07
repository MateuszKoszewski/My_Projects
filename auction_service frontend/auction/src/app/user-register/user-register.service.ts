import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddressEntity } from '../AddressEntity';
import { UserEntity } from '../UserEntity';

@Injectable({
  providedIn: 'root'
})
export class UserRegisterService {


  constructor(private http: HttpClient) { }



  registerUser(userToRegister: UserEntity): Observable<any> {
    return this.http.post('http://localhost:8080/user', userToRegister);
  }
}
