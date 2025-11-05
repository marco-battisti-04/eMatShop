import { Component, computed, inject, input, OnInit, signal } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CatalogService } from '../../services/catalogService/catalog.service';
import { CartService } from '../../services/cartService/cart.service';

@Component({
  selector: 'app-product-card-complete',
  imports: [RouterLink],
  templateUrl: './product-card-complete.component.html',
  styleUrl: './product-card-complete.component.scss'
})
export class ProductCardCompleteComponent implements OnInit {
  #router = inject(ActivatedRoute)
  #routerNav = inject(Router)
  #serviceCatalog = inject(CatalogService)
  #serviceCart = inject(CartService)
  #prodotto = signal<any>({})
  prodottoComp = computed<any>(()=>this.#prodotto())

  constructor(){
    this.#serviceCatalog.getProducts()
  }

  ngOnInit(){
    if(this.#serviceCatalog.productListComp().length==0)
      this.#routerNav.navigate([""])
    let tmp;
    this.#serviceCatalog.productListComp().forEach((element, index) => {
      if(this.#router.snapshot.params['index'] ==  element.id){
        tmp = element
        return;
      }
    });
    this.#prodotto.set(tmp)
  }
  addToCart(){
    this.#serviceCart.addCart(this.#router.snapshot.params['index'], 1)
  }
}
