public class Videojuego {
    private int idVieojuego;
    private int cantidad;

    public Videojuego(int idVieojuego, int cantidad) {
        this.idVieojuego = idVieojuego;
        this.cantidad = cantidad;
    }

    public void addQuantity(){
        this.cantidad++;
    }

    public void lessQuantity(){
        this.cantidad++;
    }
}
