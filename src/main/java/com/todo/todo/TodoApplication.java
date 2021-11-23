package com.todo.todo;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}
	@Bean
	public OpenAPI customAPI(
			@Value("ToDo API") String title,
			@Value("1.0") String version,
			@Value("ToDo projesi") String description
	) {
		return new OpenAPI().info(new Info()
				.title(title)
				.version(version)
				.description(description)
				.contact(new Contact()
						.name("Emre Serbes")
						.url("https://github.com/mrserbes")
						.email("emreserbes8391@gmail.com"))
				.license(new License().name("GNU GPLv3").url("https://github.com/mrserbes")));
	}

}
