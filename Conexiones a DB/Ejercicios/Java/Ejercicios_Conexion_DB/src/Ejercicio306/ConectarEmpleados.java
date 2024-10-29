package Ejercicio306;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarEmpleados {

    public static Connection connection;
    private String URL = "jdbc:mysql://localhost:3306/Empleados";
    private String USER = "root";
    private String PSW = "abc123.";

    private ConectarEmpleados(){
        try{
             connection = DriverManager.getConnection(URL,USER,PSW);
        }catch (SQLException e){
            System.out.println("Error al conectarse a la base de datos empleados");
        }
    }

    public static Connection getInstance(){
        if (connection == null){
            new ConectarEmpleados();
        }
        return connection;
    }
}
