import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute, Params} from "@angular/router";

import {BaseSessionComponent} from "../base/base-session.component";
import {SessionService} from "../../services/session.service";
import {Article} from "app/entity/article.entity";
import {SearchService} from "app/services/search.service";
import {URL_IMAGES} from "app/shared/config.object";

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
      console.log("search es:", this.search);
    });
  }

  private sectionSearch() {
    this.searchLoading = true;
    this.searchService.getSearch(this.search, this.page).subscribe(
      response => this.searchSuccess(response),
      error => console.log(error)
    );
  }

  private searchSuccess(response: any) {
    this.results = this.results.concat(response.content);
    this.lastPage = response.last;
    this.searchLoading = !this.searchLoading;
    console.log(this.results);
    console.log(this.page);
  }

  private moreResults() {
    this.page = this.page + 1;
    this.sectionSearch();
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onReloginCalls() {}
  protected onLogoutCalls() {}
}
