import {PipeTransform, Pipe} from "@angular/core";

/*
 * Transforma un string HTML en un string solo texto
 */

@Pipe({name: 'toText'})
export class pipeToText implements PipeTransform {
  transform(value: string, args: string[]): any {
    if (!value) return '';

    return value.replace(/<(?:.|\n)*?>/gm, '');
  }

}
