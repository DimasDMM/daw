import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import {ArticleService} from "../../services/article.service";
import {Article} from "../../entity/article.entity";
import {Http} from "@angular/http";


@Component({
  selector: 'category',
  templateUrl: 'category.component.html',
  styleUrls: []
})
export class CategoryComponent{

  concreteCategory:string;
  articleList: Article[];

  ngOnInit() {
    this.articleService.getArticlesFromCategory(this.concreteCategory, 1,10).subscribe(
      articles=>this.articleList=articles,
      error=>console.error(error)
    );
  }

  constructor(private http: Http,private router: Router, private activatedRoute: ActivatedRoute, private articleService:ArticleService) {
    this.concreteCategory = activatedRoute.snapshot.params['category.id'];
  }

}
