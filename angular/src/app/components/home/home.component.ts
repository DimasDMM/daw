import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import {Article} from "../../entity/article.entity";
import {ArticleService} from "../../services/article.service";

@Component({
  selector: 'app',
  templateUrl: 'home.component.html',
  styleUrls: []
})
export class HomeComponent implements OnInit {
  public carrousel:Article[] = [];

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private articleService:ArticleService) {}

  ngOnInit() {
    this.initCarrouselArticles();
  }

  private initCarrouselArticles() {
    this.articleService.carrousel().subscribe(
      response => this.carrousel = response,
      error => console.error(error)
    );
  }
}
