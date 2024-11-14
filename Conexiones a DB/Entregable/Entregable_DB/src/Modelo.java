import Connections.MySQL_Connection;
import Connections.PostgreSQL_Connection;

import javax.xml.transform.stream.StreamResult;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modelo {
    //TODO gestionar cada error por separado
    private Connection modeloConnection;

    public void crearCategoria(String nombreCategoria){
        modeloConnection = PostgreSQL_Connection.getPostgreSQLConnection();
        try (PreparedStatement preparedStatement = modeloConnection.prepareStatement
                ("INSERT INTO categorias(nombre_categoria) VALUES (?)")){
            preparedStatement.setString(1,nombreCategoria);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                modeloConnection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }

    }

    public void crearProveedor(String nombreProveedor, String nif, int telefono, String email ){

        // TODO aquí tengo que duplicarlo a narices,no?
        modeloConnection = PostgreSQL_Connection.getPostgreSQLConnection();
        try (PreparedStatement preparedStatement = modeloConnection.prepareStatement
                ("INSERT INTO proveedores(nombre_proveedor, contacto) " +
                        "VALUES (?, ROW(?,?,?))")){
            preparedStatement.setString(1,nombreProveedor);
            preparedStatement.setString(2,nombreProveedor);
            preparedStatement.setString(3,nif);
            preparedStatement.setInt(4,telefono);
            preparedStatement.setString(5,email);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                modeloConnection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }

    }

    public void eliminarProveedor(int id){
        modeloConnection = PostgreSQL_Connection.getPostgreSQLConnection();
        try (PreparedStatement preparedStatement = modeloConnection.prepareStatement
                ("DELETE FROM proveedores WHERE id_proveedor = ?")){

            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                modeloConnection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }
    }

    public void crearUsuario(String nombre, String email, int anho_nacimiento){
        modeloConnection = MySQL_Connection.getMySQLConnection();
        try (PreparedStatement preparedStatement = modeloConnection.prepareStatement
                ("INSERT INTO usuarios VALUES (?,?,?) ")){

            preparedStatement.setString(1,nombre);
            preparedStatement.setString(2,email);
            preparedStatement.setInt(3,anho_nacimiento);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                modeloConnection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }
    }

    public void eliminarUsuario(int id){
        //TODO pendiente de crear un metodo para compronar si existe el usuario en la base de datos

        if (comprobarUsuarioExiste(id)){
            modeloConnection = MySQL_Connection.getMySQLConnection();
            try(PreparedStatement preparedStatement = modeloConnection.prepareStatement
                    ("DELETE FROM usuarios WHERE id_usuario = ?")){
                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally{
                try {
                    modeloConnection.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexion");
                }
            }
        }


    }

    private boolean comprobarUsuarioExiste(int idParaComprobar) {
        modeloConnection = MySQL_Connection.getMySQLConnection();
        try(PreparedStatement preparedStatement = modeloConnection.prepareStatement
                ("SELECT nombre FROM usuarios WHERE id_usuario = ?")){
            preparedStatement.setInt(1,idParaComprobar);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                modeloConnection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }
    }

    public void crearProducto(String nombre, Double precio, int stock, String nombre_categoria, String nif){
        // TODO  Preguntar a Jose añadir a una DB y luego a la otra en diferentes conexiones simultaneas ? o paso a paso?
        modeloConnection = MySQL_Connection.getMySQLConnection();
        try(PreparedStatement preparedStatement = modeloConnection.prepareStatement(
                "INSERT INTO productos VALUES (?,?,?)")){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                modeloConnection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }
    }


    void listarProductosBajoStock(int stock){
        modeloConnection = MySQL_Connection.getMySQLConnection();
        try(PreparedStatement preparedStatement = modeloConnection.prepareStatement(
                "SELECT nombre_producto as Nombre, stock as Stock FROM productos WHERE stock <= ?")){
            preparedStatement.setInt(1,stock);
            ResultSet resultSet = preparedStatement.executeQuery();
            Producto product = new Producto();
            while(resultSet.next()){
                product.setNombre_producto(resultSet.getString(1));
                product.setStock(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                modeloConnection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }
    }

}
