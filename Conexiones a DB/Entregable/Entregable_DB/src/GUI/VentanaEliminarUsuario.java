package GUI;

import javax.swing.*;
import java.awt.*;

public class VentanaEliminarUsuario extends JFrame {
    JTextField textFieldNombreUsuarioEliminar;
    JButton btnEliminarUsuario;

    VentanaEliminarUsuario() {
        setTitle("Eliminar Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Crear el campo de texto para ingresar el nombre del usuario
        JLabel labelNombreUsuarioEliminar = new JLabel("Nombre del usuario a eliminar:");
        textFieldNombreUsuarioEliminar = new JTextField(20);

        // Crear el botón para eliminar el usuario
        btnEliminarUsuario = new JButton("Eliminar");

        // Agregar los componentes a la ventana
        add(labelNombreUsuarioEliminar);
        add(textFieldNombreUsuarioEliminar);
        add(btnEliminarUsuario);

        // Ajustar el tamaño de la ventana y centrarla
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Acción del botón
        btnEliminarUsuario.addActionListener(e -> eliminarUsuario());
    }

    private void eliminarUsuario() {
        String nombreUsuario = textFieldNombreUsuarioEliminar.getText();

        // Lógica para eliminar el usuario (aquí solo se muestra en consola, deberías agregar la lógica de base de datos)
        System.out.println("Usuario eliminado: " + nombreUsuario);

        // Validaciones o mensajes de éxito/advertencia podrían ir aquí
    }

}
