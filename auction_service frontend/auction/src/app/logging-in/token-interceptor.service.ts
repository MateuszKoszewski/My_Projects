import { HttpHandler, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { LoggingService } from './logging.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService {

  constructor(private injector: Injector) { }


  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let loggingService = this.injector.get(LoggingService);
    let tokenizedReq = null;
    console.log(loggingService.encoder)
    if (loggingService.encoder != null) {
      tokenizedReq = req.clone({
        headers: req.headers.set('Authorization', loggingService.encoder)
        // setHeaders: {
        //   Authorization: 'Basic ' + btoa(loggingService.useremail + ':' + loggingService.password)
        // }
      })
    }
    else {
      tokenizedReq = req.clone({
        headers: req.headers.set('No-Auth', 'True')
      })
    }
    return next.handle(tokenizedReq)
  }
}
