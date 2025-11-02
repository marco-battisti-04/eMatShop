import { Injectable, signal, inject, computed } from '@angular/core';
import { UserResponseLogin, UserResponseRegister } from '../../model/user.model';
import { HttpClient } from '@angular/common/http';
import { catchError, of, retry } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  readonly #user = signal<any>({
    id: "",
  });
  readonly userComp = computed(() => this.#user());
  readonly #uri: string = "http://192.168.1.19:9999/";
  readonly #http = inject(HttpClient);
  readonly #router = inject(Router)
  constructor() { }

  public register(email: string, name: string, surname: string, password: string): UserResponseRegister {
    this.#http.post(this.#uri + "user/register", {
      "email": email,
      "name": name,
      "surname": surname,
      "password": password
    }, {
      headers: {
        'Content-Type': 'application/json'
      },
      withCredentials: false  // solo se il backend usa i cookie/sessione
    })
      .subscribe(resp => {
        console.log(resp)
      })
    //nel caso funzioni restituisci la roba del register
    return {
      userId: "",
      name: "",
      surname: "",
      email: ""
    }
  }

  public login(email: string, password: string): UserResponseLogin {
    let tmp = ""
    this.#http.post<UserResponseLogin>(this.#uri + "user/login", { email, password })
      .subscribe(resp => {
        localStorage.setItem("token", resp.token)
        tmp = resp.token
      });

    return { token: tmp }
  }

  returnToken() {
    const token = localStorage.getItem('token') || '';
    return token;
  }

  verify() {
    const token = localStorage.getItem('token') || '';
    const headers = { 'Authorization': `Bearer ${token}` };
    if (token == '')
      this.#router.navigate(['/access'], { fragment: 'login' });

    return this.#http.get<any>(this.#uri + 'user/me', { headers })
      // .subscribe(resp => { this.#user.set(resp) })
  }

}
