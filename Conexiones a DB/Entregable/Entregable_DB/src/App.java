import java.io.*;

public class App {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private Modelo m;
    public App (Modelo m){
        this.m = m;
    }
    private final String NIF_PATTERN = "^[1-9][0-9]{7}[A-Za-z]$";
    private final String NOMBRE_PATTERN = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]{1,20}$";
    private final String TELEFONO_PATTERN = "^\\d{9}$";
    private final String ID_PATTERN = "^\\d{1,50}$";
    private final String CORREO_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(?:\\.[a-zA-Z]{2,})?$";
    private final String PRECIO_PATTERN = "^(0|[1-9]\\d*)(\\.\\d{1,2})?$";
    private final String STOCK_PATTERN = "^[1-9]\\d*$";

    public void iniciarApp() throws IOException {
        boolean continuar = true;
        do{
            String entradaTeclado;
            System.out.println("""
                ##############################################################################################
                                            MENU
                0.  Salir de la aplicacion.
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
                ##############################################################################################
                """);
        /*
        do{
             entradaTeclado = br.readLine();
             //TODO introducir el patron regex que corresponda
        }while(!comprobarPatronRegex(entradaTeclado,"PATRON REGEX"));
         */

            entradaTeclado = br.readLine();


            switch (Integer.parseInt(entradaTeclado)){
                case 0 -> continuar = false;
                case 1 -> obtieneDatosCrearCategoria();
                case 2 -> obtieneDatosCrearProveedor();
                case 3 -> obtieneDatosEliminarProveedor();
                case 4 -> obtieneCrearNuevoUsuario();
                case 5 -> obtieneDatosEliminarUsuario();
                case 6 -> obtieneDatosCrearNuevoProducto();
                case 7 -> obtieneDatosEliminarProductoPorNombre();
                case 8 -> obtieneDatosCrearProveedor();
                case 9 -> obtieneDatosCrearProveedor();
                case 10 -> obtieneDatosCrearProveedor();
                case 11 -> obtieneDatosCrearProveedor();
                case 12 -> obtieneDatosCrearProveedor();
            }

        }while(continuar);
    }

    // 1
    private void obtieneDatosCrearCategoria() {
    String nombreCategoria;
    try {
        do {
            System.out.println("Introduce el nombre de la nueva categoria");
            nombreCategoria = br.readLine();
        }while(!comprobarPatronRegex(nombreCategoria,NOMBRE_PATTERN));
        m.crearCategoria(nombreCategoria);
    } catch (IOException e) {
        System.out.println("Error al recoger el nombre de la nueva categoria");
    }
}
    //2
    private void obtieneDatosCrearProveedor() {
    String  nombreProveedor = null;
    String  nifProveedor = null;
    int     telefonoProveedor = -1;
    String  telefonoProveedor1;
    String  emailProveedor = null;

    try {
        do {
            System.out.println("Nombre del nuevo proveedor");
            nombreProveedor = br.readLine();
        }while(!comprobarPatronRegex(nombreProveedor,NOMBRE_PATTERN));
    } catch (IOException e) {
        System.out.println("Error al recoger el nombre del nuevo proveedor");
    }

    try {
        do {
            System.out.println("NIF para el nuevo proveedor");
            nifProveedor = br.readLine();
        }while(!comprobarPatronRegex(nifProveedor,NIF_PATTERN));
    } catch (IOException e) {
        System.out.println("Error al recoger el NIF del nuevo proveedor");
    }

    try {
        do {
            System.out.println("Telefono de contacto del proveedor");
            telefonoProveedor1 = br.readLine();
        }while(!comprobarPatronRegex(telefonoProveedor1,TELEFONO_PATTERN));
        telefonoProveedor = Integer.parseInt(telefonoProveedor1);
    } catch (IOException e) {
        System.out.println("Error al recoger el telefono del nuevo proveedor");
    }

    try {
        do {
            System.out.println("Email del nuevo proveedor");
            emailProveedor = br.readLine();
        }while(!comprobarPatronRegex(emailProveedor,CORREO_PATTERN));
    } catch (IOException e) {
        System.out.println("Error al recoger el email del nuevo proveedor");
    }

    if (nombreProveedor != null && nifProveedor != null && emailProveedor != null && telefonoProveedor != -1){
        m.crearProveedor(nombreProveedor,nifProveedor,telefonoProveedor,emailProveedor);
    }

    }
    //3
    private void obtieneDatosEliminarProveedor() {
        String idProveedorParaEliminar;
        try {
            do {
                System.out.println("Introduce el ID para eliminar");
                idProveedorParaEliminar = br.readLine();
            }while(!comprobarPatronRegex(idProveedorParaEliminar,ID_PATTERN));
            m.eliminarProveedor(Integer.parseInt(idProveedorParaEliminar));
        } catch (IOException e) {
            System.out.println("Error al recoger el id del proveedor a eliminar");
        }
    }
    //4
    private void obtieneCrearNuevoUsuario() {
        String nombre, correo, anho;

        try {
            do {
                System.out.println("Introduce el nombre del nuevo usuario");
                nombre = br.readLine();
            }while(!comprobarPatronRegex(nombre,"^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]{1,20}$"));

            do {
                System.out.println("Introduce el correo del nuevo usuario");
                correo = br.readLine();
            }while(!comprobarPatronRegex(correo,"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(?:\\.[a-zA-Z]{2,})?$"));

            do {
                System.out.println("Introduce el año de nacimiento del nuevo usuario");
                anho = br.readLine();
            }while(!comprobarPatronRegex(anho,"19[0-9]{2}|20[0-9]{2}|2100"));

            m.crearUsuario(nombre,correo,Integer.parseInt(anho));
        } catch (IOException e) {
            System.out.println("Error al recoger el id del proveedor a eliminar");
        }
    }
    //5
    private void obtieneDatosEliminarUsuario(){
        String idUsuarioEliminar;
        try {
            do {
                System.out.println("Introduce el nombre del nuevo usuario");
                idUsuarioEliminar = br.readLine();
            }while(!comprobarPatronRegex(idUsuarioEliminar,ID_PATTERN));

            m.eliminarUsuario(Integer.parseInt(idUsuarioEliminar));
        } catch (IOException e) {
            System.out.println("Error al recoger el id del proveedor a eliminar");
        }
    }
    //6
    private void obtieneDatosCrearNuevoProducto(){
        String nombreProducto, nombreCategoria, nif, precio, stock;

        try {
            do {
                System.out.println("Introduce el nombre del nuevo producto");
                nombreProducto = br.readLine();
            }while(!comprobarPatronRegex(nombreProducto,NOMBRE_PATTERN));
            do {
                System.out.println("Introduce el precio del nuevo producto");
                precio = br.readLine();
            }while(!comprobarPatronRegex(precio,PRECIO_PATTERN));
            do {
                System.out.println("Introduce el stock del nuevo producto");
                stock = br.readLine();
            }while(!comprobarPatronRegex(stock,STOCK_PATTERN));
            do {
                System.out.println("Introduce el nombre de la categoria del nuevo producto");
                nombreCategoria = br.readLine();
            }while(!comprobarPatronRegex(nombreCategoria,NOMBRE_PATTERN));
            do {
                System.out.println("Introduce el nif del nuevo producto");
                nif = br.readLine();
            }while(!comprobarPatronRegex(nif,NIF_PATTERN));
            m.crearProducto(nombreProducto,Double.parseDouble(precio),Integer.parseInt(stock),nombreCategoria,nif);
        } catch (IOException e) {
            System.out.println("Error al recoger el datos para crear un nuevo producto");
        }
    }
    //7
    private void obtieneDatosEliminarProductoPorNombre(){
        String nombreProductoParaEliminar;
        try {
            do {
                System.out.println("Introduce el nombre del producto para eliminarlo");
                nombreProductoParaEliminar = br.readLine();
            }while(!comprobarPatronRegex(nombreProductoParaEliminar,NOMBRE_PATTERN));

            m.eliminarProductoPorNombre(nombreProductoParaEliminar);
        } catch (IOException e) {
            System.out.println("Error al recoger el datos para eliminar un producto por nombre");
        }

    }
    public boolean comprobarPatronRegex(String string, String pattern){
            return string.matches(pattern);
    }
}
