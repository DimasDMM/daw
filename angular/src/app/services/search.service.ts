import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import 'rxjs/Rx';

import { Category } from '../entity/category.entity';
import { URL_API } from "../shared/config.object";
import {SessionService} from "./session.service";
import {Ad} from "../entity/ad.entity";

@Injectable()
export class SearchService {

  constructor(private http:Http) {
    console.log("Init SearchService")
  }

//Resultado búsqueda
  public getSearch(search:string, page:number) {
    let url = URL_API+"/buscar?page="+page+"&search="+search;
    return this.http.get(url).map(
      response => response.json()
    );
  }
}
