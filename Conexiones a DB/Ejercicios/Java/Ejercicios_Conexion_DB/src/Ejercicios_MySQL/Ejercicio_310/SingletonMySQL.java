package Ejercicios_MySQL.Ejercicio_310;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonMySQL {
    private final String url = "jdbc:mysql://localhost:3306/tienda";;
    private final String user = "root"; ;
    private final String password = "abc123." ;
    private static Connection conn;

    private SingletonMySQL(){
        try {
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            System.out.println("Imposible conectar usando patron singleton");
        }
    }

    public static Connection getConnectionMySQLTienda(){
        if (conn == null){
            new SingletonMySQL();
        }
        return conn;
    }
}
