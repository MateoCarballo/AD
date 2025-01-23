package org.example;

import Entity.Departamento;
import Entity.Empleado;
import Repository.DepartamentoRepository;
import Repository.EmpleadoRepository;
import org.hibernate.Session;

import java.util.ArrayList;

public class App 
{
    public static void main( String[] args )
    {
        Session session = HibernateUtil.get().openSession();
        DepartamentoRepository departamentoRepository = new DepartamentoRepository(session);

        Departamento departamento1 = new Departamento("Departamento 1", "Localidad 1");
        Departamento departamento2 = new Departamento("Departamento 2", "Localidad 2");
        Departamento departamento3= new Departamento("Departamento 3", "Localidad 3");
        Departamento departamento4 = new Departamento("Departamento 4", "Localidad 4");
        Departamento departamento5 = new Departamento("Departamento 5", "Localidad 5");

        ArrayList<Empleado> empleados = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String nombre = "Empleado " + i;
            empleados.add(new Empleado(nombre,"Puesto "+ i,1000.00 * i,10 * i, i+ "23456789A",true));
        }





        departamento1.addEmployee(empleados.get(0));
        departamento1.addEmployee(empleados.get(1));

        departamento2.addEmployee(empleados.get(2));
        departamento2.addEmployee(empleados.get(3));

        departamento3.addEmployee(empleados.get(4));
        departamento3.addEmployee(empleados.get(5));

        departamento4.addEmployee(empleados.get(6));
        departamento4.addEmployee(empleados.get(7));

        departamento5.addEmployee(empleados.get(8));
        departamento5.addEmployee(empleados.get(9));

        departamentoRepository.guardar(departamento1);
        departamentoRepository.guardar(departamento2);
        departamentoRepository.guardar(departamento3);
        departamentoRepository.guardar(departamento4);
        departamentoRepository.guardar(departamento5);



        /*
       AQUI
       TODA
       LA
       CHICHA
        */


        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }
}
