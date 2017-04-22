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
  public getPreferences() {
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

  // Guardar contraseña
  public savePassword(oldPassword:string, newPassword1:string, newPassword2:string) {
    let headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let vars = {
      'pass_now': oldPassword,
      'pass_new': newPassword1,
      'pass_new2': newPassword2
    };

    let url = URL_API+"/ajustes/contrasena";
    return this.http.put(url, vars, options).map(
      response => response.json()
    );
  }

  // Guardar imagen
  public saveImage(file: File) {
    let headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let formData:FormData = new FormData();
    formData.append('file', file, file.name);

    let url = URL_API+"/ajustes/imagen";

    return this.http.post(url, formData, options).map(
      response => response.json()
    );
  }
}
