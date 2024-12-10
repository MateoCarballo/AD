package Ejercicios_MySQL.Ejercicio305;

public class Modelo {

    public Modelo(){

    }

    public String sumarCampos(String[] campos){
        int sumando1= Integer.parseInt(campos[0]);
        int sumando2= Integer.parseInt(campos[1]);
        return String.valueOf(sumando1+sumando2);
    }
}
