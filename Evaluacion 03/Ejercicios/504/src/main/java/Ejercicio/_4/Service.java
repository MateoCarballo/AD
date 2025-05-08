package Ejercicio._4;

import Ejercicio._4.entity.Departamento;
import Ejercicio._4.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {
    Repository repository;

    @Autowired
    public Service(Repository repository) {
        this.repository = repository;
    }

    public void createDepartament(Departamento dpto){
        repository.createDpto(dpto);
    }
    public void createEmployee(Empleado emp){
        repository.createEmp(emp);
    }
}
