package GUI;

import javax.swing.*;
import java.awt.*;

public class VentanaCrearUsuario extends JFrame {
    JTextField textFieldNombreUsuario, textFieldCorreoUsuario, textFieldTelefonoUsuario;
    JButton btnCrearUsuario;

    VentanaCrearUsuario() {
        setTitle("Crear Nuevo Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Crear los campos de texto para los datos del usuario
        JLabel labelNombreUsuario = new JLabel("Nombre del usuario:");
        textFieldNombreUsuario = new JTextField(20);

        JLabel labelCorreoUsuario = new JLabel("Correo electrónico:");
        textFieldCorreoUsuario = new JTextField(20);

        JLabel labelTelefonoUsuario = new JLabel("Teléfono:");
        textFieldTelefonoUsuario = new JTextField(20);

        // Crear el botón para crear el usuario
        btnCrearUsuario = new JButton("Crear");

        // Agregar los componentes a la ventana
        add(labelNombreUsuario);
        add(textFieldNombreUsuario);
        add(labelCorreoUsuario);
        add(textFieldCorreoUsuario);
        add(labelTelefonoUsuario);
        add(textFieldTelefonoUsuario);
        add(btnCrearUsuario);

        // Ajustar el tamaño de la ventana y centrarla
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Accion del botón
        btnCrearUsuario.addActionListener(e -> crearUsuario());
    }

    private void crearUsuario() {
        String nombre = textFieldNombreUsuario.getText();
        String correo = textFieldCorreoUsuario.getText();
        String telefono = textFieldTelefonoUsuario.getText();

        // Lógica para crear un nuevo usuario (aquí solo se muestra en consola, deberías agregar la lógica de base de datos)
        System.out.println("Usuario creado: " + nombre + ", " + correo + ", " + telefono);

        // Puedes agregar validaciones o mensajes de éxito/advertencia según lo necesites
    }

}
