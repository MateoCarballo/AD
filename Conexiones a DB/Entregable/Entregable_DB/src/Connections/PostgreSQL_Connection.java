package Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQL_Connection {
    private final String URL = "jdbc:postgresql://localhost:5432/Almacenes";
    private final String USER = "root";
    private final String PASSWORD = "abc123.";
    private static Connection connection;

    private PostgreSQL_Connection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro en la conexion con la base datos postgre");
        }
    }

    public static Connection getPostgreSQLConnection (){
        if (connection == null) {
            new PostgreSQL_Connection();
        }
        return connection;
    }
}
