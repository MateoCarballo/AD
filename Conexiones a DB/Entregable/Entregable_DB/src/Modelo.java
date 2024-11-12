import Connections.PostgreSQL_Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Modelo {

    public void crearCategoria(String nombreCategoria){
        Connection connectionPostgree = PostgreSQL_Connection.getPostgreSQLConnection();
        try (PreparedStatement preparedStatement = connectionPostgree.prepareStatement
                ("INSERT INTO categorias(nombre_categoria) VALUES (?)")){
            preparedStatement.setString(1,nombreCategoria);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                connectionPostgree.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }

    }

    public void crearProveedor(String nombreProveedor, String nif, int telefono, String email ){
        Connection connectionPostgree = PostgreSQL_Connection.getPostgreSQLConnection();
        try (PreparedStatement preparedStatement = connectionPostgree.prepareStatement
                ("INSERT INTO proveedores(nombre_proveedor, contacto) " +
                        "VALUES (?, ROW(?,?,?))")){
            preparedStatement.setString(1,nombreProveedor);
            preparedStatement.setString(2,nif);
            preparedStatement.setInt(3,telefono);
            preparedStatement.setString(4,email);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                connectionPostgree.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }

    }

    public void eliminarProveedor(int id){
        Connection connectionPostgree = PostgreSQL_Connection.getPostgreSQLConnection();
        try (PreparedStatement preparedStatement = connectionPostgree.prepareStatement
                ("DELETE FROM proveedores WHERE id_proveedor = ?")){

            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                connectionPostgree.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }
    }

}
