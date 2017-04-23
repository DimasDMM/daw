import {Component, OnInit} from "@angular/core";
import {SessionService} from "../../services/session.service";
import {BaseSessionComponent} from "../base/base-session.component";
import {User} from "../../entity/user.entity";
import {MessageService} from "../../services/message.service";
import {MessageObject} from "../../shared/message.object";
import {SimplePageScrollService} from "ng2-simple-page-scroll";
import {PreferencesService} from "../../services/preferences.service";
import {SubscriptionService} from "../../services/subscription.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app',
  templateUrl: 'register.component.html'
})
export class RegisterComponent extends BaseSessionComponent implements OnInit {

  // Variables
  private message:MessageObject;
  private signupFinish:boolean;

  // Formulario
  private submitButtonDisabled:boolean;
  private submitButtonHtml:string;
  private fUser:User;
  private formImage:File;
  private formSubscription:boolean;


  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private simplePageScrollService: SimplePageScrollService,
    private subscriptionService: SubscriptionService,
    private preferencesService: PreferencesService,
    private messageService: MessageService,
    sessionService: SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();
    console.log("Init RegisterComponent");

    if(!this.hasRole(["ROLE_USER"]))
      this.router.navigate(['/']);

    this.signupFinish = false;
    this.submitButton(true);
    this.fUser = this.sessionService.getUserLogged();
    this.formSubscription = true;
  }

  /*
   * Opciones de favoritos
   */
  public submitForm(event) {
    console.log("Submit");
    event.stopPropagation();
    this.message = null;

    this.submitFormFavourites();
    this.subscriptionService.saveSubscription( this.fUser.email );
  }

  public submitFormFavourites() {
    this.submitButton(false);
    this.preferencesService.savePreferences(this.fUser).subscribe(
      response => this.submitFormFavouritesSuccess(response),
      error => this.submitFormFavouritesError(error)
    );
  }

  private submitFormFavouritesSuccess(user:User) {
    this.fUser = user;
    this.submitFormImage();
  }

  private submitFormFavouritesError(error:any) {
    if(error._body != "") {
      error = JSON.parse( error._body );
      if(error.code) {
        this.message = {
          "code": error.code,
          "message": error.message,
          "isError": true
        };
      }
    }
    if(this.message == null)
      this.message = this.messageService.getMessage(701);

    this.submitButton(true);
    this.simplePageScrollService.scrollToElement("#message", 0);
  }

  /*
   * Envio de imagen
   */
  private submitFormImage() {
    if(this.formImage == null) {
      this.submitFormSuccess();
    } else {
      this.preferencesService.saveImage(this.formImage).subscribe(
        response => this.submitFormSuccess(),
        error => this.submitFormImageError(error)
      );
    }
  }

  private submitFormSuccess() {
    this.submitButton(true);
    this.signupFinish = true;
  }

  private submitFormImageError(error:any) {
    if(error._body != "") {
      error = JSON.parse( error._body );
      if(error.code) {
        this.message = {
          "code": error.code,
          "message": error.message,
          "isError": true
        };
      }
    }
    if(this.message == null)
      this.message = this.messageService.getMessage(703);

    this.submitButton(true);
    this.simplePageScrollService.scrollToElement("#message", 0);
  }

  /*
   * Otras funciones
   */

  // Estado del boton submit
  private submitButton(enabled:boolean) {
    if(enabled) {
      this.submitButtonDisabled = false;
      this.submitButtonHtml = "Aceptar";
    } else {
      this.submitButtonDisabled = true;
      this.submitButtonHtml = "Cargando...";
    }
  }

  // Input para imagen
  private onChangeFile(event) {
    this.formImage = event.srcElement.files.item(0);
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onReloginCalls() {}
  protected onLogoutCalls() {}
}
