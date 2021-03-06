import {Component, OnInit, ViewChild, Output, EventEmitter} from "@angular/core";
import {Router, ActivatedRoute, Params} from "@angular/router";

import {PaginationComponent} from "../../pagination/pagination.component";
import {AsideOptionsComponent} from "../../aside-options/aside-options.component";
import {SessionService} from "../../../services/session.service";
import {MessageService} from "../../../services/message.service";

import {BaseSessionComponent} from "../../base/base-session.component";
import {EditorService} from "../../../services/editor.service";
import {ArticleService} from "../../../services/article.service";
import {Article} from "../../../entity/article.entity";
import {MessageObject} from "../../../shared/message.object";

@Component({
  selector: 'app',
  templateUrl: 'list.component.html'
})
export class EditorListComponent extends BaseSessionComponent implements OnInit {

  // Vistas
  @ViewChild('appAsideOptions') appAsideOptions: AsideOptionsComponent;
  @ViewChild('pagination') pagination: PaginationComponent;

  // Variables
  private optionActiveStr = "editor-list";
  private articlesList:Article[] = [];
  private message:MessageObject;

  // Paginacion
  private currentPage = 1;
  private displayPages = 7;
  private numberItemsPage = 1;
  private numberItemsTotal = 0;
  @Output() private paginationChange = new EventEmitter<number>();


  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private articleService: ArticleService,
    private editorService: EditorService,
    private messageService: MessageService,
    sessionService: SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();
    if(!this.hasRole(["ROLE_EDITOR", "ROLE_ADMIN"]))
      this.router.navigate(['/']);

    console.log("Init EditorListComponent");
    this.sectionArticlesList();
    this.sectionMessage();
  }

  // Cargar mensaje
  private sectionMessage() {
    this.activatedRoute.params.subscribe((params: Params) => {
      if(params['msg'])
        this.message = this.messageService.getMessage( params['msg'] );
    });
  }

  // Cargar lista de articulos
  private sectionArticlesList() {
    this.editorService.getArticlesList( this.currentPage ).subscribe(
      response => this.setArticlesList(response),
      error => console.error(error)
    );
  }

  // Cargar lista y paginacion
  private setArticlesList(response:any) {
    this.articlesList = response.content;
    this.numberItemsPage = response.size;
    this.numberItemsTotal = response.totalElements;
    this.pagination.init(this.currentPage, this.displayPages, this.numberItemsPage, this.numberItemsTotal);
  }

  // Evento de cambio de pagina
  public onPageChange(page:number) {
    page = this.currentPage + page;
    console.log("Event Page Change: "+ page);

    this.currentPage = page;
    this.sectionArticlesList();
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onReloginCalls() {}
  protected onLogoutCalls() {
    this.router.navigate(['/']);
  }
}
