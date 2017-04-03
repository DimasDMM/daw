import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './components/home/home.component';

const appRoutes = [
  { path: '', redirectTo: 'portada', pathMatch: 'full' },
  { path: 'portada', component: HomeComponent }
]

export const routing = RouterModule.forRoot(appRoutes);
