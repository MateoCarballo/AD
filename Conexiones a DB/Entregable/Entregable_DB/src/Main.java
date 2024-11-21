import GUI.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {

        Modelo m = new Modelo();
        //m.crearCategoria("Categoría de prueba"); //1
        //m.crearProveedor("Nombre prueba proveedor","12345678A",123456789,"email@deprueba.com"); //2
        // m.eliminarProveedor(8);        //3
        //m.crearUsuario("Nombre prueba","email@prueba.com",2024);      //4
        //m.eliminarUsuario(5);     //5
        //m.crearProducto("nombre producto prueba",99.99,1000,"Monitor","67890123A"); //6
        //m.eliminarProductoPorNombre("nombre producto prueba");
        //m.listarProductosBajoStock(50);
        //m.obtenerTotalPedidosUsuarios();
        //m.obtenerCantidadProductosEnCadaAlmacen();
        m.listarTodosProductosConCategoriaYProveedor();
    }
}