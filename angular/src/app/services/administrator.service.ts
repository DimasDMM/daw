import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import 'rxjs/Rx';

import {URL_API} from "../shared/config.service";
import {SessionService} from "./session.service";

@Injectable()
export class AdministratorService {

  constructor(
    private http:Http,
    private sessionService:SessionService
  ) {
    console.log("Init AdministratorService")
  }

  // Obtener usuario concreto
  public getUser(id:number) {
    let headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/administrador/usuario/"+id;
    return this.http.get(url, options).map(
      response => response.json()
    );
  }

  // Obtener lista de usuarios
  public getUserList(page:number) {
    let headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/administrador/usuarios?page="+page;
    return this.http.get(url, options).map(
      response => response.json()
    );
  }
}
