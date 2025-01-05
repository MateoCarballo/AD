import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnectionPostgre {
    private final String url = "jdbc:postgresql://localhost:5432/academia";
    private final String user = "postgres";
    private final String password = "abc123.";
    private static Connection connection;

    private SingletonConnectionPostgre() {
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println("Error al crear conexion -> " + e.getMessage());
        }
    }

    public static Connection getConnection(){
        if (connection == null) new SingletonConnectionPostgre();
        return connection;
    }

    public static void closeConnection(){
        try{
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }catch (SQLException e){
            System.out.println("Error al cerrar la conexion -> " + e.getMessage());
        }
    }
}
