import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import {Article} from "../../entity/article.entity";
import {ArticleFavourites} from "../../entity/article_favourites";

import {URL_IMAGES} from "../../shared/config.service";
import {EventSessionComponent} from "../base/event-session.component";
import {CommentService} from "../../services/comment.service";
import {ArticleService} from "../../services/article.service";
import {SessionService} from "../../services/session.service";


@Component({
  selector: 'app',
  templateUrl: 'home.component.html',
  styleUrls: []
})
export class HomeComponent extends EventSessionComponent implements OnInit {

  public urlImages = URL_IMAGES;

  public lastArticlesId = 0;
  public lastArticles:Article[] = [];

  public articlesFavourite:ArticleFavourites;
  public articlesPopular:Article[] = [];

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private articleService:ArticleService,
    sessionService:SessionService
  ) { super(sessionService) }

  ngOnInit() {
    console.log("# Init Home");
    this.sectionFavourites();
    this.sectionPopularLastWeek();
    this.sectionLastArticles();
  }

  // Ultimos articulos publicados
  private sectionLastArticles() {
    this.articleService.getLastArticles(10).subscribe(
      response => this.lastArticles = response,
      error => console.error(error)
    );
  }

  // Carga articulos para seccion de favoritos
  private sectionFavourites() {
    this.articleService.favourites().subscribe(
      response => this.articlesFavourite = response,
      error => console.error(error)
    );
  }

  // Carga articulos para seccion de articulos mas leidos ultima semana
  private sectionPopularLastWeek() {
    this.articleService.popularLastWeek().subscribe(
      response => this.articlesPopular = response,
      error => console.error(error)
    );
  }

  /*
   * Efectos
   */
  public lastArticlesNext() {
    this.lastArticlesId++;
    if(this.lastArticlesId >= this.lastArticles.length) this.lastArticlesId = 0;
  }

  public lastArticlesPrevious() {
    this.lastArticlesId--;
    if(this.lastArticlesId <= 0) this.lastArticlesId = this.lastArticles.length;
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {
    this.sectionFavourites();
  }

  protected onLogoutCalls() {
    this.sectionFavourites();
  }
}
