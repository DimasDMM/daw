import {Component, OnInit, ViewChild, ElementRef, Output, EventEmitter} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";

import {AsideOptionsComponent} from "../aside-options/aside-options.component";
import {SessionService} from "../../services/session.service";

import {BaseSessionComponent} from "../base/base-session.component";
import {User} from "../../entity/user.entity";
import {AdministratorService} from "../../services/administrator.service";
import {MessageObject} from "../../shared/message.object";
import {PreferencesService} from "../../services/preferences.service";
import {HeaderComponent} from "../header/header.component";

@Component({
  selector: 'app',
  templateUrl: 'preferences.component.html'
})
export class PreferencesComponent extends BaseSessionComponent implements OnInit {

  // Elementos HTML
  @ViewChild('appHeader') appHeader: HeaderComponent;
  @ViewChild('appAsideOptions') appAsideOptions: AsideOptionsComponent;

  private optionActiveStr = "preferences";
  private fUser:User;

  private tab = {
    "personalData": {"in":true, "active":true},
    "password": {"active":false},
    "favourites": {"active":false},
    "photo": {"active":false}
  };


  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private preferencesService: PreferencesService,
    sessionService: SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();
    if(!this.hasRole(["ROLE_USER"]))
      this.router.navigate(['/']);

    console.log("Init PreferencesComponent");

    this.preferencesService.getPreferences().subscribe(
      response => this.fUser = response,
      error => console.log(error)
    );
  }

  // Cambiar a otra pestaña
  private tabSwitch(tab:string) {
    if(this.tab[tab] == null) return;
    this.closeAll();
    this.tab[tab]["active"] = true;
    this.tab[tab]["in"] = true;
    console.log("Tab Switch: "+ tab);
  }

  private closeAll() {
    for (let k in this.tab) {
      this.tab[k]["active"] = false;
      this.tab[k]["in"] = false;
    }
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onReloginCalls() {
    this.appHeader.onRelogin();
    this.appAsideOptions.onRelogin();
  }
  protected onLogoutCalls() {
    this.router.navigate(['/']);
  }
}
