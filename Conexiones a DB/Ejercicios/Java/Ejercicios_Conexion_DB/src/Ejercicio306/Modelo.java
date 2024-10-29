package Ejercicio306;


import java.sql.*;
import java.util.ArrayList;

public class Modelo {
    private Connection conn;
    public Modelo(){
        this.conn = ConectarEmpleados.getInstance();
    }

    public void obtenerResultados(String consulta){
        String query = "SELECT * FROM empleado WHERE NSS = " + consulta;
        ArrayList<String> datosEmpleado = new ArrayList<>();
        try {
            Statement preparedStatement = conn.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()){
                datosEmpleado.add(resultSet.getString("NSS"));
                datosEmpleado.add(resultSet.getString("Nombre"));
                datosEmpleado.add(resultSet.getString("Apel1"));
                datosEmpleado.add(resultSet.getString("Apel2"));
                datosEmpleado.add(resultSet.getString("Sexo"));
                datosEmpleado.add(resultSet.getString("Dirección"));
                datosEmpleado.add(resultSet.getString("Fechanac"));
                datosEmpleado.add(String.valueOf(resultSet.getInt("Salario")));
                datosEmpleado.add(String.valueOf(resultSet.getInt("Numdept")));
                datosEmpleado.add(resultSet.getString("NSSsup"));
            }
        }catch (SQLException e){
            System.out.println();
        }

    }

    // TODO: DEFINICIÓN DE LA CONEXIÓN CON LA BASE DE DATOS
    //TODO: CREAR FUNCIÓN PARA LA OBTENCIÓN DE LOS RESULTADOS DE LA CONSULTA
    
}
