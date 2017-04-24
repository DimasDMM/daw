import {Component, OnInit, ElementRef, ViewChild, EventEmitter, Output} from "@angular/core";
import {Router} from "@angular/router";

import {ArticleService} from "../../services/article.service";
import {SessionService} from "../../services/session.service";

import {URL_IMAGES} from "../../shared/config.object";

import {Category} from "../../entity/category.entity";
import {BaseSessionComponent} from "../base/base-session.component";
import {MessageObject} from "../../shared/message.object";
import {MessageService} from "../../services/message.service";
import {Article} from "../../entity/article.entity";


@Component({
  selector: 'header',
  templateUrl: 'header.component.html'
})
export class HeaderComponent extends BaseSessionComponent implements OnInit {

  @Output()
  private login = new EventEmitter<boolean>();

  @Output()
  private logout = new EventEmitter<boolean>();

  // Estilos CSS
  private modalLogReg = { "is-visible": false, "cd-user-modal": true };
  private modalBtnLogin = { "selected":false };
  private modalTabLogin = { "is-selected":false };
  private modalBtnSignup = { "selected":false };
  private modalTabSignup = { "is-selected":false };

  private searchDisplay = false;
  private searchInput:string;

  private urlImages = URL_IMAGES;
  private message:MessageObject;
  private navigationCollapse:boolean;

  private categories:Category[] = [];
  private last_articles = {};
  private dateNow:Date;

  // Variables de formularios
  @ViewChild('loginEmail') loginEmail: ElementRef;
  @ViewChild('loginPassword') loginPassword: ElementRef;

  @ViewChild('signupName') signupName: ElementRef;
  @ViewChild('signupLastname') signupLastname: ElementRef;
  @ViewChild('signupEmail') signupEmail: ElementRef;
  @ViewChild('signupPassword1') signupPassword1: ElementRef;
  @ViewChild('signupPassword2') signupPassword2: ElementRef;
  @ViewChild('signupTerms') signupTerms: ElementRef;

  // Elementos HTML
  @ViewChild('formLoginSubmit') formLoginSubmit: ElementRef;
  @ViewChild('formSignupSubmit') formSignupSubmit: ElementRef;

  constructor(
    private router:Router,
    private articleService: ArticleService,
    private messageService: MessageService,
    sessionService: SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();
    console.log("Init Header");

    this.categories = this.articleService.getCategories();
    this.navigationCollapse = true;

    let that = this;
    this.categories.forEach(function (category) {
      that.last_articles[ category.id ] = {};
      that.articleService.getArticlesFromCategory( category.id, 1, 10 ).subscribe(
        articles => {
          that.last_articles[ category.id ] = articles.content;
          for(let i = 0; i < articles.content.length; i++) that.loadNumberComments(that.last_articles[ category.id ][i]);
        },
        error => console.error(error)
      );
    });

    this.dateNow = new Date();
  }

  // Numero de comentarios en articulo
  public loadNumberComments(article:Article) {
    article.nComments = 0;
    this.articleService.getNumberComments(article).subscribe(
      response => article.nComments = response.nComments,
      error => console.log(error)
    );
  }

  // Eventos sobre el header
  public logoutButton() {
    this.sessionService.logout().subscribe(
      response => this.logoutSuccess(),
      error => this.logoutSuccess()
    );
  }
  private logoutSuccess() {
    this.userLogged = null;
    this.logout.emit(true); // Emitir evento de logout
  }

  private toggleNavigation() {
    this.navigationCollapse = !this.navigationCollapse;
  }

  /*
   * Acciones sobre el popup de login-registro
   */
  public modalOpenLogin() {
    this.modalSelectTabLogin();
    this.modalOpen();
  }

  public modalOpenSignup() {
    this.modalSelectTabSignup();
    this.modalOpen();
  }

  public modalSelectTabLogin() {
    this.modalBtnLogin["selected"] = true;
    this.modalTabLogin["is-selected"] = true;
    this.modalBtnSignup["selected"] = false;
    this.modalTabSignup["is-selected"] = false;
  }

  public modalSelectTabSignup() {
    this.modalBtnLogin["selected"] = false;
    this.modalTabLogin["is-selected"] = false;
    this.modalBtnSignup["selected"] = true;
    this.modalTabSignup["is-selected"] = true;
  }

  // Alternar abrir-cerrar
  public modalOpen() {
    this.modalLogReg["is-visible"] = true;
  }

  public modalClose() {
    this.modalLogReg["is-visible"] = false;
  }

  /*
   * Formulario del login
   */
  public submitFormLogin(event) {
    event.stopPropagation();

    this.buttonLogin(false);
    let email = this.loginEmail.nativeElement.value;
    let password = this.loginPassword.nativeElement.value;

    this.sessionService.login(email, password).subscribe(
      response => this.loginSuccess(),
      error => this.loginError(error)
    );
  }

  private buttonLogin(enable:boolean) {
    if(enable) {
      this.formLoginSubmit.nativeElement.value = "Entrar";
      this.formLoginSubmit.nativeElement.disabled = true;
    } else {
      this.formLoginSubmit.nativeElement.value = "Cargando...";
      this.formLoginSubmit.nativeElement.disabled = false;
    }
  }

  private loginSuccess() {
    this.userLogged = this.sessionService.getUserLogged();
    this.modalClose();
    this.buttonLogin(true);
    this.login.emit(true); // Emitir evento de login
  }

  private loginError(error) {
    this.buttonLogin(true);

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
      this.message = this.messageService.getMessage(700);
  }

  /*
   * Formulario del registro
   */
  public submitFormSignup(event) {
    event.stopPropagation();

    this.buttonSignup(false);
    let name = this.signupName.nativeElement.value;
    let lastname = this.signupLastname.nativeElement.value;
    let email = this.signupEmail.nativeElement.value;
    let password1 = this.signupPassword1.nativeElement.value;
    let password2 = this.signupPassword2.nativeElement.value;
    let terms = this.signupTerms.nativeElement.value;

    terms = (terms == 'on' || terms == true);

    this.sessionService.signupStep1(name, lastname, email, password1, password2, terms).subscribe(
      response => this.signupSuccess(),
      error => this.signupError(error)
    );
  }

  private buttonSignup(enable:boolean) {
    if(enable) {
      this.formSignupSubmit.nativeElement.value = "Entrar";
      this.formSignupSubmit.nativeElement.disabled = true;
    } else {
      this.formSignupSubmit.nativeElement.value = "Cargando...";
      this.formSignupSubmit.nativeElement.disabled = false;
    }
  }

  private signupSuccess() {
    this.router.navigate(["/registro"]);
  }

  private signupError(error) {
    this.buttonSignup(true);

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
  }

  /*
   * Buscador
   */
  private onSearch(){
    this.router.navigate(["/buscar",{'search':this.searchInput}]);
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onReloginCalls() {}
  protected onLogoutCalls() {}
}
