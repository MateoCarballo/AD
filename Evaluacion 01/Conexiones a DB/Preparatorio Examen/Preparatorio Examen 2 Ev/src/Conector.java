import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
    private static Conector instance;
    public static Conector getInstace(){
        if(instance == null){
            instance = new Conector();
        }
        return  instance;
    }
    private String url = "jdbc:mysql://localhost:3306/bbdd";
    private String user = "root";
    private String pass = "123asd";
    private Connection conn;

    public void openConnection(){
        try {
            conn = DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
