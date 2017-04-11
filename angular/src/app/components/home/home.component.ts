import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import {ArticleService} from "../../services/article.service";

import {Article} from "../../entity/article.entity";
import {User} from "../../entity/user.entity";
import {ArticleFavourites} from "../../entity/article_favourites";

import {URL_IMAGES} from "../../shared/config.service";


@Component({
  selector: 'app',
  templateUrl: 'home.component.html',
  styleUrls: []
})
export class HomeComponent implements OnInit {

  public urlImages = URL_IMAGES;
  public userLogged:User;
  public carrousel:Article[] = [];
  public sectionFavourite:ArticleFavourites;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private articleService:ArticleService) {}

  ngOnInit() {
    this.initCarrouselArticles();
    this.initSectionFavourites();
  }

  private initCarrouselArticles() {
    this.articleService.carrousel().subscribe(
      response => this.carrousel = response,
      error => console.error(error)
    );
  }

  private initSectionFavourites() {
    this.articleService.favourites(this.userLogged).subscribe(
      response => this.sectionFavourite = response,
      error => console.error(error)
    );
  }
}
