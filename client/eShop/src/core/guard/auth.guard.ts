import { CanActivate, Router } from '@angular/router';
import { UserService } from '../services/userService/userService.service';
import { inject, Injectable } from '@angular/core';
@Injectable({
  providedIn:'root'
})

export class AuthGuard implements CanActivate{
  #service = inject(UserService);
  #router = inject(Router);

  canActivate() {
    if(localStorage.getItem('token') != "" && localStorage.getItem('token')){
      return true;
    }
    this.#router.navigate(['access'], {fragment:'login'})
    return false;
  }
};
