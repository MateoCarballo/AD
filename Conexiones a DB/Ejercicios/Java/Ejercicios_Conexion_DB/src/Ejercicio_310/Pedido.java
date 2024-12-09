package Ejercicio_310;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {

    private int id;
    private Cliente cliente;
    private Date fecha;
    private ArrayList<LineaPedido> lineasPedido;

    public Pedido(int id, Date fecha, Cliente cliente, ArrayList<LineaPedido> lineasPedido) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.lineasPedido = lineasPedido;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public ArrayList<LineaPedido> getLineasPedido() {
        return lineasPedido;
    }

    private double getPrecioTotal() {
        double precioTotal = 0.0;

        for (LineaPedido lineaPedido: lineasPedido) {
            precioTotal += lineaPedido.getProducto().getPrecio() * lineaPedido.getCantidad();
        }

        return precioTotal;
    }

    @Override
    public String toString() {
        String infoPedido = "";
        infoPedido += String.format("Cliente:%n %s%n", cliente);
        infoPedido += String.format("Fecha:%n %s%n", fecha);
        infoPedido += "\nProductos:\n";

        for (LineaPedido lineaPedido: lineasPedido) {
            infoPedido += lineaPedido.getProducto().toString();
            infoPedido += String.format("Cantidad: %d%n", lineaPedido.getCantidad());
        }

        infoPedido += String.format("Precio total: %.2f%n", getPrecioTotal());

        return infoPedido;
    }
}
