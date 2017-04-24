import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute, Params} from "@angular/router";

import {BaseSessionComponent} from "../base/base-session.component";
import {SessionService} from "../../services/session.service";
import {Article} from "app/entity/article.entity";
import {SearchService} from "app/services/search.service";
import {URL_IMAGES} from "app/shared/config.object";
import {ArticleService} from "../../services/article.service";

@Component({
  selector: 'app',
  templateUrl: 'search.component.html',
  styleUrls: []
})
export class SearchComponent extends BaseSessionComponent implements OnInit {

  private search: string;
  private results: Article[];
  private page: number;
  private searchLoading: boolean;
  private lastPage: boolean;
  private urlImages = URL_IMAGES;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private searchService: SearchService,
    private articleService: ArticleService,
    sessionService: SessionService
  ) {
    super(sessionService)
  }

  ngOnInit() {
    super.ngOnInit();

    this.activatedRoute.params.subscribe((params: Params) => {
      this.results = [];
      this.page = 1;
      this.search = params["search"];
      this.sectionSearch();
    });
  }

  private sectionSearch() {
    this.searchLoading = true;
    this.searchService.getSearch(this.search, this.page).subscribe(
      response => this.searchSuccess(response),
      error => {
        this.searchLoading = false;
        console.log(error)
      }
    );
  }

  private searchSuccess(response: any) {
    this.searchLoading = false;

    if(!response.content) return;

    let articlesResponse = response.content;

    this.results = this.results.concat(articlesResponse);
    this.lastPage = response.last;

    for(let i = 0; i < articlesResponse.length; i++)
      this.loadNumberComments(articlesResponse[i]);
  }

  private moreResults() {
    this.page = this.page + 1;
    this.sectionSearch();
  }

  // Numero de comentarios en articulo
  public loadNumberComments(article:Article) {
    article.nComments = 0;
    this.articleService.getNumberComments(article).subscribe(
      response => article.nComments = response.nComments,
      error => console.log(error)
    );
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onReloginCalls() {}
  protected onLogoutCalls() {}
}
