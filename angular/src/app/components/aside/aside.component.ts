import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import {Article} from "../../entity/article.entity";
import {ArticleFavourites} from "../../entity/article_favourites";

import {URL_IMAGES} from "../../shared/config.service";
import {EventSessionComponent} from "../base/event-session.component";
import {CommentService} from "../../services/comment.service";
import {ArticleService} from "../../services/article.service";
import {SessionService} from "../../services/session.service";
import {Category} from "../../entity/category.entity";


@Component({
  selector: 'aside',
  templateUrl: 'aside.component.html',
  styleUrls: []
})
export class AsideComponent extends EventSessionComponent implements OnInit {

  public urlImages = URL_IMAGES;

  public categories:Category[] = [];
  public lastComments:Comment[] = [];

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private articleService:ArticleService,
    private commentService:CommentService,
    sessionService:SessionService
  ) { super(sessionService) }

  ngOnInit() {
    console.log("# Init Aside");
    this.sectionCategories();
    this.sectionLastComments();
  }

  // Ultimos articulos publicados
  private sectionCategories() {
    this.categories = this.articleService.getCategories();
  }

  // Ultimos comentarios publicados
  private sectionLastComments() {
    this.commentService.getLastComments(3).subscribe(
      response => this.lastComments = response,
      error => console.error(error)
    );
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onLogoutCalls() {}
}
