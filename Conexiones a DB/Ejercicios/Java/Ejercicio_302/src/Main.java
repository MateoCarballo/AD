import java.sql.*;
import java.util.logging.Level;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {
        //Primer apartado del ejercicio
        mostrarInformacionConexion();
        //mostrarTodasLasTablas();
        //Segundo apartado del ejercicio
        //mostrarInformacionTablaProyecto();
        //Tercer apartado
        //insertarNuevoProyecto();
        //Cuarto apartaado
        int numeroDeProyecto = 11;
        //eliminarProyectoPorId(numeroDeProyecto);
    }

    private static void mostrarInformacionConexion() {
        Connection connection = ConnMySQL.getInstance();
        DatabaseMetaData dbmd = null;
        try {
            dbmd = connection.getMetaData();
            out.println("Gestor : " + dbmd.getDatabaseProductName());
            out.println("Conector : " + dbmd.getDriverName());
            out.println("URL : " + dbmd.getURL());
            out.println("Usuario : " + dbmd.getUserName());
        } catch (SQLException e) {
            out.println(e.getMessage());
        }

    }

    private static void eliminarProyectoPorId(int idProyecto) {
        Connection conn = ConnMySQL.getInstance();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement("DELETE FROM proyecto WHERE Numproy = ? ");
            preparedStatement.setInt(1,idProyecto);
            int tuplasAfectadas = preparedStatement.executeUpdate();
            out.println("Numero de filas afectadas = " + tuplasAfectadas);
        } catch (SQLException e) {
            out.println(e.getMessage());
        }finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                out.println(e.getMessage());
            }

        }
    }
