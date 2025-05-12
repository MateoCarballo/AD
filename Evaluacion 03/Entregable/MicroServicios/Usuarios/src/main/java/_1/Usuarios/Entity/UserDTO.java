package _1.Usuarios.Entity;

public class UserDTO {
    private String nombre;
    private int clave;

    public UserDTO(String nombre, int clave) {
        this.nombre = nombre;
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }
}
