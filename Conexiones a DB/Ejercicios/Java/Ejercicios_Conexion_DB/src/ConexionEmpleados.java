import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionEmpleados {
    static Connection conn;
    private final String usuario = "root";
    private final String clave = "abc123.";
    private final String url = "jdbc:mysql://localhost:3306/Empleados";

    private ConexionEmpleados(){
        try{
            conn = DriverManager.getConnection(url,usuario,clave);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static Connection getInstance(){
        if(conn == null){
            new ConexionEmpleados();
        }
        return conn;
    }

}
