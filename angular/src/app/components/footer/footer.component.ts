import {Component, OnInit} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import {Http} from "@angular/http";

import {ArticleService} from "../../services/article.service";
import {SessionService} from "../../services/session.service";

import {URL_IMAGES} from "../../shared/config.service";
import {Category} from "../../entity/category.entity";


@Component({
  selector: 'app-footer',
  templateUrl: 'footer.component.html'
})
export class FooterComponent implements OnInit {

  public urlImages = URL_IMAGES;

  public categories:Category[] = [];
  public last_articles = {};
  public dateNow:Date;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private http: Http,
    private articleService: ArticleService,
    private sessionService: SessionService
  ) {}

  ngOnInit() {
    console.log("# Init Footer");
    this.categories = this.articleService.getCategories();

    this.articleService.getLastArticles(5).subscribe(
      response => this.last_articles = response,
      error => console.error(error)
    );

    this.dateNow = new Date();
  }

  private loginSuccess() {}
}
