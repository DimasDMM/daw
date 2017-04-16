import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import {BaseSessionComponent} from "../base/base-session.component";
import {SessionService} from "../../services/session.service";

@Component({
  selector: 'app',
  templateUrl: 'terms-and-conditions.component.html',
  styleUrls: []
})
export class TermsAndConditionsComponent extends BaseSessionComponent implements OnInit {

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    sessionService:SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();
    console.log("Init TermsAndConditions");
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onLogoutCalls() {}
}
