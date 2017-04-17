import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import 'rxjs/Rx';

import { Category } from '../entity/category.entity';
import { URL_API } from "../shared/config.object";
import {SessionService} from "./session.service";
import {Ad} from "../entity/ad.entity";

@Injectable()
export class AdsService {

  // Para no sumar visitas 'de mas' se guarda en un array
  // los identificadores de los anuncios a los que se ha sumado la visita
  private adView = {};

  constructor(private http:Http) {
    console.log("Init AdsService")
  }

  // Obtener datos de un anuncio en concreto
  public getAd(id:number) {
    let url = URL_API+"/anuncio/"+id;
    return this.http.get(url).map(
      response => response.json()
    );
  }

  // Anuncio al azar
  public getRandom() {
    let url = URL_API+"/anuncio";
    return this.http.get(url).map(
      response => response.json()
    );
  }

  // Sumar visita
  public addView(id:number) {
    if(this.adView[id]) return;

    this.adView[id] = true;

    let url = URL_API+"/anuncio/"+id+"/visualizacion";
    this.http.get(url).subscribe();
  }

  // Sumar click
  public addClick(id:number) {
    let url = URL_API+"/anuncio/"+id+"/click";
    this.http.get(url).subscribe();
  }
}
