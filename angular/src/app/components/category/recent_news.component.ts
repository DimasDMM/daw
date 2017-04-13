import {Http} from "@angular/http";
import {Component, OnInit} from '@angular/core';

import {URL_IMAGES} from "../../shared/config.service";

import {Article} from "../../entity/article.entity";
import {EventSessionComponent} from "../base/event-session.component";

import {ArticleService} from "../../services/article.service";
import {SessionService} from "../../services/session.service";


@Component({
  selector: 'recent_news',
  templateUrl: 'recent_news.component.html',
  styleUrls: []
})

export class RecentNewsComponent extends EventSessionComponent implements OnInit {


  ngOnInit(): void {
  }

  public articles: Article[];
  public urlImages = URL_IMAGES;

  constructor(
    private http: Http,
    private articleService:ArticleService,
    sessionService:SessionService
  ) { super(sessionService) }

  protected onLoginCalls() {  }

  protected onLogoutCalls() {  }
}
