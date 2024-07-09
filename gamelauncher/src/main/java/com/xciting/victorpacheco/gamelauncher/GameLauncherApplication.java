package com.xciting.victorpacheco.gamelauncher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main application class for the Game Launcher application.
 * Configures Spring Boot and starts the application.
 *
 * @autor <a href="mailto:victorpacheco3107@gmail.com">Victor Pacheco</a>
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.xciting.victorpacheco.gamelauncher.infrastructure.adapters.outbound.persistence")
@EntityScan(basePackages = "com.xciting.victorpacheco.gamelauncher.infrastructure.entities")
public class GameLauncherApplication {
	public static void main(String[] args) {
		SpringApplication.run(GameLauncherApplication.class, args);
	}
}
