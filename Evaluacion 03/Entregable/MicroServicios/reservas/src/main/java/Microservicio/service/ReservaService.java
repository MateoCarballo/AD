package Microservicio.service;

import Microservicio.entity.Reserva;
import Microservicio.repository.HabitacionRepository;
import Microservicio.repository.HotelRepository;
import Microservicio.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private ReservaRepository reservaRepositoryImpl;

    @Autowired
    public ReservaService(ReservaRepository reservaRepositoryImpl) {
        this.reservaRepositoryImpl = reservaRepositoryImpl;
    }

    public List<Reserva> devolverTodos(){
        return reservaRepositoryImpl.findAll();
    }
}
