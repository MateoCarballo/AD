package GUI;

import javax.swing.*;
import java.awt.*;

public class VentanaCrearProducto extends JFrame {
    JTextField textFieldNombreProducto;
    JTextField textFieldPrecio;
    JTextField textFieldStock;
    JTextField textFieldNombreCategoria;
    JTextField textFieldNif;
    JButton btnCrearProducto;

    VentanaCrearProducto() {
        setTitle("Crear Producto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Crear el campo de texto para ingresar el nombre del usuario
        JLabel labelNombreProducto = new JLabel("Nombre del producto");
        textFieldNombreProducto = new JTextField(20);

        JLabel labelPrecio = new JLabel("Precio");
        textFieldPrecio = new JTextField(20);

        JLabel labelStock = new JLabel("Stock");
        textFieldStock = new JTextField(20);

        JLabel labelNombreCategoría = new JLabel("Nombre Categoría");
        textFieldNombreCategoria = new JTextField(20);

        JLabel labelNif = new JLabel("Nif");
        textFieldNif = new JTextField(20);

        // Crear el botón para eliminar el usuario
        btnCrearProducto = new JButton("Crear");

        // Agregar los componentes a la ventana
        add(labelNombreProducto);
        add(textFieldNombreProducto);
        add(labelPrecio);
        add(textFieldPrecio);
        add(labelStock);
        add(textFieldStock);
        add(labelNombreCategoría);
        add(textFieldNombreCategoria);
        add(labelNif);
        add(textFieldNif);
        add(btnCrearProducto);

        // Ajustar el tamaño de la ventana y centrarla
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Acción del botón
        //btnEliminarUsuario.addActionListener(e -> crearProducto());
    }
}
