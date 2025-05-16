package Microservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReservasApplication {
	@SpringBootApplication
	@EnableEurekaClient
	public static void main(String[] args) {
		SpringApplication.run(ReservasApplication.class, args);
	}

}
