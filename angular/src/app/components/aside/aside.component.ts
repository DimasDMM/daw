import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import {URL_IMAGES} from "../../shared/config.service";
import {EventSessionComponent} from "../base/event-session.component";
import {CommentService} from "../../services/comment.service";
import {ArticleService} from "../../services/article.service";
import {AdsService} from "../../services/ads.service";
import {SessionService} from "../../services/session.service";

import {Article} from "../../entity/article.entity";
import {Category} from "../../entity/category.entity";
import {Ad} from "../../entity/ad.entity";
import {WindowRef} from "../../shared/window.service";


@Component({
  selector: 'aside',
  templateUrl: 'aside.component.html',
  styleUrls: []
})
export class AsideComponent extends EventSessionComponent implements OnInit {

  public nativeWindow:any;
  public urlImages = URL_IMAGES;

  public categories:Category[] = [];
  public lastComments:Comment[] = [];
  public adBanner:Ad;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private articleService:ArticleService,
    private commentService:CommentService,
    private adsService:AdsService,
    private windowRef:WindowRef,
    sessionService:SessionService
  ) { super(sessionService) }

  ngOnInit() {
    console.log("# Init Aside");
    this.sectionCategories();
    this.sectionLastComments();
    this.sectionAds();
    this.sectionTwitterScript();
    this.nativeWindow = this.windowRef.getNativeWindow();
  }

  // Ultimos articulos publicados
  private sectionCategories() {
    this.categories = this.articleService.getCategories();
  }

  // Ultimos comentarios publicados
  private sectionLastComments() {
    this.commentService.getLastComments(3).subscribe(
      response => this.lastComments = response,
      error => console.error(error)
    );
  }

  // Anuncio aleatorio
  private sectionAds() {
    this.adsService.getRandom().subscribe(
      response => this.onLoadAd(response),
      error => console.error(error)
    );
  }

  private onLoadAd(ad:Ad) {
    this.adBanner = ad;
    this.adsService.addView(ad.id);
  }

  // Abrir anuncio en pestaña nueva
  public openAd(ad:Ad) {
    this.adsService.addClick(ad.id);
    this.nativeWindow.open(ad.url);
  }

  // Cargar script de Twitter
  public sectionTwitterScript() {
    console.log('preparing to load...')
    let node = document.createElement('script');
    node.src = "https://platform.twitter.com/widgets.js";
    node.type = 'text/javascript';
    node.async = true;
    node.charset = 'utf-8';
    document.getElementById('twitter-timeline')[0].appendChild(node);
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onLogoutCalls() {}
}
