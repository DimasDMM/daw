# Desarrollo de Aplicaciones Web


##Nombre de la aplicación: The Madrid Times



## Componentes del grupo / Correo / Cuenta en GitHub
 
 - Alma Parias García / a.parias@alumnos.urjc.es / Alpargata

 - Rafael Muñoz Gómez / r.munozgo@alumnos.urjc.es / rafaelmg07

 - Dimas Muñoz Montesinos / d.munozmo@alumnos.urjc.es / DimasDMM

 - Félix Manuel Mellado Romero / fm.mellado@alumnos.urjc.es / FelixManuel



## Enlace a Trello

https://trello.com/b/r6rN8FrO/the-madrid-times



## Descripción de la temática:
 - Página de noticias

### Funcionalidad pública:
 
- Acceso a las noticias

 - Posibilidad de valorarlas

 - Contacto mediante correo electrónico

### Funcionalidad privada:
 - Las noticias se mostrarán en función de las preferencias indicadas en el momento del registro; y que irán cambiando según los clicks que vaya haciendo
 - Posibilidad de valorar las noticias
 - Realizar comentarios
 
- Mediante su localización, personalizar ciertos aspectos, como el tiempo

 - Administrar publicidad

 - Administrar noticias

 - Administrar roles

 - Contacto mediante correo electrónico



## Entidades

 - Artículo

 - Usuario
 
 - Publicidad

 - Comentario/Valoración

 - Redactor

