import { HttpClient } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';
import { UserService } from '../userService/userService.service';
import { CatalogService } from '../catalogService/catalog.service';

// Interfaccia per il prodotto nel carrello
interface CartItem {
  id: string;
  name: string;
  price: number;
  quantity: number;
}

@Injectable({
  providedIn: 'root'
})
export class CartService {
  readonly #uri: string = "http://192.168.1.19:9999/";

  readonly #http = inject(HttpClient);
  readonly #cartList = signal<any>({}); // Modifica il tipo in base ai dati reali del carrello
  readonly cartListComp = computed<any>(() => this.#cartList());
  readonly #serviceUser = inject(UserService);
  readonly #serviceCatalog = inject(CatalogService);

  #userID: string = "";

  constructor() {
    this.#serviceCatalog.getProducts();
    this.#serviceUser.verify().subscribe(user => {
      this.#userID = user.userId;
    });
  }

  public getCart() {
    const token = this.#serviceUser.returnToken();
    if (!token) {
      console.error('Token mancante');
      return;
    }

    this.#http.get<any>(`${this.#uri}cart/me`, {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
      withCredentials: false  // solo se il backend usa i cookie/sessione
    }).subscribe(resp=>{
        this.#cartList.set(resp);
        console.log(resp);
    });
  }

  public addCart(idProduct: string, quantity = 1) {
    const token = this.#serviceUser.returnToken();
    if (!token) {
      console.error('Token mancante');
      return;
    }

    this.#http.put<CartItem[]>(`${this.#uri}cart/${this.#userID}/${idProduct}/${quantity}`, {}, {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
      withCredentials: false  // solo se il backend usa i cookie/sessione
    })
    .subscribe({
      next: (resp) => {
        console.log(resp);
      },
      error: (err) => {
        if (err.status === 401) {
          console.error('Token scaduto o non valido');
          // Gestisci il rinnovo del token o la logica di logout
        } else {
          console.error('Errore nella richiesta:', err);
        }
      }
    });
  }
}
