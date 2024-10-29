package Ejercicio303;

import java.sql.*;
import java.util.ArrayList;

public class ConsultarNombres {
    private ConsultarNombres(){

    }
    public static ArrayList<String>  devolverEmpleados(char inicial){
        return  buscarEmpleados(conectarDB(),inicial);
    }

    private static Connection conectarDB() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Empleados","root","abc123.");
        } catch (SQLException e) {
            System.out.println("Error durante la conexion a la Base de Datos");
        }
        return connection;
    }

    private static ArrayList<String> buscarEmpleados(Connection conn, char inicial) {
        ArrayList<String> empleados = new ArrayList<>();
        try (PreparedStatement preparedStatement =  conn.prepareStatement("SELECT Nombre FROM empleado WHERE Nombre LIKE ?")){
            preparedStatement.setString(1,inicial + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                empleados.add(resultSet.getString(1));
            }
        }catch (SQLException e) {
            System.out.println("Error durante la busqueda en la base de datos");
        }finally{
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error al intentar cerrar la conexion con la BD");
                }
            }
        }
        return empleados;
    }
}
