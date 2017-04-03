# Documentación API Rest

##Aspectos comunes
Todas las peticiones que se han realizado, tenían en común los siguientes aspectos (X-CSRF-TOKEN varía según la ejecución):
#### Authorization: 
 - **Username**:   justamente@mail.com
 - **Password**:   pass
#### Headers:
 - **Content-Type**:   application/json
 - **X-CSRF-TOKEN**:   6556332b-6dd1-43cf-a0a6-cb0ea9e1fa20
 - **Authorization**:  Basic anVzdGFtZW50ZUBtYWlsLmNvbTpwYXNz
#### Body:
 - **raw**
#### Código HTTP en caso de error:
 - **403 Forbbiden, 404 Not Found, 500 Internal Server error**
#### Código HTTP en caso de éxito:
 - **200 OK**
 
##Peticiones URL 
A continuación se describirán todas las URL a las que es posible hacer peticiones, junto al tipo de la misma y ejemplos del body de entrada y salida si existe (POST y PUT):
 - **Peticiones GET**
  - **http://localhost:8080/api/csrf**
     6556332b-6dd1-43cf-a0a6-cb0ea9e1fa20
  - **http://localhost:8080/api/login**
    {
          "id": 2,
          "name": "Jorge",
          "lastname": "Injusto",
          "email": "justamente@mail.com",
          "roles": [
            "ROLE_USER",
            "ROLE_ADMIN",
            "ROLE_PUBLICIST",
            "ROLE_EDITOR"
          ],
          "alias": null,
          "favourites": {
            "madrid": true,
            "spain": false,
            "world": true,
            "sports": false,
            "technology": false,
            "culture": false
          },
          "sex": null,
          "city": null,
          "country": null,
          "phoneNumber": null,
          "description": null,
          "personalWeb": null
      }
  - **http://localhost:8080/api/logout**
