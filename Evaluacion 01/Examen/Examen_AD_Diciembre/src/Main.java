import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Connection connection = SingletonConnectionPostgre.getConnection();
    public static void main(String[] args) {
        boolean continuar = true;
        do {
            System.out.println("""
                1.Crear esquema academia Teis.
                2.Crear tipo instituto.
                3.Insertar nueva inscripcion.
                4.Actualizar el email de un estudiante.
                5.Eliminar la informcion del curso.
                6.Consulta 1 ().
                7.Consulta 2 ().
                8.Consulta 3 ().
                9.Consulta 4 ().
                10.Consulta 5().
                11.Salir.
                """);
            String entradaTeclado = null;
            try {
                entradaTeclado = br.readLine();
            } catch (IOException e) {
                System.out.println("Error en la entrada teclado");
            }
            switch (Integer.parseInt(entradaTeclado)){
                case 1 -> crearEsquemaAcademia();
                case 2 -> crearTipoInstituto();
                case 3 -> insertarNuevaInscripcion();
                case 4 -> actualizarEmailEstudiante();
                case 5 -> eliminarInformacionCurso();
                case 6 -> ejecutarConsulta1();
                case 7 -> ejecutarConsulta2();
                case 8 -> ejecutarConsulta3();
                case 9 -> ejecutarConsulta4();
                case 10 -> ejecutarConsulta5();
                case 11 -> continuar = false;
            }
            
            
            
        }while (continuar);
            
        
    }

    private static void crearEsquemaAcademia() {
        String query = "CREATE SCHEMA academiaTeis";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la creacion del schema -> " + e.getMessage());
        }
    }

    private static void crearTipoInstituto() {
        String query = "CREATE TYPE academiaTeis.instituto AS (Nombre VARCHAR(100), Numero_profesores INTEGER, Presupuesto DECIMAL(10, 2))";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la creacion del tipo de dato instituto -> " + e.getMessage());
        }
    }

    private static void insertarNuevaInscripcion() {
        // 1. Pedir el id de un estudiante y comprobar que exite (hardcodeo el id para ir mas rapido) podriamos mostras los disponibles y que elija
        int idEstudiante = 1;
        int idCurso = 1;
        String date = "'2023-02-10'";
        int idGenerado = 0;

        //3. INSERTAR EN LA TABLA inscripciones la nueva entrada.
        String query = "INSERT INTO Inscripciones (curso_id, estudiante_id) VALUES (?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setInt(1,idCurso);
            preparedStatement.setInt(2,idEstudiante);
            if (preparedStatement.executeUpdate() != 0){
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    while(resultSet.next()){
                        idGenerado = resultSet.getInt(1);
                    }
                }
                System.out.println("Se ha insertado una inscripcion con idCurso -> " + idCurso + " idEstudiante -> " + idEstudiante );
                System.out.println("El id para la nueva insercion es -> " + idGenerado);
            }
        } catch (SQLException e) {
            System.out.println("Error en la creacion del tipo de dato instituto -> " + e.getMessage());
        }
    }

    private static void actualizarEmailEstudiante() {
// TODO sin completar
    }

    private static void eliminarInformacionCurso() {
        int idCursoEliminado = 1;
        String query="";
        try{
            connection.setAutoCommit(false);
            //Borrado de la tabla materialCurso
            query = "DELETE FROM materialcurso WHERE curso_id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setInt(1,idCursoEliminado);
                if (preparedStatement.executeUpdate() != 0){
                    System.out.println("Se ha eliminado el curso de la tabla materialcurso con curso_id -> " + idCursoEliminado);
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar un curso de la tabla materialcurso -> " + e.getMessage());
            }

            //Borrado de la tabla materialCurso
            query = "DELETE FROM inscripciones WHERE curso_id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setInt(1,idCursoEliminado);
                if (preparedStatement.executeUpdate() != 0){
                    System.out.println("Se ha eliminado el curso de la tabla inscripciones con curso_id -> " + idCursoEliminado);
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar un curso de la tabla inscripciones -> " + e.getMessage());
            }

            // Borrar de la tabla principal donde curso_id es clave primaria
             query = "DELETE FROM cursos WHERE curso_id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setInt(1,idCursoEliminado);
                if (preparedStatement.executeUpdate() != 0){
                    System.out.println("Se ha eliminado el curso con id -> " + idCursoEliminado);
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar un curso -> " + e.getMessage());
            }

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Error en la transaccion -> " + e.getMessage());
        }
    }

    private static void ejecutarConsulta1() {
        //Listar el nombre y email de todos los estudiantes de la tabla
        int numeroEstudiantes = 0;
        String query = "SELECT (info_estudiante).nombre, (info_estudiante).email FROM estudiantes";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    numeroEstudiantes++;
                    String nombre = resultSet.getString(1);
                    String email = resultSet.getString(2);
                    System.out.println("Estudiante "+ numeroEstudiantes  + "\n" +
                                        "Nombre: " + nombre + " \n" +
                                        "Email: "+ email + " \n");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar un curso de la tabla inscripciones -> " + e.getMessage());
        }
        
    }

    private static void ejecutarConsulta2() {
        //Edad promedio de los estudiantes
        String query = "SELECT avg((info_estudiante).edad) FROM estudiantes";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    double edadPromedio = resultSet.getDouble(1);
                    System.out.println("La edad promedio de los estudiantes es -> " + edadPromedio);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar la edad promedio de los estudiantes -> " + e.getMessage());
        }
    }

    private static void ejecutarConsulta3() {
        int numeroCurso= 0;
        String query = "SELECT c.nombre_curso, c.descripcion, p.nombre FROM Cursos AS c INNER JOIN Profesores AS p ON c.profesor_id = p.profesor_id WHERE c.precio > 180";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    numeroCurso++;
                    String nombreCurso = resultSet.getString(1);
                    String descripcionCurso = resultSet.getString(2);
                    String nombreProfesor = resultSet.getString(3);
                    System.out.println("Nombre del curso "+ numeroCurso +" : " + nombreCurso +"\n"+
                                        "Descripcion del curso: " + descripcionCurso + "\n"+
                                        "Nombre porfesor: " + nombreProfesor+"\n");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar la edad promedio de los estudiantes -> " + e.getMessage());
        }
    }

    private static void ejecutarConsulta4() {
        int numeroCurso = 0;
        String query =
                "SELECT c.nombre_curso, c.descripcion FROM Cursos AS c INNER JOIN Inscripciones AS i ON c.curso_id = i.curso_id INNER JOIN Estudiantes AS e ON i.estudiante_id = e.estudiante_id WHERE (e.info_estudiante).edad > 28";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    numeroCurso++;
                    String nombreCurso = resultSet.getString(1);
                    String descripcionCurso = resultSet.getString(2);
                    System.out.println("Nombre del curso con mayores de 28 "+ numeroCurso +" : " + nombreCurso +"\n"+
                            "Descripcion del curso: " + descripcionCurso + "\n");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar la edad promedio de los estudiantes -> " + e.getMessage());
        }
    }

    private static void ejecutarConsulta5() {

        //Primero obtenemos todos los estudiantes que tenemos registrados

        int numeroCursos = 0;
        String queryObtenerNombres = "SELECT estudiante_id ,(info_estudiante).nombre FROM estudiantes";
        String queryObtenerCursos = "SELECT curso_id FROM inscripciones WHERE estudiante_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(queryObtenerNombres)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    int id = resultSet.getInt(1);
                    String nombreEstudiante = resultSet.getString(2);
                    numeroCursos = 0;
                    try(PreparedStatement preparedStatement1 = connection.prepareStatement(queryObtenerCursos)){
                        preparedStatement1.setInt(1,id);
                        try(ResultSet resultSet1 = preparedStatement1.executeQuery()){
                         while (resultSet1.next()){
                             numeroCursos++;
                         }
                        }
                    }
                    if (numeroCursos > 0) System.out.println("El estudiante " + nombreEstudiante + " está matriculado en "+ numeroCursos + " cursos.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar la edad promedio de los estudiantes -> " + e.getMessage());
        }
    }
}