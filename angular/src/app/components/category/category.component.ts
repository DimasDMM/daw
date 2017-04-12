import {Http} from "@angular/http";
import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import {URL_IMAGES} from "../../shared/config.service";

import {Article} from "../../entity/article.entity";
import {Category} from "../../entity/category.entity";
import {EventSessionComponent} from "../base/event-session.component";

import {ArticleService} from "../../services/article.service";
import {SessionService} from "../../services/session.service";


@Component({
  selector: 'category',
  templateUrl: 'category.component.html',
  styleUrls: []
})
export class CategoryComponent extends EventSessionComponent implements OnInit {

  public urlImages = URL_IMAGES;
  public category:Category;
  public articles: Article[];

  constructor(
    private http: Http,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private articleService:ArticleService,
    sessionService:SessionService
  ) { super(sessionService) }

  ngOnInit() {
    console.log("# Init Category");
    this.category = this.articleService.getCategoryById( this.activatedRoute.snapshot.params['categoryId'] );

    this.articleService.getArticlesFromCategory(this.category.id, 1,10).subscribe(
      articles => this.articles = articles,
      error => console.error(error)
    );
  }


  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onLogoutCalls() {}
}
