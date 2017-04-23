import {Http} from "@angular/http";
import {Component, OnInit} from '@angular/core';

import {URL_IMAGES} from "../../shared/config.object";

import {Article} from "../../entity/article.entity";
import {BaseSessionComponent} from "../base/base-session.component";

import {ArticleService} from "../../services/article.service";
import {SessionService} from "../../services/session.service";


@Component({
  selector: 'recent_news',
  templateUrl: 'recent_news.component.html',
  styleUrls: []
})

export class RecentNewsComponent extends BaseSessionComponent implements OnInit {

  ngOnInit() {
    super.ngOnInit();
  }

  public articles: Article[];
  public urlImages = URL_IMAGES;

  constructor(
    private http: Http,
    private articleService:ArticleService,
    sessionService:SessionService
  ) { super(sessionService) }

  protected onLoginCalls() {}
  protected onReloginCalls() {}
  protected onLogoutCalls() {}
}
