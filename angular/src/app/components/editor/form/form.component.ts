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
  @ViewChild('buttonArticleVisible') buttonArticleVisible: ElementRef;
  @ViewChild('buttonSubmit') buttonSubmit: ElementRef;
  private buttonArticleVisibleHtml:string;
  private buttonArticleVisibleDisabled:boolean;

  // Variables
  private urlImages = URL_IMAGES;
  private categories:Category[] = [];
  private formImage:File;
  private fArticle:Article;
  private fArticlePreview:Article;
  private optionActiveStr = "editor-form";
  private message:MessageObject;


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
    this.sectionAsideOption();
    this.sectionPreview();
    this.sectionForm();
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
    this.redirectToList();
  }

  private redirectToList() {
    this.router.navigate(['/editor/articulos']);
  }

  private redirectToListWithMsg(msg:number) {
    this.router.navigate(['/editor/articulos', {'msg':msg}]);
  }

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
    this.editorService.saveImage(article.id, this.formImage).subscribe(
      response => this.submitImageFormSuccess(article),
      error => this.submitImageFormError(error, article)
    );
  }

  private submitImageFormSuccess(article:Article) {
    this.fArticlePreview = article;
    this.fArticle.id = this.fArticlePreview.id;

    this.buttonArticleVisibleEnable(this.fArticle.visible);

    this.buttonSubmitEnable();
    this.message = this.messageService.getMessage(200);
    this.simplePageScrollService.scrollToElement("#message", 0);
  }

  private submitFormError(error:any) {
    if(error.code) {
      this.message = {
        "code": error.code,
        "message": error.message,
        "isError": true
      };
    } else {
      this.message = this.messageService.getMessage(201);
    }

    this.buttonSubmitEnable();
    this.simplePageScrollService.scrollToElement("#message", 0);
  }

  private submitImageFormError(error:any, article:Article) {
    if(error.code) {
      this.message = {
        "code": error.code,
        "message": error.message,
        "isError": true
      };
    } else {
      this.message = this.messageService.getMessage(203);
    }

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
      error => {
        console.log(error);
        this.message = this.messageService.getMessage(204);
        this.simplePageScrollService.scrollToElement("#message", 0);
        this.buttonArticleVisibleEnable(this.fArticle.visible);
      }
    );
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
   * Overwrited
   */
  protected onLoginCalls() {}
  protected onLogoutCalls() {
    this.router.navigate(['/']);
  }
}
