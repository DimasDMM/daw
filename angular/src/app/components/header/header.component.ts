import {Component, OnInit} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import {ArticleService} from "../../services/article.service";
import {Article} from "../../entity/article.entity";
import {Category} from "../../entity/category.entity";

@Component({
  selector: 'header',
  templateUrl: 'header.component.html'
})
export class HeaderComponent implements OnInit {
  public categories:Category[] = [];
  public last_articles:Article[] = [];
  public dateNow:Date;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private articleService:ArticleService,
  ) {}

  ngOnInit() {
    this.categories = this.articleService.getCategories();
    this.dateNow = new Date();
  }

}
