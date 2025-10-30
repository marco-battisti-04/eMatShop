import { CanActivate, Router } from '@angular/router';
import { UserService } from '../services/userService/userService.service';
import { inject, Injectable } from '@angular/core';
@Injectable({
  providedIn:'root'
})

export class AuthGuard implements CanActivate{
  #service = inject(UserService);
  #router = inject(Router);

  async canActivate() {
    let data = await this.#service.verify()
    data.subscribe((resp)=>{
      if(resp.userId == null){
        this.#router.navigate(['access'], {fragment:'login'})
    return false;
      }else{
        return true
      }
    })
    return false;
  }
};
