import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { GlobalService } from '../global.service';
import { LoggingService } from './logging.service';

@Injectable({
  providedIn: 'root'
})
export class UserGuardGuard implements CanActivate {
  constructor(private globalService: GlobalService, private router: Router) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {

    if (sessionStorage.length === 0) {
      this.router.navigate(['logg'], { queryParams: { returnUrl: state.url } });
      return false;
    }
    else {

      return true;
    }
    return true;

  }

}
