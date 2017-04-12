import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './components/home/home.component';
import {PrivacyComponent} from "./components/privacy/privacy.component";

const appRoutes = [
  { path: '', redirectTo: 'portada', pathMatch: 'full' },
  { path: 'portada', component: HomeComponent },
  { path: 'privacidad', component: PrivacyComponent }
]

export const routing = RouterModule.forRoot(appRoutes);
