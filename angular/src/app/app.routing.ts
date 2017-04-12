import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './components/home/home.component';
import {PrivacyComponent} from "./components/privacy/privacy.component";
import {TermsAndConditionsComponent} from "./components/terms-and-conditions/terms-and-conditions.component";

const appRoutes = [
  { path: '', redirectTo: 'portada', pathMatch: 'full' },
  { path: 'portada', component: HomeComponent },
  { path: 'privacidad', component: PrivacyComponent },
  { path: 'terminos-y-condiciones', component: TermsAndConditionsComponent }
]

export const routing = RouterModule.forRoot(appRoutes);
