import {Component, OnInit, ViewChild, ElementRef} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import {Http} from "@angular/http";

import {AsideOptionsComponent} from "../../aside-options/aside-options.component";
import {SessionService} from "../../../services/session.service";

import {BaseSessionComponent} from "../../base/base-session.component";
import {User} from "../../../entity/user.entity";
import {AdministratorService} from "../../../services/administrator.service";

@Component({
  selector: 'app',
  templateUrl: 'list.component.html'
})
export class AdministratorListComponent extends BaseSessionComponent implements OnInit {

  // Vistas
  @ViewChild('appAsideOptions') appAsideOptions: AsideOptionsComponent;

  // Variables
  public optionActiveStr = "administrator";
  private userList:User[] = [];

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private http: Http,
    private administratorService: AdministratorService,
    sessionService: SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();
    console.log("# Init AdministratorListComponent");
    this.sectionUserList(1);
  }

  // Cargar lista de usuarios
  private sectionUserList(page:number) {
    this.administratorService.getUserList(page).subscribe(
      response => this.userList = response,
      error => console.error(error)
    );
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {
    this.appAsideOptions.onLogin();
  }
  protected onLogoutCalls() {}
}
