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
