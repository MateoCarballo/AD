package org.example;

import Entity.Departamento;
import Entity.Empleado;
import Repository.EmpleadoRepository;
import org.hibernate.Session;

import java.util.HashSet;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int bucle =0;
        //Crear los departamentos y empleados sin relacionarlos entre ellos

        //Crear los 10 empleados
        Empleado[] empleados = crearEmpleados();
        //Crear los 5 departamentos
        Departamento[] departamentos = crearDepartamentos();


        //Vincular los empleados a los departamentos
        for (Departamento departamento : departamentos) {
            for (int j = 0; j <; j++) {
                //Añado a la lista de empleados del departamento
                departamento.addEmployee(empleados[j]);
                //Añado al departamento del empleado
                empleados[j].setDepartamento(departamento);
            }

        }

        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        EmpleadoRepository er = new EmpleadoRepository(session);
        // Aqui todo el trabajo
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
