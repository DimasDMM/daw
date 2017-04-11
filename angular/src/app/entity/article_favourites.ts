import {Article} from "./article.entity";

export class ArticleFavourites {
  public id:string;
  public name:string;
  public articles:Article[] = [];
}
