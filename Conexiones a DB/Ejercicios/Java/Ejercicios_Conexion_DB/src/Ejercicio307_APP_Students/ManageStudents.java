package Ejercicio307_APP_Students;

import java.sql.*;
import java.util.ArrayList;

public class ManageStudents {
    Connection connection;
    final String URL ;
    final String USR;
    final String PSW;


    public ManageStudents(String url, String user, String password){
        this.URL = url;
        this.USR = user;
        this.PSW = password;
        this.connection = null;
    }

    public void openConnection(){
        if (connection == null){
            try {
                connection = DriverManager.getConnection(URL,USR,PSW);
            } catch (SQLException e) {
                System.out.println("Error durante la conexion con la DB School");
            }
        }


    }
    public void closeConnection(){
        if (connection != null){
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.out.println("Error durante la desconexión con la DB School");
            }
        }
    }
    public boolean addStudent(Student student){
        openConnection();
        boolean insercion = false;
        int filasAfectadas = 0;

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO student (id,name,surname,age) VALUES (?,?,?,?)");){
            preparedStatement.setString(1,student.getId());
            preparedStatement.setString(2,student.getName());
            preparedStatement.setString(3,student.getSurname());
            preparedStatement.setInt(4,student.getAge());
            filasAfectadas = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la insercion del estudiante");
        }finally {
            closeConnection();
        }
        if (filasAfectadas == 1) {
            insercion = true;
        }
    return insercion ;
    }
    public Student getStudent (String id){
        return new Student();
    }
    public boolean deleteStudent(String id){
        int filasAfectadas = 0;
        boolean borradoExitoso = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM student WHERE id = ?")){
            preparedStatement.setString(1,id);
            filasAfectadas = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al borrar el estudiante de la base de datos");
        }finally{
            closeConnection();
        }
        if (filasAfectadas == 1){
            borradoExitoso = true;
        }

        return borradoExitoso;
    }
    public boolean modifyStudent(Student student){
        return true;
    }
    public ArrayList <Student> getStudents(){
        openConnection();
        ArrayList<Student> students = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,name,surname,age FROM student")){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                students.add(new Student(resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("age")));
            }
        } catch (SQLException e) {
            System.out.println("Error al traer los datos desde la DB");
        }finally{
            closeConnection();
        }
        return students;
    }
}
