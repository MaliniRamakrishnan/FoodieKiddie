import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    let url: string = state.url;
    return this.checkLogin(url);
  }

  checkLogin(url: string): boolean {
    if (this.authService.userID) {
      // console.log("authguard true part");
      return true;
    }  
    // console.log("in checkLogin else auth guard");
    //Store the attempted URL for redirecting
    this.authService.redirectURL = url;
    // Navigate to the login page with extras
    this.router.navigate(['/login']);
    return false;
  }
}