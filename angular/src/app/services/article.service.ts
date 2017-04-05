import {Injectable, OnInit} from '@angular/core';
import { Category } from '../entity/category.entity';

@Injectable()
export class ArticleService implements OnInit {

  private categories = [
    { id:"espana", name:"España" }
  ];

  ngOnInit() {
    console.log("## "+ this.categories);
  }

  public getCategories() {
    return this.categories;
  }

  public getArticlesFromCategory(number:number) {
    return [];
  }

}
