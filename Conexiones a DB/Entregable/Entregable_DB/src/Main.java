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
                app.getM().modeloConnectionMySQL().close();
                app.getM().modeloConnectionPostgre().close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar alguna de las conexiones");
                e.printStackTrace();
            }
        }


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