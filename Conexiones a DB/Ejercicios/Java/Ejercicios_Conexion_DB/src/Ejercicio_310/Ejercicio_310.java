package Ejercicio_310;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class Ejercicio_310 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        Connection conexionSingleton = SingletonMySQL.getConnectionMySQLTienda();
       while (true){
           try {
               realizarPedido(conexionSingleton);
           } catch (IOException e) {
               System.out.println("Error al al leer datos desde teclado");
           }
       }
    }

    private static void realizarPedido(Connection conn) throws IOException {
        System.out.println("Introduce el DNI del cliente");
        String dniCliente = br.readLine();
        if (!comprobarClienteExiste(conn,dniCliente)){
            System.out.println("El cliente no esta registrado en el sistema, introduce el nombre para crearlo antes de seguir");
            System.out.println("Introduce el nombre para el nuevo cliente");
            String nombreCliente = br.readLine();
            insertarCliente(conn,dniCliente,nombreCliente);
        }
        System.out.println("Los productos disponibles son estos:");
        printearListado(obtenerListadoProductosDisponibles(conn));
        insertarDatosTablaPedidosProductos(conn,obtnerProductosDeseados(crearPedido(conn,dniCliente,new Timestamp(System.currentTimeMillis()))));

    }

    private static void insertarDatosTablaPedidosProductos(Connection conn, ArrayList<Producto_pedido> productosPedido) {
        int totalDeProductoAñadidos = productosPedido.size();
        try(PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO producto_pedido (idPedido, idProducto, cantidad) VALUES (?,?,?)")){
            for (Producto_pedido pp : productosPedido){
                preparedStatement.setInt(1,pp.getIdPedido());
                preparedStatement.setInt(2,pp.getIdProducto());
                preparedStatement.setInt(3,pp.getCantidadProducto());
                if (preparedStatement.executeUpdate() != 0){
                    System.out.println("Añadido una tupla a la tabla producto_pedido");
                }
                System.out.println("Se han añadido un total de " + totalDeProductoAñadidos);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    private static ArrayList<Producto_pedido> obtnerProductosDeseados(int idPedidoGenerado) {
        ArrayList<Producto_pedido> productosPedido = new ArrayList<>();
        String idProducto="";
        String cantidadProducto="";
        boolean productoYaExiste;
        do {
            try {
                productoYaExiste = false;
                System.out.println("Introduce la id del producto");
                idProducto = br.readLine();
                System.out.println("Cantidad del producto seleccionado");
                cantidadProducto = br.readLine();
                Producto_pedido pp = new Producto_pedido(idPedidoGenerado,Integer.parseInt(idProducto),Integer.parseInt(cantidadProducto));
                for (Producto_pedido producto : productosPedido){
                    if (producto.getIdProducto() == pp.getIdProducto()) {
                     productoYaExiste = true;
                    }
                }
                if (!productoYaExiste){
                    productosPedido.add(pp);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }while (Integer.parseInt(idProducto) != 0);

        return productosPedido;
    }

    private static void printearListado(ArrayList<Producto> listadoProductosDisponibles) {
        for (int i = 0; i < listadoProductosDisponibles.size(); i++) {
            System.out.println("Producto -> " + (i+1));
            System.out.println(listadoProductosDisponibles.get(i));
        }
    }

    private static boolean comprobarClienteExiste(Connection connection,String dni) {
        boolean existeCliente = false;
        String row2 = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM cliente WHERE dni = ?")) {
            preparedStatement.setString(1,dni);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    row2 = resultSet.getString("nombre");
                }
                if (( row2 != null)){
                    existeCliente = true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar en la base de datos la existencia del cliente");
        }

        return existeCliente;
    }



    private static void insertarCliente(Connection conn, String dniNuevoCliente, String nombreCliente) {
        try(PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO cliente (dni, nombre) VALUES (?,?)")) {
            preparedStatement.setString(1,dniNuevoCliente);
            preparedStatement.setString(2,nombreCliente);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar datos del nuevo cliente");
        }
    }


    private static ArrayList<Producto> obtenerListadoProductosDisponibles(Connection conn) {
        ArrayList<Producto> listadoProductos = obtenerProductos(conn);
        return listadoProductos;
    }

    private static ArrayList<Producto> obtenerProductos(Connection conn) {
        ArrayList <Producto> productList = new ArrayList<>();
        try(PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM producto")){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    String description = resultSet.getString(3);
                    double price = resultSet.getDouble(4);
                    productList.add(new Producto(id,name,description,price));
                }

            }

        }catch (SQLException e ){
            System.out.println("Error al mostrar productos");
        }return productList;
    }



    private static int crearPedido(Connection connection,String dni, Timestamp timestamp) {
        int idGenerado = 0;

        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO pedido(dniCliente, fecha) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS) ) {
            preparedStatement.setString(1,dni);
            preparedStatement.setString(2,timestamp.toString());
            if (preparedStatement.executeUpdate() == 1){
                System.out.println("Insercion en la tabla pedidos creada con exito!");
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    while (resultSet.next()){
                        idGenerado = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return idGenerado;
    }
}
