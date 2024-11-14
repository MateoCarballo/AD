package GUI;

import javax.swing.*;
import java.awt.*;

public class VentanaEliminarProveedor extends JFrame {
    JTextField textFieldIdProveedorEliminar;
    JButton btnEliminarProveedor;

    VentanaEliminarProveedor() {
        setTitle("Eliminar Proveedor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Crear el campo de texto para ingresar el nombre del proveedor
        JLabel labelProveedorEliminar = new JLabel("ID del proveedor a eliminar:");
        textFieldIdProveedorEliminar = new JTextField(20);

        // Crear el botón para eliminar el proveedor
        btnEliminarProveedor = new JButton("Eliminar");

        // Agregar los componentes a la ventana
        add(labelProveedorEliminar);
        add(textFieldIdProveedorEliminar);
        add(btnEliminarProveedor);

        // Ajustar el tamaño de la ventana y centrarla
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
