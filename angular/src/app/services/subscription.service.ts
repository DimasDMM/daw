import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import 'rxjs/Rx';

import {URL_API} from "../shared/config.object";
import {SessionService} from "./session.service";
import {User} from "../entity/user.entity";

@Injectable()
export class SubscriptionService {

  constructor(
    private http:Http,
    private sessionService:SessionService
  ) {
    console.log("Init SubscriptionService")
  }

  // Guardar subscripcion
  public saveSubscription(email:string) {
    let headers = new Headers({ 'Authorization': this.sessionService.getAuthHeader() });
    let options = new RequestOptions({ headers: headers });

    let url = URL_API+"/subscripcion";
    return this.http.post(url, {'email':email}, options).map(
      response => response.json()
    );
  }
}
