import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import 'rxjs/Rx';

import {URL_API} from "../shared/config.object";
import {SessionService} from "./session.service";

@Injectable()
export class EditorService {

  constructor(
    private http:Http,
    private sessionService:SessionService
  ) {
    console.log("Init EditorService")
  }

  // Obtener lista de anuncios
  public getArticlesList(page:number) {
    let headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/editor/articulos?page="+page;
    return this.http.get(url, options).map(
      response => response.json()
    );
  }
}
