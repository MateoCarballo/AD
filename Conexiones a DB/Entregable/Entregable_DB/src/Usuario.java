public class Usuario {
    private int id_usuario;
    private String Usuario;
    private int numeroDePedidos;

    public Usuario(){

    }

    public Usuario(int id_usuario, String usuario, int numeroDePedidos) {
        this.id_usuario = id_usuario;
        Usuario = usuario;
        this.numeroDePedidos = numeroDePedidos;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public int getNumeroDePedidos() {
        return numeroDePedidos;
    }

    public void setNumeroDePedidos(int numeroDePedidos) {
        this.numeroDePedidos = numeroDePedidos;
    }
}
