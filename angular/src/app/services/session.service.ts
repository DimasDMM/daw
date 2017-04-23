import {Injectable, setTestabilityGetter} from "@angular/core";
import {Http, RequestOptions, Headers, Response} from "@angular/http";
import {URL_API} from "../shared/config.object";
import {User} from "../entity/user.entity";
import {Favourite} from "../entity/favourite.entity";

@Injectable()
export class SessionService {
  private session:string;
  private userLogged:User;

  constructor(private http:Http) {
    console.log("Init SessionService");

    // Recuperar usuario logeado
    if(localStorage.getItem("userLogged") && localStorage.getItem("session")) {
      this.userLogged = JSON.parse( localStorage.getItem("userLogged") );
      this.session = localStorage.getItem("session");
    }
  }

  /*
   * ReLogin
   */
  public relogin() {
    let headers = new Headers({ 'Authorization': this.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/login";
    return this.http.get(url, options).map(
      response => this.onLogin( this.getAuthHeader(), response )
    );
  }

  /*
   * Login
   */

  // Iniciar sesion
  public login(username:string, password:string) {
    let headers = new Headers({ 'Authorization': this.generateAuthHeader(username, password) });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/login";
    return this.http.get(url, options).map(
      response => this.onLogin( this.generateAuthHeader(username, password), response)
    );
  }

  // Metodo ejecutado cuando se logea
  private onLogin(authHeader:string, response) {
    let user = response.json();

    if(user != null) {
      this.setAuthHeader( authHeader );
      this.setUserLogged( user );
    }

    return response.json();
  }

  /*
   * Registro
   */

  // Registrar nuevo usuario
  public signupStep1(name:string, lastname:string, email:string, password1:string, password2:string, terms:boolean) {
    let headers = new Headers();
    let options = new RequestOptions({ headers: headers });

    let data = {
      'name': name,
      'lastname': lastname,
      'email': email,
      'password1': password1,
      'password2': password2,
      'terms': terms
    };

    let url = URL_API+"/registro";
    return this.http.post(url, data, options).map(
      response => this.onSignup(data, response)
    );
  }

  // Metodo ejecutado cuando se logea
  private onSignup(data:any, response) {
    let user:User = response.json();

    if(user != null) {
      this.setAuthHeader( this.generateAuthHeader( data.email, data.password1 ) );
      this.setUserLogged( user );
    }

    return response.json();
  }

  /*
   * Logout
   */

  // Cerrar sesion
  public logout() {
    if(!this.isUserLogged()) return;

    let headers = new Headers({ 'Authorization': this.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/logout";
    return this.http.get(url, options).map(
      response => this.onLogout(response)
    );
  }

  // Metodo ejecutado cuando se cierra sesion
  private onLogout(response:any) {
    this.clearAuthHeader();
    this.clearUserLogged();
    return response.json();
  }

  /*
   * Otras funciones
   */

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
  public setAuthHeader(authHeader:string) {
    this.session = authHeader;
    localStorage.setItem("session", this.session);
  }

  // Generar cabecera Auth-basic a partir de un usuario y contraseña
  private generateAuthHeader(user:string, password:string) {
    let session = user + ':' + password;
    session = "Basic " + btoa(session);
    return session;
  }

  // Limpiar cabecera Auth-basic
  private clearAuthHeader() {
    localStorage.removeItem("session");
    this.session = null;
  }

  // Limpiar usuario logeado
  private clearUserLogged() {
    localStorage.removeItem("userLogged");
    this.userLogged = null;
  }

  // Verificar roles
  public hasRole(roles:string[], user?:User) {
    user = (user == null ? this.userLogged : user);

    if(user == null) return false;

    for(let j = 0; j < roles.length; j++)
      for(let i = 0; i < user.roles.length; i++)
        if(user.roles[i] == roles[j])
          return true;

    return false;
  }
}
