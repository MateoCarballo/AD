package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaUsuariosPorComprasEnCategoria extends JFrame {
    JComboBox<String> comboCategorias;
    JButton btnListarUsuarios;
    JTextArea textAreaUsuarios;
    JScrollPane scrollPane;

    // Constructor
    VentanaUsuariosPorComprasEnCategoria() {
        setTitle("Usuarios que han comprado en una categoría");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Crear ComboBox para seleccionar la categoría
        JLabel labelCategoria = new JLabel("Seleccionar categoría:");
        comboCategorias = new JComboBox<>(new String[] {"Electrónica", "Ropa", "Alimentos", "Hogar"});

        // Crear botón para listar los usuarios que han comprado productos de la categoría seleccionada
        btnListarUsuarios = new JButton("Listar Usuarios");

        // Crear un área de texto para mostrar los usuarios
        textAreaUsuarios = new JTextArea(10, 40);
        textAreaUsuarios.setEditable(false);
        scrollPane = new JScrollPane(textAreaUsuarios);

        // Agregar los componentes a la ventana
        add(labelCategoria);
        add(comboCategorias);
        add(btnListarUsuarios);
        add(scrollPane);

        // Ajustar el tamaño de la ventana y centrarla
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Acción del botón
        btnListarUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }
}
