package Ejercicio307_APP_Students;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageStudents {
    private Connection connection;
    final String URL = "jdbc:mysql://localhost:3306/School";
    final String USR = "root";
    final String PSW = "abc123.";


    public ManageStudents(){
        connection = null;
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
            } catch (SQLException e) {
                System.out.println("Error durante la desconexión con la DB School");
            }
        }
    }
    public boolean addStudent(Student student){
        openConnection();
        boolean insercion = false;
        int filasAfectadas = 0;

        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO student (id,name,surname,age) VALUES (?,?,?,?)");){
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
        return true;
    }
    public boolean modifyStudent(Student student){
        return true;
    }
    public ArrayList <Student> getStudents(){
        return new ArrayList<>();
    }
}
