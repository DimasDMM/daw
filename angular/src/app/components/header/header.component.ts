import {Component, OnInit} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import {ArticleService} from "../../services/article.service";

@Component({
  selector: 'app-header',
  templateUrl: 'header.component.html'
})
export class HeaderComponent implements OnInit {
  public classComponent = 'header-v8 header-sticky';
  public dateNow:Date;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private articleService:ArticleService) {}

  ngOnInit() {
    this.dateNow = new Date();
  }

}
