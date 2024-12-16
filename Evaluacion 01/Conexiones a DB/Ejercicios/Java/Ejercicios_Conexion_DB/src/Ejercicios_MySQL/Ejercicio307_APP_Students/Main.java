package Ejercicios_MySQL.Ejercicio307_APP_Students;

public class Main {
    public static void main(String[] args) {
        ManageStudents manageStudents = new ManageStudents(
                "jdbc:mysql://localhost:3306/school","root","abc123.");
        AppStudents app = new AppStudents(manageStudents);
        app.getValuesFromDB();
        app.arranca();
    }
}
