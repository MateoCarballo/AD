package Ejercicios_MySQL.Ejercicio305;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controlador implements ActionListener{
    
    // TODO CONSTRUCTOR DEL CONTROLADOR CON LA VISTA Y EL MODELO
    private Modelo model;
    private Vista view;
    public Controlador(Modelo modelo, Vista vista){
        this.model = modelo;
        this.view=vista;
    }

    public void arrancarApp(){
        view.arranca();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("SUMAR")){
            view.escribirResultado(model.sumarCampos(view.obtenerValoresJTextFields()));
        }
        //TODO: USAR e.getActionCommand() PARA SABER QUE SE ESTÁ EJECUTANDO
        //TODO: OBTENER PARÁMETROS DE LA VISTA, LLAMAR A SUMAR Y PINTAR EL VALOR EN LA VISTA
    } 
}
