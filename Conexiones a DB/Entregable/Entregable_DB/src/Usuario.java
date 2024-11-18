public class Usuario {
    private String nombreUsuario;
    private int numeroDePedidos;

    public Usuario(){

    }

    public Usuario(String nombreUsuario, int id_usuario) {
        nombreUsuario = nombreUsuario;
        this.numeroDePedidos = numeroDePedidos;
    }

    public String getUsuario() {
        return nombreUsuario;
    }

    public void setUsuario(String usuario) {
        nombreUsuario = usuario;
    }

    public int getNumeroDePedidos() {
        return numeroDePedidos;
    }

    public void setNumeroDePedidos(int numeroDePedidos) {
        this.numeroDePedidos = numeroDePedidos;
    }
}
