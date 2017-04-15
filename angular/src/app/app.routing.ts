import { RouterModule } from '@angular/router';

import {HomeComponent} from './components/home/home.component';
import {CategoryComponent} from "./components/category/category.component";
import {PrivacyComponent} from "./components/privacy/privacy.component";
import {TermsAndConditionsComponent} from "./components/terms-and-conditions/terms-and-conditions.component";

import {AdministratorListComponent} from "./components/administrator/list/list.component";

const appRoutes = [
  { path: 'portada', component: HomeComponent },
  { path: 'categoria/:categoryId', component: CategoryComponent},
  { path: 'privacidad', component: PrivacyComponent },
  { path: 'terminos-y-condiciones', component: TermsAndConditionsComponent },

  { path: 'administrador', redirectTo: '/administrador/usuarios', pathMatch: 'full' },
  { path: 'administrador/usuarios', component: AdministratorListComponent },

  { path: '', redirectTo: 'portada', pathMatch: 'full' }
];

export const routing = RouterModule.forRoot(appRoutes);
