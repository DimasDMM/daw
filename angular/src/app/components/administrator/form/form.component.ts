import {Component, OnInit, ViewChild, ElementRef} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";

import {AsideOptionsComponent} from "../../aside-options/aside-options.component";
import {SessionService} from "../../../services/session.service";
import {MessageService} from "../../../services/message.service";

import {BaseSessionComponent} from "../../base/base-session.component";
import {User} from "../../../entity/user.entity";
import {AdministratorService} from "../../../services/administrator.service";
import {MessageObject} from "../../../shared/message.object";

@Component({
  selector: 'app',
  templateUrl: 'form.component.html'
})
export class AdministratorFormComponent extends BaseSessionComponent implements OnInit {

  // Elementos HTML
  @ViewChild('appAsideOptions') appAsideOptions: AsideOptionsComponent;
  @ViewChild('buttonSubmit') buttonSubmit: ElementRef;

  // Variables
  private optionActiveStr = "administrator";
  private message:MessageObject;
  private fUser:User;


  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private administratorService: AdministratorService,
    private messageService: MessageService,
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

  private redirectToListWithMsg(msg:number) {
    this.router.navigate(['/administrador/usuarios', {'msg':msg}]);
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

  // Guardar formulario
  public submitForm(event) {
    event.stopPropagation();

    console.log("Submit Form");

    this.buttonSubmitDisable();
    this.administratorService.saveUser(this.fUser).subscribe(
      response => this.submitFormSuccess(),
      error => this.submitFormError(error)
    );
  }

  // Resultado de guardar formulario
  private submitFormSuccess() {
    this.buttonSubmitEnable();
    this.redirectToListWithMsg(100);
  }

  private submitFormError(error:any) {
    if(error.code) {
      this.message = {
        "code": error.code,
        "message": error.message,
        "isError": true
      };
    } else {
      this.message = this.messageService.getMessage(101);
    }

    this.buttonSubmitEnable();
  }

  // Desactivar/Activar boton submit
  private buttonSubmitDisable() {
    this.buttonSubmit.nativeElement.innerHTML = "<i class='fa fa-spinner'></i> Cargando...";
    this.buttonSubmit.nativeElement.disabled = true;
  }

  private buttonSubmitEnable() {
    this.buttonSubmit.nativeElement.innerHTML = "<i class='fa fa-floppy-o'></i> Guardar";
    this.buttonSubmit.nativeElement.disabled = false;
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onLogoutCalls() {
    this.router.navigate(['/']);
  }
}
