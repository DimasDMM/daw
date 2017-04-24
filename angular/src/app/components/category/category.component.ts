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
  private page = 1;
  private lastPage:boolean;

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
      this.page = 1;
      this.category = this.articleService.getCategoryById(res['categoryId']);
      this.articleService.getArticlesFromCategory(res['categoryId'], 1,10).subscribe(
        articles => {this.articles = articles.content,
                    this.lastPage = articles.last},
        error => console.error(error)
    );
    });
    console.log("Init Category"+this.category);
  }

  private loadMoreArticles(){
    this.articleService.getArticlesFromCategory(this.category.id, this.page,10).subscribe(
      response => {
          let articlesResponse = response.content;

          for(let i = 0; i < articlesResponse.length; i++)
            this.loadNumberComments(articlesResponse[i]);

          this.articles = this.articles.concat(articlesResponse),
          this.lastPage = response.last;
        },
        error => console.error(error)
    );
  }

  private moreResults(){
    this.page=this.page+1;
    this.loadMoreArticles();
    console.log("lastPage",this.lastPage);
  }

  // Numero de comentarios en articulo
  public loadNumberComments(article:Article) {
    article.nComments = 0;
    this.articleService.getNumberComments(article).subscribe(
      response => article.nComments = response.nComments,
      error => console.log(error)
    );
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onReloginCalls() {}
  protected onLogoutCalls() {}
}
