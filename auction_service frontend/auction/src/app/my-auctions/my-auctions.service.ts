import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { flatMap, mergeMap } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class MyAuctionsService {

  constructor(private http: HttpClient) { }



  getAuctions(userEmail: String): Observable<any> {
    return this.http.get(`http://localhost:8080/auctions/${userEmail}`);
  }


  // public downloadDataAsBase64(url: string): Observable<string> {
  //   return this.http.get(url, { responseType: 'blob' }).pipe(
  //     mergeMap(blob => {
  //       return this.blobToBase64(blob);
  //     })
  //   );
  // }

  // private blobToBase64(blob: Blob): Observable<any> {
  //   const fileReader = new FileReader();
  //   const observable = new Observable(observer => {
  //     fileReader.onloadend = () => {
  //       observer.next(fileReader.result);
  //       observer.complete();
  //     };
  //   });
  //   fileReader.readAsDataURL(blob);
  //   return observable;
  // }
}
