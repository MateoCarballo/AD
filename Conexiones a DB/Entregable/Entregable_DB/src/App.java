import java.io.*;

public class App {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private Modelo m;
    public App (Modelo m){
        this.m = m;
    }

    public void iniciarApp() throws IOException {

        String entradaTeclado;
        System.out.println("""
                ############################################################
                                            MENU
                1.  Crear una nueva categoría (PostgreSQL)
                2.  Crear un nuevo proveedor (PostgreSQL)
                3.  Eliminar un nuevo proveedor (PostgreSQL)
                4.  Crear un nuevo usuario (MySQL)
                5.  Eliminar un usuario (MySQL)
                6.  Crear nuevo producto (nombre, precio, stock, categoria, proveedor) (MySQL + PostgreSQL)
                7.  Eliminar un producto por su nombre (MySQL + PostgreSQL)
                8.  Listar los productos con bajo stock (menos de X unidades disponibles) (MySQL)
                9.  Obtener el total de pedidos realizados por cada usuario (MySQL)
                10. Obtener la cantidad de productos almacenados por cada almacén (PostgreSQL)
                11. Listar todos los productos con sus respectivas categorías y proveedores (PostgreSQL)
                12. Obtener todos los Usuarios que han comprado algún producto de una categoria dada (MySQL + PostgreSQL).
                """);
        do{
             entradaTeclado = br.readLine();
             //TODO introducir el patron regex que corresponda
        }while(!comprobarPatronRegex(entradaTeclado,"PATRON REGEX"));

        switch (Integer.parseInt(entradaTeclado)){
            case 1 ->
            case 2 ->
        }



    }

    public boolean comprobarPatronRegex(String string, String pattern){
        return string.matches(pattern);
    }
}
