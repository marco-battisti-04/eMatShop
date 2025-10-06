import { Injectable, signal, inject, computed } from '@angular/core';
import { UserResponseLogin, UserResponseRegister } from '../../model/user.model';
import { HttpClient } from '@angular/common/http';
import { catchError, of, retry } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  readonly #userID= signal<string>("");
  readonly userIDComp = computed(() => this.#userID());

  readonly #uri : string = "http://10.36.28.31:9999/";

  readonly #http = inject(HttpClient);

  constructor() {}

  public register(email:string, name:string, surname:string, password:string) : UserResponseRegister{
    this.#http.post(this.#uri+"user/register", {
      email,
      name,
      surname,
      password
    })
    .pipe(
      retry(3),
      catchError((err) => {
        console.error(err);
        return of(null);
      }),
    )
    .subscribe(resp=>{
      console.log(resp)
    })
    try {
      //prova a registrarti
    } catch (e) {
      // riprova oppure altro
    }

    //nel caso funzioni restituisci la roba del register
    return {
      userId:"",
      name:"",
      surname:"",
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
