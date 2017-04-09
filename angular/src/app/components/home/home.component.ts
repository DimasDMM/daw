import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import {Article} from "../../entity/article.entity";

@Component({
  selector: 'app',
  templateUrl: 'home.component.html',
  styleUrls: []
})
export class HomeComponent implements OnInit {
  public carrouselArticles:Article[] = [];

  constructor(private router: Router, private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.initCarrouselArticles();
  }

  private initCarrouselArticles() {

  }
}
