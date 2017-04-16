import {Component, OnInit} from '@angular/core';

import {Ad} from "../../entity/ad.entity";
import {Article} from "../../entity/article.entity";
import {ArticleFavourites} from "../../entity/article_favourites";

import {URL_IMAGES} from "../../shared/config.service";
import {BaseSessionComponent} from "../base/base-session.component";
import {ArticleService} from "../../services/article.service";
import {SessionService} from "../../services/session.service";
import {AdsService} from "../../services/ads.service";


@Component({
  selector: 'app',
  templateUrl: 'home.component.html',
  styleUrls: []
})
export class HomeComponent extends BaseSessionComponent implements OnInit {

  public urlImages = URL_IMAGES;

  public lastArticlesId = 0;
  public lastArticles:Article[] = [];

  public articlesFavourite:ArticleFavourites;
  public articlesPopular:Article[] = [];
  public articlesRandom:Article[] = [];
  public articlesLastWeek:Article[] = [];
  public adBanner:Ad;

  constructor(
    private articleService:ArticleService,
    private adsService:AdsService,
    sessionService:SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();
    console.log("Init Home");
    this.sectionFavourites();
    this.sectionPopularLastWeek();
    this.sectionLastArticles();
    this.sectionAds();
    this.sectionArticlesRandom();
    this.sectionArticlesLastWeek();
  }

  // Anuncio aleatorio
  private sectionAds() {
    this.adsService.getRandom().subscribe(
      response => this.onLoadAd(response),
      error => console.error(error)
    );
  }

  private onLoadAd(ad:Ad) {
    this.adBanner = ad;
    this.adsService.addView(ad.id);
  }

  // Varios articulos al azar
  private sectionArticlesRandom() {
    this.articleService.getArticlesRandom(4).subscribe(
      response => this.articlesRandom = response,
      error => console.error(error)
    );
  }

  // Varios articulos al azar
  private sectionArticlesLastWeek() {
    this.articleService.getArticlesLastWeek().subscribe(
      response => this.articlesLastWeek = response,
      error => console.error(error)
    );
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
    this.articleService.getArticlesFavourites().subscribe(
      response => this.articlesFavourite = response,
      error => console.error(error)
    );
  }

  // Carga articulos para seccion de articulos mas leidos ultima semana
  private sectionPopularLastWeek() {
    this.articleService.getPopularLastWeek().subscribe(
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
