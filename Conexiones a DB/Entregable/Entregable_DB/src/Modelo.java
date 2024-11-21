import Connections.MySQL_Connection;
import Connections.PostgreSQL_Connection;

import java.sql.*;
import java.util.ArrayList;

public class Modelo {
    //TODO gestionar cada error por separado
    private Connection modeloConnectionMySQL;
    private Connection modeloConnectionPostgre;

/*
1 -> Crear una nueva categoría (PostgreSQL).
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
            System.out.println("Error en la creacion de la categoría");
        } finally{
            try {
                modeloConnectionPostgre.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion Postgre");
            }
        }

    }
/*
* 2 -> Crear un nuevo proveedor (PostgreSQL)
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
3 -> Eliminar un nuevo proveedor (PostgreSQL)
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
4 -> Crear un nuevo usuario (MySQL)
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
5 -> Eliminar un usuario (MySQL)
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
           System.out.println("Error durante la busqueda de usuario por id");
        }
        return existeUsuario;
    }
/*
6 -> Crear nuevo producto (nombre, precio, stock, categoria, proveedor) (MySQL + PostgreSQL)
Se implementará una función con la siguiente cabecera:
void crearProducto(String nombre, Double precio, int stock, String nombre_categoria, String nif).
Se tendrá que obtener el id de la categoría y el id del proveedor a partir del nombre y del nif.
Se añadirá en la base de datos MySQL y en la base de datos PostgreSQL.
El identificador del producto tendrá que ser el mismo en ambas bases de datos.
 */
    public void crearProducto(String nombre, Double precio, int stock, String nombre_categoria, String nif){

        int idProdutcotIntroducido = -1;
        int idProveedor = -1;
        int idCategoria = -1;
        ResultSet resultSet;
        PreparedStatement preparedStatement;
        modeloConnectionMySQL = MySQL_Connection.getMySQLConnection();
        modeloConnectionPostgre = PostgreSQL_Connection.getPostgreSQLConnection();
        /*
        Pasos seguidos para llevar a cabo la insercion de un nuevo producto:
            1 -> INSERTAR EL PRODUCTO EN DB MYSQL Y OBTENER EL ID (del nuevo producto creado).
            2 -> OBTENEMOS EL id_categoria DESDE EL DATO 'nombre_categoria' DE LA TABLA CATEGORIAS (POSTGRE)
            3 -> OBTENER EL id_proveedor DESDE EL DATO 'nif' DEL TIPO DE DATO 'Contacto' DESDE LA TABLA PROVEEDORES (POSTGRE)
            4 -> INSERTAR EN LA DB POSTGRE EL NUEVO PRODUCTO INTRODUCIDO EN LA DB MYSQL,
                TRAYENDO DESDE AHI LA 'id_producto' Y BUSCANDO 'id_proveedor' e 'id_categoria' EN LAS TABLAS DE LA BASE DE DATOS POSTGRE
         */

        //1.INSERTAMOS EN LA BASE DE DATOS MYSQL EL NUEVO PRODUCTO PARA GENERAR UN ID Y RECUPERAR EL VALOR DEL ULTIMO ID
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

            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                idProdutcotIntroducido = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error en la creacion de un producto en la DB MySQL");
        }finally {
            try {
                modeloConnectionMySQL.close();
            } catch (SQLException e) {
                System.out.println("Error al cerra la conexion con DB MySQL");
            }
        }

        //  2.OBTENEMOS EL id_categoria DESDE EL DATO 'nombre_categoria' DE LA TABLA CATEGORIAS
        try{
            preparedStatement = modeloConnectionPostgre.prepareStatement(
                    "SELECT id_categoria FROM categorias WHERE nombre_categoria = ?");
            preparedStatement.setString(1,nombre_categoria);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                idCategoria = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error durante la busqueda del id_producto en la tabla proveedores");;
        } finally{
            try {
                modeloConnectionMySQL.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }

        // 3.OBTENEMOS EL id_proveedor DESDE EL DATO 'nif' DEL TIPO DE DATO 'Contacto' DESDE LA TABLA PROVEEDORES
        try{

            preparedStatement = modeloConnectionPostgre.prepareStatement(
                    "SELECT id_proveedor FROM proveedores WHERE (contacto).nif = ?"
            );

            preparedStatement.setString(1,nif);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                idProveedor = resultSet.getInt(1);
            }
        }catch (SQLException e){
            System.out.println();
        }



