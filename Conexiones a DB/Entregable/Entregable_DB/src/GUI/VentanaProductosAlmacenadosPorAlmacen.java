package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaProductosAlmacenadosPorAlmacen extends JFrame {
    JTextField textFieldNombreAlmacen;
    JButton btnListarProductos;
    JTextArea textAreaProductos;
    JScrollPane scrollPane;

    VentanaProductosAlmacenadosPorAlmacen() {
        setTitle("Productos Almacenados por Almacén");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Crear campo de texto para ingresar el nombre o ID del almacén
        JLabel labelNombreAlmacen = new JLabel("Nombre o ID del almacén:");
        textFieldNombreAlmacen = new JTextField(20);

        // Crear el botón para listar los productos almacenados en el almacén
        btnListarProductos = new JButton("Listar Productos");

        // Crear un área de texto para mostrar los productos almacenados
        textAreaProductos = new JTextArea(10, 40);
        textAreaProductos.setEditable(false);
        scrollPane = new JScrollPane(textAreaProductos);

        // Agregar los componentes a la ventana
        add(labelNombreAlmacen);
        add(textFieldNombreAlmacen);
        add(btnListarProductos);
        add(scrollPane);

        // Ajustar el tamaño de la ventana y centrarla
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Acción del botón
        btnListarProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

}
