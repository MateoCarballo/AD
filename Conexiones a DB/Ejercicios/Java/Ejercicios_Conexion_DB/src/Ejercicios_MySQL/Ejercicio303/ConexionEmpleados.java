package Ejercicios_MySQL.Ejercicio303;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionEmpleados {
    public static Connection connection;
    final String USUARIO = "root";
    final String CLAVE = "abc123.";
    final String URL = "jdbc:mysql://localhost:3306/Empleados";

    private ConexionEmpleados(){
        try {
            connection = DriverManager.getConnection(URL,USUARIO,CLAVE);
        } catch (SQLException e) {
            System.out.println("Erro durante la conexion con la BD");
        }
    }

    public static Connection getInstance(){
        if (connection == null) new ConexionEmpleados();
        return connection;
    }
}
