/*
 * Componentes de Angular y librerias adicionales
 */
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

/*
 * Componentes de la aplicacion
 */
import { AppComponent } from './app.component';
import {HeaderComponent} from "./components/header/header.component";
import {FooterComponent} from "./components/footer/footer.component";
import {AsideComponent} from "./components/aside/aside.component";
import {AsideOptionsComponent} from "./components/aside-options/aside-options.component";

import {AdministratorListComponent} from "./components/administrator/list/list.component";

import { HomeComponent } from './components/home/home.component';
import {NgbdCarouselBasic} from "./components/home/carrousel.component";
import {CategoryComponent} from "./components/category/category.component";
import {PrivacyComponent} from "./components/privacy/privacy.component";
import {TermsAndConditionsComponent} from "./components/terms-and-conditions/terms-and-conditions.component";

/*
 * Servicios, pipes y componentes adicionales
 */
import { routing } from './app.routing';
import {ArticleService} from "./services/article.service";
import {SessionService} from "./services/session.service";
import {CommentService} from "./services/comment.service";
import {AdsService} from "./services/ads.service";
import {AdministratorService} from "./services/administrator.service";

import {WindowRef} from "./shared/window.service";
import {toText} from "./shared/toText.pipe";
import {listRolesToText} from "./shared/listRolesToText.pipe";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    AsideComponent,
    AsideOptionsComponent,

    AdministratorListComponent,

    HomeComponent,
    CategoryComponent,
    PrivacyComponent,
    TermsAndConditionsComponent,
    NgbdCarouselBasic,
    toText,
    listRolesToText
  ],
  imports: [
    NgbModule.forRoot(),
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [
    WindowRef,
    SessionService,
    ArticleService,
    CommentService,
    AdsService,
    AdministratorService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