        /*
        4.INSERTAR EN LA DB POSTGRE EL NUEVO PRODUCTO INTRODUCIDO EN LA DB MYSQL,
         TRAYENDO DESDE AHI LA 'id_producto' Y BUSCANDO 'id_proveedor' e 'id_categoria' EN LAS TABLAS DE LA BASE DE DATOS POSTGRE
         */
        try{
            preparedStatement = modeloConnectionPostgre.prepareStatement(
                    "INSERT INTO productos (id_producto,id_proveedor,id_categoria) " +
                            "VALUES (?,?,?)");
            preparedStatement.setInt(1,idProdutcotIntroducido);
            preparedStatement.setInt(2,idProveedor);
            preparedStatement.setInt(3,idCategoria);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error durante la insercion de los datos en la tabla productos de postgre");;
        } finally{
            try {
                modeloConnectionMySQL.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }

    }


    /*
    7 -> Eliminar un producto por su nombre (MySQL + PostgreSQL)
    Se implementará una función con la siguiente cabecera:
    void eliminarProductoPorNombre(String nombre).
    Se tendrá que eliminar el producto de ambas bases de datos.

    */

    public void eliminarProductoPorNombre(String nombre){
        //TODO trabajando aquí

        //Llamo a un metodo para que me devuelva el id_producto si existe en la DB
        int idProducto = buscarProducto(nombre);

        //Lllamos a un metodo para ejecutar el borrado de las tablas como una transacción
        eliminarRegistrosDelProducto(idProducto);



    }

