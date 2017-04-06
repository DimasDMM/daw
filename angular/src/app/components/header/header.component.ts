import {Component, OnInit} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import {ArticleService} from "../../services/article.service";
import {Article} from "../../entity/article.entity";
import {Category} from "../../entity/category.entity";
import {Http} from "@angular/http";

@Component({
  selector: 'header',
  templateUrl: 'header.component.html'
})
export class HeaderComponent implements OnInit {
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

    this.categories.forEach(function (category) {
      //this.last_articles[ category.id ] = {};
      console.log("# "+ category.id);

      let tempArticles = this.articleService.getArticlesFromCategory( category.id, 1, 9 );
      /*
      this.articleService.getArticlesFromCategory( category.id, 1, 9 ).subscribe(
        articles => console.log(articles),
        error => console.error(error)
      );*/
    });

    this.dateNow = new Date();
  }
}