- **http://localhost:8080/api/anuncio/1**
  {
             "id": 1,
             "title": "ZalduaAbogados",
             "url": "http://www.zgasociados.com/"
  }
  
  - **http://localhost:8080/api/anuncio/1/visualizacion**
  {
             "id": 1,
             "title": "ZalduaAbogados",
             "url": "http://www.zgasociados.com/"
  }
  - **http://localhost:8080/api/anuncio/1/click**
  {
             "id": 1,
             "title": "ZalduaAbogados",
             "url": "http://www.zgasociados.com/"
  }
  - **http://localhost:8080/api/administrador/usuario/2**
    {
              "id": 2,
              "name": "Jorge",
              "lastname": "Injusto",
              "email": "justamente@mail.com",
              "roles": [
                "ROLE_USER",
                "ROLE_ADMIN",
                "ROLE_PUBLICIST",
                "ROLE_EDITOR"
              ],
              "alias": null,
              "favourites": {
                "madrid": true,
                "spain": false,
                "world": true,
                "sports": false,
                "technology": false,
                "culture": false
              },
              "sex": null,
              "city": null,
              "country": null,
              "phoneNumber": null,
              "description": null,
              "personalWeb": null
        }
  - **http://localhost:8080/api/administrador/usuario/lista/**
      {
      "totalElements": 3,
      "totalPages": 1,
      "number": 0,
      "size": 20,
      "first": true,
      "last": true,
      "content": [
        {
          "id": 3,
          "name": "admin",
          "lastname": "1",
          "email": "admin@mail.com",
          "roles": [
            "ROLE_USER",
            "ROLE_ADMIN"
          ],
          "alias": null,
          "favourites": {
            "madrid": true,
            "spain": false,
            "world": true,
            "sports": false,
            "technology": false,
            "culture": false
          },
          "sex": null,
          "city": null,
          "country": null,
          "phoneNumber": null,
          "description": null,
          "personalWeb": null
        },
        {
          "id": 2,
          "name": "Jorge",
          "lastname": "Injusto",
          "email": "justamente@mail.com",
          "roles": [
            "ROLE_USER",
            "ROLE_ADMIN",
            "ROLE_PUBLICIST",
            "ROLE_EDITOR"
          ],
          "alias": null,
          "favourites": {
            "madrid": true,
            "spain": false,
            "world": true,
            "sports": false,
            "technology": false,
            "culture": false
          },
          "sex": null,
          "city": null,
          "country": null,
          "phoneNumber": null,
          "description": null,
          "personalWeb": null
        },
        {
          "id": 1,
          "name": "pepe",
          "lastname": "jiménez",
          "email": "pepji@mail.com",
          "roles": [
            "ROLE_USER",
            "ROLE_PUBLICIST"
          ],
          "alias": null,
          "favourites": {
            "madrid": true,
            "spain": false,
            "world": true,
            "sports": false,
            "technology": false,
            "culture": false
          },
          "sex": null,
          "city": null,
          "country": null,
          "phoneNumber": null,
          "description": null,
          "personalWeb": null
        }
      ]
    }
  - **http://localhost:8080/api/publicista/anuncio/1**
{
          "id": 1,
          "author": {
            "id": 2,
            "name": "Jorge",
            "lastname": "Injusto",
            "alias": null
          },
           "title": "Abogados",
           "url": "http://www.zgasociados.com/",
           "weight": 40,
           "limDateStart": null,
           "limDateEnd": null,
           "limClicks": 1500,
           "limViews": 700,
           "clicks": 0,
           "views": 0,
           "dateInsert": 1491173486000
}
  - **http://localhost:8080/api/editor/articulo/1**
  
  {
      "id": 1,
      "category": "madrid",
      "title": "El Ayuntamiento de Madrid prohíbe circular a los conductores del Partido Popular",
      "content": "Madrid ha activado esta mañana por primera vez en su historia el escenario 3 del protocolo de contaminación, que **prohíbe circular** por la ciudad a cualquier persona que no haya votado a Manuela Carmena en las pasadas elecciones municipales. \"*La alta **contaminación** de dióxido de nitrógeno (NO2) nos obliga a prohibir que entren en la almendra central de la ciudad los vehículos cuyos propietarios sean de derechas*\", ha explicado Marta Higueras, teniente de alcalde del Ayuntamiento de Madrid.\r\n\r\n~~\r\n\"Nadie de izquierdas tiene coche\",\r\ninsisten desde el consistorio.\r\n~~\r\n\r\n\"*El objetivo principal es minimizar los efectos de la polución en la salud de la ciudadanía, pues había días que no se podía respirar en la ciudad*\", ha explicado la delegada de Medio Ambiente para defender la aplicación, en el día de hoy, de las restricciones al tráfico a los conductores que no han votado a partidos de izquierdas. Según los primeros datos manejados por el Consistorio, en la primera hora de aplicación de la medida (entre las 7 y las 8 de la mañana) hubo un **8% menos de coches** que accedieron al centro de la capital.\r\n\r\nTambién según los datos manejados por las autoridades, se han escuchado muchas menos emisoras de ideología conservadora y se han atropellado menos ciclistas de Podemos que en un día normal.\r\n\r\n[[http://madrid.gonzalocebrian.com/wp-content/themes/journal2/img/loading_home3.jpg|right|Imagen lateral]]\r\n\r\nLa delegada no ha avanzado datos sobre **multas**, algo que se dejará para el final del episodio, y ha querido incidir en el mensaje principal: poner el foco en la salud de la población. \"*El mensaje principal es que hay que cumplir las ordenanza*\", ha afirmado Sabanés, y ha recordado: \"*Todos los agentes saben lo que tienen que hacer, que es informar y dar alternativas ideológicas a los conductores*\".\r\n\r\n\"*Yo soy de derechas y por lo tanto mis pulmones son liberales y pueden soportar cualquier tipo de aire contaminado siempre que éste sea español y provenga de coches fabricados por grandes corporaciones capitalistas*\", ha explicado un ciudadano que se ha visto obligado a coger el tren **como los hippies de la Edad Media**.\r\n\r\nLa medida ha entrado en vigor a las 6.30 de esta mañana y se extenderá **hasta las 21.00**. Las restricciones de aparcamiento arrancan a las 9.00 y finalizan a las 21.00. La policía ya ha empezado a multar a los coches con pegatinas con la bandera de España.\r\n\r\nSegún todas las fuentes, **Esperanza Aguirre ha estado circulando durante toda la mañana** por Gran Vía dirigiendo un convoy formado por 15 camiones altamente contaminantes diseñados por ingenieros orcos para defender \"el liberalismo, el librepensamiento, la libertad individual y la intromisión ilegítima del Estado en el Medio Ambiente individual\".\r\n[[http://www.precege.com/wp-content/uploads/2015/12/oposiciones-madrid.jpg|full|Imagen final]]",
      "author": {
        "id": 2,
        "name": "Jorge",
        "lastname": "Injusto",
        "alias": null
      },
      "source": "http://www.mifuente.com",
      "tags": [
        "Madrid",
        "Cosas que pasan",
        "Alicatador"
      ],
      "visible": true,
      "views": 0,
      "dateInsert": 1491173486000
}

  
  
   - **http://localhost:8080/api/categoria/1**
{
  "content": [
        {
              "id": 1,
              "category": {},
              "title": "El Ayuntamiento de Madrid prohíbe circular a los conductores del Partido Popular",
              "titleShort": "El Ayuntamiento de M...",
              "titleMid": "El Ayuntamiento de Madrid prohíbe circul...",
              "content": "Madrid ha activado esta mañana por primera vez en su historia el escenario 3 del protocolo de contaminación, que **prohíbe circular** por la ciudad a cualquier persona que no haya votado a Manuela Carmena en las pasadas elecciones municipales. \"*La alta **contaminación** de dióxido de nitrógeno (NO2) nos obliga a prohibir que entren en la almendra central de la ciudad los vehículos cuyos propietarios sean de derechas*\", ha explicado Marta Higueras, teniente de alcalde del Ayuntamiento de Madrid.\r\n\r\n~~\r\n\"Nadie de izquierdas tiene coche\",\r\ninsisten desde el consistorio.\r\n~~\r\n\r\n\"*El objetivo principal es minimizar los efectos de la polución en la salud de la ciudadanía, pues había días que no se podía respirar en la ciudad*\", ha explicado la delegada de Medio Ambiente para defender la aplicación, en el día de hoy, de las restricciones al tráfico a los conductores que no han votado a partidos de izquierdas. Según los primeros datos manejados por el Consistorio, en la primera hora de aplicación de la medida (entre las 7 y las 8 de la mañana) hubo un **8% menos de coches** que accedieron al centro de la capital.\r\n\r\nTambién según los datos manejados por las autoridades, se han escuchado muchas menos emisoras de ideología conservadora y se han atropellado menos ciclistas de Podemos que en un día normal.\r\n\r\n[[http://ma
        }
  ]
}

   - **http://localhost:8080/api/ajustes**
{
              "id": 2,
              "name": "Jorge",
              "lastname": "Injusto",
              "email": "justamente@mail.com",
              "roles": [
                "ROLE_USER",
                "ROLE_ADMIN",
                "ROLE_PUBLICIST",
                "ROLE_EDITOR"
              ],
              "alias": null,
              "favourites": {
                "madrid": true,
                "spain": false,
                "world": true,
                "sports": false,
                "technology": false,
                "culture": false
              },
              "sex": null,
              "city": null,
              "country": null,
              "phoneNumber": null,
              "description": null,
              "personalWeb": null
}

