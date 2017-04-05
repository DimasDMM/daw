import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from './app.component';
import {HeaderComponent} from "./components/header/header.component";
import { HomeComponent } from './components/home/home.component';

import { routing } from './app.routing';
import {ArticleService} from "./services/article.service";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent
  ],
  imports: [
    NgbModule.forRoot(),
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [
    ArticleService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
