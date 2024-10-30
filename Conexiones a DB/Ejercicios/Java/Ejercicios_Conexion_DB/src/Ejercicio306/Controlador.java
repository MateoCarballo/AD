package Ejercicio306;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener{
    private Modelo model;
    private Vista view;

    // TODO: CONSTRUCTOR DEL CONTROLADOR CON LA VISTA Y EL MODELO
    public Controlador(Modelo modelo, Vista vista){
        this.model = modelo;
        this.view = vista;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        //TODO: USAR e.getActionCommand() PARA SABER QUE SE ESTÁ EJECUTANDO

        //TODO: OBTENER PARÁMETROS DE LA VISTA, OBTENER INFORMACIÓN USUARIO Y PINTAR EL VALOR EN LA VISTA
        if (e.getActionCommand().equals("BUSCAR")){
            view.escribirResultado(model.obtenerResultados(view.getValue()));
        }

    }

    public void arrancaApp() {
        view.arrancar();

    }
}
