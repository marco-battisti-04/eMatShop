import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CatalogService {

  readonly #uri: string = "http://10.36.28.31:9999/";

  readonly #http = inject(HttpClient);
  readonly productList = signal<any>({}) //da aggiungere che sia un interfaccia prodotto


  constructor() {
    this.getProducts()
  }

  /**
   * get product
  */
  public getProducts() {
    this.#http.get(/*`${this.#uri}catalog`*/"https://fakestoreapi.com/products").subscribe(res => {
      console.log(res)
    })
  }
  getProductByID(idProduct: any) {
    this.#http.get(/*`${this.#uri}catalog`*/`https://fakestoreapi.com/products/${idProduct}`).subscribe(res => {
      console.log(res)
    })
  }
}
