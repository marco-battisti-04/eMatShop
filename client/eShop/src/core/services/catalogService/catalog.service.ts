import { HttpClient } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CatalogService {

  readonly #uri: string = "http://10.36.28.31:9999/";

  readonly #http = inject(HttpClient);
  readonly #productList = signal<any[]>([]) //da aggiungere che sia un interfaccia prodotto
  readonly productListComp = computed<any[]>(()=>this.#productList()) //da aggiungere che sia un interfaccia prodotto


  constructor() {
    this.getProducts()
  }

  /**
   * get product
  */
  public getProducts() {
    this.#http.get(/*`${this.#uri}catalog`*/"https://fakestoreapi.com/products").subscribe((res) => {
      this.#productList.set(res as [])
    })
  }
  public getProductByID(idProduct: any) {
    return this.#productList().find(p => p.id === idProduct)
  }
}
