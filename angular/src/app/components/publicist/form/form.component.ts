import {Component, OnInit, ViewChild, ElementRef} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";

import {AsideOptionsComponent} from "../../aside-options/aside-options.component";
import {SessionService} from "../../../services/session.service";

import {BaseSessionComponent} from "../../base/base-session.component";
import {PublicistService} from "../../../services/publicist.service";
import {Ad} from "../../../entity/ad.entity";
import {MessageObject} from "../../../shared/message.object";
import {MessageService} from "../../../services/message.service";
import {SimplePageScrollService} from "ng2-simple-page-scroll";
import {URL_IMAGES, TIMEZONE} from "../../../shared/config.object";

@Component({
  selector: 'app',
  templateUrl: 'form.component.html'
})
export class PublicistFormComponent extends BaseSessionComponent implements OnInit {

  // Vistas
  @ViewChild('appAsideOptions') appAsideOptions: AsideOptionsComponent;
  @ViewChild('buttonSubmit') buttonSubmit: ElementRef;
  private buttonDeleteAdHtml:string;
  private buttonDeleteAdDisabled:boolean;

  // Variables
  private urlImages = URL_IMAGES;
  private fAd:Ad;
  private optionActiveStr = "publicist-form";
  private formImage:File;
  private message:MessageObject;
  private showModal = false;
  private timestamp:number;

  // Zona horaria
  private timezone = TIMEZONE;


  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private messageService: MessageService,
    private publicistService: PublicistService,
    private simplePageScrollService: SimplePageScrollService,
    sessionService: SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();
    if(!this.hasRole(["ROLE_PUBLICIST", "ROLE_ADMIN"]))
      this.router.navigate(['/']);

    console.log("Init PublicistFormComponent");

    this.sectionAsideOption();
    this.sectionForm();
    this.buttonDeleteAd(true);
    this.timestamp = new Date().getTime();
  }

  private sectionForm() {
    let id = this.activatedRoute.snapshot.params['id'];

    if(id && id != "nuevo") {
      this.publicistService.getAd(id).subscribe(
        response => {
          this.loadAd(response, this.timezone);
        },
        error => this.adNotFound(error)
      );
    } else {
      this.fAd = new Ad();
    }
  }

  private sectionAsideOption() {
    let id = this.activatedRoute.snapshot.params['id'];
    if(id && id != "nuevo") this.optionActiveStr = null;
  }

  // Caso de no existir ningun anuncio con el 'id' dado por URL
  private adNotFound(error:any) {
    console.error(error);
    this.router.navigate(['/publicista/anuncios', {'msg':402}]);
  }

  // Cargar anunciante en la variable. Tener en cuenta la zona horaria
  private loadAd(ad:Ad, timezone:number) {
    this.fAd = ad;

    if(this.fAd.limDateStart != null)
      this.fAd.limDateStart = this.loadDate(this.fAd.limDateStart, timezone);

    if(this.fAd.limDateEnd != null)
      this.fAd.limDateEnd = this.loadDate(this.fAd.limDateEnd, timezone);
  }

  private loadDate(date:Date, timezone:number) {
    let tmp = ( new Date(date) ).getTime();
    tmp = tmp + timezone * 3600 * 1000;
    return new Date(tmp);
  }

  /*
   * Formulario
   */

  // Eliminar articulo
  private deleteAd() {
    console.log("Delete Ad");

    this.buttonDeleteAd(false);
    this.publicistService.deleteAd(this.fAd).subscribe(
      response => this.deleteAdSuccess(),
      error => this.deleteAdError(error)
    );
  }

  private deleteAdSuccess() {
    this.router.navigate(['/publicista/anuncios', {'msg': 404}]);
  }

  private deleteAdError(error:any) {
    this.buttonDeleteAd(true);
    this.closeModal();
    this.message = this.messageService.getMessage(405);
    this.simplePageScrollService.scrollToElement("#message", 0);
  }

  private buttonDeleteAd(enable:boolean) {
    if(enable) {
      this.buttonDeleteAdDisabled = false;
      this.buttonDeleteAdHtml = "<i class='fa fa-trash'></i> Eliminar";
    } else {
      this.buttonDeleteAdDisabled = true;
      this.buttonDeleteAdHtml = "Cargando...";
    }
  }

  // Guardar cambios
  private saveAd(event:any) {
    event.preventDefault();

    console.log("Submit Form");

    this.buttonSubmitDisable();
    this.publicistService.saveAd(this.fAd).subscribe(
      response => this.submitFormSuccess(response),
      error => this.submitFormError(error)
    );
  }

  // Inputs de fechas
  private getFormDate(date:Date) {
    if(date == null) {
      return "";
    } else {
      let tmp = new Date( date );
      let day = (tmp.getDate() > 9 ? tmp.getDate() : "0"+(tmp.getDate()));
      let month = (tmp.getMonth()+1 > 9 ? tmp.getMonth()+1 : "0"+(tmp.getMonth()+1));
      return day+"-"+month+"-"+tmp.getFullYear();
    }
  }
  private changeLimDateStart(event:any) {
    if(event.target.value != "") {
      let tmp = event.target.value.split("-");

      let day = Number(tmp[0]);
      let month = Number(tmp[1]) - 1; // El mes empieza en 0
      let year = Number(tmp[2]);
      let date = new Date(year, month, day);

      this.fAd.limDateStart = this.loadDate(date, this.timezone);
    }
  }
  private changeLimDateEnd(event:any) {
    if(event.target.value != "") {
      let tmp = event.target.value.split("-");

      let day = Number(tmp[0]);
      let month = Number(tmp[1]) - 1; // El mes empieza en 0
      let year = Number(tmp[2]);
      let date = new Date(year, month, day);

      this.fAd.limDateEnd = this.loadDate(date, this.timezone);
    }
  }

  // Resultado de guardar formulario
  private submitFormSuccess(ad:Ad) {
    this.loadAd(ad, this.timezone);

    if(this.formImage != null) {
      this.publicistService.saveImage(ad, this.formImage).subscribe(
        response => this.submitImageFormSuccess(),
        error => this.submitImageFormError(error)
      );
    } else {
      this.submitImageFormSuccess();
    }
  }

  private submitImageFormSuccess() {
    this.timestamp = new Date().getTime();

    this.buttonSubmitEnable();
    this.message = this.messageService.getMessage(400);
    this.simplePageScrollService.scrollToElement("#message", 0);
  }

  private submitFormError(error:any) {
    error = JSON.parse( error._body );
    if(error.code) {
      this.message = {
        "code": error.code,
        "message": error.message,
        "isError": true
      };
    } else {
      this.message = this.messageService.getMessage(401);
    }

    this.buttonSubmitEnable();
    this.simplePageScrollService.scrollToElement("#message", 0);
  }

  private submitImageFormError(error:any) {
    if(error.code) {
      this.message = {
        "code": error.code,
        "message": error.message,
        "isError": true
      };
    } else {
      this.message = this.messageService.getMessage(403);
    }

    this.buttonSubmitEnable();
    this.simplePageScrollService.scrollToElement("#message", 0);
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

  // Input para imagen
  private onChangeFile(event) {
    this.formImage = event.srcElement.files.item(0);
  }

  /*
   * Modal
   */
  private openModal() {
    this.showModal = true;
  }
  private closeModal() {
    this.showModal = false;
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
