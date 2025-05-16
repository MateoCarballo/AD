package Microservicio.repository;

import Microservicio.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitacionRepository extends JpaRepository<Reserva,Long> {
}
