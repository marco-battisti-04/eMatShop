import { Injectable, signal, inject, computed } from '@angular/core';
import { UserResponseLogin, UserResponseRegister } from '../../model/user.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  readonly #userID= signal<string>("");
  readonly userIDComp = computed(() => this.#userID());

  readonly #uri : string = "";

  readonly #http = inject(HttpClient);

  constructor() {}

  public register(email:string, nome:string, cognome:string, password:string) : UserResponseRegister{

    try {
      //prova a registrarti
    } catch (e) {
      // riprova oppure altro
    }

    //nel caso funzioni restituisci la roba del register
    return {
      userId:"",
      nome:"",
      cognome:"",
      email:""
    }
  }

  public login(email:string, password:string) : UserResponseLogin{

    try {
      //prova a registrarti
    } catch (e) {
      // riprova oppure altro
    }

    //nel caso funzioni restituisci la roba del register
    return {
      token:""
    }
  }
}
