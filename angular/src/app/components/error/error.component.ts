import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import {BaseSessionComponent} from "../base/base-session.component";
import {SessionService} from "../../services/session.service";

@Component({
  selector: 'app',
  templateUrl: 'error.component.html',
  styleUrls: []
})
export class ErrorComponent extends BaseSessionComponent implements OnInit {

  private message:string;
  private error = -1;
  private list = {
    "-1": "Error desconocido.",
    "404": "Ups! La pagina solicitada no existe. Por favor, revisa que la URL introducida es correcta."
  };


  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    sessionService:SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();

    let error = this.activatedRoute.snapshot.params['error'];
    if(error != null) {
      this.error = error;
    }

    this.sectionErrorPage();
  }

  private sectionErrorPage() {
    let error = this.error;

    if(this.list[error]) {
      this.message = this.list[ error ];
    } else {
      this.message = this.list['-1'];
    }
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onReloginCalls() {}
  protected onLogoutCalls() {}
}
