package com.sushi;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Initialisateur servlet pour le deploiement en conteneur externe (WAR).
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * Configure la source Spring Boot utilisee par le conteneur.
	 *
	 * @param application builder Spring
	 * @return builder configure
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

}
