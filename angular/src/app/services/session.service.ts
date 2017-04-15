import {Injectable} from "@angular/core";
import {Http, RequestOptions, Headers, Response} from "@angular/http";
import {URL_API} from "../shared/config.service";
import {User} from "../entity/user.entity";

@Injectable()
export class SessionService {
  private session:string;
  private userLogged:User;

  constructor(private http:Http) {
    console.log("# Init SessionService");

    // Recuperar usuario logeado
    if(localStorage.getItem("userLogged") && localStorage.getItem("session")) {
      this.userLogged = JSON.parse( localStorage.getItem("userLogged") );
      this.session = localStorage.getItem("session");
    }
  }

  public login(username:string, password:string) {
    let headers = new Headers({ 'Authorization': this.generateAuthHeader(username, password) });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/login";
    return this.http.get(url, options).map(
      response => this.onLogin(username, password, response)
    );
  }

  public logout() {
    if(!this.isUserLogged()) return;

    let headers = new Headers({ 'Authorization': this.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/logout";
    return this.http.get(url, options).map(
      response => this.onLogout()
    );
  }

  // Devuelve true si hay una sesion iniciada
  public isUserLogged() {
    return (this.userLogged != null);
  }

  // Devuelve usuario logeado
  public getUserLogged() {
    return this.userLogged;
  }

  // Establecer usuario logeado
  public setUserLogged(userLogged: User) {
    localStorage.setItem("userLogged", JSON.stringify(userLogged));
    this.userLogged = userLogged;
  }

  // Obtener cabecera Auth-basic
  public getAuthHeader() {
    return this.session;
  }

  // Establecer cabecera Auth-basic
  public setAuthHeader(user:string, password:string) {
    this.session = this.generateAuthHeader(user, password);
    localStorage.setItem("session", this.session);
  }

  // Generar cabecera Auth-basic a partir de un usuario y contraseña
  private generateAuthHeader(user:string, password:string) {
    let session = user + ':' + password;
    session = "Basic " + btoa(session);
    return session;
  }

  // Metodo ejecutado cuando se logea
  private onLogin(username:string, password:string, response: Response) {
    let user = response.json();

    if(user != null) {
      this.setAuthHeader( username, password );
      this.setUserLogged( user );
    }
  }

  // Metodo ejecutado cuando se cierra sesion
  private onLogout() {
    this.clearAuthHeader();
    this.clearUserLogged();
  }

  // Limpiar cabecera Auth-basic
  private clearAuthHeader() {
    localStorage.removeItem("session");
    this.session = null;
  }

  // Limpiar usuario logeado
  private clearUserLogged() {
    localStorage.removeItem("userLogged");
    this.session = null;
  }
}
