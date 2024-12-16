package Ejercicios_MySQL.Ejercicio306;


import java.sql.*;

public class Modelo {


    public String [] obtenerResultados(String numeroSS){
        String query = "SELECT * FROM empleado WHERE NSS = " + numeroSS ;
        String [] cadenaResultado= new String[10];
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Empleados","root","abc123.")){
            try(PreparedStatement preparedStatement = conn.prepareStatement(query)){
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    cadenaResultado[0] = resultSet.getString("NSS");
                    cadenaResultado[1] = resultSet.getString("Nombre");
                    cadenaResultado[2] = resultSet.getString("Apel1");
                    cadenaResultado[3] = resultSet.getString("Apel2");
                    cadenaResultado[4] = resultSet.getString("Sexo");
                    cadenaResultado[5] = resultSet.getString("Dirección");
                    cadenaResultado[6] = resultSet.getString("Fechanac");
                    cadenaResultado[7] = String.valueOf(resultSet.getInt("Salario"));
                    cadenaResultado[8] = String.valueOf(resultSet.getInt("Numdept"));
                    cadenaResultado[9] = resultSet.getString("NSSsup");
                }
            }
        }catch (SQLException e){
            System.out.println();
        }
        return cadenaResultado;
    }

    // TODO: DEFINICIÓN DE LA CONEXIÓN CON LA BASE DE DATOS
    //TODO: CREAR FUNCIÓN PARA LA OBTENCIÓN DE LOS RESULTADOS DE LA CONSULTA
    
}
