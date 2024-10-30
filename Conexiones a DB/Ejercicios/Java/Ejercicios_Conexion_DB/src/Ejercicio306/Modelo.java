package Ejercicio306;


import java.sql.*;

public class Modelo {


    public String obtenerResultados(String numeroSS){
        String query = "SELECT * FROM empleado WHERE NSS = numeroSS" ;
        String cadenaResultado= "";
        PreparedStatement preparedStatement;
        try {
            Connection conn = ConectarEmpleados.getInstance();
            preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            cadenaResultado = (resultSet.getString("NSS")+
                    resultSet.getString("Nombre")+
                    resultSet.getString("Apel1")+
                    resultSet.getString("Apel2")+
                    resultSet.getString("Sexo")+
                    resultSet.getString("Dirección")+
                    resultSet.getString("Fechanac")+
                    String.valueOf(resultSet.getInt("Salario"))+
                    String.valueOf(resultSet.getInt("Numdept"))+
                    resultSet.getString("NSSsup"));
        }catch (SQLException e){
            System.out.println();
        }
        return cadenaResultado;
    }

    // TODO: DEFINICIÓN DE LA CONEXIÓN CON LA BASE DE DATOS
    //TODO: CREAR FUNCIÓN PARA LA OBTENCIÓN DE LOS RESULTADOS DE LA CONSULTA
    
}
