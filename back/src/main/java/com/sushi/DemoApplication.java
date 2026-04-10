package com.sushi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * Point d'entree de l'application Spring Boot Sushi Talence.
 */
public class DemoApplication {

	/**
	 * Lance l'application.
	 *
	 * @param args arguments de ligne de commande
	 */
	public static void main(String[] args) {
		 SpringApplication.run(DemoApplication.class, args);
	}

}
