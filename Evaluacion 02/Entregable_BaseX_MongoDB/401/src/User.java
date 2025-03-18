public class User {
    private int userId;
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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Contenido del usuario \n" +
                "userId: " + userId + "\n" +
                "name: " + name + "\n" +
                "email: " + email + "\n" +
                "age: " + age + "\n" +
                "direction: " + direction + "\n";
    }
}
