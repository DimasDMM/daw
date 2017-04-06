import {Component, OnInit} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import {ArticleService} from "../../services/article.service";
import {Article} from "../../entity/article.entity";
import {Category} from "../../entity/category.entity";
import {Http} from "@angular/http";
import {URL_IMAGES} from "../../shared/config.service";

@Component({
  selector: 'header',
  templateUrl: 'header.component.html'
})
export class HeaderComponent implements OnInit {

  public urlImages = URL_IMAGES;

  public categories:Category[] = [];
  public last_articles = {};
  public dateNow:Date;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private http: Http,
    private articleService: ArticleService,
  ) {}

  ngOnInit() {
    this.categories = this.articleService.getCategories();

    let that = this;
    this.categories.forEach(function (category) {
      that.last_articles[ category.id ] = {};
      that.getArticleService().getArticlesFromCategory( category.id, 1, 10 ).subscribe(
        articles => that.last_articles[ category.id ] = articles,
        error => console.error(error)
      );
    });

    this.dateNow = new Date();
  }

  getArticleService() {
    return this.articleService;
  }
}
