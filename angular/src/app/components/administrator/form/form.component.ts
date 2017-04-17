import {Component, OnInit, ViewChild} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";

import {AsideOptionsComponent} from "../../aside-options/aside-options.component";
import {SessionService} from "../../../services/session.service";

import {BaseSessionComponent} from "../../base/base-session.component";
import {User} from "../../../entity/user.entity";
import {AdministratorService} from "../../../services/administrator.service";

@Component({
  selector: 'app',
  templateUrl: 'form.component.html'
})
export class AdministratorFormComponent extends BaseSessionComponent implements OnInit {

  // Vistas
  @ViewChild('appAsideOptions') appAsideOptions: AsideOptionsComponent;

  // Variables
  private optionActiveStr = "administrator";
  private fUser:User;


  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private administratorService: AdministratorService,
    sessionService: SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();
    if(!this.hasRole(["ROLE_ADMIN"]))
      this.router.navigate(['/']);

    console.log("Init AdministratorFormComponent");

    this.sectionForm();
  }

  private sectionForm() {
    let id = this.activatedRoute.snapshot.params['id'];
    this.administratorService.getUser(id).subscribe(
      response => this.fUser = response,
      error => this.userNotFound(error)
    );
  }

  // Caso de no existir ningun usuario con el 'id' dado por URL
  private userNotFound(error:any) {
    console.error(error);
    this.redirectToList();
  }

  private redirectToList() {
    this.router.navigate(['/administrador/usuarios']);
  }

  // Verificar si role esta activo
  private hasRoleForm(role:string):boolean {
    return this.sessionService.hasRole([role], this.fUser);
  }

  // Marcar/desmarcar checkbox
  private toggleRole(role:string) {
    if(this.fUser == null) return;

    console.log("Toggle Role: "+ role);

    // Comprobar si esta activado
    let i = this.fUser.roles.indexOf(role);
    if(i > -1) {
      this.fUser.roles.splice(i, 1);
      return;
    }

    // Activarlo en caso contrario
    this.fUser.roles.push(role);
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onLogoutCalls() {
    this.router.navigate(['/']);
  }
}
