package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaListarProductosPorCategoriasYProveedores extends JFrame {
    JComboBox<String> comboCategorias, comboProveedores;
    JButton btnListarProductos;
    JTextArea textAreaProductos;
    JScrollPane scrollPane;

    // Constructor
    VentanaListarProductosPorCategoriasYProveedores() {
        setTitle("Listar Productos por Categoría y Proveedor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Crear los ComboBoxes para seleccionar la categoría y el proveedor
        JLabel labelCategoria = new JLabel("Seleccionar categoría:");
        comboCategorias = new JComboBox<>(new String[] {"Electrónica", "Ropa", "Alimentos", "Hogar"});

        JLabel labelProveedor = new JLabel("Seleccionar proveedor:");
        comboProveedores = new JComboBox<>(new String[] {"Proveedor A", "Proveedor B", "Proveedor C"});

        // Crear el botón para listar los productos por categoría y proveedor
        btnListarProductos = new JButton("Listar Productos");

        // Crear un área de texto para mostrar los productos
        textAreaProductos = new JTextArea(10, 40);
        textAreaProductos.setEditable(false);
        scrollPane = new JScrollPane(textAreaProductos);

        // Agregar los componentes a la ventana
        add(labelCategoria);
        add(comboCategorias);
        add(labelProveedor);
        add(comboProveedores);
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
