package Ejercicio307_APP_Students;

public class Main {
    public static void main(String[] args) {
        ManageStudents manageStudents = new ManageStudents();
        AppStudents app = new AppStudents(manageStudents);
        app.arranca();
    }
}
