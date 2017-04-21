/*
 * Componentes de Angular y librerias adicionales
 */
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { Ng2SimplePageScrollModule } from "ng2-simple-page-scroll";

/*
 * Componentes de la aplicacion
 */
import { AppComponent } from './app.component';
import {HeaderComponent} from "./components/header/header.component";
import {FooterComponent} from "./components/footer/footer.component";
import {AsideComponent} from "./components/aside/aside.component";
import {AsideOptionsComponent} from "./components/aside-options/aside-options.component";
import {PaginationComponent} from "./components/pagination/pagination.component";

import {AdministratorListComponent} from "./components/administrator/list/list.component";
import {AdministratorFormComponent} from "./components/administrator/form/form.component";
import {PublicistListComponent} from "./components/publicist/list/list.component";
import {PublicistFormComponent} from "./components/publicist/form/form.component";
import {EditorListComponent} from "./components/editor/list/list.component";
import {EditorFormComponent} from "./components/editor/form/form.component";
import {PreferencesComponent} from "./components/preferences/preferences.component";
import {SearchComponent} from "./components/search/search.component";

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
import {MessageService} from "./services/message.service";
import { SearchService } from "app/services/search.service";

import {AdministratorService} from "./services/administrator.service";
import {PublicistService} from "./services/publicist.service";
import {EditorService} from "./services/editor.service";

import {WindowRef} from "./shared/window.service";
import {pipeToText} from "./shared/toText.pipe";
import {pipeListRolesToText} from "./shared/listRolesToText.pipe";
import {pipeInfinOnEmpty} from "./shared/infinOnEmpty.pipe";
import {pipeAdStatus} from "./components/publicist/list/ad-status.pipe";
import { pipeArticleStatus } from "./components/editor/list/article-status.pipe";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    AsideComponent,
    AsideOptionsComponent,
    PaginationComponent,

    AdministratorListComponent,
    AdministratorFormComponent,
    PublicistListComponent,
    PublicistFormComponent,
    EditorListComponent,
    EditorFormComponent,
    PreferencesComponent,
    SearchComponent,

    HomeComponent,
    NgbdCarouselBasic,
    CategoryComponent,
    PrivacyComponent,
    TermsAndConditionsComponent,

    pipeToText,
    pipeInfinOnEmpty,
    pipeListRolesToText,
    pipeAdStatus,
    pipeArticleStatus
  ],
  imports: [
    Ng2SimplePageScrollModule.forRoot(),
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
    MessageService,
    SearchService,

    AdministratorService,
    PublicistService,
    EditorService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
