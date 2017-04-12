import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import {ArticleService} from "../../services/article.service";

import {Article} from "../../entity/article.entity";
import {User} from "../../entity/user.entity";
import {ArticleFavourites} from "../../entity/article_favourites";

import {URL_IMAGES} from "../../shared/config.service";
import {EventSessionComponent} from "../base/event_session.component";
import {SessionService} from "../../services/session.service";

@Component({
  selector: 'app',
  templateUrl: 'privacy.component.html',
  styleUrls: []
})
export class PrivacyComponent extends EventSessionComponent implements OnInit {

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private articleService:ArticleService, protected sessionService:SessionService) {super(sessionService)}

  ngOnInit() {}

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
}