http://localhost:8080/api/csrf *URL temporal para obtener CSRF* -> OK
Resultado: 200 OK. Ejemplo:  6556332b-6dd1-43cf-a0a6-cb0ea9e1fa20

---------------------------------------------------------------------------------------------------------------------------
http://localhost:8080/api/login - GET -> OK 
Petición: Authorization: Username: justamente@mail.com
                                          Password: pass
                    Headers: Content-Type: application/json
                                    X-CSRF-TOKEN: 6556332b-6dd1-43cf-a0a6-cb0ea9e1fa20
                                    Authorization: Basic anVzdGFtZW50ZUBtYWlsLmNvbTpwYXNz
Resultado: 200 OK. 403 Forbbiden. 404 Not Found. 
{
  "id": 2,
  "name": "Jorge",
  "lastname": "Injusto",
  "email": "justamente@mail.com",
  "roles": [
	"ROLE_USER",
	"ROLE_ADMIN",
	"ROLE_PUBLICIST",
	"ROLE_EDITOR"
  ],
  "alias": null,
  "favourites": {
	"madrid": true,
	"spain": false,
	"world": true,
	"sports": false,
	"technology": false,
	"culture": false
  },
  "sex": null,
  "city": null,
  "country": null,
  "phoneNumber": null,
  "description": null,
  "personalWeb": null
}
------------------------------------------------------------------------------------------------------------------------------------------------------
http://localhost:8080/api/logout - GET -> OK
---------------------------------------------------------------------------------------------------------------------------
http://localhost:8080/api/registro - POST -> OK

formato de datos del body:
{
"terms": true,
"alias": null,
"name": "prueba",
"lastname":"prueba",
"email":"prueba@mail.com",
"favourites":{
"madrid":false,
"spain":false,
"world":false,
"sports":false,
"technology":true,
"culture":false
},
"password":"prueba1"
}

formato de salida:
{
 "terms": true,
  "alias": null,
  "name": "prueba",
  "lastname":"prueba",
  "email":"prueba@mail.com",
  "favourites":{
  	"madrid":false,
  	"spain":false,
  	"world":false,
  	"sports":false,
  	"technology":true,
  	"culture":false
  },
  "password":"prueba1"
}


Resultado: Registra al usuario en la BBDD.
---------------------------------------------------------------------------------------------------------------------------
http://localhost:8080/api/anuncio/{id} - GET -> OK
Resultado: 200 OK.
{
  "id": 1,
  "title": "ZalduaAbogados",
  "url": "http://www.zgasociados.com/"
}
---------------------------------------------------------------------------------------------------------------------------
http://localhost:8080/api/anuncio/{id}/visualizacion - GET -> OK
Resultado:  200 OK.
{
  "id": 1,
  "title": "ZalduaAbogados",
  "url": "http://www.zgasociados.com/"
}
---------------------------------------------------------------------------------------------------------------------------
http://localhost:8080/api/anuncio/{id}/click - GET - OK
Resultado: 200 OK.
{
  "id": 1,
  "title": "ZalduaAbogados",
  "url": "http://www.zgasociados.com/"
}
---------------------------------------------------------------------------------------------------------------------------
http://localhost:8080/api/administrador/usuario/{id} - GET -> OK
Resultado: 200 OK.
{
  "id": 2,
  "name": "Jorge",
  "lastname": "Injusto",
  "email": "justamente@mail.com",
  "roles": [
	"ROLE_USER",
	"ROLE_ADMIN",
	"ROLE_PUBLICIST",
	"ROLE_EDITOR"
  ],
  "alias": null,
  "favourites": {
	"madrid": true,
	"spain": false,
	"world": true,
	"sports": false,
	"technology": false,
	"culture": false
  },
  "sex": null,
  "city": null,
  "country": null,
  "phoneNumber": null,
  "description": null,
  "personalWeb": null
}
---------------------------------------------------------------------------------------------------------------------------
http://localhost:8080/api/administrador/usuario/{id} - PUT - OK

{
  "id": 1,
   "alias": "Paco",
  "name": "pepe",
  "lastname": "jiménez",
  "email": "pepji@mail.com",
   "roles": [
    "ROLE_USER",
    "ROLE_PUBLICIST"
  ],
  "favourites": {
    "madrid": true,
    "spain": false,
    "world": true,
    "sports": false,
    "technology": false,
    "culture": false
  },
  "sex": null,
  "city": null,
  "country": null,
  "phoneNumber": null,
  "description": null,
  "personalWeb": null
}

Resultado: Devuelve los cambios realizados. 
{
  "id": 1,
  "name": "pepe",
  "lastname": "jiménez",
  "email": "pepji@mail.com",
  "roles": [
    "ROLE_USER",
    "ROLE_PUBLICIST"
  ],
  "alias": "Paco",
  "favourites": {
    "madrid": true,
    "spain": false,
    "world": true,
    "sports": false,
    "technology": false,
    "culture": false
  },
  "sex": null,
  "city": null,
  "country": null,
  "phoneNumber": null,
  "description": null,
  "personalWeb": null
}

