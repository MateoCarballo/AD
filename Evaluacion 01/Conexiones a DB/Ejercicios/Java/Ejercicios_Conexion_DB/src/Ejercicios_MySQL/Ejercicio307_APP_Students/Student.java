package Ejercicios_MySQL.Ejercicio307_APP_Students;

public class Student {
    private String id;
    private String name;
    private String surname;
    private int age;

    public Student(){

    }

    public Student(String id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Estudiante --------------<> " + "\n" +
                "id -> " + id + "\n" +
                "name-> " + name  + "\n" +
                "surname-> " + surname  + "\n" +
                "age-> " + age + "\n" +
                "-------------------------<>";

    }
}
