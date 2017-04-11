import {Injectable} from "@angular/core";
import {Http, RequestOptions, Headers, Response} from "@angular/http";
import {URL_API} from "../shared/config.service";
import {User} from "../entity/user.entity";

@Injectable()
export class SessionService {
  private session:string;
  private userLogged:User;

  constructor(private http:Http) {}

  public login(username:string, password:string) {
    let headers = new Headers({ 'Authorization': this.generateAuthHeader(username, password) });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/login";
    return this.http.get(url, options).map(
      response => this.onLogin(username, password, response)
    );
  }

  public isUserLogged() {
    return (this.userLogged != null);
  }

  public getUserLogged() {
    return this.userLogged;
  }

  public setUserLogged(userLogged: User) {
    this.userLogged = userLogged;
  }

  public getAuthHeader() {
    return this.session;
  }

  public setAuthHeader(user:string, password:string) {
    this.session = this.generateAuthHeader(user, password);
  }

  private generateAuthHeader(user:string, password:string) {
    let session = user + ':' + password;
    session = "Basic " + btoa(session);
    return session;
  }

  private onLogin(username:string, password:string, response: Response) {
    let user = response.json();

    if(user != null) {
      this.setAuthHeader( username, password );
      this.setUserLogged( user );
    }

  }
}
