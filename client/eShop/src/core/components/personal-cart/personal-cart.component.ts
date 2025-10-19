import { Component, inject } from '@angular/core';
import { CartService } from '../../services/cartService/cart.service';

@Component({
  selector: 'app-personal-cart',
  imports: [],
  templateUrl: './personal-cart.component.html',
  styleUrl: './personal-cart.component.scss'
})
export class PersonalCartComponent {
  readonly service = inject(CartService)
  constructor(){
    this.service.getCart()
  }
}
