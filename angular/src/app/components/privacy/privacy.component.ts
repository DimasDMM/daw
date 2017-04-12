import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import {URL_IMAGES} from "../../shared/config.service";
import {EventSessionComponent} from "../base/event_session.component";
import {SessionService} from "../../services/session.service";

@Component({
  selector: 'app',
  templateUrl: 'privacy.component.html',
  styleUrls: []
})
export class PrivacyComponent extends EventSessionComponent implements OnInit {

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    protected sessionService:SessionService
  ) { super(sessionService) }

  ngOnInit() {}

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onLogoutCalls() {}
}
