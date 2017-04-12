import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import {EventSessionComponent} from "../base/event-session.component";
import {SessionService} from "../../services/session.service";

@Component({
  selector: 'app',
  templateUrl: 'terms-and-conditions.component.html',
  styleUrls: []
})
export class TermsAndConditionsComponent extends EventSessionComponent implements OnInit {

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    sessionService:SessionService
  ) { super(sessionService) }

  ngOnInit() {
    console.log("# Init TermsAndConditions");
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onLogoutCalls() {}
}
