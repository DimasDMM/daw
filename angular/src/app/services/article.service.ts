import {Injectable, OnInit} from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/Rx';

import { Category } from '../entity/category.entity';
import { URL_API } from "../shared/config.service";
import { Article } from "../entity/article.entity";

@Injectable()
export class ArticleService {

  private categories:Category[] = [
    { id:"madrid",     name:"Madrid" },
    { id:"espana",     name:"España" },
    { id:"mundo",      name:"Mundo" },
    { id:"deportes",   name:"Deportes" },
    { id:"tecnologia", name:"Tecnologia" },
    { id:"cultura",    name:"Cultura" }
  ];

  constructor(private http:Http) {
    console.log("# Init ArticleService")
  }

  public getCategories() {
    return this.categories;
  }

  public getArticlesFromCategory(id:string, page:number, number:number) {
    let url = URL_API+"/categoria/"+id+"?page="+page+"&number="+number;
    return this.http.get(url).map(
      response => response.json().content.map( article => article )
    );
  }

  public carrousel() {
    let url = URL_API+"/carrousel";
    return this.http.get(url).map(
      response => response.json()
    );
  }
}
