package Microservicio.service;

import Microservicio.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservasService {

    private ReservaRepository reservasRepositoryImpl;

    @Autowired
    public ReservasService(ReservaRepository reservasRepositoryImpl) {
        this.reservasRepositoryImpl = reservasRepositoryImpl;
    }
}
