package Ejercicio3;

import java.io.*;
import java.util.ArrayList;

public class Ejercicio3 {
    final static String RUTA_FICHERO_1 = "src/Ejercicio3/Recursos/paises1.txt";
    final static String RUTA_FICHERO_2 = "src/Ejercicio3/Recursos/paises2.txt";
    final static String RUTA_FICHERO_3 = "src/Ejercicio3/Recursos/paises3.txt";
    final static String RUTA_FICHERO_RESULTADO = "src/Ejercicio3/Recursos/paises Resultado.txt";

    public static void main(String[] args) {
        ArrayList <String> paisesArrayList = new ArrayList<>();
        String [] paises1 = leerFicheros(RUTA_FICHERO_1,7);
        String [] paises2 = leerFicheros(RUTA_FICHERO_2,7);
        String [] paises3 = leerFicheros(RUTA_FICHERO_3,6);

        rellenarPaises(paisesArrayList,paises1);
        rellenarPaises(paisesArrayList,paises2);
        rellenarPaises(paisesArrayList,paises3);

        escribirFichero(paisesArrayList,RUTA_FICHERO_RESULTADO);


    }

    private static void escribirFichero(ArrayList<String> paises, String ruta) {
        File ficheroResultado = new File(ruta);
        if (!ficheroResultado.exists()){
            try {
                ficheroResultado.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ficheroResultado))){
            int numeroDeCaracteresMayor = 25;
            boolean bucleCompleto = false;
            boolean encontradoUno = false;
            boolean recorridoCompleto = false;

            do{
                if (bucleCompleto && !encontradoUno ){
                    numeroDeCaracteresMayor--;
                }

                for (int i = 0; i < paises.size(); i++) {
                    String paisSinEspacios = paises.get(i).trim();
                    if(paisSinEspacios.length() == numeroDeCaracteresMayor){
                        dos.writeUTF(paises.get(i));
                        paises.remove(i);
                        encontradoUno = true;
                    }
                }
                encontradoUno= false;
                bucleCompleto=true;
            }while(!paises.isEmpty());



            //TODO
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void rellenarPaises(ArrayList<String> paisesArrayList, String[] paises) {
        for (int i = 0; i < paises.length; i++) {
            paisesArrayList.add(paises[i]);
        }
    }

    private static String[] leerFicheros(String ruta, int numeroDeCiclos) {
        File fichero = new File (ruta);
        String [] paises = new String[7];
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fichero))){
            for (int i = 0; i < numeroDeCiclos; i++) {
                paises[i] = dis.readUTF();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return paises;
    }
}