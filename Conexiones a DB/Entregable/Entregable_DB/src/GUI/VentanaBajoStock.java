package GUI;

import javax.swing.*;
import java.awt.*;

public class VentanaBajoStock extends JFrame {
    JTextField textFieldStockUmbral;
    JButton btnListarProductos;

    VentanaBajoStock() {
        setTitle("Listar Productos con Bajo Stock");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Crear el campo de texto para ingresar el umbral de stock
        JLabel labelUmbralStock = new JLabel("Unidades disponibles:");
        textFieldStockUmbral = new JTextField(5);

        // Crear el botón para listar los productos con bajo stock
        btnListarProductos = new JButton("Listar");

        // Agregar los componentes a la ventana
        add(labelUmbralStock);
        add(textFieldStockUmbral);
        add(btnListarProductos);

        // Ajustar el tamaño de la ventana y centrarla
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
