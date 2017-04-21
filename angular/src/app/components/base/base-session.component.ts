/*
 * Eventos basicos de la aplicacion
 */

import {SessionService} from "../../services/session.service";
import {User} from "../../entity/user.entity";
import {OnInit} from "@angular/core";

export abstract class BaseSessionComponent implements OnInit {

  protected userLogged:User;

  constructor(protected sessionService:SessionService) {}

  ngOnInit() {
    this.userLogged = this.sessionService.getUserLogged();
  }

  public hasRole(roles:string[]) {
    if(this.userLogged == null) return false;

    for(let j = 0; j < roles.length; j++)
      for(let i = 0; i < this.userLogged.roles.length; i++)
        if(this.userLogged.roles[i] == roles[j])
          return true;

    return false;
  }

  public onLogin() {
    this.userLogged = this.sessionService.getUserLogged();
    this.onLoginCalls();
  }

  public onRelogin() {
    this.userLogged = this.sessionService.getUserLogged();
    this.onReloginCalls();
  }

  public onLogout() {
    this.onLogoutCalls();
  }

  // Despues de haber realizado el login o logout, se llamara a este metodo
  // Por ejemplo, si hay que llamar a algun metodo de un componente hijo hay que sobreescribir este metodo
  protected abstract onLoginCalls();
  protected abstract onReloginCalls();
  protected abstract onLogoutCalls();
}
