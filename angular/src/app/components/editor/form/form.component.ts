import {Component, OnInit, ViewChild, ElementRef} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";

import {AsideOptionsComponent} from "../../aside-options/aside-options.component";
import {SessionService} from "../../../services/session.service";
import {MessageService} from "../../../services/message.service";
import {ArticleService} from "../../../services/article.service";
import {EditorService} from "../../../services/editor.service";

import {BaseSessionComponent} from "../../base/base-session.component";
import {MessageObject} from "../../../shared/message.object";
import {Article} from "../../../entity/article.entity";
import {URL_IMAGES} from "../../../shared/config.object";
import {Category} from "../../../entity/category.entity";
import {SimplePageScrollService} from "ng2-simple-page-scroll";

@Component({
  selector: 'app',
  templateUrl: 'form.component.html'
})
export class EditorFormComponent extends BaseSessionComponent implements OnInit {

  // Vistas
  @ViewChild('appAsideOptions') appAsideOptions: AsideOptionsComponent;
  @ViewChild('buttonSubmit') buttonSubmit: ElementRef;
  private buttonArticleVisibleHtml:string;
  private buttonDeleteArticleHtml:string;
  private buttonArticleVisibleDisabled:boolean;
  private buttonDeleteArticleDisabled:boolean;

  // Variables
  private urlImages = URL_IMAGES;
  private categories:Category[] = [];
  private formImage:File;
  private fArticle:Article;
  private fArticlePreview:Article;
  private optionActiveStr = "editor-form";
  private message:MessageObject;
  private showModal = false;