---------------------------------------------------------------------------------------------------------------------------
http://localhost:8080/api/administrador/usuario/{id} - DELETE -> OK
Resultado: 200 OK.
---------------------------------------------------------------------------------------------------------------------------
http://localhost:8080/api/administrador/usuario/lista/{numPagina} - GET - OK
Resultado: Devuelve una lista de usuarios, del más nuevo al más antiguo. Si pones una página en la que no llega a haber usuarios, devuelve página vacía.

http://localhost:8080/api/publicista/anuncio - POST - OK 
{"title" : "AAAA", 
"url" : "www.AAAA.com", 
"weight" : 50, 
"limDateStart" : null, 
"limDateEnd" : null, 
"limClicks" : 10, 
"limViews" : 50}

Resultado: Todo ok 200. Sino 403.
{
  "id": 3,
  "author": {
    "id": 2,
    "name": "Jorge",
    "lastname": "Injusto",
    "alias": null
  },
  "title": "AAAA",
  "url": "www.AAAA.com",
  "weight": 50,
  "limDateStart": null,
  "limDateEnd": null,
  "limClicks": 10,
  "limViews": 50,
  "clicks": 0,
  "views": 0,
  "dateInsert": 1491174431869
}


http://localhost:8080/api/publicista/anuncio/1 - GET - OK
Resultado: Devuelve el anuncio, si el id no existe, devuelve 404.
{
  "id": 1,
  "author": {
    "id": 2,
    "name": "Jorge",
    "lastname": "Injusto",
    "alias": null
  },
  "title": "Abogados",
  "url": "http://www.zgasociados.com/",
  "weight": 40,
  "limDateStart": null,
  "limDateEnd": null,
  "limClicks": 1500,
  "limViews": 700,
  "clicks": 0,
  "views": 0,
  "dateInsert": 1491173486000
}

http://localhost:8080/api/publicista/anuncio/1 - PUT - OK

{
  "id": 1,
  "title": "Abogados",
  "url": "http://www.zgasociados.com/",
  "weight": 40,
  "limDateStart": null,
  "limDateEnd": null,
  "limClicks": 1500,
  "limViews": 700
}

Resultados: Va. Sino 404.
{
  "id": 1,
  "author": {
    "id": 2,
    "name": "Jorge",
    "lastname": "Injusto",
    "alias": null
  },
  "title": "Abogados",
  "url": "http://www.zgasociados.com/",
  "weight": 40,
  "limDateStart": null,
  "limDateEnd": null,
  "limClicks": 1500,
  "limViews": 700,
  "clicks": 0,
  "views": 0,
  "dateInsert": 1491173486000
}

http://localhost:8080/api/publicista/anuncio/1/imagen - POST -OK
Petición:  Headers: form-data de tipo file y seleccionamos un archivo
Resultado: todo correcto 200. Si no hay, 404.
{
  "id": 1,
  "author": {
    "id": 2,
    "name": "Jorge",
    "lastname": "Injusto",
    "alias": null
  },
  "title": "Abogados",
  "url": "http://www.zgasociados.com/",
  "weight": 40,
  "limDateStart": null,
  "limDateEnd": null,
  "limClicks": 1500,
  "limViews": 700,
  "clicks": 0,
  "views": 0,
  "dateInsert": 1491173486000
}

http://localhost:8080/api/publicista/anuncio/1 - DELETE -> OK
Resultado:  Lo mismo de siempre … si no tienes permiso no te deja, si los tienes lo borras …(200,404)

---------------------------------------------------------------------------------------------------------------------------
http://localhost:8080/api/editor/articulo - POST -> OK
	Body:
{
 "category" : "madrid",
 "title" : "Andreu abandona Movistar",
 "content" : "Andreu ya no sabe a que television irse",
 "source" : "http://www.mifuente.com",
 "visible" : true,
 "tags" : ["Humor"]
}
Resultado: 200 OK
{
  "id": 33,
  "category": "madrid",
  "title": "Andreu abandona Movistar",
  "content": "Andreu ya no sabe a que television irse",
  "author": {
	"id": 2,
	"name": "Jorge",
	"lastname": "Injusto",
	"alias": null
  },
  "source": "http://www.mifuente.com",
  "tags": [
	"Humor"
  ],
  "visible": true,
  "views": 0,
  "dateInsert": 1491173150556
}
---------------------------------------------------------------------------------------------------------------------------
http://localhost:8080/api/editor/articulo/1 - GET -> OK

