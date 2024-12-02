import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //ejemploConexionYConsultaSimpleMySQL();
        //ejemploConexionYConsultaSimplePostgre();
        ejemploDosDBSingleton();
         }

    private static void ejemploDosDBSingleton() {
        Connection connMySQL = SingletonMySQL.getConnection();
        Connection connPostgre  =   SingletonPostgre.getConnection();

        try (PreparedStatement preparedStatement = connMySQL.prepareStatement("""
                SELECT * FROM productos
                """)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt(1);
                    System.out.println("Id del producto -> " + id);
                }
            }
        } catch (SQLException e) {
            System.out.println("ERRROR");
        }

        try (PreparedStatement preparedStatement = connPostgre.prepareStatement("""
                SELECT * FROM productos
                """)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt(1);
                    System.out.println("Id-> " + id);
                }
            }
        } catch (SQLException e) {
            System.out.println("ERRROR");
        }

    }

    private static void ejemploConexionYConsultaSimpleMySQL() {
        String url = "jdbc:mysql://localhost:3306/Productos";
        String user = "root";
        String password = "abc123.";

        try(Connection connection = DriverManager.getConnection(url,user,password)){
            try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM productos")){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    while(resultSet.next()){
                        int id = resultSet.getInt(1);
                        String nombre = resultSet.getString(2);
                        double precio = resultSet.getDouble(3);
                        int stock = resultSet.getInt(4);
                        System.out.println(id + "\n" + nombre + "\n" + precio + "\n" + stock + "\n");
                    }
                } catch (SQLException e) {
                    System.out.println("Error al escribir los valores de la tabla");
                }
            }catch (SQLException e){
                System.out.println("Error al preparar la consulta");
            }
        } catch (SQLException e) {
            System.out.println("Error al crear la conexión");
        }
    }

    private static void ejemploConexionYConsultaSimplePostgre() {
        String url = "jdbc:postgresql://localhost:5432/almacenes";
        String user = "postgres";
        String pwd = "abc123.";

        try(Connection connection = DriverManager.getConnection(url,user,pwd)){
            try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM productos")){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    while(resultSet.next()){
                        int col1 = resultSet.getInt(1);
                        int col2 = resultSet.getInt(2);
                        int col3 =resultSet.getInt(3);
                        System.out.println(col1 + "\n" + col2 + "\n" + col3 + "\n");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la conexion");
        }

    }

    /*
    IMPRESCINDIBLE QUE ESTO ESTÉ EN OTRA CLASE

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

    public static Connection createConnection(){
        if(SingletonMySQL.connection == null){
            new SingletonMySQL();
        }
        return connection;
    }
}
     */

}

