import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonPostgre {
    private static Connection connection;
    private final String usuario="postgres";
    private final String pas="abc123.";
    private final String url="jdbc:postgresql://localhost:5432/productos";

    private SingletonPostgre(){
        try {
            connection=DriverManager.getConnection(url,usuario,pas);
        }catch (SQLException a){
            System.out.println(a.toString());
        }
    }

    public static Connection concretarConexion(){
        if (connection == null){
            new SingletonPostgre();
        }
        return connection;
    }


}