## Diagrama de navegación
Desde user-index.html se puede acceder a cualquiera de las páginas desde index.html, pero con su versión para el usuario, donde sólo cambia el topbar (user-spain.html, user-article.html, etc.).
Páginas como el index, los términos y condiciones o la política y privacidad son accesibles desde cualquier otra.
![alt tag](https://github.com/DimasDMM/daw/blob/master/screenshots/Navigation_diagram.png)

## Capturas de pantalla

- **index.html**
Página principal del sitio web. Desde aquí, se puede obtener una visión general de las noticias, categorías (en el navbar), secciones, así como acceder al registro o el login de usuario. En todas las páginas hay un footer común con enlaces a sitios de otro interés (legalidad, otras webs, etc.) y un navbar para navegar por las categorías y volver al index estés donde estés.

![alt tag](https://github.com/DimasDMM/daw/blob/master/screenshots/index.jpg)

![alt tag](https://github.com/DimasDMM/daw/blob/master/screenshots/footer.jpg)

- **article.html**
Ejemplo de un artículo: titular, foto, subtítulo. A la derecha aparecen columnas sobre las últimas noticias, comentarios, conexión con redes sociales, etc. Debajo de la noticia, podemos leer los comentarios de otros usuarios.

![alt tag](https://github.com/DimasDMM/daw/blob/master/screenshots/articulo.jpg)

![alt tag](https://github.com/DimasDMM/daw/blob/master/screenshots/comentarios.jpg)

- **madrid.html**
Esta es una de las seis secciones temáticas de noticias de las que dispone la web. En este HTML se recopilan enlaces a las noticias (junto al titular, foto y el comienzo de la misma) que han sido relacionadas con el ámbito de Madrid.

![alt tag](https://github.com/DimasDMM/daw/blob/master/screenshots/madrid.jpg)

- **user-settings.html**
Esta es la pantalla de configuración de las opciones del usuario registrado. Está dividida en tres secciones: datos personales (nombre, apellidos, teléfono, etc.), email y contraseña (para poder cambiar ambas) y mis noticias (donde podrá seleccionar sus preferencias).

![alt tag](https://github.com/DimasDMM/daw/blob/master/screenshots/ajustes.jpg)

- **article_list.html**
Se muestra el listado de los artículos que han sido publicados y sus datos (redactor, fecha, categoría, etc.). A esta página sólo tendrá acceso un usuario redactor o administrador. Existe una página análoga para los anuncios (que sólo puede ser vista para el administrador).

![alt tag](https://github.com/DimasDMM/daw/blob/master/screenshots/lista_articulos.jpg)

- **article_new.html**
Página donde los usuarios redactores pueden escribir artículos con un editor básico pero completo. Además, se incluye una función de previsualización para ver el resultado final.

![alt tag](https://github.com/DimasDMM/daw/blob/master/screenshots/redactar_articulo.jpg)

- **ads_create.html**
Página donde los usuarios administradores pueden confeccionar los anuncios y gestionar la publicidad que se mostrará en el sitio.

![alt tag](https://github.com/DimasDMM/daw/blob/master/screenshots/redactar_anuncio.jpg)

- **user_sign**
Formulario para que los nuevos usuarios se den de alta en el sitio.

![alt tag](https://github.com/DimasDMM/daw/blob/master/screenshots/registro.jpg)

## Diagrama de BBDD Relacionales
A continuación podemos ver las entidades que tiene nuestra BBDD, así como las relaciones existentes entre ellas.

![alt tag](https://github.com/DimasDMM/daw/blob/master/screenshots/BBDDRelacional.jpg)

## Diagrama de Clases

Estas son las clases que componen la aplicación. Hemos utilizado los colores para diferenciar las siguientes clases:
 - Blanco  -> Normal
 - Azul    -> Service
 - Naranja -> Controller, RestController
 - Verde   -> Template
 - Rojo    -> Entity
 
![alt tag](https://github.com/DimasDMM/daw/blob/master/screenshots/Classes.png)

## SPA Angular

A continuación, se muestras los componentes con las correspondientes relaciones y los servicios.
 - Naranja -> Component
 - Gris    -> Service

![alt tag](https://github.com/DimasDMM/daw/blob/master/screenshots/SPA_Angular.png)

## DOCKER

# Ir a la carpeta de ./docker y ejecutar el docker-compose

docker rm -f $(docker ps -a -q)
docker-compose up --build

## Cambios respecto a la plantilla

 - index.html
 Hemos añadido el título de portada
 Cambiado el topbar
 Añadido y cambiado nuevas pestañas en el navbar al igual que el formato en que aparece el desplegable al pasar el ratón.
 Borrado Tags y Monthly news
 Añadido contactar con mailto
 Cuando se pinche en la categoría te lleve a una página de categoría, que no estaba implementado.
 Al hacerse más pequeño el navbar, si se clickea en una categoría te mande a la página de categoría correspondiente y no despliegue las noticias.
 En el navbar hemos cambiado en el CSS correspondiente el tamñano de las fotos.
 Hemos cambiado en CSS el estilo del título de portada.
 Hemos añadido noticias e imágenes de ejemplo.
 
 - madrid.html
 En la plantilla no existe esta página como la hemos insertado nosotros.
 Hemos borrado el breadcrums.
 Hemos aadido el fondo.
 Cambiado el estilo del título de cada categoría en CSS.
  
 - user_sign.html
 Las modificaciones que se han hecho ha sido eliminar toda la parte central de la página y añadir los distintos elementos de un formulario para que el usuario pueda terminar de registrarse. 
 Igualmente se han traducido tanto el pie de página como la cabecera.
  
 - ads_list.html y article_list.html:
 Añadido menu lateral procedente de otra plantilla con la navegación básica por la "zona de usuario/redactor/administrador". 
 También se ha añadido una tabla con el listado de anuncios/articulos publicados y sus correspondientes características. 
 Los iconos usados proceden de fontawesome.
  
 - Login
 Traducción, y eliminación de loggearse a través de las redes sociales.
 
 - Register 
 Traducción, y eliminación de registrarse a través de las redes sociales

 - user-settings.html 
 Se aprovechó la implementación de las pestañas y el formato de los forms con sus apartados (los text input). 
 También se hizo uso de las checkbox. Se eliminaron las barras laterales y top, y se sustituyeron por una nueva y el navbar respectivamente. 
 El estilo fue adaptado para ser consistente con el del resto de la página (fuentes, colores, footer, etc.)
 
 - article_new.html:
 Menú lateral idéntico a user-settings.html y otros HTML de la zona privada. 
 Se ha creado un formulario, el cual entre otros elementos tiene un textarea que usa Markdown, para poder escribir artículos, dándoles un mínimo de estilo.
 
 - ads_create.html:
 El formulario es similar al de article_new: al igual que en article_new se han modificado bordes y márgenes de los "div" que agrupan los inputs.
 
 - article_new_preview:
 Página muy similar a article.html. 
 Se ha añadido en la parte superior e inferior del artículo un bloque azul para confirmar la publicación del artículo.
 
 - article.html
 Se ha modificado el menú lateral: noticias recientes, últimos comentarios, etc. 
 Además, se ha traducido el JS que realiza la verificación del formulario.
 Se ha borrado el breadcrums.
 Añadido el fondo para que tenga bordes.
 
 -Análogo para las páginas adaptadas al inicio de sesión.
 En estos html's hemos cambiado el topbar añadiendo las cosas necesarias para la redacción de artículos y ajustes del usuario.