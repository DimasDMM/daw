import {Component, OnInit, ElementRef, ViewChild, EventEmitter, Output} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import {Http} from "@angular/http";

import {ArticleService} from "../../services/article.service";
import {SessionService} from "../../services/session.service";

import {URL_IMAGES} from "../../shared/config.service";

import {User} from "../../entity/user.entity";
import {Category} from "../../entity/category.entity";


@Component({
  selector: 'header',
  templateUrl: 'header.component.html'
})
export class HeaderComponent implements OnInit {

  @Output()
  private login = new EventEmitter<boolean>();

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
    private sessionService: SessionService
  ) {}

  ngOnInit() {
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
  public loginForm(event) {
    event.stopPropagation();

    this.formLoginInput.nativeElement.value = "Cargando...";

    let email = this.loginEmail.nativeElement.value;
    let password = this.loginPassword.nativeElement.value;

    this.sessionService.login(email, password).subscribe(
      response => this.loginSuccess(),
      error => console.error(error)
    );
  }

  public toggleModalLogReg() {
    console.log("# Modal toggle");
    this.modalLogReg["is-visible"] = !this.modalLogReg["is-visible"];
  }

  private loginSuccess() {
    this.userLogged = this.sessionService.getUserLogged();
    this.toggleModalLogReg();

    this.login.emit();

    console.log("# Login: "+ this.userLogged.name);
  }
}
