package GUI;

import javax.swing.*;
import java.awt.*;

public class VentanaCrearProveedor extends JFrame {
    JTextField textFieldNombreProveedor, textFieldTelefono, textFieldDireccion;
    JButton btnCrearProveedor;

    VentanaCrearProveedor() {
        setTitle("Crear Nuevo Proveedor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Crear los campos de texto
        JLabel labelNombreProveedor = new JLabel("Nombre del proveedor:");
        textFieldNombreProveedor = new JTextField(20);

        JLabel labelTelefono = new JLabel("Teléfono:");
        textFieldTelefono = new JTextField(20);

        JLabel labelDireccion = new JLabel("Dirección:");
        textFieldDireccion = new JTextField(20);

        // Crear el botón para crear el proveedor
        btnCrearProveedor = new JButton("Crear");

        // Agregar los componentes a la ventana
        add(labelNombreProveedor);
        add(textFieldNombreProveedor);
        add(labelTelefono);
        add(textFieldTelefono);
        add(labelDireccion);
        add(textFieldDireccion);
        add(btnCrearProveedor);

        // Ajustar el tamaño de la ventana y centrarla
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}

