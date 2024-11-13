package GUI;

import javax.swing.*;
import java.awt.*;

public class VentanaCrearCategoria extends JFrame {
    JTextField textFieldNombreCategoria;
    JButton btnCrear;

    VentanaCrearCategoria() {
        setTitle("Crear Nueva Categoría");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Crear el campo de texto para el nombre de la categoría
        JLabel labelNombreCategoria = new JLabel("Nombre de la categoría:");
        textFieldNombreCategoria = new JTextField(20);

        // Crear el botón para crear la categoría
        btnCrear = new JButton("Crear");

        // Agregar los componentes a la ventana
        add(labelNombreCategoria);
        add(textFieldNombreCategoria);
        add(btnCrear);

        // Ajustar el tamaño de la ventana y centrarla
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
