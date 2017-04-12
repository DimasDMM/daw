import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './components/home/home.component';
import {CategoryComponent} from "./components/category/category.component";

const appRoutes = [
  { path: '', redirectTo: 'portada', pathMatch: 'full' },
  {path: 'categoria/:category.id', component: CategoryComponent},
  { path: 'portada', component: HomeComponent }
]

export const routing = RouterModule.forRoot(appRoutes);
