import {PipeTransform, Pipe} from "@angular/core";

/*
 * Transforma una lista de roles en un string solo texto
 */

@Pipe({name: 'listRolesToText'})
export class pipeListRolesToText implements PipeTransform {
  transform(value: string, args: string[]): any {
    if(!value) return '';

    let result = "";
    for(let i = 0; i < value.length; i++) {
      result += this.toText(value[i]);
      if( (i+1) < value.length )
        result += ", ";
    }

    return result;
  }

  private toText(role:string) {
    switch(role) {
      case "ROLE_ADMIN":
        return "Administrador";
      case "ROLE_PUBLICIST":
        return "Publicista";
      case "ROLE_EDITOR":
        return "Editor";
      case "ROLE_USER":
        return "Usuario";
    }
  }
}
