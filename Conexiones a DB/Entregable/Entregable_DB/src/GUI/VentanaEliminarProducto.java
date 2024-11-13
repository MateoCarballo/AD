package GUI;

import javax.swing.*;
import java.awt.*;

public class VentanaEliminarProducto extends JFrame {
    JTextField textFieldProductoEliminar;
    JButton btnEliminarProducto;

    VentanaEliminarProducto() {
        setTitle("Eliminar Producto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Crear el campo de texto para ingresar el nombre del producto
        JLabel labelProductoEliminar = new JLabel("Nombre del producto a eliminar:");
        textFieldProductoEliminar = new JTextField(20);

        // Crear el botón para eliminar el producto
        btnEliminarProducto = new JButton("Eliminar");

        // Agregar los componentes a la ventana
        add(labelProductoEliminar);
        add(textFieldProductoEliminar);
        add(btnEliminarProducto);

        // Ajustar el tamaño de la ventana y centrarla
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
