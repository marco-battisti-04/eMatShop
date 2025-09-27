import { Routes } from '@angular/router';
import { LoginComponent } from '../core/components/login/login.component';
import { HomepageComponent } from '../core/components/homepage/homepage.component';
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
  }
];
