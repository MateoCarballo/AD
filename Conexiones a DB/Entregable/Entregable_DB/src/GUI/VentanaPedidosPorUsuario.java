package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaPedidosPorUsuario extends JFrame {
    JTextField textFieldNombreUsuario;
    JButton btnListarPedidos;
    JTextArea textAreaPedidos;
    JScrollPane scrollPane;

    VentanaPedidosPorUsuario() {
        setTitle("Listar Pedidos por Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Crear campo de texto para ingresar el nombre o ID del usuario
        JLabel labelNombreUsuario = new JLabel("Nombre del usuario o ID:");
        textFieldNombreUsuario = new JTextField(20);

        // Crear el botón para listar los pedidos del usuario
        btnListarPedidos = new JButton("Listar Pedidos");

        // Crear un área de texto para mostrar los pedidos
        textAreaPedidos = new JTextArea(10, 40);
        textAreaPedidos.setEditable(false);
        scrollPane = new JScrollPane(textAreaPedidos);

        // Agregar los componentes a la ventana
        add(labelNombreUsuario);
        add(textFieldNombreUsuario);
        add(btnListarPedidos);
        add(scrollPane);

        // Ajustar el tamaño de la ventana y centrarla
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Acción del botón
        btnListarPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

}
