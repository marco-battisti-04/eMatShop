import { Component, computed, inject, input, OnInit, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CatalogService } from '../../services/catalogService/catalog.service';

@Component({
  selector: 'app-product-card-complete',
  imports: [],
  templateUrl: './product-card-complete.component.html',
  styleUrl: './product-card-complete.component.scss'
})
export class ProductCardCompleteComponent implements OnInit {
  #router = inject(ActivatedRoute)
  #routerNav = inject(Router)
  #service = inject(CatalogService)
  #prodotto = signal<any>({})
  prodottoComp = computed<any>(()=>this.#prodotto())

  constructor(){
    this.#service.getProducts()
  }

  ngOnInit(){
    if(this.#service.productListComp().length==0)
      this.#routerNav.navigate([""])
    let tmp;
    this.#service.productListComp().forEach((element, index) => {
      if(this.#router.snapshot.params['index'] ==  element.id){
        tmp = element
        return;
      }
    });
    this.#prodotto.set(tmp)
  }
  addToCart(){
    
  }
}
