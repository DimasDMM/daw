import {Injectable} from '@angular/core';
import 'rxjs/Rx';
import {MessageObject} from "../shared/message.object";

@Injectable()
export class MessageService {

  constructor() {
    console.log("Init MessageService")
  }

  // Obtener lista de anuncios
  public getMessage(n:number) {
    let message;

    if(this.messagesList[n]) {
      message = this.messagesList[n];
    } else {
      message = { "code":-1, "message":"Error desconocido", "isError":true };
    }

    return message;
  }

  /**
   * LISTA DE MENSAJES
   */
  private messagesList = {
    // 1-99: Errores devueltos por la API

    // 100-199: AdministratorListComponent y AdministratorFormComponent
    '100': { "code":100, "message":"Los datos del usuario han sido guardados correctamente", "isError":false },
    '101': { "code":101, "message":"Ha ocurrido un error cuando se intentaban guardar los datos del usuario", "isError":true },
    '102': { "code":102, "message":"No se ha podido encontrar el usuario en la base de datos", "isError":true },

    // 200-299: EditorListComponent y EditorFormComponent
    '200': { "code":200, "message":"El articulo ha sido guardado correctamente", "isError":false },
    '201': { "code":201, "message":"Ha ocurrido un error cuando se intentaba guardar el articulo", "isError":true },
    '202': { "code":202, "message":"No se ha podido encontrar el articulo en la base de datos", "isError":true },
    '203': { "code":203, "message":"Se han guardado los datos del articulo pero no se pudo subir la imagen", "isError":true },
    '204': { "code":204, "message":"No se ha podido cambiar el estado del articulo", "isError":true },

    '205': { "code":205, "message":"El articulo ha sido borrado correctamente", "isError":false },
    '206': { "code":206, "message":"Ha ocurrido un error cuando se intentaba eliminar el articulo", "isError":true },

    // 300-399: PreferencesComponent y derivados
    '300': { "code":300, "message":"Tus datos han sido guardados correctamente", "isError":false },
    '301': { "code":301, "message":"Ha ocurrido un error cuando se intentaba guardar tus datos", "isError":true },
    '302': { "code":302, "message":"Se ha subido la imagen correctamente", "isError":false },
    '303': { "code":303, "message":"Ha ocurrido un error cuando se intentaba actualizar la imagen", "isError":true },
    '304': { "code":304, "message":"Se ha cambiado la contraseña correctamente", "isError":false },
    '305': { "code":305, "message":"Ha ocurrido un error cuando se intentaba cambiar la contraseña", "isError":true },

    // 400-499: PublicistListComponent y PublicistFormComponent
    '400': { "code":400, "message":"El anuncio ha sido guardado correctamente", "isError":false },
    '401': { "code":401, "message":"Ha ocurrido un error cuando se intentaba guardar el anuncio", "isError":true },
    '402': { "code":402, "message":"No se ha podido encontrar el anuncio en la base de datos", "isError":true },
    '403': { "code":403, "message":"Se han guardado los datos del anuncio pero no se pudo subir la imagen", "isError":true },
    '404': { "code":404, "message":"El anuncio ha sido borrado correctamente", "isError":false },
    '405': { "code":405, "message":"Ha ocurrido un error cuando se intentaba eliminar el anuncio", "isError":true },
  };
}
