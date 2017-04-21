import {Http} from "@angular/http";
import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import {URL_IMAGES} from "../../shared/config.object";

import {Article} from "../../entity/article.entity";
import {Category} from "../../entity/category.entity";
import {BaseSessionComponent} from "../base/base-session.component";

import {ArticleService} from "../../services/article.service";
import {SessionService} from "../../services/session.service";


@Component({
  selector: 'category',
  templateUrl: 'category.component.html',
  styleUrls: []
})
export class CategoryComponent extends BaseSessionComponent implements OnInit {

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
    super.ngOnInit();
    this.activatedRoute.params.subscribe(res => {
      this.category = this.articleService.getCategoryById(res['categoryId']);
      this.articleService.getArticlesFromCategory(res['categoryId'], 1,10).subscribe(
        articles => this.articles = articles,
        error => console.error(error)
      );
    });
    console.log("Init Category"+this.category);
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onLogoutCalls() {}
}