    public int buscarProducto(String nombre){
        modeloConnectionMySQL = MySQL_Connection.getMySQLConnection();
        int idProducto=-1;

        //BUSCAMOS EL 'id_producto' EN LA BASE DE DATOS MYSQL
        try(PreparedStatement preparedStatement = modeloConnectionMySQL.prepareStatement(
                "SELECT id_producto FROM productos WHERE nombre_producto= ?")){
            preparedStatement.setString(1,nombre);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    idProducto = resultSet.getInt(1);
                }
            }catch (SQLException e){
                System.out.println("Error al leer los resultados con resulset de la DB MySQL");
            }
        }catch (SQLException e){
            System.out.println("Error en la busqueda del producto por nombre");
        }

        return idProducto;

    }

    private void eliminarRegistrosDelProducto(int idProducto) {
        //con.setAutoCommit(false);

        modeloConnectionMySQL = MySQL_Connection.getMySQLConnection();
        modeloConnectionPostgre = PostgreSQL_Connection.getPostgreSQLConnection();
        PreparedStatement preparedStatement;
        int affectedRows = -1;

        // EMPEZAMOS LA TRANSACCION
        try {
            modeloConnectionMySQL.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Error al cambiar el auto commit a false");
        }
        // 1 -> BORRADO EN MYSQL

        // 1.1 -> Borrado de la tabla 'pedidos_productos'
        try {
            preparedStatement = modeloConnectionMySQL.prepareStatement(
                    "DELETE FROM pedidos_productos WHERE id_producto = ?");
            preparedStatement.setInt(1,idProducto);
            affectedRows =  preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error durante el borrado del producto en la tabla pedidos_productos en MySQL");
        }
        // 1.2 -> Borrado de la tabla 'productos'
        try {
            preparedStatement = modeloConnectionMySQL.prepareStatement(
                    "DELETE FROM productos WHERE id_producto = ?");
            preparedStatement.setInt(1,idProducto);
            affectedRows =  preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error durante el borrado del producto en la tabla productos en MySQL");
        }

        // 2 -> BORRADO EN POSTGRE

        // 2.1 -> Borrado de la tabla alamacenes_productos
        try {
            preparedStatement = modeloConnectionPostgre.prepareStatement(
                    "DELETE FROM almacenes_productos WHERE id_producto = ?");
            preparedStatement.setInt(1,idProducto);
            affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error durante el borrado del producto en la tabla alamacenes_productos en Postgre");
        }

        // 2.2 -> Borrado de la tabla productos
        try {
            preparedStatement = modeloConnectionPostgre.prepareStatement(
                    "DELETE FROM productos WHERE id_producto = ?");
            preparedStatement.setInt(1,idProducto);
            affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error durante el borrado del producto en la tabla productos en Postgre");
        }

        // CERRAMOS LA TRANSACCION
        try {
            modeloConnectionMySQL.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Error al cambiar el auto commit a true");
        }
    }

    /*
    8 -> Listar los productos con bajo stock (menos de X unidades disponibles) (MySQL)
    Se implementará una función con la siguiente cabecera: void listarProductosBajoStock(int stock) .
    Mediante una única consulta se tendrá que obtener el conjunto de filas resultante y mostrar el nombre de los productos junto con su stock.
     */

    public void listarProductosBajoStock(int stock){
        String nombre = "";
        int stockEncontrado;
        ArrayList <Producto> productosFiltrados = new ArrayList<>();
        modeloConnectionMySQL = MySQL_Connection.getMySQLConnection();
        try(PreparedStatement preparedStatement = modeloConnectionMySQL.prepareStatement(
                "SELECT nombre_producto, stock FROM productos WHERE stock <= ?")){
            preparedStatement.setInt(1,stock);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                nombre = resultSet.getString(1);
                stockEncontrado = resultSet.getInt(2);
                productosFiltrados.add(new Producto(nombre,stockEncontrado));
            }
            // TODO llevarme esto a una ventana donde pueda printearlo sonbre un elemento
            for(Producto p: productosFiltrados){
                System.out.println(p.toStringTuneado());
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar los productos por stock");
        } finally{
            try {
                modeloConnectionMySQL.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }
    }


    /*
    9 -> Obtener el total de pedidos realizados por cada usuario (MySQL)
    Se implementará una función con la siguiente cabecera:
    void obtenerTotalPedidosUsuarios().
    Mediante una consulta se tendrá que obtener toda la información e
    imprimir por pantalla: el nombre del usuario y el total de pedidos que ha hecho.
     */
    public void obtenerTotalPedidosUsuarios(){
        ArrayList <Usuario> usuariosFiltrados = new ArrayList<Usuario>();
        int contadorNumeroUsuarios;
        modeloConnectionMySQL = MySQL_Connection.getMySQLConnection();
        try(PreparedStatement preparedStatement = modeloConnectionMySQL.prepareStatement(
                "SELECT u.nombre as Usuario, COUNT(p.id_pedido) as Total " +
                        "FROM pedidos as p " +
                        "INNER JOIN usuarios as u " +
                        "WHERE p.id_usuario = u.id_usuario " +
                        "GROUP BY u.nombre")){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                    String nombre = resultSet.getString(1);
                    int numeroPedidos = resultSet.getInt(2);
                    usuariosFiltrados.add(new Usuario(nombre,numeroPedidos));
            }
            //TODO llevar esto a una ventana
            for(Usuario u: usuariosFiltrados){
                System.out.println(u.toStringTuneado());
            }

        } catch (SQLException e) {
            System.out.println(" Error al buscar la informacion necesaria para printear los pedidos registrados para cada usuario");
        }finally {
            try{
                modeloConnectionMySQL.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion con Mysql");
            }
        }
    }
        /*
        10 -> Obtener la cantidad de productos almacenados por cada almacén (PostgreSQL)
        Se implementará una función con la siguiente cabecera: void obtenerCantidadProductosEnCadaAlmacen().
        Mediante una consulta se tendrá que obtener toda la información solicitada y pintar por pantalla el
        nombre del almacén y el total de productos de los que dispone.
         */
    public void obtenerCantidadProductosEnCadaAlmacen(){
        modeloConnectionPostgre = PostgreSQL_Connection.getPostgreSQLConnection();
        try(PreparedStatement preparedStatement = modeloConnectionPostgre.prepareStatement(
                "SELECT a.nombre_almacen, SUM(ap.cantidad) FROM almacenes as a INNER JOIN almacenes_productos as ap ON a.id_almacen = ap.id_almacen GROUP BY a.nombre_almacen")){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    String s = resultSet.getString(1);
                    int i = resultSet.getInt(2);
                    System.out.println("Almacen  " + s + "\n" +
                            "Cantidad de productos -> " + i + "\n" );
                }
            }catch (SQLException e){
                System.out.println("Error al recuperar los datos de la DB Postgre");
            }

        }catch (SQLException e){
            System.out.println("Error durante la obtencion de la cantidad de productos por alamacen");
            e.printStackTrace();
        }
    }
    /*
    11 -> Listar todos los productos con sus respectivas categorías y proveedores (PostgreSQL)
    Se implementará una función con la siguiente cabecera: void listarTodosProductosConCategoriaYProveedor().
    Se realizará una primera consulta en PostgreSQL que permita obtener toda la información del producto:
     id, nombre, nif, teléfono e email del proveedor así como el nombre de la categoría.
    Se realizará una consulta en MySQL para obtener el nombre, precio y stock del producto
    Se concatenará y se mostrarán todos los datos indicados anteriormente usando Java.
     */
   public void listarTodosProductosConCategoriaYProveedor(){
       modeloConnectionPostgre = PostgreSQL_Connection.getPostgreSQLConnection();
       modeloConnectionMySQL = MySQL_Connection.getMySQLConnection();
       int idProducto;
       String nombreProducto;
       String nif;
       String telefono;
       String email;
       String nombreCategoria;
       ArrayList <Producto> listaProductoDesdePostgre = new ArrayList<Producto>();


       try (PreparedStatement preparedStatement =
                    modeloConnectionPostgre.prepareStatement("SELECT prod.id_producto, prov.nombre_proveedor, (prov.contacto).nif, (prov.contacto).telefono, (prov.contacto).email, cate.nombre_categoria FROM productos as prod INNER JOIN categorias as cate ON prod.id_categoria = cate.id_categoria INNER JOIN proveedores as prov ON prod.id_proveedor = prov.id_proveedor;")){
           try(ResultSet resultSet = preparedStatement.executeQuery()){
               while(resultSet.next()){
                   idProducto = resultSet.getInt(1);
                   nombreProducto = resultSet.getString(2);
                   nif = resultSet.getString(3);
                   telefono = resultSet.getString(4);
                   email = resultSet.getString(5);
                   nombreCategoria = resultSet.getString(6);
                   listaProductoDesdePostgre.add(new Producto(idProducto, nombreProducto, nif, telefono,
                           email,nombreCategoria));
               }
               for (Producto p:listaProductoDesdePostgre){
                   System.out.println(p.toStringTuneadoMetodo11A());;
               }
           } catch (Exception e) {
               System.out.println("Error al recuperar los datos de la DB mediante ' Result Set ' ");
           }
       } catch (SQLException e) {
           System.out.println("Error durante la consulta a la DB Postgre");
       }

       try(PreparedStatement preparedStatement =
                   modeloConnectionMySQL.prepareStatement("SELECT nombre_producto, precio, stock FROM productos WHERE id_producto = ?")){
           for (Producto p : listaProductoDesdePostgre){
               preparedStatement.setInt(1, p.getId_producto());
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(resultSet.next()){
                        p.setNombreProducto(resultSet.getString(1));
                        p.setPrecio(resultSet.getDouble(2));
                        p.setStock(resultSet.getInt(3));
                        System.out.println(p.toStringTuneadoMetodo11B());;
                    }
                }catch (SQLException e){
                    System.out.println("Error al obtner los datos desde la DB MySQL");
                }
           }
       }catch (SQLException e){
           System.out.println("Error al conectar con la DB de MySQL");
       }


    }

    /*
    11 -> Obtener todos los usuarios que han comprado algún producto de una categoria dada (MySQL + PostgreSQL).
    Se implementará una función con la siguiente cabecera: void obtenerUsuariosCompraronProductosCategoria(int idCategoria).
    Se recibirá el id de la categoría y se obtendrá en PostgreSQL el id de los productos que pertenezcan a esa categoría.
    En MySQL se obtendrá el nombre de los usuarios que han comprado algún producto de los indicados anteriormente.
    Se mostrará por pantalla el nombre de los usuarios.
     */
    public void obtenerUsuariosCompraronProductosCategoria(int idCategoria){
        // Obtener el id_producto de los productos de esa categoria POSTGRE
        // Obtener el id_usuario que han comprado los productos obtenidos en postgre.
        // Mostras el nombre_usuario de los que han comprado productos de esa categoria.

    }

}
