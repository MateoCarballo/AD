package Ejercicio2;

import java.io.Serializable;

public class Producto implements Serializable {

    String nombre;
    float precio;
    int stock;

    public Producto(String nombre, float precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
}
