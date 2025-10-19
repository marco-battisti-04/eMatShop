import { HttpClient } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';
import { UserService } from '../userService/userService.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  readonly #uri: string = "http://192.168.1.19:9999/";

  readonly #http = inject(HttpClient);
  readonly #cartList = signal<any[]>([]) //da aggiungere che sia un interfaccia prodotto
  readonly cartListComp = computed<any[]>(()=>this.#cartList()) //da aggiungere che sia un interfaccia prodotto
  readonly #serviceUser = inject(UserService);
  constructor() { }
  public addToCart(){
    console.log(localStorage.getItem('token'))
    this.#http.get(`${this.#uri}cart/${}`, {

    }, {
      headers: {
        'Content-Type': 'application/json'
      },
      withCredentials: false  // solo se il backend usa i cookie/sessione
    })
    .subscribe(resp => {
      console.log(resp)
    })
  }
}
