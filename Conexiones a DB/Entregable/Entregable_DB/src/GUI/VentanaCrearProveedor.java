package GUI;

import javax.swing.*;
import java.awt.*;

public class VentanaCrearProveedor extends JFrame {
    JTextField textFieldNombreProveedor,
            textFieldTelefono,
            textFieldEmail,
            textFieldNIF;
    JButton btnCrearProveedor;

    VentanaCrearProveedor() {
        setTitle("Crear Nuevo Proveedor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Crear los campos de texto
        JLabel labelNombreProveedor = new JLabel("Nombre del proveedor:");
        textFieldNombreProveedor = new JTextField(20);

        JLabel labelNif = new JLabel("NIF:");
        textFieldNIF = new JTextField(20);

        JLabel labelTelefono = new JLabel("Teléfono:");
        textFieldTelefono = new JTextField(20);

        JLabel labelEmail = new JLabel("E-mail:");
        textFieldEmail = new JTextField(20);

        // Crear el botón para crear el proveedor
        btnCrearProveedor = new JButton("Crear");

        // Agregar los componentes a la ventana
        add(labelNombreProveedor);
        add(textFieldNombreProveedor);
        add(labelNif);
        add(textFieldNIF);
        add(labelTelefono);
        add(textFieldTelefono);
        add(labelEmail);
        add(textFieldEmail);
        add(btnCrearProveedor);

        // Ajustar el tamaño de la ventana y centrarla
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}

