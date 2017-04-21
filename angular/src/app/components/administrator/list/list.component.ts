import {Component, OnInit, ViewChild, Output, EventEmitter} from "@angular/core";
import {Router, ActivatedRoute, Params} from "@angular/router";

import {PaginationComponent} from "../../pagination/pagination.component";
import {AsideOptionsComponent} from "../../aside-options/aside-options.component";
import {SessionService} from "../../../services/session.service";

import {MessageObject} from "../../../shared/message.object";
import {BaseSessionComponent} from "../../base/base-session.component";
import {User} from "../../../entity/user.entity";
import {AdministratorService} from "../../../services/administrator.service";
import {MessageService} from "../../../services/message.service";

@Component({
  selector: 'app',
  templateUrl: 'list.component.html'
})
export class AdministratorListComponent extends BaseSessionComponent implements OnInit {

  // Vistas
  @ViewChild('appAsideOptions') appAsideOptions: AsideOptionsComponent;
  @ViewChild('pagination') pagination: PaginationComponent;

  // Variables
  private optionActiveStr = "administrator";
  private userList:User[] = [];
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
    private messageService: MessageService,
    private administratorService: AdministratorService,
    sessionService: SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();
    if(!this.hasRole(["ROLE_ADMIN"]))
      this.router.navigate(['/']);

    console.log("Init AdministratorListComponent");
    this.sectionUserList();
    this.sectionMessage();
  }

  // Cargar mensaje
  private sectionMessage() {
    this.activatedRoute.params.subscribe((params: Params) => {
      if(params['msg'])
        this.message = this.messageService.getMessage( params['msg'] );
    });
  }

  // Cargar lista de usuarios
  private sectionUserList() {
    this.administratorService.getUserList( this.currentPage ).subscribe(
      response => this.setUserList(response),
      error => console.error(error)
    );
  }

  // Cargar lista y paginacion
  private setUserList(response:any) {
    this.userList = response.content;
    this.numberItemsPage = response.size;
    this.numberItemsTotal = response.totalElements;
    this.pagination.init(this.currentPage, this.displayPages, this.numberItemsPage, this.numberItemsTotal);
  }

  // Evento de cambio de pagina
  public onPageChange(page:number) {
    page = this.currentPage + page;
    console.log("Event Page Change: "+ page);

    this.currentPage = page;
    this.sectionUserList();
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
