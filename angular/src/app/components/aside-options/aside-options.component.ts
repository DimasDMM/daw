import {Component, OnInit, Input, EventEmitter, Output} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";

import {SessionService} from "../../services/session.service";
import {BaseSessionComponent} from "../base/base-session.component";
import {URL_IMAGES} from "../../shared/config.object";

@Component({
  selector: 'app-aside-options',
  templateUrl: 'aside-options.component.html'
})
export class AsideOptionsComponent extends BaseSessionComponent implements OnInit {

  private timestamp:number;
  private urlImages = URL_IMAGES;

  @Output()
  private logout = new EventEmitter<boolean>();

  @Input()
  private optionActiveStr:string;

  private optionActiveList = {
    "administrator": {"active": false, "list-group-item": true },
    "editor-list": {"active": false, "list-group-item": true },
    "editor-form": {"active": false, "list-group-item": true },
    "publicist-list": {"active": false, "list-group-item": true },
    "publicist-form": {"active": false, "list-group-item": true },
    "preferences": {"active": false, "list-group-item": true }
  };


  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    sessionService: SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();
    console.log("Init AsideOptionsComponent");

    this.timestamp = new Date().getTime();
    this.setAsideActiveOption();
  }

  // Marcar opcion 'activa' en el menu lateral
  private setAsideActiveOption() {
    if(this.optionActiveStr != null && this.optionActiveList[ this.optionActiveStr ] != null)
      this.optionActiveList[ this.optionActiveStr ].active = true;
  }

  // Evento de logout
  public logoutButton() {
    this.sessionService.logout().subscribe(
      response => this.logoutSuccess(),
      error => console.log(error)
    );
  }
  private logoutSuccess() {
    this.userLogged = null;
    this.logout.emit(true); // Emitir evento de logout
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onReloginCalls() {
    this.timestamp = new Date().getTime();
  }
  protected onLogoutCalls() {}
}
