import {PipeTransform, Pipe} from "@angular/core";

/*
 * Transforma un string HTML en un string solo texto
 */

@Pipe({name: 'toText'})
export class toText implements PipeTransform {
  transform(value: string, args: string[]): any {
    if (!value) return value;

    return value.replace(/<(?:.|\n)*?>/gm, '');
  }

}
