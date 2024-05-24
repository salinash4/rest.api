package py.edu.ucsa.jweb.rest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { 
		"py.edu.ucsa.jweb.rest.api.web.controllers",
		"py.edu.ucsa.jweb.rest.api.core.services",
		"py.edu.ucsa.jweb.rest.api.core.repositories"})
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
