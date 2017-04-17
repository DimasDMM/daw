import {Component, OnInit} from '@angular/core';
import {ArticleService} from "../../services/article.service";
import {Article} from "../../entity/article.entity";
import {URL_IMAGES} from "../../shared/config.object";
import {BaseSessionComponent} from "../base/base-session.component";
import {SessionService} from "../../services/session.service";

@Component({
  selector: 'ngbd-carousel-basic',
  templateUrl: 'carrousel.component.html'
})
export class NgbdCarouselBasic extends BaseSessionComponent implements OnInit {

  public urlImages = URL_IMAGES;
  public slides:Article[] = [];

  constructor(
    private articleService:ArticleService,
    sessionService:SessionService
  ) { super(sessionService); }

  ngOnInit() {
    super.ngOnInit();
    console.log("Init Carrousel");

    this.initCarrousel();
  }

  private initCarrousel() {
    this.articleService.getCarrousel().subscribe(
      response => this.slides = response,
      error => console.error(error)
    );
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}

  protected onLogoutCalls() {}
}
