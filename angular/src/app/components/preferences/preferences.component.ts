import {Component, OnInit, ViewChild, ElementRef, Output, EventEmitter} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";

import {AsideOptionsComponent} from "../aside-options/aside-options.component";
import {SessionService} from "../../services/session.service";

import {BaseSessionComponent} from "../base/base-session.component";
import {User} from "../../entity/user.entity";
import {AdministratorService} from "../../services/administrator.service";

@Component({
  selector: 'app',
  templateUrl: 'preferences.component.html'
})
export class PreferencesComponent extends BaseSessionComponent implements OnInit {

  @ViewChild('appAsideOptions') appAsideOptions: AsideOptionsComponent;

  private optionActiveStr = "preferences";
  public tab = {
    "personalData": {"in":true, "active":true},
    "password": {"active":false},
    "favourites": {"active":false},
    "photo": {"active":false}
  };


  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private administratorService: AdministratorService,
    sessionService: SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();
    if(!this.hasRole(["ROLE_USER"]))
      this.router.navigate(['/']);

    console.log("Init PreferencesComponent");
  }

  // Cambiar a otra pestaña
  public tabSwitch(tab:string) {
    if(this.tab[tab] == null) return;
    this.closeAll();
    this.tab[tab]["active"] = true;
    this.tab[tab]["in"] = true;
    console.log("Tab Switch: "+ tab);
  }

  public closeAll() {
    for (let k in this.tab) {
      this.tab[k]["active"] = false;
      this.tab[k]["in"] = false;
    }
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onLogoutCalls() {
    this.router.navigate(['/']);
  }
}
