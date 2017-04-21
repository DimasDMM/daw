import {Component, OnInit, Output, EventEmitter} from "@angular/core";

import {SessionService} from "../../services/session.service";

import {BaseSessionComponent} from "../base/base-session.component";
import {toString} from "@ng-bootstrap/ng-bootstrap/util/util";

@Component({
  selector: 'app-pagination',
  templateUrl: 'pagination.component.html'
})
export class PaginationComponent extends BaseSessionComponent implements OnInit {

  // Paginacion
  private pagination:Page[] = [];
  @Output() private pageChange = new EventEmitter<number>();

  // Configuracion
  private currentPage:number;
  private displayPages:number;
  private numberItemsPage:number;
  private numberItemsTotal:number;


  constructor(
    sessionService: SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();
    console.log("Init PaginationComponent");
  }

  // Configuracion de la paginacion: pagina actual, numero de paginas a mostrar, numero de elementos por pagina, total de elementos
  public init(currentPage:number, displayPages:number, numberItemsPage:number, numberItemsTotal:number) {
    this.currentPage = currentPage;
    this.displayPages = displayPages;
    this.numberItemsPage = numberItemsPage;
    this.numberItemsTotal = numberItemsTotal;

    this.initAux();
  }

  // Inicializar paginacion
  private initAux() {
    this.pagination = [];

    let lastPage = Math.ceil( this.numberItemsTotal / this.numberItemsPage );
    let middle = Math.floor( this.displayPages / 2 );

    // Flecha lateral izquierda
    if(this.currentPage > 1)
      this.pagination.push( { "action":-1, "text":"&laquo;", "current":false } );

    // Puntos suspensivos para paginaciones grandes
    if( this.currentPage - middle > 1 )
      this.pagination.push( { "action":0, "text":"...", "current":false } );

    // Paginacion
    for(let i = (this.currentPage - middle); i <= (this.currentPage + middle); i++ ) {
      if( i < 1 || i > lastPage) continue;
      this.pagination.push( { "action":(i-this.currentPage), "text":toString(i), "current":(i==this.currentPage) } );
    }

    // Puntos suspensivos para paginaciones grandes
    if( this.currentPage + middle < lastPage )
      this.pagination.push( { "action":0, "text":"...", "current":false } );

    // Flecha lateral derecha
    if(this.currentPage < lastPage)
      this.pagination.push( { "action":1, "text":"&raquo;", "current":false } );
  }

  // Evento de cambio de pagina
  public onPageChange(page:number) {
    this.pageChange.emit(page);
  }

  /*
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onReloginCalls() {}
  protected onLogoutCalls() {}
}

/*
Plantilla para objeto Page:

{
  "action": -1,
  "text": "Anterior",
  "current": false
}
 */
export class Page {
  public action:number;
  public text:string;
  public current:boolean;
}
