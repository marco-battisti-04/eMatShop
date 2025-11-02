import { FormsModule, ReactiveFormsModule, NgModel } from '@angular/forms';
import { Component, inject, model } from '@angular/core';
import { CartService } from '../../services/cartService/cart.service';
import { PaymentService } from '../../services/paymentService/payment.service';
@Component({
  selector: 'app-personal-cart',
  imports: [FormsModule],
  templateUrl: './personal-cart.component.html',
  styleUrl: './personal-cart.component.scss'
})
export class PersonalCartComponent{
  readonly service = inject(CartService)
  readonly #payService = inject(PaymentService)
  cvv =model<string>();
  cardNumber =model<string>();
  expirationDate = model<string>();
  constructor(){
    this.service.getCart()
  }
  pay() {
    this.#payService.pay({
      "cvv":""+this.cvv,
      "cardNumber":""+this.cardNumber,
      "expirationDate":""+this.expirationDate
    })
  }
}
