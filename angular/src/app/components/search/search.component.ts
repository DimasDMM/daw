import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from "@angular/router";

import {BaseSessionComponent} from "../base/base-session.component";
import { SessionService } from "../../services/session.service";
import { Article } from "app/entity/article.entity";
import { SearchService } from "app/services/search.service";

@Component({
  selector: 'app',
  templateUrl: 'search.component.html',
  styleUrls: []
})
export class SearchComponent extends BaseSessionComponent implements OnInit {

private search:string;
private results : Article[] = [];
private page = 1;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private searchService : SearchService,
    sessionService:SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();

    this.activatedRoute.params.subscribe((params: Params) => {
      this.search = params["search"];
      this.sectionSearch();
    });
  }

  private sectionSearch() {
    this.searchService.getSearch(this.search, this.page).subscribe(
      response => this.searchSuccess(response),
      error => console.log(error)
    );
  }

private searchSuccess(response:any){
    this.results=response.content;
    console.log(this.results);
}

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onReloginCalls() {}
  protected onLogoutCalls() {}
}
