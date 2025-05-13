package Microservicio.controller;

import Microservicio.service.ReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservasController {

    private ReservasService reservasServiceImpl;
    @Autowired
    public ReservasController(ReservasService reservasServiceImpl) {
        this.reservasServiceImpl = reservasServiceImpl;
    }

    @PostMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Todo ok");
    }
}
