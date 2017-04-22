import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import 'rxjs/Rx';

import {URL_API} from "../shared/config.object";
import {SessionService} from "./session.service";
import {Ad} from "../entity/ad.entity";

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

  // Obtener anunciante concreto
  public getAd(id:number) {
    let headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/publicista/anuncio/"+id;
    return this.http.get(url, options).map(
      response => response.json()
    );
  }

  // Eliminar anunciante
  public deleteAd(ad:Ad) {
    let headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/publicista/anuncio/"+ad.id;
    return this.http.delete(url, options).map(
      response => response
    );
  }

  // Guardar anunciante
  public saveAd(ad:Ad) {
    let headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    if(ad.id) {
      let url = URL_API+"/publicista/anuncio/"+ad.id;
      return this.http.put(url, ad, options).map(
        response => response.json()
      );
    } else {
      let url = URL_API+"/publicista/anuncio/nuevo";
      return this.http.post(url, ad, options).map(
        response => response.json()
      );
    }
  }

  // Guardar imagen
  public saveImage(ad:Ad, file: File) {
    let headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let formData:FormData = new FormData();
    formData.append('file', file, file.name);

    let url = URL_API+"/publicista/anuncio/"+ad.id+"/imagen";

    return this.http.post(url, formData, options).map(
      response => response.json()
    );
  }
}
