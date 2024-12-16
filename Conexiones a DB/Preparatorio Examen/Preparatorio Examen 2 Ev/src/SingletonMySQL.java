import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonMySQL{
  private static Connection conn;
  private final String url = "jdbc:mysql://localhost:3306/tienda";
  private final String user = "root";
  private final String password = "abc123.";

  private SingletonMySQL(){
      try {
          conn=DriverManager.getConnection(url,user,password);
      } catch (SQLException e) {
          System.out.println("Error" + e.getMessage());
      }
  }

  public static Connection getInstance(){
   if (conn==null){
       new SingletonMySQL();
   }
   return conn;
  }

  public static void closeConnection(){

  }

}