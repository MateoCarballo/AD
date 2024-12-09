import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonMySQL{
    private String url = "jdbc:mysql://localhost:3306/Productos";
    private String user = "root";
    private String password = "abc123.";
    // Deberia hacer esto tambien estatico pero si lo hago podria acceder sin instanciar ningun objeto
    private static Connection connection;

    private SingletonMySQL(){
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println("Error al crear la conexion con la DB");
        }
    }

    public static Connection getConnection(){
        if(SingletonMySQL.connection == null){
            new SingletonMySQL();
        }
        return connection;
    }
}