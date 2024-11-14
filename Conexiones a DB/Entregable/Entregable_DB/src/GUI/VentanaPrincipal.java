package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    JComboBox<String> comboOpciones;
    JButton btnEjecutar;

    public VentanaPrincipal() {
        // Configurar la ventana
        setTitle("Entregable Acceso a Datos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));  // Centrado y separación entre componentes

        // Crear ComboBox con opciones
        comboOpciones = new JComboBox<>(new String[]{
                "Crear nueva categoría",
                "Crear nuevo proveedor",
                "Eliminar proveedor",
                "Crear nuevo usuario",
                "Eliminar usuario",
                "Crear nuevo producto",
                "Eliminar producto",
                "Listar productos con bajo stock",
                "Obtener total de pedidos",
                "Obtener productos por almacén",
                "Listar productos con categorías",
                "Obtener usuarios por categoría"
        });

        // Crear el botón para ejecutar la acción
        btnEjecutar = new JButton("Ejecutar");

        // Agregar el ComboBox y el botón a la ventana
        add(comboOpciones);
        add(btnEjecutar);

        // Ajustar el tamaño y la posición de la ventana
        pack();  // Ajusta el tamaño de la ventana automáticamente
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla
        setVisible(true);

        // Agregar ActionListener (aunque no tenga implementación todavía)
        btnEjecutar.addActionListener(e -> VentanaPrincipal.this.ejecutarAccion());


    }

    private void ejecutarAccion() {
        String opcionSeleccionada = (String) comboOpciones.getSelectedItem();

        // Usar switch para abrir la ventana correspondiente según la opción seleccionada
        switch (opcionSeleccionada) {
            case "Crear nueva categoría":
                new VentanaCrearCategoria();
                break;
            case "Crear nuevo proveedor":
                new VentanaCrearProveedor();
                break;
            case "Eliminar proveedor":
                new VentanaEliminarProveedor();
                break;
            case "Crear nuevo usuario":
                new VentanaCrearUsuario();
                break;
            case "Eliminar usuario":
                new VentanaEliminarUsuario();
                break;
            case "Crear nuevo producto":
                new VentanaCrearProducto();
                break;
            case "Eliminar producto":
                new VentanaEliminarProducto();
                break;
            case "Listar productos con bajo stock":
                new VentanaBajoStock();
                break;
            case "Obtener total de pedidos":
                new VentanaPedidosPorUsuario();
                break;
            case "Obtener productos por almacén":
                new VentanaProductosAlmacenadosPorAlmacen();
                break;
            case "Listar productos con categorías":
                new VentanaListarProductosPorCategoriasYProveedores();
                break;
            case "Obtener usuarios por categoría":
                new VentanaUsuariosPorComprasEnCategoria();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Opción no válida.");
        }


        } }


