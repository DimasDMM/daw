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

import {toText} from "./shared/toText.pipe";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    AsideComponent,

    HomeComponent,
    CategoryComponent,
    PrivacyComponent,
    TermsAndConditionsComponent,
    NgbdCarouselBasic,
    toText
  ],
  imports: [
    NgbModule.forRoot(),
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [
    SessionService,
    ArticleService,
    CommentService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
