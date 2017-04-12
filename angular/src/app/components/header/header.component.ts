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

  private modalLogReg = { "is-visible": false, "cd-user-modal": true };
  public urlImages = URL_IMAGES;
  public userLogged:User;

  public categories:Category[] = [];
  public last_articles = {};
  public dateNow:Date;

  // Variables de formularios
  @ViewChild('loginEmail') loginEmail: ElementRef;
  @ViewChild('loginPassword') loginPassword: ElementRef;

  // Elementos HTML
  @ViewChild('formLoginInput') formLoginInput: ElementRef;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private http: Http,
    private articleService: ArticleService,
    sessionService: SessionService
  ) { super(sessionService) }

  ngOnInit() {
    console.log("# Init Header");

    this.categories = this.articleService.getCategories();

    let that = this;
    this.categories.forEach(function (category) {
      that.last_articles[ category.id ] = {};
      that.getArticleService().getLastArticlesFromCategory( category.id, 1, 10 ).subscribe(
        articles => that.last_articles[ category.id ] = articles,
        error => console.error(error)
      );
    });

    this.dateNow = new Date();
  }

  public getArticleService() {
    return this.articleService;
  }

  // Eventos sobre el header
  public loginButton(event) {
    event.stopPropagation();

    this.formLoginInput.nativeElement.value = "Cargando...";

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

  // Mostrar/ocultar modal de login-registro
  public toggleModalLogReg() {
    this.modalLogReg["is-visible"] = !this.modalLogReg["is-visible"];
  }

  private loginSuccess() {
    this.userLogged = this.sessionService.getUserLogged();
    this.toggleModalLogReg();

    this.formLoginInput.nativeElement.value = "Entrar";

    this.login.emit(); // Emitir evento de login
  }

  private loginError(error) {
    console.log(error);
    this.formLoginInput.nativeElement.value = "Entrar";
  }

  private logoutSuccess() {
    this.userLogged = null;
    this.logout.emit(); // Emitir evento de logout
  }

  private logoutError(error) {
    console.log(error);
    this.formLoginInput.nativeElement.value = "Entrar";
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onLogoutCalls() {}
}
