import {Component, OnInit} from '@angular/core';
import {ArticleService} from "../../services/article.service";
import {Article} from "../../entity/article.entity";
import {URL_IMAGES} from "../../shared/config.service";

@Component({
  selector: 'ngbd-carousel-basic',
  templateUrl: 'carrousel.component.html'
})
export class NgbdCarouselBasic implements OnInit {

  public urlImages = URL_IMAGES;
  public slides:Article[] = [];

  constructor(private articleService:ArticleService) {}

  ngOnInit() {
    console.log("# Init Carrousel");
    this.initCarrousel();
  }

  private initCarrousel() {
    this.articleService.getCarrousel().subscribe(
      response => this.slides = response,
      error => console.error(error)
    );
  }
}