Resultado: Devuelve el artículo, en caso de que no exista devolverá un 404. 
{
  "id": 1,
  "category": "madrid",
  "title": "El Ayuntamiento de Madrid prohíbe circular a los conductores del Partido Popular",
  "content": "Madrid ha activado esta mañana por primera vez en su historia el escenario 3 del protocolo de contaminación, que **prohíbe circular** por la ciudad a cualquier persona que no haya votado a Manuela Carmena en las pasadas elecciones municipales. \"*La alta **contaminación** de dióxido de nitrógeno (NO2) nos obliga a prohibir que entren en la almendra central de la ciudad los vehículos cuyos propietarios sean de derechas*\", ha explicado Marta Higueras, teniente de alcalde del Ayuntamiento de Madrid.\r\n\r\n~~\r\n\"Nadie de izquierdas tiene coche\",\r\ninsisten desde el consistorio.\r\n~~\r\n\r\n\"*El objetivo principal es minimizar los efectos de la polución en la salud de la ciudadanía, pues había días que no se podía respirar en la ciudad*\", ha explicado la delegada de Medio Ambiente para defender la aplicación, en el día de hoy, de las restricciones al tráfico a los conductores que no han votado a partidos de izquierdas. Según los primeros datos manejados por el Consistorio, en la primera hora de aplicación de la medida (entre las 7 y las 8 de la mañana) hubo un **8% menos de coches** que accedieron al centro de la capital.\r\n\r\nTambién según los datos manejados por las autoridades, se han escuchado muchas menos emisoras de ideología conservadora y se han atropellado menos ciclistas de Podemos que en un día normal.\r\n\r\n[[http://madrid.gonzalocebrian.com/wp-content/themes/journal2/img/loading_home3.jpg|right|Imagen lateral]]\r\n\r\nLa delegada no ha avanzado datos sobre **multas**, algo que se dejará para el final del episodio, y ha querido incidir en el mensaje principal: poner el foco en la salud de la población. \"*El mensaje principal es que hay que cumplir las ordenanza*\", ha afirmado Sabanés, y ha recordado: \"*Todos los agentes saben lo que tienen que hacer, que es informar y dar alternativas ideológicas a los conductores*\".\r\n\r\n\"*Yo soy de derechas y por lo tanto mis pulmones son liberales y pueden soportar cualquier tipo de aire contaminado siempre que éste sea español y provenga de coches fabricados por grandes corporaciones capitalistas*\", ha explicado un ciudadano que se ha visto obligado a coger el tren **como los hippies de la Edad Media**.\r\n\r\nLa medida ha entrado en vigor a las 6.30 de esta mañana y se extenderá **hasta las 21.00**. Las restricciones de aparcamiento arrancan a las 9.00 y finalizan a las 21.00. La policía ya ha empezado a multar a los coches con pegatinas con la bandera de España.\r\n\r\nSegún todas las fuentes, **Esperanza Aguirre ha estado circulando durante toda la mañana** por Gran Vía dirigiendo un convoy formado por 15 camiones altamente contaminantes diseñados por ingenieros orcos para defender \"el liberalismo, el librepensamiento, la libertad individual y la intromisión ilegítima del Estado en el Medio Ambiente individual\".\r\n[[http://www.precege.com/wp-content/uploads/2015/12/oposiciones-madrid.jpg|full|Imagen final]]",
  "author": {
    "id": 2,
    "name": "Jorge",
    "lastname": "Injusto",
    "alias": null
  },
  "source": "http://www.mifuente.com",
  "tags": [
    "Madrid",
    "Cosas que pasan",
    "Alicatador"
  ],
  "visible": true,
  "views": 0,
  "dateInsert": 1491173486000
}

http://localhost:8080/api/editor/articulo/1 - PUT - OK
Petición:Authorization: Username: justamente@mail.com
                                          Password: pass  
Headers: Content-Type: application/json
                                    X-CSRF-TOKEN: 6556332b-6dd1-43cf-a0a6-cb0ea9e1fa20
                                    Authorization: Basic anVzdGFtZW50ZUBtYWlsLmNvbTpwYXNz

{
  "id": 1,
  "title": "Abogados",
  "url": "http://www.zgasociados.com/",
  "weight": 40,
  "limDateStart": null,
  "limDateEnd": null,
  "limClicks": 1500,
  "limViews": 700
}

Resultado: Devuelve la noticia con los cambios realizados en el body de la petición PUT. Se refleja en la BBDD y en la página web. Si el artículo no existe, se devuelve un 404.
{
  "id": 1,
  "author": {
    "id": 2,
    "name": "Jorge",
    "lastname": "Injusto",
    "alias": null
  },
  "title": "Abogados",
  "url": "http://www.zgasociados.com/",
  "weight": 40,
  "limDateStart": null,
  "limDateEnd": null,
  "limClicks": 1500,
  "limViews": 700,
  "clicks": 0,
  "views": 0,
  "dateInsert": 1491173486000
}

http://localhost:8080/api/editor/articulo/19/imagen - POST - OK
Petición: Headers:   form-data de tipo file y seleccionamos un archivo
Resultado: Sube la imagen. Si id mal entonces 404.
{
  "id": 19,
  "category": "tecnologia",
  "title": "Muere Masaya Nakamura, el padre del legendario videojuego Pac-Man",
  "content": "Madrid ha activado esta mañana por primera vez en su historia el escenario 3 del protocolo de contaminación, que **prohíbe circular** por la ciudad a cualquier persona que no haya votado a Manuela Carmena en las pasadas elecciones municipales. \"*La alta **contaminación** de dióxido de nitrógeno (NO2) nos obliga a prohibir que entren en la almendra central de la ciudad los vehículos cuyos propietarios sean de derechas*\", ha explicado Marta Higueras, teniente de alcalde del Ayuntamiento de Madrid.\r\n\r\n~~\r\n\"Nadie de izquierdas tiene coche\",\r\ninsisten desde el consistorio.\r\n~~\r\n\r\n\"*El objetivo principal es minimizar los efectos de la polución en la salud de la ciudadanía, pues había días que no se podía respirar en la ciudad*\", ha explicado la delegada de Medio Ambiente para defender la aplicación, en el día de hoy, de las restricciones al tráfico a los conductores que no han votado a partidos de izquierdas. Según los primeros datos manejados por el Consistorio, en la primera hora de aplicación de la medida (entre las 7 y las 8 de la mañana) hubo un **8% menos de coches** que accedieron al centro de la capital.\r\n\r\nTambién según los datos manejados por las autoridades, se han escuchado muchas menos emisoras de ideología conservadora y se han atropellado menos ciclistas de Podemos que en un día normal.\r\n\r\n[[http://madrid.gonzalocebrian.com/wp-content/themes/journal2/img/loading_home3.jpg|right|Imagen lateral]]\r\n\r\nLa delegada no ha avanzado datos sobre **multas**, algo que se dejará para el final del episodio, y ha querido incidir en el mensaje principal: poner el foco en la salud de la población. \"*El mensaje principal es que hay que cumplir las ordenanza*\", ha afirmado Sabanés, y ha recordado: \"*Todos los agentes saben lo que tienen que hacer, que es informar y dar alternativas ideológicas a los conductores*\".\r\n\r\n\"*Yo soy de derechas y por lo tanto mis pulmones son liberales y pueden soportar cualquier tipo de aire contaminado siempre que éste sea español y provenga de coches fabricados por grandes corporaciones capitalistas*\", ha explicado un ciudadano que se ha visto obligado a coger el tren **como los hippies de la Edad Media**.\r\n\r\nLa medida ha entrado en vigor a las 6.30 de esta mañana y se extenderá **hasta las 21.00**. Las restricciones de aparcamiento arrancan a las 9.00 y finalizan a las 21.00. La policía ya ha empezado a multar a los coches con pegatinas con la bandera de España.\r\n\r\nSegún todas las fuentes, **Esperanza Aguirre ha estado circulando durante toda la mañana** por Gran Vía dirigiendo un convoy formado por 15 camiones altamente contaminantes diseñados por ingenieros orcos para defender \"el liberalismo, el librepensamiento, la libertad individual y la intromisión ilegítima del Estado en el Medio Ambiente individual\".\r\n[[http://www.precege.com/wp-content/uploads/2015/12/oposiciones-madrid.jpg|full|Imagen final]]",
  "author": {
    "id": 2,
    "name": "Jorge",
    "lastname": "Injusto",
    "alias": null
  },
  "source": "http://www.mifuente.com",
  "tags": [
    "Madrid",
    "Cosas que pasan",
    "Alicatador"
  ],
  "visible": true,
  "views": 0,
  "dateInsert": 1491173487000
}

