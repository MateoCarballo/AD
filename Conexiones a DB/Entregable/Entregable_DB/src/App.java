import java.io.*;

public class App {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //Patron para los menus, numeros del 1 al 12
    private final String MENU_PATTERN = "^(1[0-2]|[0-9])$";
    private final String NIF_PATTERN = "^[1-9][0-9]{7}[A-Za-z]$";
    private final String NOMBRE_PATTERN = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{1,20}$";
    private final String NOMBRE_PRODUCTO_PATTERN = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s\\-_.\"]{1,50}$";
    private final String TELEFONO_PATTERN = "^\\d{9}$";
    private final String ID_PATTERN = "^\\d{1,50}$";
    private final String CORREO_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(?:\\.[a-zA-Z]{2,})?$";
    private final String PRECIO_PATTERN = "^(0|[1-9]\\d*)(\\.\\d{1,2})?$";
    private final String STOCK_PATTERN = "^[1-9]\\d*$";
    private final Modelo m;

    public App (Modelo m){
        this.m = m;
    }

    public Modelo getM() {
        return m;
    }

    public void iniciarApp() throws IOException {
        boolean continuar = true;
        do{
            String entradaTeclado;
            for (int i = 0; i < 110; i++) {
                System.out.print("\u2550");
            }
            System.out.println("");
            System.out.println("""
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
                """);
            for (int i = 0; i < 110; i++) {
                System.out.print("\u2550");
            }
            System.out.println();

            do {
                entradaTeclado = br.readLine();
                if (!comprobarPatronRegex(entradaTeclado,MENU_PATTERN)){
                    System.out.println("Introduce un numero entre 0 y 12");
                }
            }while(!comprobarPatronRegex(entradaTeclado,MENU_PATTERN));


            switch (Integer.parseInt(entradaTeclado)){
                case 0 ->   continuar = cerrarApp();
                case 1 ->   obtieneDatosCrearCategoria();
                case 2 ->   obtieneDatosCrearProveedor();
                case 3 ->   obtieneDatosEliminarProveedor();
                case 4 ->   obtieneCrearNuevoUsuario();
                case 5 ->   obtieneDatosEliminarUsuario();
                case 6 ->   obtieneDatosCrearNuevoProducto();
                case 7 ->   obtieneDatosEliminarProductoPorNombre();
                case 8 ->   obtenerDatosListarProductosBajoStock();
                case 9 ->   listarTotalPedidosUsuarios();
                case 10 ->  listarTotalPedidosPorAlmacen();
                case 11 ->  listarProductosCategoriasProveedores();
                case 12 ->  obtenerDatosObtenerUsuariosCompraronCategoria();
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
            }while(!comprobarPatronRegex(nombre, "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{1,20}$"));

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
                System.out.println("Introduce el ID del usuario a eliminar");
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
            }while(!comprobarPatronRegex(nombreProducto,NOMBRE_PRODUCTO_PATTERN));
            do {
                System.out.println("Introduce el precio del nuevo producto");
                precio = br.readLine();
            }while(!comprobarPatronRegex(precio,PRECIO_PATTERN));
            do {
                System.out.println("Introduce el stock del nuevo producto");
                stock = br.readLine();
            }while(!comprobarPatronRegex(stock,STOCK_PATTERN));
            do {
                System.out.println("Introduce el nombre de la categoria del nuevo producto (debe existir en la DB almacenes)");
                nombreCategoria = br.readLine();
            }while(!comprobarPatronRegex(nombreCategoria,NOMBRE_PATTERN));
            do {
                System.out.println("Introduce el nif proveedor del producto (debe existir en la DB almacenes)");
                nif = br.readLine();
            }while(!comprobarPatronRegex(nif,NIF_PATTERN));
            m.crearProducto(nombreProducto,Double.parseDouble(precio),Integer.parseInt(stock),nombreCategoria,nif);
        } catch (IOException e) {
            System.out.println("Error al recoger el datos para crear un nuevo producto");
            e.printStackTrace();
        }
    }

    //7
    private void obtieneDatosEliminarProductoPorNombre(){
        String nombreProductoParaEliminar;
        try {
            do {
                System.out.println("Introduce el nombre del producto para eliminarlo");
                nombreProductoParaEliminar = br.readLine();
            }while(!comprobarPatronRegex(nombreProductoParaEliminar,NOMBRE_PRODUCTO_PATTERN));

            m.eliminarProductoPorNombre(nombreProductoParaEliminar);
        } catch (IOException e) {
            System.out.println("Error al recoger el datos para eliminar un producto por nombre");
        }
    }

    //8
    private void obtenerDatosListarProductosBajoStock(){
        String stock;
        try {
            do {
                System.out.println("Introduce el numero de stock maximo que quieres mostar");
                stock = br.readLine();
            }while(!comprobarPatronRegex(stock,STOCK_PATTERN));

            m.listarProductosBajoStock(Integer.parseInt(stock));
        } catch (IOException e) {
            System.out.println("Error al recoger el datos para eliminar un producto por nombre");
        }
    }

    //9
    private void listarTotalPedidosUsuarios(){
        m.obtenerTotalPedidosUsuarios();
    }

    //10
    private void listarTotalPedidosPorAlmacen(){
        m.obtenerCantidadProductosEnCadaAlmacen();
    }

    //11
    private void listarProductosCategoriasProveedores(){
        m.listarTodosProductosConCategoriaYProveedor();
    }

    //12
    private void obtenerDatosObtenerUsuariosCompraronCategoria(){
        String idCategoria;
        try {
            do {
                System.out.println("Introduce el id de la categoria para listar los usuarios que han comprado productos de ella");
                idCategoria = br.readLine();
            }while(!comprobarPatronRegex(idCategoria,ID_PATTERN));

            m.obtenerUsuariosCompraronProductosCategoria(Integer.parseInt(idCategoria));
        } catch (IOException e) {
            System.out.println("Error al recoger el datos para listar usuarios que han comprado productos de una categoria dada");
        }
    }


    //Comprobador de patrones regex
    public boolean comprobarPatronRegex(String string, String pattern){
            return string.matches(pattern);
    }

    public boolean cerrarApp(){
        System.out.println("""
                
                ░░░░░░░░░░░░▄▄░░░░░░░░░░░░░░
                ░░░░░░░░░░░█░░█░░░░░░░░░░░░░
                ░░░░░░░░░░░█░░█░░░░░░░░░░░░░
                ░░░░░░░░░░█░░░█░░░░░░░░░░░░░
                ░░░░░░░░░█░░░░█░░░░░░░░░░░░░
                ██████▄▄█░░░░░██████▄░░░░░░░
                ▓▓▓▓▓▓█░░░░░░░░░░░░░░█░░░░░░
                ▓▓▓▓▓▓█░░░░░░░░░░░░░░█░░░░░░
                ▓▓▓▓▓▓█░░░░░░░░░░░░░░█░░░░░░
                ▓▓▓▓▓▓█░░░░░░░░░░░░░░█░░░░░░
                ▓▓▓▓▓▓█░░░░░░░░░░░░░░█░░░░░░
                ▓▓▓▓▓▓█████░░░░░░░░░██░░░░░░
                █████▀░░░░▀▀████████░░░░░░░░
                ░░░░░░░░░░░░░░░░░░░░░░░░░░░░
                ╔════╗░░╔════╗╔═══╗░░░░░░░░░
                ║████║░░║████║║███╠═══╦═════╗
                ╚╗██╔╝░░╚╗██╔╩╣██╠╝███║█████║
                ░║██║░░░░║██║╔╝██║███╔╣██══╦╝
                ░║██║╔══╗║██║║██████═╣║████║
                ╔╝██╚╝██╠╝██╚╬═██║███╚╣██══╩╗
                ║███████║████║████║███║█████║
                ╚═══════╩════╩════════╩═════╝
                """);
        return false;
    }
}