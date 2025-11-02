import { HttpClient } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';
import { UserService } from '../userService/userService.service';
import { CatalogService } from '../catalogService/catalog.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  readonly #uri: string = "http://192.168.1.19:9999/";

  readonly #http = inject(HttpClient);
  readonly #cartList = signal<any>({}) //da aggiungere che sia un interfaccia prodotto
  readonly cartListComp = computed<any>(()=>this.#cartList()) //da aggiungere che sia un interfaccia prodotto
  readonly #serviceUser = inject(UserService);
  readonly #serviceCatalog = inject(CatalogService);

  #userID : string = ""

  constructor() {
    this.#serviceCatalog.getProducts()
    this.#serviceUser.verify().subscribe(user=>{
      this.#userID = user.userId
    })
  }
  public getCart(){
    this.#http.get(`${this.#uri}cart/mycart`, {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.#serviceUser.returnToken()}`
      },
      withCredentials: false  // solo se il backend usa i cookie/sessione
    })
    .subscribe(resp => {
      this.#cartList.set(resp)
      console.log(resp)
    })
  }
  public addCart(idProduct : string, quantity=1){

    this.#http.put(`${this.#uri}cart/${this.#serviceUser.returnToken()}/${idProduct}/${quantity}`, {
      headers: {
        'Content-Type': 'application/json'
      },
      withCredentials: false  // solo se il backend usa i cookie/sessione
    })
    .subscribe(resp => {
      this.#cartList.set(resp)
      console.log(resp)
    })
  }
}
