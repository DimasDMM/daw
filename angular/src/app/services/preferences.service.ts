import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import 'rxjs/Rx';

import {URL_API} from "../shared/config.object";
import {SessionService} from "./session.service";
import {User} from "../entity/user.entity";

@Injectable()
export class PreferencesService {

  constructor(
    private http:Http,
    private sessionService:SessionService
  ) {
    console.log("Init PreferencesService")
  }

  // Preferencias
  public getPreferences(user:User) {
    if(!user.id) return;

    let headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/ajustes";
    return this.http.get(url, options).map(
      response => response.json()
    );
  }

  // Guardar usuario
  public savePreferences(user:User) {
    if(!user.id) return;

    let headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/ajustes";
    return this.http.put(url, user, options).map(
      response => response.json()
    );
  }
}
