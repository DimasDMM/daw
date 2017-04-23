import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import 'rxjs/Rx';

import { Category } from '../entity/category.entity';
import { URL_API } from "../shared/config.object";
import {SessionService} from "./session.service";
import {Article} from "../entity/article.entity";

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

  constructor(private http:Http, private sessionService:SessionService) {
    console.log("Init ArticleService")
  }

  public getCategories() {
    return this.categories;
  }

  public getCategoryById(id:string):Category {
    for(let i = 0; i < this.categories.length; i++) {
      if(this.categories[i].id == id)
        return this.categories[i];
    }

    return null;
  }

  public getArticleByID(id:number){
    let url = URL_API+"/articulo/"+id;
    return this.http.get(url).map(
      response => response.json()
    );
  }

  // Ultimos articulos publicados
  public getLastArticles(number:number) {
    let url = URL_API+"/articulos?number="+number;
    return this.http.get(url).map(
      response => response.json()
    );
  }

  // Populares durante la ultima semana
  public getPopularLastWeek() {
    let url = URL_API+"/articulos/popular";
    return this.http.get(url).map(
      response => response.json()
    );
  }

  // Devuelve varios articulos al azar
  public getArticlesRandom(number:number) {
    let url = URL_API+"/articulos/aleatorio?number="+number;
    return this.http.get(url).map(
      response => response.json()
    );
  }

  // Devuelve varios articulos al azar
  public getArticlesLastWeek() {
    let url = URL_API+"/articulos/semana";
    return this.http.get(url).map(
      response => response.json()
    );
  }

  // Ultimos articulos publicados en una categoria determinada. Las paginas empiezan en 1
  public getArticlesFromCategory(id:string, page:number, number:number) {
    let url = URL_API+"/articulos/"+id+"?page="+page+"&number="+number;
    return this.http.get(url).map(
      response => response.json()
    );
  }

  // Articulos para el articlesCarrousel
  public getCarrousel() {
    let url = URL_API+"/articulos/carrousel";
    return this.http.get(url).map(
      response => response.json()
    );
  }

  // Devuelve ultimas noticias en categoria de favoritos
  public getArticlesFavourites() {
    let headers, options;

    // Verificar si esta logeado
    if(this.sessionService.isUserLogged()) {
      headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    } else {
      headers = new Headers({});
    }

    options = new RequestOptions({ headers: headers });

    let url = URL_API+"/articulos/favoritos";
    return this.http.get(url, options).map(
      response => response.json()
    );
  }

  // Publicar un comentario en un articulo
  saveComment(article: Article, comment: string) {
    let headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/articulo/"+article.id+"/comentarios";
    return this.http.post(url, {'comment':comment}, options).map(
      response => response.json()
    );
  }

  // Devuelve los comentarios de un articulo
  public getCommentsFromArticle(article:Article, page:number) {
    let headers = new Headers();
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/articulo/"+article.id+"/comentarios?page="+page;
    return this.http.get(url, options).map(
      response => response.json()
    );
  }
}
