package Microservicio.controller;

import Microservicio.service.HabitacionService;
import Microservicio.service.HotelService;
import Microservicio.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservasController {

    private ReservaService reservaServiceImpl;
    private HotelService hotelServiceImpl;
    private HabitacionService habitacionServiceImpl;

    @Autowired
    public ReservasController(ReservaService reservaServiceImpl, HotelService hotelServiceImpl, HabitacionService habitacionServiceImpl) {
        this.reservaServiceImpl = reservaServiceImpl;
        this.hotelServiceImpl = hotelServiceImpl;
        this.habitacionServiceImpl = habitacionServiceImpl;
    }
    //Endpoint para confirmar la conexion correcta desde postman
    @PostMapping("/test")
    public ResponseEntity<?> testController(){
        return ResponseEntity.ok("Todo ok");
    }
    // EndPoint para poder consultar todos los datos desde postman
    @PostMapping("/habitacion/All")
    public ResponseEntity<?> habitaciontest(){
        return ResponseEntity.ok(habitacionServiceImpl.devolverTodos());
    }
    @PostMapping("/hotel/All")
    public ResponseEntity<?> hotelTest(){
        return ResponseEntity.ok(hotelServiceImpl.devolverTodos());
    }
    @PostMapping("/reserva/All")
    public ResponseEntity<?> reservaTest(){
        return ResponseEntity.ok(reservaServiceImpl.devolverTodos());
    }
}
