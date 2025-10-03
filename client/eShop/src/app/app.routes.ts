import { Routes } from '@angular/router';
import { LoginComponent } from '../core/components/login/login.component';
import { HomepageComponent } from '../core/components/homepage/homepage.component';
import { ProductCardCompleteComponent } from '../core/components/product-card-complete/product-card-complete.component';

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
    path:'details',
    component: ProductCardCompleteComponent
  }
];
