package Ejercicio307_APP_Students;

public class Main {
    public static void main(String[] args) {
        ManageStudents manageStudents = new ManageStudents("jdbc:mysql://localhost:3306/students","root","abc123.");
        AppStudents app = new AppStudents(manageStudents);
        app.initApp();
        app.arranca();
    }
}
