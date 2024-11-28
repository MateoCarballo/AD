import Connections.MySQL_Connection;
import Connections.PostgreSQL_Connection;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        
        App app = new App(new Modelo(MySQL_Connection.getMySQLConnection(), PostgreSQL_Connection.getPostgreSQLConnection()));

        try {
            app.iniciarApp();
        } catch (IOException e) {
            System.out.println("Error del tipo IOExcepcion");
            e.printStackTrace();
        }finally{
            try {
                app.getM().getModeloConnectionMySQL().close();
                app.getM().getModeloConnectionPostgre().close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar alguna de las conexiones");
                e.printStackTrace();
            }
        }

        // TODO Dudas en cuanto a la gestion de las aperturas y cierres de la
        //  conexion para poder permitir varias operaciones del mismo tipo

        //TODO es necesario commitear al acabar la transaccion ? sin haberlo escrito en un metodo me borro esa tupla

        //TODO en el metodo 7 tengo dudas con el orden de los try catch donde enlazarlos 77para asegurarme de que salta  y no ejecuta el codigo si se produce un error



        //m.crearCategoria(" "); //1
        //m.crearProveedor("Nombre prueba proveedor","12345678A",123456789,"email@deprueba.com"); //2
        //m.eliminarProveedor(2);        //3
        //m.crearUsuario("Nombre prueba","email@prueba.com",2024);      //4
        //m.eliminarUsuario(4);     //5
        //m.crearProducto("nombre producto prueba",99.99,1000,"Monitor","67890123A"); //6
        //m.eliminarProductoPorNombre("Laptop Lenovo"); //7
        //m.listarProductosBajoStock(50); //8
        //m.obtenerTotalPedidosUsuarios(); //9
        //m.obtenerCantidadProductosEnCadaAlmacen(); //10
        //m.listarTodosProductosConCategoriaYProveedor(); //11
        //m.obtenerUsuariosCompraronProductosCategoria(1); //12
    }
}