package Ejercicio309.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Operaciones  {
    final private String URL = "jdbc:mysql//localhost:3306/library";
    final private String USER = "root";
    final private String PSW = "abc123.";
    private Operaciones(){

    }
    static Connection connection;

    //CONEXION Y DESCONEXION DE LA DATABASE

    public void openConnection(){
        try {
            if (connection == null){
                connection = DriverManager.getConnection(URL,USER,PSW);
            }
        } catch (SQLException e) {
            System.out.println("Error durante la conexion con la DB");
        }
    }

    public void closeConnection(){
        try {
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error durante el cierre de la conexion a la DB");
        }
    }

    public boolean existsClient(String id){
        // TODO -> Pendiente de incluir el código.
        return true;
    }

}
