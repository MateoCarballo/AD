package Microservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReservasApplication {
	//TODO preguntar a Jose. Me surgen sobre la estructura de los proyectos y que necesito tener en cada uno segun sus necesidades.
	// Por ejemplo aqui que tenemos varias tablas tendremos un repositorio por cada una de ellas
	public static void main(String[] args) {
		SpringApplication.run(ReservasApplication.class, args);
	}

}
