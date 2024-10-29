package Ejercicio304;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejercicio304 {
    /*
    Ejercicio 304
    Implementa un programa en Java donde mediante una clase llamada TransaccionEmpleado se
    permita insertar en la base de datos Empleados tres nuevos contables: Pedro, Lucía y Daniel que pertenecerán al departamento 1.
    ¿Qué pasa si hay un error al insertar alguno de los empleados?
    Utiliza transacciones para controlar que se inserten los 3 empleados a la vez y si ocurre un error, no se insertará ninguno.
     */
    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Empleados","root","abc123.")){
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO empleado (NSS,Nombre,Numdept) VALUES ( ? , ? , ? )")){
                connection.setAutoCommit(false);
                //Introducir a Pedro
                preparedStatement.setInt(1,12345);
                preparedStatement.setString(2,"Pedro");
                preparedStatement.setInt(3,1);
                preparedStatement.executeUpdate();

                //Introducir a Lucía
                preparedStatement.setInt(1,23451);
                preparedStatement.setString(2,"Lucía");
                preparedStatement.setInt(3,2);
                preparedStatement.executeUpdate();

                //Introducir a Daniel
                preparedStatement.setInt(1,34512);
                preparedStatement.setString(2,"Daniel");
                preparedStatement.setInt(3,1);
                preparedStatement.executeUpdate();

                connection.commit();
                connection.setAutoCommit(true);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
