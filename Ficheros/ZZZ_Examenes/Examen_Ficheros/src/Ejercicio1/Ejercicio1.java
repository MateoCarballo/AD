package Ejercicio1;

import java.io.File;

public class Ejercicio1 {
    public static void main(String[] args) {
        String contenidoPorTeclado = "texto.txt";
        solicitarDatosConsola();
        filtrarContenido("src/Archivos ejercicio 1",contenidoPorTeclado);
    }

    private static void solicitarDatosConsola() {
    }

    private static void filtrarContenido(String ruta, String entradaTeclado) {
        File dir = new File(ruta);
        Filtrado filtro = new Filtrado(dir.listFiles());
        for (int i = 0; i <filtro.files.length ; i++) {
            if ((filtro.files[i].isFile()) && (filtro.accept(filtro.files[i],entradaTeclado))){
                System.out.println("El archivo que buscas es -> " + filtro.files[i].getName());
            }
        }
    }

    /* OTRA VERSION

    public static void main(String[] args) {
        solicitarDatosConsola();
        filtrarContenido("src/Archivos ejercicio 1");
    }

    private static void solicitarDatosConsola() {
    }

    private static void filtrarContenido(String ruta) {
        Filtrado filtro = new Filtrado();
        File dir = new File(ruta);
        File[] archivoscontenidos = dir.listFiles();

        for (File archivoscontenido : archivoscontenidos) {
            if (((filtro.accept(dir, archivoscontenido.getName()))) && (archivoscontenido.isFile())) {
                System.out.println("El archivo que buscas es -> " + archivoscontenido.getName());
            }
        }
    }

     */
}
