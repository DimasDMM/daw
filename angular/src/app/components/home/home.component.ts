import { Component } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import {ArticleService} from "../../services/article.service";

@Component({
  selector: 'app',
  templateUrl: 'home.component.html',
  styleUrls: []
})
export class HomeComponent {
  constructor(private router: Router, private activatedRoute: ActivatedRoute, private articleService:ArticleService) {}
}
