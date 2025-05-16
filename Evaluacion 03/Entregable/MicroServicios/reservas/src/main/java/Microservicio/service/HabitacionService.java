package Microservicio.service;

import Microservicio.entity.Habitacion;
import Microservicio.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionService {

    private HabitacionRepository habitacionRepositoryImpl;
    @Autowired

    public List<Habitacion> devolverTodos() {
        return habitacionRepositoryImpl.findAll();
    }
}

