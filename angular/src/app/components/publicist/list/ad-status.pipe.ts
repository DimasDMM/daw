import {PipeTransform, Pipe} from "@angular/core";
import {Ad} from "../../../entity/ad.entity";

/*
 * Muestra en un HTML el estado de un anuncio determinado
 */

@Pipe({name: 'adStatus'})
export class pipeAdStatus implements PipeTransform {
  transform(value: Ad, args: string[]): any {
    if (!value) return '';

    let now = new Date;

    if(value.limClicks > 0 && value.limClicks <= value.clicks) {
      return "<span class='label label-danger'>Finalizado</span>";

    } else if(value.limViews > 0 && value.limViews <= value.views) {
      return "<span class='label label-danger'>Finalizado</span>";

    } else if(value.limDateStart != null && value.limDateStart > now) {
      return "<span class='label label-primary'>En espera</span>";

    } else if(value.limDateEnd != null && value.limDateEnd <= now) {
      return "<span class='label label-danger'>Finalizado</span>";
    }

    return "<span class='label label-success'>Activo</span>";
  }

}
