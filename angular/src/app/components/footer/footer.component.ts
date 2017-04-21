import {Component, OnInit} from "@angular/core";

import {ArticleService} from "../../services/article.service";
import {SessionService} from "../../services/session.service";

import {URL_IMAGES} from "../../shared/config.object";
import {Category} from "../../entity/category.entity";
import {BaseSessionComponent} from "../base/base-session.component";

@Component({
  selector: 'app-footer',
  templateUrl: 'footer.component.html'
})
export class FooterComponent extends BaseSessionComponent implements OnInit {

  public urlImages = URL_IMAGES;

  public categories:Category[] = [];
  public last_articles = {};
  public dateNow:Date;

  constructor(
    private articleService: ArticleService,
    sessionService: SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();
    console.log("Init Footer");

    this.categories = this.articleService.getCategories();

    this.articleService.getLastArticles(5).subscribe(
      response => this.last_articles = response,
      error => console.error(error)
    );

    this.dateNow = new Date();
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onReloginCalls() {}
  protected onLogoutCalls() {}
}