  private timestamp:number;


  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private articleService: ArticleService,
    private editorService: EditorService,
    private messageService: MessageService,
    private simplePageScrollService: SimplePageScrollService,
    sessionService: SessionService
  ) { super(sessionService) }

  ngOnInit() {
    super.ngOnInit();
    if(!this.hasRole(["ROLE_EDITOR", "ROLE_ADMIN"]))
      this.router.navigate(['/']);

    console.log("Init EditorFormComponent");

    this.categories = this.articleService.getCategories();
    this.timestamp = new Date().getTime();

    this.sectionAsideOption();
    this.sectionPreview();
    this.sectionForm();
    this.buttonDeleteArticle(true);
  }

  private sectionAsideOption() {
    let id = this.activatedRoute.snapshot.params['id'];
    if(id && id != "nuevo") this.optionActiveStr = null;
  }

  private sectionPreview() {
    let id = this.activatedRoute.snapshot.params['id'];

    if(id && id != "nuevo") {
      this.editorService.getArticlePreview(id).subscribe(
        response => {
          this.fArticlePreview = response;
          this.buttonArticleVisibleEnable(this.fArticlePreview.visible);
        },
        error => console.log(error)
      );
    }
  }

  private sectionForm() {
    let id = this.activatedRoute.snapshot.params['id'];

    if(id && id != "nuevo") {
      this.editorService.getArticle(id).subscribe(
        response => this.fArticle = response,
        error => this.articleNotFound(error)
      );
    } else {
      this.fArticle = new Article();
    }
  }

  // Caso de no existir ningun articulo con el 'id' dado por URL
  private articleNotFound(error:any) {
    console.error(error);
    this.router.navigate(['/editor/articulos', {'msg':202}]);
  }

  /*
   * Formularios
   */

  // Tags del articulo en formato string separadas por comas
  private getArticleTagsStr() {
    let tagsStr = "";

    if(!this.fArticle.tags) return tagsStr;

    for(let i = 0; i < this.fArticle.tags.length; i++) {
      tagsStr += this.fArticle.tags[i];
      if(i+1 < this.fArticle.tags.length)
        tagsStr += ",";
    }

    return tagsStr;
  }

  // Modificar lista de etiquetas
  private changeArticleTags(event:any) {
    if(event.target.value == "") {
      this.fArticle.tags = [];
    } else {
      this.fArticle.tags = event.target.value.split(",");
    }
  }

  // Eliminar articulo
  private deleteArticle() {
    console.log("Delete Article");

    this.buttonDeleteArticle(false);
    this.editorService.deleteArticle(this.fArticle).subscribe(
      response => this.deleteArticleSuccess(),
      error => this.deleteArticleError(error)
    );
  }

  private deleteArticleSuccess() {
    this.router.navigate(['/editor/articulos', {'msg': 205}]);
  }

  private deleteArticleError(error:any) {
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
      this.message = this.messageService.getMessage(206);

    this.buttonDeleteArticle(true);
    this.closeModal();
    this.simplePageScrollService.scrollToElement("#message", 0);
  }

  private buttonDeleteArticle(enable:boolean) {
    if(enable) {
      this.buttonDeleteArticleDisabled = false;
      this.buttonDeleteArticleHtml = "<i class='fa fa-trash'></i> Eliminar";
    } else {
      this.buttonDeleteArticleDisabled = true;
      this.buttonDeleteArticleHtml = "Cargando...";
    }
  }

  // Guardar cambios
  private saveArticle(event:any) {
    event.preventDefault();

    console.log("Submit Form");

    this.fArticle.visible = false;
    this.buttonSubmitDisable();
    this.editorService.saveArticle(this.fArticle).subscribe(
      response => this.submitFormSuccess(response),
      error => this.submitFormError(error)
    );
  }

  // Resultado de guardar formulario
  private submitFormSuccess(article:Article) {
    if(this.formImage == null) {
      this.submitImageFormSuccess(article);
    } else {
      this.editorService.saveImage(article, this.formImage).subscribe(
        response => this.submitImageFormSuccess(article),
        error => this.submitImageFormError(error, article)
      );
    }
  }

  private submitImageFormSuccess(article:Article) {
    this.fArticlePreview = article;
    this.fArticle.id = this.fArticlePreview.id;
    this.timestamp = new Date().getTime();

    this.buttonArticleVisibleEnable(this.fArticle.visible);

    this.buttonSubmitEnable();
    this.message = this.messageService.getMessage(200);
    this.simplePageScrollService.scrollToElement("#message", 0);
  }

  private submitFormError(error:any) {
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
      this.message = this.messageService.getMessage(201);

    this.buttonSubmitEnable();
    this.simplePageScrollService.scrollToElement("#message", 0);
  }

  private submitImageFormError(error:any, article:Article) {
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
      this.message = this.messageService.getMessage(203);

    this.fArticlePreview = article;
    this.fArticle.id = this.fArticlePreview.id;

    this.buttonArticleVisibleEnable(this.fArticle.visible);

    this.buttonSubmitEnable();
    this.simplePageScrollService.scrollToElement("#message", 0);
  }

  // Desactivar/Activar boton submit
  private buttonSubmitDisable() {
    this.buttonSubmit.nativeElement.innerHTML = "<i class='fa fa-spinner'></i> Cargando...";
    this.buttonSubmit.nativeElement.disabled = true;
  }

  private buttonSubmitEnable() {
    this.buttonSubmit.nativeElement.innerHTML = "<i class='fa fa-floppy-o'></i> Guardar y previsualizar";
    this.buttonSubmit.nativeElement.disabled = false;
  }

  // Publicar/ocultar articulo
  private toggleArticleVisible() {
    this.buttonArticleVisibleHtml = "<i class='fa fa-spinner'></i> Cargando...";
    this.buttonArticleVisibleDisabled = true;

    this.fArticle.visible = !this.fArticle.visible;
    this.editorService.saveArticle(this.fArticle).subscribe(
      response => {
        this.fArticle.visible = response.visible;
        this.fArticlePreview.visible = response.visible;
        this.buttonArticleVisibleEnable(this.fArticle.visible);
      },
      error => this.onArticleVisibleError(error)
    );
  }

  private onArticleVisibleError(error:any) {
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
      this.message = this.messageService.getMessage(204);

    this.simplePageScrollService.scrollToElement("#message", 0);
    this.buttonArticleVisibleEnable(this.fArticle.visible);
  }

  private buttonArticleVisibleEnable(visible:boolean) {
    this.buttonArticleVisibleDisabled = false;
    if(visible) {
      this.buttonArticleVisibleHtml = "Ocultar";
    } else {
      this.buttonArticleVisibleHtml = "Publicar";
    }
  }

  // Input para imagen
  private onChangeFile(event) {
    this.formImage = event.srcElement.files.item(0);
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
  protected onLogoutCalls() {
    this.router.navigate(['/']);
  }
}
