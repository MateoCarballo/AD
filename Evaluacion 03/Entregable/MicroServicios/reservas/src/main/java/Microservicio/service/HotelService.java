package Microservicio.service;

import Microservicio.entity.Hotel;
import Microservicio.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private HotelRepository hotelRepositoryImpl;
    @Autowired
    public HotelService(HotelRepository hotelRepositoryImpl) {
        this.hotelRepositoryImpl = hotelRepositoryImpl;
    }

    public List<Hotel> devolverTodos() {
        return hotelRepositoryImpl.findAll();
    }
}
