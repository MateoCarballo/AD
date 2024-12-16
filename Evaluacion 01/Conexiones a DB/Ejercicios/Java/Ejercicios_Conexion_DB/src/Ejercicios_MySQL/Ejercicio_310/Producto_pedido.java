package Ejercicios_MySQL.Ejercicio_310;

public class Producto_pedido {
    private int idPedido;
    private int idProducto;
    private int cantidadProducto;

    public Producto_pedido(int idPedido, int idProducto, int cantidadProducto) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidadProducto = cantidadProducto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
}
