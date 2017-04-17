import {Component, OnInit, Input} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";

import {SessionService} from "../../services/session.service";
import {BaseSessionComponent} from "../base/base-session.component";
import {URL_IMAGES} from "../../shared/config.object";

@Component({
  selector: 'app-aside-options',
  templateUrl: 'aside-options.component.html'
})
export class AsideOptionsComponent extends BaseSessionComponent implements OnInit {

  private urlImages = URL_IMAGES;

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
    this.setAsideActiveOption();
  }

  // Marcar opcion 'activa' en el menu lateral
  private setAsideActiveOption() {
    if(this.optionActiveStr != null && this.optionActiveList[ this.optionActiveStr ] != null)
      this.optionActiveList[ this.optionActiveStr ].active = true;
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onLogoutCalls() {}
}
