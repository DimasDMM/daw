import { RouterModule } from '@angular/router';

import {HomeComponent} from './components/home/home.component';
import {CategoryComponent} from "./components/category/category.component";
import {PrivacyComponent} from "./components/privacy/privacy.component";
import {SearchComponent} from "./components/search/search.component";
import {TermsAndConditionsComponent} from "./components/terms-and-conditions/terms-and-conditions.component";

import {AdministratorListComponent} from "./components/administrator/list/list.component";
import {AdministratorFormComponent} from "./components/administrator/form/form.component";
import {ArticleComponent} from "./components/article/article.component";
import {PublicistListComponent} from "./components/publicist/list/list.component";
import {PublicistFormComponent} from "./components/publicist/form/form.component";
import {EditorListComponent} from "./components/editor/list/list.component";
import {EditorFormComponent} from "./components/editor/form/form.component";
import {PreferencesComponent} from "./components/preferences/preferences.component";
import {RegisterComponent} from "./components/register/register.component";
import {ErrorComponent} from "./components/error/error.component";

const appRoutes = [
  { path: 'portada', component: HomeComponent },
  { path: 'categoria/:categoryId', component: CategoryComponent},
  { path: 'buscar', component: SearchComponent },
  { path: 'privacidad', component: PrivacyComponent },
  { path: 'terminos-y-condiciones', component: TermsAndConditionsComponent },
  { path: 'registro', component: RegisterComponent },

  { path: 'articulo/:id', component: ArticleComponent },

  { path: 'administrador', redirectTo: '/administrador/usuarios', pathMatch: 'full' },
  { path: 'administrador/usuarios', component: AdministratorListComponent },
  { path: 'administrador/usuarios/:id', component: AdministratorFormComponent },

  { path: 'publicista', redirectTo: '/publicista/anuncios', pathMatch: 'full' },
  { path: 'publicista/anuncios', component: PublicistListComponent },
  { path: 'publicista/anuncios/:id', component: PublicistFormComponent },

  { path: 'editor', redirectTo: '/editor/articulos', pathMatch: 'full' },
  { path: 'editor/articulos', component: EditorListComponent },
  { path: 'editor/articulos/:id', component: EditorFormComponent },

  { path: 'ajustes', component: PreferencesComponent },

  { path: '', redirectTo: 'portada', pathMatch: 'full' },

  {path: 'error', component: ErrorComponent},
  {path: 'error/:error', component: ErrorComponent},
  {path: '**', redirectTo: '/error/404'}
];

export const routing = RouterModule.forRoot(appRoutes);
