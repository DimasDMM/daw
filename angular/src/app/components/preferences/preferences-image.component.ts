import {Component, OnInit, Input, Output, EventEmitter} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";

import {SessionService} from "../../services/session.service";

import {BaseSessionComponent} from "../base/base-session.component";
import {MessageObject} from "../../shared/message.object";
import {MessageService} from "../../services/message.service";
import {User} from "../../entity/user.entity";
import {PreferencesService} from "../../services/preferences.service";
import {SimplePageScrollService} from "ng2-simple-page-scroll";

@Component({
  selector: 'app-preferences-image',
  templateUrl: 'preferences-image.component.html'
})
export class PreferencesImageComponent extends BaseSessionComponent implements OnInit {

  @Output()
  private relogin = new EventEmitter<boolean>();

  @Input() private fUser:User;
  private message:MessageObject;
  private formImage:File;
  private submitButtonDisabled = false;
  private submitButtonHtml = "Guardar";


  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private messageService: MessageService,
    private preferencesService: PreferencesService,
    private simplePageScrollService: SimplePageScrollService,
    sessionService: SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();
    console.log("Init PreferencesImageComponent");
  }

  /*
   * Formulario
   */
  private onSubmit(event) {
    event.stopPropagation();
    console.log("Submit");

    this.submitButton(false);
    this.preferencesService.saveImage(this.formImage).subscribe(
      response => this.submitFormSuccess(),
      error => this.submitFormError(error)
    );
  }

  private submitFormSuccess() {
    this.sessionService.relogin().subscribe(
      respone => this.relogin.emit(true),
      error => console.log(error)
    );

    this.submitButton(true);
    this.message = this.messageService.getMessage(302);
    this.simplePageScrollService.scrollToElement("#message_image", 0);
  }

  private submitFormError(error:any) {
    this.submitButton(true);
    error = JSON.parse( error._body );
    if(error.code) {
      this.message = {
        "code": error.code,
        "message": error.message,
        "isError": true
      };
    } else {
      this.message = this.messageService.getMessage(303);
    }

    this.simplePageScrollService.scrollToElement("#message_image", 0);
  }

  // Input para imagen
  private onChangeFile(event) {
    this.formImage = event.srcElement.files.item(0);
  }

  // Estado del boton submit
  private submitButton(enabled:boolean) {
    if(enabled) {
      this.submitButtonDisabled = false;
      this.submitButtonHtml = "Guardar";
    } else {
      this.submitButtonDisabled = true;
      this.submitButtonHtml = "Cargando...";
    }
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onReloginCalls() {}
  protected onLogoutCalls() {
    this.router.navigate(['/']);
  }
}