---------------------------------------------------------------------------------------------------------------------------
http://localhost:8080/api/editor/articulo/1 - DELETE - OK
Resultado: 200 OK. 
---------------------------------------------------------------------------------------------------------------------------
http://localhost:8080/api/editor/articulo/lista - GET - OK
Resultado: Devuelve lista de artículos. Si no estás loggeado, aparece “No autorizado”
{
  "totalElements": 32,
  "totalPages": 2,
  "number": 0,
  "size": 20,
  "first": true,
  "last": false,
  "content": [
    {
      "id": 32,
      "category": "madrid",
      "title": "La plaza de Santo Domingo volverá a tener un aparcamiento7",
      "content": "Madrid ha activado esta mañana por primera vez en su historia el escenario 3 del protocolo de contaminación, que **prohíbe circular** por la ciudad a cualquier persona que no haya votado a Manuela Carmena en las pasadas elecciones municipale

Y sigue con todos los artículos de esa página.

http://localhost:8080/api/editor/articulo/lista?page={page} - GET -  OK
Resultado: Devuelve la página correspondiente. con anuncios o vacía según corresponda.
{
  "totalElements": 32,
  "totalPages": 2,
  "number": 1,
  "size": 20,
  "first": false,
  "last": true,
  "content": [
    {
      "id": 12,
      "category": "espana",
      "title": "El 60% de los aspirantes a bombero en Burgos, eliminado por faltas ortográficas",
      "content": "Madrid ha activado esta mañana por primera vez en su historia el escenario 3 del protocolo de contaminación, que **prohíbe circular** por la ciudad a cualquier persona que no haya votado a Manuela Carmena en las pasadas elecciones municipales. \"*La alta **contaminación** de dióxido de nitrógeno (NO2) nos obliga a prohibir que entren en la almendra central de la ciudad los vehículos cuyos propietarios sean de derechas*\", ha explicado Marta Higueras, teniente de alcalde del Ayuntamiento de Madrid.\r\n\r\n~~\r\n\"Nadie d

http://localhost:8080/api/articulo/5 - GET -OK
Resultado: Si no existe id devuelve 404.
{
  "id": 5,
  "category": "deportes",
  "title": "Federer se toma la revancha contra Nadal y gana su quinto Open de Australia",
  "content": "Madrid ha activado esta mañana por primera vez en su historia el escenario 3 del protocolo de contaminación, que **prohíbe circular** por la ciudad a cualquier persona que no haya votado a Manuela Carmena en las pasadas elecciones municipales. \"*La alta **contaminación** de dióxido de nitrógeno (NO2) nos obliga a prohibir que entren en la almendra central de la ciudad los vehículos cuyos propietarios sean de derechas*\", ha explicado Marta Higueras, teniente de alcalde del Ayuntamiento de Madrid.\r\n\r\n~~\r\n\"Nadie de izquierdas tiene coche\",\r\ninsisten desde el consistorio.\r\n~~\r\n\r\n\"*El objetivo principal es minimizar los efectos de la polución en la salud de la ciudadanía, pues había días que no se podía respirar en la ciudad*\", ha explicado la delegada de Medio Ambiente para defender la aplicación, en el día de hoy, de las restricciones al tráfico a los conductores que no han votado a partidos de izquierdas. Según los primeros datos manejados por el Consistorio, en la primera hora de aplicación de la medida (entre las 7 y las 8 de la mañana) hubo un **8% menos de coches** que accedieron al centro de la capital.\r\n\r\nTambién según los datos manejados por las autoridades, se han escuchado muchas menos emisoras de ideología conservadora y se han atropellado menos ciclistas de Podemos que en un día normal.\r\n\r\n[[http://madrid.gonzalocebrian.com/wp-content/themes/journal2/img/loading_home3.jpg|right|Imagen lateral]]\r\n\r\nLa delegada no ha avanzado datos sobre **multas**, algo que se dejará para el final del episodio, y ha querido incidir en el mensaje principal: poner el foco en la salud de la población. \"*El mensaje principal es que hay que cumplir las ordenanza*\", ha afirmado Sabanés, y ha recordado: \"*Todos los agentes saben lo que tienen que hacer, que es informar y dar alternativas ideológicas a los conductores*\".\r\n\r\n\"*Yo soy de derechas y por lo tanto mis pulmones son liberales y pueden soportar cualquier tipo de aire contaminado siempre que éste sea español y provenga de coches fabricados por grandes corporaciones capitalistas*\", ha explicado un ciudadano que se ha visto obligado a coger el tren **como los hippies de la Edad Media**.\r\n\r\nLa medida ha entrado en vigor a las 6.30 de esta mañana y se extenderá **hasta las 21.00**. Las restricciones de aparcamiento arrancan a las 9.00 y finalizan a las 21.00. La policía ya ha empezado a multar a los coches con pegatinas con la bandera de España.\r\n\r\nSegún todas las fuentes, **Esperanza Aguirre ha estado circulando durante toda la mañana** por Gran Vía dirigiendo un convoy formado por 15 camiones altamente contaminantes diseñados por ingenieros orcos para defender \"el liberalismo, el librepensamiento, la libertad individual y la intromisión ilegítima del Estado en el Medio Ambiente individual\".\r\n[[http://www.precege.com/wp-content/uploads/2015/12/oposiciones-madrid.jpg|full|Imagen final]]",
  "author": {
    "id": 2,
    "name": "Jorge",
    "lastname": "Injusto",
    "alias": null
  },
  "source": "http://www.mifuente.com",
  "tags": [
    "Madrid",
    "Cosas que pasan",
    "Alicatador"
  ],
  "views": 0,
  "dateInsert": 1491173486000
}

http://localhost:8080/api/articulo/2 - POST -  OK
{
	"comment":"Hola, esto es muy interesante"
}

Resultado: Devuelve el cometario, su autor, la fecha y el id del comentario. Si el id introducido en la URL no existe, devuelve 404.
{
  "id": 2,
  "author": {},
  "number": 1,
  "comment": "Hola, esto es muy interesante",
  "dateInsert": 1491175107046
}

http://localhost:8080/api/articulo/1/comentarios - GET- OK
Resultado: Devuelve los comentarios correspondientes si el id es correcto y si existen, sino existen devuelve vacío y si el id no existe devuelve 404.
{
  "comments": [
    {
      "id": 1,
      "author": {},
      "number": 1,
      "comment": "Este es un comentario de remero raso",
      "dateInsert": 1491173488000
    }
  ]
}

http://localhost:8080/api/articulo/{id}/visualizacion - GET - OK
Resultado: Devuelve el artículo si el id es correcto, sino 404.
{
  "id": 1,
  "title": "Abogados",
  "url": "http://www.zgasociados.com/"
}

---------------------------------------------------------------------------------------------------------------------------
http://localhost:8080/api/subscripcion - POST - OK
{
 "email" : "juan@meimportaunamierda.com"
}
Resultado: 200 OK
{
 "email" : "juan@meimportaunamierda.com"
}
---------------------------------------------------------------------------------------------------------------------------

http://localhost:8080/api/buscar?search={search} - GET -> OK
Resultado: Devuelve las noticias que en su título tienen la palabra a buscar. 
{
  "content": [
    {
      "id": 1,
      "category": {},
      "title": "El Ayuntamiento de Madrid prohíbe circular a los conductores del Partido Popular",
      "titleShort": "El Ayuntamiento de M...",
      "titleMid": "El Ayuntamiento de Madrid prohíbe circul...",
      "content": "Madrid ha activado esta mañana por primera vez en su historia el escenario 3 del protocolo de contaminación, que **prohíbe circular** por la ciudad a cualquier persona que no haya votado a Manuela Carmena en las pasadas elecciones municipales. \"*La alta **contaminación** de dióxido de nitrógeno (NO2) nos obliga a prohibir que entren en la almendra central de la ciudad los vehículos cuyos propietarios sean de derechas*\", ha explicado Marta Higueras, teniente de alcalde del Ayuntamiento de Madrid.\r\n\r\n~~\r\n\"Nadie de izquierdas tiene coche\",\r\ninsisten desde el consistorio.\r\n~~\r\n\r\n\"*El objetivo principal es minimizar los efectos de la polución en la salud de la ciudadanía, pues había días que no se podía respirar en la ciudad*\", ha explicado la delegada de Medio Ambiente para defender la aplicación, en el día de hoy, de las restricciones al tráfico a los conductores que no han votado a partidos de izquierdas. Según los primeros datos manejados por el Consistorio, en la primera hora de aplicación de la medida (entre las 7 y las 8 de la mañana) hubo un **8% menos de coches** que accedieron al centro de la capital.\r\n\r\nTambién según los datos manejados por las autoridades, se han escuchado muchas menos emisoras de ideología conservadora y se han atropellado menos ciclistas de Podemos que en un día normal.\r\n\r\n

http://localhost:8080/api/buscar?search={search}&page={page} - GET -> OK
Resultado: Devuelve las noticias que en su título tienen la palabra a buscar y las noticias y la página correspondiente. 
{
  "content": [
    {
      "id": 17,
      "category": {},
      "title": "La nueva F1 estudia eliminar el DRS",
      "titleShort": "La nueva F1 estudia ...",
      "titleMid": "La nueva F1 estudia eliminar el DRS",
      "content": "Madrid ha activado esta mañana por primera vez en su historia el escenario 3 del protocolo de contaminación, que **prohíbe circular** por la ciudad a cualquier persona que no haya votado a Manuela Carmena en las pasadas elecciones municipales. \"*La alta **contaminación** de dióxido de nitrógeno (NO2) nos obliga a prohibir que entren en la almendra central de la ciudad los vehículos cuyos propietarios sean de derechas*\", ha explicado Marta Higueras, teniente de alcalde del Ayuntamiento de Madrid.\r\n\r\n~~\r\n\"Nadie de izquierdas tiene coche\",\r\ninsisten desde el consistorio.\r\n~~\r\n\r\n\"*El objetivo principal es minimizar los efectos de la polución en la salud de la ciudadanía, pues había días que no se podía respirar en la ciudad*\", ha explicado la delegada de Medio Ambiente para defender la aplicación, en el día de hoy, de las restricciones al tráfico a los 

http://localhost:8080/api/categoria/1 - GET - OK
Resultado: Devuelve las noticias de la categoría introducida. Si no existe la categoría devuelve 404.
{
  "content": [
    {
      "id": 1,
      "category": {},
      "title": "El Ayuntamiento de Madrid prohíbe circular a los conductores del Partido Popular",
      "titleShort": "El Ayuntamiento de M...",
      "titleMid": "El Ayuntamiento de Madrid prohíbe circul...",
      "content": "Madrid ha activado esta mañana por primera vez en su historia el escenario 3 del protocolo de contaminación, que **prohíbe circular** por la ciudad a cualquier persona que no haya votado a Manuela Carmena en las pasadas elecciones municipales. \"*La alta **contaminación** de dióxido de nitrógeno (NO2) nos obliga a prohibir que entren en la almendra central de la ciudad los vehículos cuyos propietarios sean de derechas*\", ha explicado Marta Higueras, teniente de alcalde del Ayuntamiento de Madrid.\r\n\r\n~~\r\n\"Nadie de izquierdas tiene coche\",\r\ninsisten desde el consistorio.\r\n~~\r\n\r\n\"*El objetivo principal es minimizar los efectos de la polución en la salud de la ciudadanía, pues había días que no se podía respirar en la ciudad*\", ha explicado la delegada de Medio Ambiente para defender la aplicación, en el día de hoy, de las restricciones al tráfico a los conductores que no han votado a partidos de izquierdas. Según los primeros datos manejados por el Consistorio, en la primera hora de aplicación de la medida (entre las 7 y las 8 de la mañana) hubo un **8% menos de coches** que accedieron al centro de la capital.\r\n\r\nTambién según los datos manejados por las autoridades, se han escuchado muchas menos emisoras de ideología conservadora y se han atropellado menos ciclistas de Podemos que en un día normal.\r\n\r\n[[http://ma

http://localhost:8080/api/ajustes - GET -> OK
Resultado: En caso de no estar logueado nos sale ‘No Autorizado’, en caso de estar registrado te devuelve información.
{
  "id": 2,
  "name": "Jorge",
  "lastname": "Injusto",
  "email": "justamente@mail.com",
  "roles": [
    "ROLE_USER",
    "ROLE_ADMIN",
    "ROLE_PUBLICIST",
    "ROLE_EDITOR"
  ],
  "alias": null,
  "favourites": {
    "madrid": true,
    "spain": false,
    "world": true,
    "sports": false,
    "technology": false,
    "culture": false
  },
  "sex": null,
  "city": null,
  "country": null,
  "phoneNumber": null,
  "description": null,
  "personalWeb": null
}


http://localhost:8080/api/ajustes - PUT - OK
{
  "id": 1,
   "alias": "Paco",
  "name": "pepe",
  "lastname": "jiménez",
  "email": "pepji@mail.com",
   "roles": [
    "ROLE_USER",
    "ROLE_PUBLICIST"
  ],
  "favourites": {
    "madrid": true,
    "spain": false,
    "world": true,
    "sports": false,
    "technology": false,
    "culture": false
  },
  "sex": null,
  "city": "Logrosán",
  "country": null,
  "phoneNumber": null,
  "description": null,
  "personalWeb": null
}

Resultado: Ok. Bien. 
{
  "id": 1,
  "name": "pepe",
  "lastname": "jiménez",
  "email": "pepji@mail.com",
  "roles": [
    "ROLE_USER",
    "ROLE_PUBLICIST"
  ],
  "alias": "Paco",
  "favourites": {
    "madrid": true,
    "spain": false,
    "world": true,
    "sports": false,
    "technology": false,
    "culture": false
  },
  "sex": null,
  "city": "Logrosán",
  "country": null,
  "phoneNumber": null,
  "description": null,
  "personalWeb": null
}

http://localhost:8080/api/ajustes/imagen - POST - OK 
Tipo file y examinar para coger una imagen.

Resultado: Funciona y se ve en la página.
{
  "id": 1,
  "name": "pepe",
  "lastname": "jiménez",
  "email": "pepji@mail.com",
  "roles": [
    "ROLE_USER",
    "ROLE_PUBLICIST"
  ],
  "alias": "Paco",
  "favourites": {
    "madrid": true,
    "spain": false,
    "world": true,
    "sports": false,
    "technology": false,
    "culture": false
  },
  "sex": null,
  "city": "Logrosán",
  "country": null,
  "phoneNumber": null,
  "description": null,
  "personalWeb": null
}

