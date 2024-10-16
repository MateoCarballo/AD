package EjerciciosExamen;

import java.io.File;
import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private String contrasenha;
    private String usuario;

    public Usuario(String nombre, String contrasenha, String usuario) {
        this.nombre = nombre;
        this.contrasenha = contrasenha;
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
