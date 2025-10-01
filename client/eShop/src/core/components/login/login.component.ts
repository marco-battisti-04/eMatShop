import { Component, computed, inject, signal, Type } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../services/userService/userService.service';

@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent{

  passwordVisible : boolean = false;
  readonly #loginRegister = signal<boolean>(true);
  loginRegisterComp = computed<boolean>(() => this.#loginRegister());

  #URL = inject(ActivatedRoute)
  #ROUTER = inject(Router)
  #service = inject(UserService)

  visibility :string = "notVisible";

  ngOnInit(){
    this.#URL.fragment.subscribe(f => {
      f == "login" || f == "register" ? f == "login" ? this.#loginRegister.set(false) : this.#loginRegister.set(true) : this.#ROUTER.navigate([""])
    });
  }

  public visibilityActionIcons : any = {
    "visible":{
      "icon":"M20 14.8335C21.3082 13.3317 22 12 22 12C22 12 18.3636 5 12 5C11.6588 5 11.3254 5.02013 11 5.05822C10.6578 5.09828 10.3244 5.15822 10 5.23552M12 9C12.3506 9 12.6872 9.06015 13 9.17071C13.8524 9.47199 14.528 10.1476 14.8293 11C14.9398 11.3128 15 11.6494 15 12M3 3L21 21M12 15C11.6494 15 11.3128 14.9398 11 14.8293C10.1476 14.528 9.47198 13.8524 9.1707 13C9.11386 12.8392 9.07034 12.6721 9.04147 12.5M4.14701 9C3.83877 9.34451 3.56234 9.68241 3.31864 10C2.45286 11.1282 2 12 2 12C2 12 5.63636 19 12 19C12.3412 19 12.6746 18.9799 13 18.9418",
      "type":"text"
    },
    "notVisible":{
      "icon":"M21.821 12.43c-.083-.119-2.062-2.944-4.793-4.875-1.416-1.003-3.202-1.555-5.028-1.555-1.825 0-3.611.552-5.03 1.555-2.731 1.931-4.708 4.756-4.791 4.875-.238.343-.238.798 0 1.141.083.119 2.06 2.944 4.791 4.875 1.419 1.002 3.205 1.554 5.03 1.554 1.826 0 3.612-.552 5.028-1.555 2.731-1.931 4.71-4.756 4.793-4.875.239-.342.239-.798 0-1.14zm-9.821 4.07c-1.934 0-3.5-1.57-3.5-3.5 0-1.934 1.566-3.5 3.5-3.5 1.93 0 3.5 1.566 3.5 3.5 0 1.93-1.57 3.5-3.5 3.5zM14 13c0 1.102-.898 2-2 2-1.105 0-2-.898-2-2 0-1.105.895-2 2-2 1.102 0 2 .895 2 2z",
      "type":"password"
    }
  }

  public changeVisibility() : void{
    this.passwordVisible = !this.passwordVisible;

    if(this.passwordVisible){
      this.visibility = "visible"
    }else{
      this.visibility = "notVisible"
    }
  }

  email : string = ""
  name : string = ""
  surname : string = ""
  pwd : string = ""
  confirmPwd : string = ""

  public register(){
    if(this.pwd!=this.confirmPwd && this.pwd.trim() != "")
      return

    this.#service.register(this.email,this.name,this.surname,this.pwd)
  }

}
