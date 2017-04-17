import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import 'rxjs/Rx';

import {URL_API} from "../shared/config.object";
import {SessionService} from "./session.service";

@Injectable()
export class PublicistService {

  constructor(
    private http:Http,
    private sessionService:SessionService
  ) {
    console.log("Init PublicistService")
  }

  // Obtener lista de anuncios
  public getAdsList(page:number) {
    let headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/publicista/anuncios?page="+page;
    return this.http.get(url, options).map(
      response => response.json()
    );
  }
}
