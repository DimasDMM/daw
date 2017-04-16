import {PipeTransform, Pipe} from "@angular/core";

/*
 * Muestra el simbolo de 'infinito' (&infin;) en caso de que sea un texto vacio
 */

@Pipe({name: 'infinOnEmpty'})
export class pipeInfinOnEmpty implements PipeTransform {
  transform(value: string, args: string[]): any {
    if (!value) return '&infin;';

    return value;
  }

}
