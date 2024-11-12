package Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL_Connection {
    private final String URL = "jdbc:mysql://localhost:3306/Productos";
    private final String USER = "root";
    private final String PASSWORD = "abc123.";
    static Connection connection;

    private MySQL_Connection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro en la conexion con la base datos mysql");
        }
    }

    public Connection getMySQLConnection (){
        if (connection == null) {
            new MySQL_Connection();
        }
         return connection;
    }
}

