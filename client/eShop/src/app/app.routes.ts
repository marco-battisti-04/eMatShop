import { Routes } from '@angular/router';
import { LoginComponent } from '../core/components/login/login.component';
import { HomepageComponent } from '../core/components/homepage/homepage.component';
import { ProductCardCompleteComponent } from '../core/components/product-card-complete/product-card-complete.component';
import { PersonalCartComponent } from '../core/components/personal-cart/personal-cart.component';
import { AuthGuard } from '../core/guard/auth.guard';

export const routes: Routes = [
  {
    path:'',
    // canActivate: [AuthGuard],
    component: HomepageComponent
  },
  {
    path:'access',
    component: LoginComponent
  },
  {
    path:'details/:index',
    component: ProductCardCompleteComponent
  },
  {
    path:'cart',
    // canActivate: [AuthGuard],
    component: PersonalCartComponent
  }
];
