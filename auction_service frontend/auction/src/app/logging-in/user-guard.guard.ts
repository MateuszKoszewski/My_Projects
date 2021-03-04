import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { LoggingInComponent } from './logging-in.component';
import { LoggingService } from './logging.service';

@Injectable({
  providedIn: 'root'
})
export class UserGuardGuard implements CanActivate {
  constructor(private loggingComp: LoggingService, private router: Router) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {

    if (!this.loggingComp.loggedIn) {
      this.router.navigateByUrl('logg')
      return false;
    }
    else {
      return true;
    }

  }

}
