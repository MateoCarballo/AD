import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonPostgre {
    private String url = "jdbc:postgresql://localhost:5432/almacenes";
    private String user = "postgres";
    private String password = "abc123.";
    private static Connection connection ;

    private SingletonPostgre(){
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println("Error al crear la conexion");
        }

    }

    public static Connection getConnection(){
        if (connection == null){
            new SingletonPostgre();
        }
        return connection;

    }

}
