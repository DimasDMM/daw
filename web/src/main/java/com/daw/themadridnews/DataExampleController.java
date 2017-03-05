package com.daw.themadridnews;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import com.daw.themadridnews.article.Article;
import com.daw.themadridnews.article.ArticleRepository;
import com.daw.themadridnews.user_model.User;
import com.daw.themadridnews.user_model.UserRepository;

@Controller
public class DataExampleController implements CommandLineRunner {

	@Autowired
	private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// Usuarios
        userRepository.save(new User("pepe", "jiménez", "pepji@mail.com", "pass", "ROLE_USER"));
        userRepository.save(new User("Jorge", "Injusto", "justamente@mail.com", "pass", "ROLE_EDITOR", "ROLE_USER"));
        userRepository.save(new User("admin", "1", "admin@mail.com", "adminpass", "ROLE_ADMIN", "ROLE_EDITOR", "ROLE_USER"));
        
        // Articulo
        
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("Madrid");
        tags.add("Cosas que pasan");
        tags.add("Alicatador");
        
        Date dateInsert = new Date();
        String newline = System.lineSeparator();
        
        String article_content = "Madrid ha activado esta mañana por primera vez en su historia el escenario 3 del protocolo de contaminación, que **prohíbe circular** por la ciudad a cualquier persona que no haya votado a Manuela Carmena en las pasadas elecciones municipales. \"*La alta **contaminación** de dióxido de nitrógeno (NO2) nos obliga a prohibir que entren en la almendra central de la ciudad los vehículos cuyos propietarios sean de derechas*\", ha explicado Marta Higueras, teniente de alcalde del Ayuntamiento de Madrid."+newline+newline+"~~"+newline+"\"Nadie de izquierdas tiene coche\","+newline+"insisten desde el consistorio."+newline+"~~"+newline+newline+"\"*El objetivo principal es minimizar los efectos de la polución en la salud de la ciudadanía, pues había días que no se podía respirar en la ciudad*\", ha explicado la delegada de Medio Ambiente para defender la aplicación, en el día de hoy, de las restricciones al tráfico a los conductores que no han votado a partidos de izquierdas. Según los primeros datos manejados por el Consistorio, en la primera hora de aplicación de la medida (entre las 7 y las 8 de la mañana) hubo un **8% menos de coches** que accedieron al centro de la capital."+newline+newline+"También según los datos manejados por las autoridades, se han escuchado muchas menos emisoras de ideología conservadora y se han atropellado menos ciclistas de Podemos que en un día normal."+newline+newline+"[[/articles/1/img_2.jpg|right|Imagen lateral]]"+newline+newline+"La delegada no ha avanzado datos sobre **multas**, algo que se dejará para el final del episodio, y ha querido incidir en el mensaje principal: poner el foco en la salud de la población. \"*El mensaje principal es que hay que cumplir las ordenanza*\", ha afirmado Sabanés, y ha recordado: \"*Todos los agentes saben lo que tienen que hacer, que es informar y dar alternativas ideológicas a los conductores*\"."+newline+newline+"\"*Yo soy de derechas y por lo tanto mis pulmones son liberales y pueden soportar cualquier tipo de aire contaminado siempre que éste sea español y provenga de coches fabricados por grandes corporaciones capitalistas*\", ha explicado un ciudadano que se ha visto obligado a coger el tren **como los hippies de la Edad Media**."+newline+newline+"La medida ha entrado en vigor a las 6.30 de esta mañana y se extenderá **hasta las 21.00**. Las restricciones de aparcamiento arrancan a las 9.00 y finalizan a las 21.00. La policía ya ha empezado a multar a los coches con pegatinas con la bandera de España."+newline+newline+"Según todas las fuentes, **Esperanza Aguirre ha estado circulando durante toda la mañana** por Gran Vía dirigiendo un convoy formado por 15 camiones altamente contaminantes diseñados por ingenieros orcos para defender \"el liberalismo, el librepensamiento, la libertad individual y la intromisión ilegítima del Estado en el Medio Ambiente individual\"."+newline+"[[/articles/1/img_1.jpg|full|Imagen final]]";
        
        User editor = userRepository.findByName("Jorge");
        articleRepository.save(new Article("madrid", "El alicatador sigue fugado", article_content, editor, "http://www.mifuente.com", tags, dateInsert));
	}
}