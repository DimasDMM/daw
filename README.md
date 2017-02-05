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