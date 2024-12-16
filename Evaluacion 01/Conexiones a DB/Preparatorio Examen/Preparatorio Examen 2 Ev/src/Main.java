import com.mysql.cj.xdevapi.PreparableStatement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static Connection connection = SingletonMySQL.getInstance();
    public static Connection connectionPostgre = SingletonPostgre.getInstance();
    private static Conector conn = Conector.getInstace();

    public static void main(String[] args) throws SQLException {
        conn.openConnection();



        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //ejemploConexionYConsultaSimpleMySQL();
        //ejemploConexionYConsultaSimplePostgre();
        //ejemploDosDBSingleton();
        //ejemploConsultaCompleja();
        //consultaSelect();
        //consultaCreate();
        //consultaDelete("silla");
        consultaUpdate();
        conn.
         }

    private static void consultaUpdate() {
        String consulta = """
                UPDATE producto
                SET nombre = ?
                WHERE id = ?                
                """;
        String nombre = "pantallassss";

        try(PreparedStatement preparedStatement = connection.prepareStatement(consulta)) {
            preparedStatement.setString(1,nombre);
            preparedStatement.setInt(2,1);
            if (preparedStatement.executeUpdate() != 0){
                System.out.println("Actualizado el valor de la tupla");
            }

        } catch (SQLException e) {
            System.out.println("Error durante la consulta");
        }
    }

    private static void consultaDelete(String nombre) throws SQLException {
        /*
        Elimina un producto introduciendo el nombre
         */
        //Buscar el id asociado al nombre
        //Borrar el id ( donde está como clave foranea)
        //Borrar el producto
        int idEncontrada = 0;

        PreparedStatement preparedStatement=connection.prepareStatement("SELECT id FROM producto WHERE nombre = ?");
        preparedStatement.setString(1,nombre);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            idEncontrada = resultSet.getInt(1);
        }

        preparedStatement = connection.prepareStatement("DELETE FROM producto_pedido WHERE idProducto = ?");
        preparedStatement.setInt(1,idEncontrada);
        if (preparedStatement.executeUpdate()>0){
            System.out.println("Eliminada tupa en la tabla producto_pedido");
        }else {
            System.out.println("error");
        }

        preparedStatement = connection.prepareStatement("DELETE FROM producto WHERE id = ?");
        preparedStatement.setInt(1,idEncontrada);
        if (preparedStatement.executeUpdate()>0){
            System.out.println("Eliminada tupa en la tabla producto");
        }else {
            System.out.println("error");
        }



    }

    private static void consultaCreate() {
        String consulta = "INSERT INTO producto (nombre,descripcion,precio) VALUES (?,?,?)";
        String nombre = "nombress";
        String descripcion = "descripcionss";
        double precio = 10.22;

        try(PreparedStatement preparedStatement = connection.prepareStatement(consulta,Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1,nombre);
            preparedStatement.setString(2,descripcion);
            preparedStatement.setDouble(3,precio);
            if (preparedStatement.executeUpdate() != 0){
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    while(resultSet.next()){
                        int idGenerado = resultSet.getInt(1);
                        System.out.println("El id generado es -> " + idGenerado);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }


    }

    private static void consultaSelect() {
        String consulta = "SELECT * FROM producto WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consulta)){
            preparedStatement.setInt(1,1);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    int id = resultSet.getInt(1);
                    String nombre = resultSet.getString(2);
                    String descripcion = resultSet.getString(3);
                    double precio = resultSet.getDouble(4);
                    System.out.println(id + nombre + descripcion + precio);
                }
            }
        }catch (SQLException e){
            System.out.println("Error en el prepared statement");
        }
    }

    private static void ejemploConsultaCompleja() {
        Connection connMySQL = SingletonMySQL.getInstance();
        Connection connPostgre  =   SingletonPostgre.concretarConexion();

        try (PreparedStatement preparedStatement = connMySQL.prepareStatement("""
                SELECT nombre_producto FROM productos WHERE id_producto = ?
                """)){
            int valorIntroducido = 1;
            int numeroDeParametro = 1;
            preparedStatement.setInt(numeroDeParametro,valorIntroducido);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    String nombre = resultSet.getString(1);
                    System.out.println("Nombre del producto -> " + nombre);
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

    private static void ejemploDosDBSingleton() {
        Connection connMySQL = SingletonMySQL.getInstance();
        Connection connPostgre  =   SingletonPostgre.concretarConexion();

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
    /*
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
     */
    /*
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
     */

}

