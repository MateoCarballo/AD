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
        /*
        do{
             entradaTeclado = br.readLine();
             //TODO introducir el patron regex que corresponda
        }while(!comprobarPatronRegex(entradaTeclado,"PATRON REGEX"));
         */

            entradaTeclado = br.readLine();


        switch (Integer.parseInt(entradaTeclado)){
            case 1 -> obtieneDatosCrearCategoria();
            case 2 -> obtieneDatosCrearProveedor();
        }



    }

    private void obtieneDatosCrearCategoria() {
        System.out.println("Introduce el nombre de la nueva categoria");
        try {
            String nombreCategoria = br.readLine();
            m.crearCategoria(nombreCategoria);
        } catch (IOException e) {
            System.out.println("Error al recoger el nombre de la nueva categoria");
        }
    }

    public void obtieneDatosCrearProveedor() {
        String  nombreProveedor;
        String  nifProveedor;
        int     telefonoProveedor;
        String  telefonoProveedor1;
        String  emailProveedor;


        try {
            do {
                System.out.println("Nombre del nuevo proveedor");
                nombreProveedor = br.readLine();
            }while(!comprobarPatronRegex(nombreProveedor,"[a-zA-Z | \\s]{1,20}"));
        } catch (IOException e) {
            System.out.println("Error al recoger el nombre del nuevo proveedor");
        }


        try {
            do {
                System.out.println("NIF para el nuevo proveedor");
                nifProveedor = br.readLine();
            }while(!comprobarPatronRegex(nifProveedor,"^[1-9]{1}[0-9]{7}[A-Z]{1}"));
        } catch (IOException e) {
            System.out.println("Error al recoger el NIF del nuevo proveedor");
        }



        try {
            do {
                System.out.println("Telefono de contacto del proveedor");
                telefonoProveedor1 = br.readLine();
            }while(!comprobarPatronRegex(telefonoProveedor1,"[0-9]{9}"));
            telefonoProveedor = Integer.parseInt(telefonoProveedor1);
        } catch (IOException e) {
            System.out.println("Error al recoger el NIF del nuevo proveedor");
        }



        try {
            do {
                System.out.println("Email del nuevo proveedor");
                emailProveedor = br.readLine();
            }while(!comprobarPatronRegex(emailProveedor,"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));

                    // "^[a-z]{1,35}[@]{1}[a-z]{1,15}[.]{1}""^[a-z]{1,35}[@]{1}[a-z]{1,15}[.]{1}"

        } catch (IOException e) {
            System.out.println("Error al recoger el NIF del nuevo proveedor");
        }
    }


    public boolean comprobarPatronRegex(String string, String pattern){
        return string.matches(pattern);
    }
}
