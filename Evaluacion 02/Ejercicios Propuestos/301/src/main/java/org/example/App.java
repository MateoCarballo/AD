package org.example;

import Entity.Departamento;
import Entity.Empleado;
import Repository.EmpleadoRepository;
import org.hibernate.Session;

import java.util.HashSet;

public class App 
{
    public static void main( String[] args )
    {
        Session session = HibernateUtil.get().openSession();

        int bucle =0;
        //Crear los departamentos y empleados sin relacionarlos entre ellos

        //Crear los 10 empleados
        Empleado[] empleados = crearEmpleados();
        //Crear los 5 departamentos
        Departamento[] departamentos = crearDepartamentos();

        for (Departamento departamento : departamentos) {

            //Añado dos empleados a cada departamento
            //Y despues de añadir el empleado al departamento le añado el departamento al empleado
            departamento.addEmployee(empleados[bucle]);
            empleados[bucle].setDepartamento(departamento);
            bucle++;
            departamento.addEmployee(empleados[bucle]);
            empleados[bucle].setDepartamento(departamento);
            bucle++;

        }



        EmpleadoRepository empleadoRepository = new EmpleadoRepository(session);
       /*
       AQUI
       TODA
       LA
       CHICHA
        */
        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }

    private static Departamento[] crearDepartamentos() {
        Departamento[] departamentos = new Departamento[5];
        for (int i = 0; i < departamentos.length; i++) {
            departamentos[i] = new Departamento();
            departamentos[i].setNombreDepar("Departamento_" + i);
            departamentos[i].setLocalidadDepar("Localidad" + i);
            departamentos[i].setEmpleados(new HashSet<>());
        }
        return departamentos;
    }

    private static Empleado[] crearEmpleados() {
        Empleado[] empleados = new Empleado[10];
        for (int i = 0; i < empleados.length; i++) {
            empleados[i] = new Empleado();
            empleados[i].setNombreEmpleado("Empleado_" + i);
            empleados[i].setPuesto("Puesto_" + i);
            empleados[i].setSueldo((i+1)*10000);

        }
        return empleados;
    }
}
