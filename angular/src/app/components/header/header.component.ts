import {Component, OnInit, ElementRef, ViewChild, EventEmitter, Output} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import {Http} from "@angular/http";

import {ArticleService} from "../../services/article.service";
import {SessionService} from "../../services/session.service";

import {URL_IMAGES} from "../../shared/config.service";

import {User} from "../../entity/user.entity";
import {Category} from "../../entity/category.entity";
import {EventSessionComponent} from "../base/event-session.component";


@Component({
  selector: 'header',
  templateUrl: 'header.component.html'
})
export class HeaderComponent extends EventSessionComponent implements OnInit {

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

  public urlImages = URL_IMAGES;
  public userLogged:User;

  public categories:Category[] = [];
  public last_articles = {};
  public dateNow:Date;

  // Variables de formularios
  @ViewChild('loginEmail') loginEmail: ElementRef;
  @ViewChild('loginPassword') loginPassword: ElementRef;

  // Elementos HTML
  @ViewChild('formLoginSubmit') formLoginSubmit: ElementRef;
  @ViewChild('formSignupSubmit') formSignupSubmit: ElementRef;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private articleService: ArticleService,
    sessionService: SessionService
  ) { super(sessionService) }

  ngOnInit() {
    console.log("# Init Header");

    this.categories = this.articleService.getCategories();

    let that = this;
    this.categories.forEach(function (category) {
      that.last_articles[ category.id ] = {};
      that.articleService.getArticlesFromCategory( category.id, 1, 10 ).subscribe(
        articles => that.last_articles[ category.id ] = articles,
        error => console.error(error)
      );
    });

    this.dateNow = new Date();
  }

  // Eventos sobre el header
  public loginButton(event) {
    event.stopPropagation();

    this.formLoginSubmit.nativeElement.value = "Cargando...";

    let email = this.loginEmail.nativeElement.value;
    let password = this.loginPassword.nativeElement.value;

    this.sessionService.login(email, password).subscribe(
      response => this.loginSuccess(),
      error => this.loginError(error)
    );
  }

  public logoutButton() {
    this.sessionService.logout().subscribe(
      response => this.logoutSuccess(),
      error => this.logoutError(error)
    );
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
  public modalToggle() {
    this.modalLogReg["is-visible"] = !this.modalLogReg["is-visible"];
  }

  public modalOpen() {
    this.modalLogReg["is-visible"] = true;
  }

  public modalClose() {
    this.modalLogReg["is-visible"] = false;
  }

  /*
   * Metodos auxiliares para los formularios del popup de login-registro
   */
  private loginSuccess() {
    this.userLogged = this.sessionService.getUserLogged();
    this.modalToggle();

    this.formLoginSubmit.nativeElement.value = "Entrar";

    this.login.emit(); // Emitir evento de login
  }

  private loginError(error) {
    console.log(error);
    this.formLoginSubmit.nativeElement.value = "Entrar";
  }

  private logoutSuccess() {
    this.userLogged = null;
    this.logout.emit(); // Emitir evento de logout
  }

  private logoutError(error) {
    console.log(error);
    this.formLoginSubmit.nativeElement.value = "Entrar";
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onLogoutCalls() {}
}
