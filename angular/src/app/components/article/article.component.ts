import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from "@angular/router";
import { Http } from "@angular/http";
import { SessionService } from "app/services/session.service";

import { Article } from "app/entity/article.entity";
import {Category} from "../../entity/category.entity";
import { URL_IMAGES } from "app/shared/config.object";

import {ArticleService} from "../../services/article.service";
import { BaseSessionComponent } from "../base/base-session.component";
import { MessageObject } from "app/shared/message.object";

@Component({
  selector: 'app',
  templateUrl: 'article.component.html',
  styleUrls: []
})
export class ArticleComponent extends BaseSessionComponent implements OnInit {

private article : Article;
private articleLoading = false;
private urlImages = URL_IMAGES;
private message : MessageObject;

  constructor(
    private http: Http,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private articleService:ArticleService,
    sessionService:SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();

    this.sectionArticle();

  }

  private sectionArticle() {
    let id = this.activatedRoute.snapshot.params['id'];

    this.articleLoading=true;
    this.articleService.getArticleByID(id).subscribe(
      article => this.articleSuccess(article),
      error => console.log(error)
    );
  }

private articleSuccess(response:any){
    this.article = response;
    this.articleLoading = !this.articleLoading;
    console.log(this.article);
}

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onReloginCalls() {}
  protected onLogoutCalls() {}
}
