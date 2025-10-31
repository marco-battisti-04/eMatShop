import { cardDTO } from './../../model/card.model';
import { HttpClient } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';
import { UserService } from '../userService/userService.service';
import { CartService } from '../cartService/cart.service';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  readonly #uri: string = "http://192.168.1.19:9999/";

  readonly #http = inject(HttpClient);
  readonly #cartList = signal<any>({}) //da aggiungere che sia un interfaccia prodotto
  readonly cartListComp = computed<any>(()=>this.#cartList()) //da aggiungere che sia un interfaccia prodotto
  readonly #serviceUser = inject(UserService);
  readonly #serviceCart = inject(CartService);

  readonly #card = signal<cardDTO>({
    cardNumber:"",
    cvv:"",
    expirationDate:""
  })
  constructor() {}
  pay(){

  }
}
