import { Component, computed, inject, input, signal } from '@angular/core';
import { CatalogService } from '../../services/catalogService/catalog.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-card-brief',
  imports: [],
  templateUrl: './product-card-brief.component.html',
  styleUrl: './product-card-brief.component.scss'
})
export class ProductCardBriefComponent {
  idProduct = input<any>(0)

  #prod = signal<any>({});
  prodComputed = computed<any>(()=>this.#prod())

  #service = inject(CatalogService)
  #router = inject(Router)

  ngOnInit(){
    
  }
  details() {
    this.#router.navigate(['/details'])
  }
}
