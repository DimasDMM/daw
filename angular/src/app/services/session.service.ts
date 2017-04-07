import {Injectable} from "@angular/core";
import {Http, RequestOptions, Headers, Response} from "@angular/http";
import {URL_API} from "../shared/config.service";
import {User} from "../entity/user.entity";

@Injectable()
export class SessionService {
  private session:string;
  private userLogged:User;

  constructor(private http:Http) {}

  login(user:string, password:string) {
    let headers = new Headers({ 'Authorization': this.generateAuthHeader(user, password) });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/login";
    return this.http.get(url, options).map(
      response => SessionService.extractUser(response)
    );
  }

  getUserLogged() {
    return this.userLogged;
  }

  setUserLogged(userLogged: User) {
    this.userLogged = userLogged;
  }

  getAuthHeader() {
    return this.session;
  }

  setAuthHeader(user:string, password:string) {
    this.session = this.generateAuthHeader(user, password);
  }

  generateAuthHeader(user:string, password:string) {
    let session = user + ':' + password;
    session = "Basic " + btoa(session);
    return session;
  }

  private static extractUser(response: Response) {
    return response.json();
  }
}
