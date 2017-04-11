/*
 * Eventos basicos de la aplicacion
 */

import {SessionService} from "../../services/session.service";
import {User} from "../../entity/user.entity";

export abstract class EventSession {

  public userLogged:User;

  constructor(protected sessionService:SessionService) {}

  public onLogin() {
    console.log("# Event Login");
    this.userLogged = this.sessionService.getUserLogged();
    this.onLoginCalls();
  }

  // Despues de haber realizado el login, se llamara a este metodo
  // Por ejemplo, si hay que llamar a algun metodo de un componente hijo hay que sobreescribir este metodo
  protected abstract onLoginCalls();
}
