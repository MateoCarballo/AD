package GUI;

import javax.swing.*;
import java.awt.*;

public class VentanaCrearProducto extends JFrame {
    JTextField textFieldNombreProducto, textFieldPrecio, textFieldStock;
    JComboBox<String> comboCategoria, comboProveedor;
    JButton btnCrearProducto;

    VentanaCrearProducto() {
        setTitle("Crear Nuevo Producto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Crear los campos de texto y combo boxes
        JLabel labelNombreProducto = new JLabel("Nombre del producto:");
        textFieldNombreProducto = new JTextField(20);

        JLabel labelPrecio = new JLabel("Precio:");
        textFieldPrecio = new JTextField(20);

        JLabel labelStock = new JLabel("Stock:");
        textFieldStock = new JTextField(20);

        JLabel labelCategoria = new JLabel("Categoría:");
        comboCategoria = new JComboBox<>(new String[]{"Electrónica", "Ropa", "Alimentos"});

        JLabel labelProveedor = new JLabel("Proveedor:");
        comboProveedor = new JComboBox<>(new String[]{"Proveedor 1", "Proveedor 2"});

        // Crear el botón para crear el producto
        btnCrearProducto = new JButton("Crear");

        // Agregar los componentes a la ventana
        add(labelNombreProducto);
        add(textFieldNombreProducto);
        add(labelPrecio);
        add(textFieldPrecio);
        add(labelStock);
        add(textFieldStock);
        add(labelCategoria);
        add(comboCategoria);
        add(labelProveedor);
        add(comboProveedor);
        add(btnCrearProducto);

        // Ajustar el tamaño de la ventana y centrarla
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
