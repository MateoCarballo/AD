import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnMySQL {
    private static Connection conn;
    private final String usuario = "root";
    private final String clave = "abc123.";
    private final String url = "jdbc:mysql://localhost:3306/Empleados";

    private ConnMySQL(){
        try{
            this.conn = DriverManager.getConnection(url,usuario,clave);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
