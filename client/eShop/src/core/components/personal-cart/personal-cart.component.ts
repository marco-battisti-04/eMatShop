import { FormsModule, ReactiveFormsModule, NgModel } from '@angular/forms';
import { Component, inject, model } from '@angular/core';
import { CartService } from '../../services/cartService/cart.service';
@Component({
  selector: 'app-personal-cart',
  imports: [FormsModule],
  templateUrl: './personal-cart.component.html',
  styleUrl: './personal-cart.component.scss'
})
export class PersonalCartComponent{
  pay() {
    
  }
  readonly service = inject(CartService)
  cvv =model<string>();
  cardNumber =model<string>();
  expirationDate = model<string>();
  constructor(){
    this.service.getCart()
  }
}
