package Ejercicio._4;

import Ejercicio._4.entity.Departamento;
import Ejercicio._4.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/503")
public class Controller {
    Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping("/depto")
    public ResponseEntity<Boolean> createDepto(@RequestBody Departamento depto){
        service.createDepartament(depto);
        return ResponseEntity.ok(true); // TODO pendiente de cambiarlo para saber si se añade o no
    }

    @PostMapping("/emp")
    public ResponseEntity<Boolean> createEmp(@RequestBody Empleado emp){
        service.createEmployee(emp);
        return ResponseEntity.ok(true);
    }
}
