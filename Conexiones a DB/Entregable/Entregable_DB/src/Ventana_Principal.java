import javax.swing.*;

public class Ventana_Principal extends JFrame {

    private JPanel contentPane;
    private JTextField textoAutor;
    private JTextField textoTitulo;
    private JButton btnValidar;
    private JButton btnCrearNuevoAutorLibro;

    public Ventana_Principal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(980, 380, 507, 376);
        setTitle("Entregable postgre mySQL");
        setResizable(false);
        setVisible(true);
    }

}

