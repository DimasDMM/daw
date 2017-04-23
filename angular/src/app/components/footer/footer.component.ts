import {Component, OnInit} from "@angular/core";

import {ArticleService} from "../../services/article.service";
import {SessionService} from "../../services/session.service";

import {URL_IMAGES} from "../../shared/config.object";
import {Category} from "../../entity/category.entity";
import {BaseSessionComponent} from "../base/base-session.component";
import {SubscriptionService} from "../../services/subscription.service";
import {MessageObject} from "../../shared/message.object";
import {MessageService} from "../../services/message.service";

@Component({
  selector: 'app-footer',
  templateUrl: 'footer.component.html'
})
export class FooterComponent extends BaseSessionComponent implements OnInit {

  private urlImages = URL_IMAGES;
  private message: MessageObject;
  private showModal = false;

  private categories:Category[] = [];
  private last_articles = {};
  private dateNow:Date;

  private formEmail:string;


  constructor(
    private articleService: ArticleService,
    private subscriptionService: SubscriptionService,
    private messageService: MessageService,
    sessionService: SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();
    console.log("Init Footer");

    this.categories = this.articleService.getCategories();

    this.articleService.getLastArticles(5).subscribe(
      response => this.last_articles = response,
      error => console.error(error)
    );

    this.dateNow = new Date();
  }

  private submitFormSubscription(event) {
    event.stopPropagation();
    this.subscriptionService.saveSubscription(this.formEmail).subscribe(
      response => {
        this.formEmail = '';
        this.message = this.messageService.getMessage(750);
        this.openModal();
      },
      error => this.submitFormSubscriptionError(error)
    );
  }

  private submitFormSubscriptionError(error:any) {
    if(error._body != "") {
      error = JSON.parse( error._body );
      if(error.code) {
        this.message = {
          "code": error.code,
          "message": error.message,
          "isError": true
        };
      }
    }
    if(this.message == null)
      this.message = this.messageService.getMessage(751);

    this.openModal();
  }

  /*
   * Modal
   */
  private openModal() {
    this.showModal = true;
  }
  private closeModal() {
    this.showModal = false;
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onReloginCalls() {}
  protected onLogoutCalls() {}
}
