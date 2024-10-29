package Ejercicio303;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class BorraEmpleados {
    private BorraEmpleados(){

    }

    public static void borrarEmpleadoPorNSS(int numeroSS){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Empleados","root","abc123.")){
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM empleado WHERE NSS = ?")){
                /*
                SET foreign_key_checks = 0;
                Delete from empleado where NSS = 11111;
                SET foreign_key_checks = 1;
                 */
                Statement stm = connection.createStatement();
                stm.execute("SET foreign_key_checks = 0");
                preparedStatement.setInt(1,numeroSS);
                int numeroFilasAfectadas = preparedStatement.executeUpdate();
                System.out.println("Has borrado " + numeroFilasAfectadas);
                stm.execute("SET foreign_key_checks = 1");
            }
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Error en la sentencia SQL");
        }catch (SQLException e) {
            System.out.println("Error generico SQL");
        }

    }

}
