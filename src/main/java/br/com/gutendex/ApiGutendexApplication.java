package br.com.gutendex;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API - Desafio Oracle One: Consumindo API do Gutendex.",
				version = "1.0",
				description = "API para consumir a API de livros do Gutendex.",
				contact = @Contact(name = "Carlos Roberto", email = "crrsj1@gmail.com")
		)
)
public class ApiGutendexApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGutendexApplication.class, args);
	}

}
