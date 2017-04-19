import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import 'rxjs/Rx';

import {URL_API} from "../shared/config.object";
import {SessionService} from "./session.service";
import {Article} from "../entity/article.entity";

@Injectable()
export class EditorService {

  constructor(
    private http:Http,
    private sessionService:SessionService
  ) {
    console.log("Init EditorService")
  }

  // Obtener lista de articulos
  public getArticlesList(page:number) {
    let headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/editor/articulos?page="+page;
    return this.http.get(url, options).map(
      response => response.json()
    );
  }

  // Obtener articulo concreto
  public getArticle(id:number) {
    let headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/editor/articulo/"+id;
    return this.http.get(url, options).map(
      response => response.json()
    );
  }

  public getArticlePreview(id:number) {
    let headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/editor/articulo/"+id+"?preview=true";
    return this.http.get(url, options).map(
      response => response.json()
    );
  }

  // Guardar articulo
  public saveArticle(article:Article) {
    let headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    if(article.id) {
      let url = URL_API+"/editor/articulo/"+article.id;
      return this.http.put(url, article, options).map(
        response => response.json()
      );
    } else {
      let url = URL_API+"/editor/articulo/nuevo";
      return this.http.post(url, article, options).map(
        response => response.json()
      );
    }
  }
}