/* MI PRIMER INTENTO SIN VER LA SOLUCION

private static void insertarNuevoProyecto() {
        Connection connection = ConnMySQL.getInstance();
        PreparedStatement preparedStatement = null;
        try {
             preparedStatement= connection.prepareStatement("INSERT INTO proyecto (Numproy,Nombreproy,Lugarproy,departamento_Numdep) VALUES (11,'Acceso a datos','Lugo',3)");
            int i = preparedStatement.executeUpdate();
            out.println("Numero de filas afectadas = " + i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                out.println(e.getMessage());
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    out.println(e.getMessage());
                }
            }
        }
    }
*/
    private static void insertarNuevoProyecto() {
        Connection connection = ConnMySQL.getInstance();
        PreparedStatement preparedStatement = null;
        try {
             preparedStatement= connection.prepareStatement("INSERT INTO proyecto VALUES (?,?,?,?)");
             preparedStatement.setInt(1,11);
             preparedStatement.setString(2,"Acceso a datos");
             preparedStatement.setString(3,"Lugo");
             preparedStatement.setInt(4,3);

            int i = preparedStatement.executeUpdate();
            out.println("Numero de filas afectadas = " + i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (connection != null) connection.close();
                if (preparedStatement != null) preparedStatement.close();
            }catch (SQLException e){
                out.println(e.getMessage());
            }
        }
    }

    private static void mostrarInformacionTablaProyecto() {
        Connection connection = ConnMySQL.getInstance();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM proyecto");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int numero = resultSet.getInt("Numproy");
                String nombre = resultSet.getString("Nombreproy");
                String lugar = resultSet.getString("Lugarproy");
                int departamentoNumeroDepartamento = resultSet.getInt("departamento_Numdep");

                out.println("Numero de proyecto : " + numero);
                out.println("Nombre : " + nombre);
                out.println("Lugar :" + lugar);
                out.println("Relacion departamento <---> Numero de departamento : " + departamentoNumeroDepartamento);

            }
        } catch (SQLException e) {
            out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                out.println(e.getMessage());
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    out.println(e.getMessage());
                }
            }
        }



    }

    private static void mostrarTodasLasTablas() {
        Connection connection = ConnMySQL.getInstance();
        String consultaEmpleado = "SELECT * FROM empleado";
        String consultaDepartamento = "SELECT * FROM departamento";
        String consultaEmpleadoProyecto = "SELECT * FROM empleadoproyecto";
        String consultaDepartamentoUbi = "SELECT * FROM `departamento-ubi`";
        String consultaProyecto = "SELECT * FROM proyecto";

        PreparedStatement preparedStatement = null;
        try {
            //Cargar la consulta de la tabla empleado
            preparedStatement = connection.prepareStatement(consultaEmpleado);
            ResultSet resultset = preparedStatement.executeQuery();
            mostrarTablaEmpleado(resultset);

            //Cargar la consulta de la tabla departamento
            preparedStatement = connection.prepareStatement(consultaDepartamento);
            resultset = preparedStatement.executeQuery();
            mostrarTablaDepartamento(resultset);

            //Cargar consulta empleado - proyecto
            preparedStatement = connection.prepareStatement(consultaEmpleadoProyecto);
            resultset = preparedStatement.executeQuery();
            mostrarTablaempleadoProyecto(resultset);

            //Cargar consulta departamento-ubi
            preparedStatement = connection.prepareStatement(consultaDepartamentoUbi);
            resultset = preparedStatement.executeQuery();
            mostrarTablaDepartamentoUbi(resultset);

            //Cargar consulta proyecto
            preparedStatement = connection.prepareStatement(consultaProyecto);
            resultset = preparedStatement.executeQuery();
            mostrarTablaProyecto(resultset);
        } catch (SQLException e) {
            out.println(e.getMessage());
        }finally {
            try {
                connection.close();
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                out.println(e.getMessage());
            }

        }


    }

    private static void mostrarTablaEmpleado(ResultSet resultado) {
        try {
            while (resultado.next()) {
                int nss = resultado.getInt("NSS");
                String nombre = resultado.getString("Nombre");
                String apel1 = resultado.getString("Apel1");
                String apel2 = resultado.getString("Apel2");
                String sexo = resultado.getString("Sexo");
                String direccion = resultado.getString("Dirección");
                String fechaNac = resultado.getString("Fechanac");
                int salario = resultado.getInt("Salario");
                int numeroDepartamento = resultado.getInt("Numdept");
                String nssSup = resultado.getString("NSSsup");

                out.println("NSS : " + nss);
                out.println("Nombre : " + nombre);
                out.println("Primer apellido : " + apel1);
                out.println("Segundo apellido : " + apel2);
                out.println("Sexo : " + sexo);
                out.println("Direccion : " + direccion);
                out.println("Fecha de nacimiento : " + fechaNac);
                out.println("Salario : " + salario);
                out.println("Numero de departamento : " + numeroDepartamento);
                out.println("Numero de seguridad social secundario : " + nssSup);
            }
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }

    private static void mostrarTablaDepartamento(ResultSet resultado) {
        try {
            while (resultado.next()) {
                int numDep = resultado.getInt("NumDep");
                String nombreDepartamento = resultado.getString("Nombredep");
                int numeroEmpleadoDepartamento = resultado.getInt("Numempdep");
                String nssGerente = resultado.getString("NSSgerente");
                String fechaInicioGenrente = resultado.getString("fechainicgerente");

                out.println("Numero de departamento : " + numDep);
                out.println("Nombre del Departamento : " + nombreDepartamento);
                out.println("Numero Empleado Departamento : " + numeroEmpleadoDepartamento);
                out.println("Numero de seguridad social gerente : " + nssGerente);
                out.println("Fecha de inicio del Gerente : " + fechaInicioGenrente);
            }
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }

    private static void mostrarTablaempleadoProyecto(ResultSet resultado) {
        try {
            while (resultado.next()) {
                String nss = resultado.getString("NSS");
                int numeroProyecto = resultado.getInt("NUMPROY");
                int numeroHoras = resultado.getInt("NUMHORAS");

                out.println("NSS : " + nss);
                out.println("Numero de proyecto : " + numeroProyecto);
                out.println("Numero de horas : " + numeroHoras);

            }
        } catch (SQLException e) {
            out.println(e.getMessage());
        }

    }


    private static void mostrarTablaDepartamentoUbi(ResultSet resultado) {
        try {
            while (resultado.next()) {
                int numeroDepartamento = resultado.getInt("Numdep");
                String lugarDepartamento = resultado.getString("Lugardep");

                out.println("Numero de departamento : " + numeroDepartamento);
                out.println("Lugar departamento : " + lugarDepartamento);

            }
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }


    private static void mostrarTablaProyecto(ResultSet resultado) {
        try {
            while (resultado.next()) {
                int numeroProyecto = resultado.getInt("Numproy");
                String nombreProyecto = resultado.getString("Nombreproy");
                String lugarProyecto = resultado.getString("Lugarproy");
                int departamentoNumeroDepartamento = resultado.getInt("departamento_Numdep");

                out.println("Numero de proyecto : " + numeroProyecto);
                out.println("Nombre proyecto : " + nombreProyecto);
                out.println("Lugar proyecto : " + lugarProyecto);
                out.println("Departamento encargado : " + departamentoNumeroDepartamento);


            }
        } catch (SQLException e) {
            out.println(e.getMessage());

        }

    }
}