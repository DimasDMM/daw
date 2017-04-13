import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import 'rxjs/Rx';

import { URL_API } from "../shared/config.service";
import {SessionService} from "./session.service";

@Injectable()
export class CommentService {

  constructor(private http:Http, private sessionService:SessionService) {
    console.log("# Init CommentService")
  }

  // Ultimos articulos publicados
  public getLastComments(number:number) {
    let url = URL_API+"/comentarios?number="+number;
    return this.http.get(url).map(
      response => response.json()
    );
  }
}
