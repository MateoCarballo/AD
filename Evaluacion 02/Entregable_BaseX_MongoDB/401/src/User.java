public class User {
    private String name;
    private String email;
    private int age;
    private String direction;

    public User(){

    }

    public User(String name, String email, int age, String direction) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getDirection() {
        return direction;
    }
}
