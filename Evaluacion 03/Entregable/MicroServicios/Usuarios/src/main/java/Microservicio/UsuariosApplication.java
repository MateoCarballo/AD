package Microservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsuariosApplication {
	@SpringBootApplication
	@EnableEurekaClient
	public static void main(String[] args) {
		SpringApplication.run(UsuariosApplication.class, args);
	}

}
