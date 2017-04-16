import {PipeTransform, Pipe} from "@angular/core";
import {Article} from "../../../entity/article.entity";

/*
 * Muestra en un HTML el estado de un articulo determinado
 */

@Pipe({name: 'articleStatus'})
export class pipeArticleStatus implements PipeTransform {
  transform(value: Article, args: string[]): any {
    if (!value) return '';

    if(!value.visible) {
      return "<span class='label label-danger'>Oculto</span>";
    }

    return "<span class='label label-success'>Activo</span>";
  }

}
