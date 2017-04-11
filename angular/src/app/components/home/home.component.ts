import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import {ArticleService} from "../../services/article.service";

import {Article} from "../../entity/article.entity";
import {User} from "../../entity/user.entity";
import {ArticleFavourites} from "../../entity/article_favourites";

import {URL_IMAGES} from "../../shared/config.service";
import {EventSession} from "../base/event_session.component";
import {SessionService} from "../../services/session.service";


@Component({
  selector: 'app',
  templateUrl: 'home.component.html',
  styleUrls: []
})
export class HomeComponent extends EventSession implements OnInit {

  public urlImages = URL_IMAGES;
  public carrousel:Article[] = [];
  public sectionFavourite:ArticleFavourites;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private articleService:ArticleService, protected sessionService:SessionService) {super(sessionService)}

  ngOnInit() {
    this.carrouselArticles();
    this.sectionFavourites();
  }

  private carrouselArticles() {
    this.articleService.carrousel().subscribe(
      response => this.carrousel = response,
      error => console.error(error)
    );
  }

  private sectionFavourites() {
    this.articleService.favourites().subscribe(
      response => this.sectionFavourite = response,
      error => console.error(error)
    );
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {
    this.sectionFavourites();
  }
}
