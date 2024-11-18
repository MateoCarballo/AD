import Connections.MySQL_Connection;
import Connections.PostgreSQL_Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Modelo {
    //TODO gestionar cada error por separado
    private Connection modeloConnectionMySQL;
    private Connection modeloConnectionPostgre;


    public void crearCategoria(String nombreCategoria){
        modeloConnectionPostgre = PostgreSQL_Connection.getPostgreSQLConnection();
        try (PreparedStatement preparedStatement = modeloConnectionPostgre.prepareStatement
                ("INSERT INTO categorias(nombre_categoria) VALUES (?)")){
            preparedStatement.setString(1,nombreCategoria);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                modeloConnectionPostgre.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }

    }

    public void crearProveedor(String nombreProveedor, String nif, int telefono, String email ){

        // TODO aquí tengo que duplicarlo a narices,no?
        modeloConnectionPostgre = PostgreSQL_Connection.getPostgreSQLConnection();
        try (PreparedStatement preparedStatement = modeloConnectionPostgre.prepareStatement
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
                modeloConnectionPostgre.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }

    }

    public void eliminarProveedor(int id){
        modeloConnectionPostgre = PostgreSQL_Connection.getPostgreSQLConnection();
        try (PreparedStatement preparedStatement = modeloConnectionPostgre.prepareStatement
                ("DELETE FROM proveedores WHERE id_proveedor = ?")){

            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                modeloConnectionPostgre.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }
    }

    public void crearUsuario(String nombre, String email, int anho_nacimiento){
        modeloConnectionMySQL = MySQL_Connection.getMySQLConnection();
        try (PreparedStatement preparedStatement = modeloConnectionMySQL.prepareStatement
                ("INSERT INTO usuarios VALUES (?,?,?) ")){

            preparedStatement.setString(1,nombre);
            preparedStatement.setString(2,email);
            preparedStatement.setInt(3,anho_nacimiento);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                modeloConnectionMySQL.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }
    }

    public void eliminarUsuario(int id){
        //TODO pendiente de crear un metodo para compronar si existe el usuario en la base de datos

        if (comprobarUsuarioExiste(id)){
            modeloConnectionMySQL = MySQL_Connection.getMySQLConnection();
            try(PreparedStatement preparedStatement = modeloConnectionMySQL.prepareStatement
                    ("DELETE FROM usuarios WHERE id_usuario = ?")){
                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally{
                try {
                    modeloConnectionMySQL.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexion");
                }
            }
        }


    }

    private boolean comprobarUsuarioExiste(int idParaComprobar) {
        modeloConnectionMySQL = MySQL_Connection.getMySQLConnection();
        try(PreparedStatement preparedStatement = modeloConnectionMySQL.prepareStatement
                ("SELECT nombre FROM usuarios WHERE id_usuario = ?")){
            preparedStatement.setInt(1,idParaComprobar);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                modeloConnectionMySQL.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }
    }

    public void crearProducto(String nombre, Double precio, int stock, String nombre_categoria, String nif){
        // TODO  Preguntar a Jose añadir a una DB y luego a la otra en diferentes conexiones simultaneas ? o paso a paso?
        modeloConnectionMySQL = MySQL_Connection.getMySQLConnection();
        try(PreparedStatement preparedStatement = modeloConnectionMySQL.prepareStatement(
                "INSERT INTO productos VALUES (?,?,?)")){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                modeloConnectionMySQL.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }
    }


    public void listarProductosBajoStock(int stock){
        String nombre = "";
        int stockEncontrado = 0;
        ArrayList <Producto> productosFiltrados = new ArrayList<>();
        modeloConnectionMySQL = MySQL_Connection.getMySQLConnection();
        try(PreparedStatement preparedStatement = modeloConnectionMySQL.prepareStatement(
                "SELECT nombre_producto, stock FROM productos WHERE stock <= ?")){
            preparedStatement.setInt(1,stock);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                nombre = resultSet.getString(1);
                stockEncontrado = resultSet.getInt(1);
                productosFiltrados.add(new Producto(nombre,stockEncontrado));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                modeloConnectionMySQL.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }
    }

    /*
    Obtener el total de pedidos realizados por cada usuario (MySQL)
    Se implementará una función con la siguiente cabecera:
    void obtenerTotalPedidosUsuarios().
    Mediante una consulta se tendrá que obtener toda la información e
    imprimir por pantalla: el nombre del usuario y el total de pedidos que ha hecho.
     */
    public ArrayList <Usuario> obtenerTotalPedidosUsuarios(){
        ArrayList <Usuario> usuariosFiltrados = new ArrayList<Usuario>();
        int contadorNumeroUsuarios = 0;
        modeloConnectionMySQL = MySQL_Connection.getMySQLConnection();
        try(PreparedStatement preparedStatement = modeloConnectionMySQL.prepareStatement(
                "SELECT u.nombre as Usuario, COUNT(p.id_pedido) as Total FROM pedidos as p INNER JOIN usuarios as u WHERE p.id_usuario = u.id_usuario GROUP BY u.nombre;")){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                    String nombre = resultSet.getString("Usuario");
                    int numeroPedidos = resultSet.getInt("Total");
                    usuariosFiltrados.add(new Usuario(nombre,numeroPedidos));
            }

            return usuariosFiltrados;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                modeloConnectionMySQL.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
        /*
        Obtener la cantidad de productos almacenados por cada almacén (PostgreSQL)
        Se implementará una función con la siguiente cabecera: void obtenerCantidadProductosEnCadaAlmacen().
        Mediante una consulta se tendrá que obtener toda la información solicitada y pintar por pantalla el
        nombre del almacén y el total de productos de los que dispone.
         */
    void obtenerCantidadProductosEnCadaAlmacen(){

    }

}
