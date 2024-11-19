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

/*
Crear una nueva categoría (PostgreSQL).
Se implementará una función con la siguiente cabecera: void crearCategoria(String nombreCategoria).
Se recibirá un String que será el nombreCategoria y se añadirá a la base de datos.
* */

    public void crearCategoria(String nombreCategoria){
        modeloConnectionPostgre = PostgreSQL_Connection.getPostgreSQLConnection();
        try (PreparedStatement preparedStatement = modeloConnectionPostgre.prepareStatement
                ("INSERT INTO categorias(nombre_categoria) VALUES (?)")){
            preparedStatement.setString(1,nombreCategoria);
            int rowsAffected = preparedStatement.executeUpdate();
            //TODO mostras una ventana emergente que confierme el insert y las filas afectadas.

        } catch (SQLException e) {
            System.out.println("Error en la creacion de la categoría");;
        } finally{
            try {
                modeloConnectionPostgre.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion Postgre");
            }
        }

    }
/*
* Crear un nuevo proveedor (PostgreSQL)
Se implementará una función con la siguiente cabecera: void crearNuevoProveedor(String nombreProveedor, String nif, int telefono, String email).
Se recibirá todos los datos del proveedor y se añadirán en la base de datos.
* */
    public void crearProveedor(String nombreProveedor, String nif, int telefono, String email ){
        modeloConnectionPostgre = PostgreSQL_Connection.getPostgreSQLConnection();
        try (PreparedStatement preparedStatement = modeloConnectionPostgre.prepareStatement
                ("INSERT INTO proveedores(nombre_proveedor, contacto) " +
                        "VALUES (?, ROW(?,?,?,?))")){
            preparedStatement.setString(1,nombreProveedor);
            preparedStatement.setString(2,nombreProveedor);
            preparedStatement.setString(3,nif);
            preparedStatement.setString(4,String.valueOf(telefono));
            preparedStatement.setString(5,email);
            //TODO pendiente de revisar que los datos introducidos sean correctos (Esto se puede generalizar a todos los metodos)
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error durante la creacion del proveedor");
        } finally{
            try {
                modeloConnectionPostgre.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }

    }
/*
Eliminar un nuevo proveedor (PostgreSQL)
Se implementará una función con la siguiente cabecera: void eliminarProveedor(int id).
Se tendrá que comprobar si el id indicado existe y si es así, eliminarlo de la base de datos.
 */
    public void eliminarProveedor(int id){
        //TODO pendiente de comprobar que el proveedor existe antes de intentar eliminarlo de la DB
        modeloConnectionPostgre = PostgreSQL_Connection.getPostgreSQLConnection();
        try (PreparedStatement preparedStatement = modeloConnectionPostgre.prepareStatement
                ("DELETE FROM proveedores WHERE id_proveedor = ?")){

            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error durante la eliminacion del proveedor");
        } finally{
            try {
                modeloConnectionPostgre.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }
    }
/*
Crear un nuevo usuario (MySQL)
Se implementará una función con la siguiente cabecera: void crearUsuario(String nombre, String email, int anho_nacimiento).
Se recibirán todos los datos del usuario.
 */
    public void crearUsuario(String nombre, String email, int anho_nacimiento){
        //TODO gestionar que los valores que llegan aquí cumplan con la forma que deberian tener (año con 4 digitos, formato email y nombre en mayusculas)
        modeloConnectionMySQL = MySQL_Connection.getMySQLConnection();
        try (PreparedStatement preparedStatement = modeloConnectionMySQL.prepareStatement
                ("INSERT INTO usuarios (nombre,email,ano_nacimiento) VALUES (?,?,?) ")){

            preparedStatement.setString(1,nombre);
            preparedStatement.setString(2,email);
            preparedStatement.setInt(3,anho_nacimiento);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error durante la creaciond de un nuevo usario");
        } finally{
            try {
                modeloConnectionMySQL.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }
    }
/*
Eliminar un usuario (MySQL)
Se implementará una función con la siguiente cabecera: void eliminarUsuario(int id).
Se tendrá que comprobar si el id indicado existe y si es así, eliminarlo de la base de datos.
 */
    public void eliminarUsuario(int id){
        //TODO pendiente de crear un metodo para compronar si existe el usuario en la base de datos
        modeloConnectionMySQL = MySQL_Connection.getMySQLConnection();
        if (comprobarUsuarioExiste(id)){
            try(PreparedStatement preparedStatement = modeloConnectionMySQL.prepareStatement
                    ("DELETE FROM usuarios WHERE id_usuario = ?")){
                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Error durante la eliminacion de un nuevo usuario");
            } finally{
                try {
                    modeloConnectionMySQL.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexion");
                }
            }
        }


    }
/*
Metodo añadido por mi cuenta para asegurarme de que el usuario existe antes de intentar eliminarlo de la base de datos
 */
    private boolean comprobarUsuarioExiste(int idParaComprobar) {
        boolean existeUsuario = false;
        modeloConnectionMySQL = MySQL_Connection.getMySQLConnection();
        try(PreparedStatement preparedStatement = modeloConnectionMySQL.prepareStatement
                ("SELECT nombre FROM usuarios WHERE id_usuario = ?")){
            preparedStatement.setInt(1,idParaComprobar);
            ResultSet resultSet = preparedStatement.executeQuery();

            existeUsuario = resultSet.next();

        } catch (SQLException e) {
           System.out.println("Error durante la busqueda de usuario por id");;;
        }
        return existeUsuario;
    }
/*
Crear nuevo producto (nombre, precio, stock, categoria, proveedor) (MySQL + PostgreSQL)
Se implementará una función con la siguiente cabecera:
void crearProducto(String nombre, Double precio, int stock, String nombre_categoria, String nif).
Se tendrá que obtener el id de la categoría y el id del proveedor a partir del nombre y del nif.
Se añadirá en la base de datos MySQL y en la base de datos PostgreSQL.
El identificador del producto tendrá que ser el mismo en ambas bases de datos.
 */
    public void crearProducto(String nombre, Double precio, int stock, String nombre_categoria, String nif){
        // TODO  trabajando aquí
        int idProdutcotIntroducido = -1;
        int idProveedor = -1;
        int idCategoria = -1;
        PreparedStatement preparedStatement = null;
        modeloConnectionMySQL = MySQL_Connection.getMySQLConnection();
        modeloConnectionPostgre = PostgreSQL_Connection.getPostgreSQLConnection();
        //INSERTAMOS EN LA BASE DE DATOS MYSQL EL NUEVO PRODUCTO PARA GENERAR UN ID
        try{
            preparedStatement = modeloConnectionMySQL.prepareStatement(
                    "INSERT INTO productos (nombre_producto,precio,stock) " +
                            "VALUES (?,?,?)");
            preparedStatement.setString(1,nombre);
            preparedStatement.setDouble(2,precio);
            preparedStatement.setInt(3,stock);
            preparedStatement.executeUpdate();

            preparedStatement = modeloConnectionMySQL.prepareStatement(
                    "SELECT LAST_INSERT_ID();");

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                idProdutcotIntroducido = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error en la creacion de un producto en la DB MySQL");
        }finally {
            try{
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar el prepared statement");
            }
        }

        try{
            preparedStatement = modeloConnectionPostgre.prepareStatement(
                    "SELECT id_categoria FROM categorias WHERE nombre_categoria = ?");
            preparedStatement.setString(1,nombre_categoria);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                idCategoria = resultSet.getInt(1);
            }
            preparedStatement.close();

            preparedStatement = modeloConnectionPostgre.prepareStatement(
                    "SELECT id_proveedor FROM proveedores WHERE contacto.nif = ?"
            );


            preparedStatement = modeloConnectionPostgre.prepareStatement(
                    "INSERT INTO productos (id_producto,id_proveedor,id_categoria) " +
                            "VALUES (?,?,?)");
            preparedStatement.setInt(1,idProdutcotIntroducido);
            preparedStatement.setInt(2,idProveedor);
            preparedStatement.setInt(3,idCategoria);
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
    Eliminar un producto por su nombre (MySQL + PostgreSQL)
    Se implementará una función con la siguiente cabecera:
    void eliminarProductoPorNombre(String nombre).
    Se tendrá que eliminar el producto de ambas bases de datos.

    */

    public void eliminarProductoPorNombre(String nombre){
        modeloConnectionMySQL = MySQL_Connection.getMySQLConnection();

        try(PreparedStatement preparedStatement = modeloConnectionMySQL.prepareStatement(
                ""
        )){

        }catch (SQLException e){
            System.out.println("Error en la conexion a la DB MySQL");
        }finally {
            try {
                modeloConnectionMySQL.close();
            } catch (SQLException e) {
                System.out.println("Error al desconectarnos de la base de datos MySQL");;
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
