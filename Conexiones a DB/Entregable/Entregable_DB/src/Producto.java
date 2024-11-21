public class Producto {
    private int id_producto;
    private String nombreProducto;
    private double precio;
    private int stock;
    private String nombreProveedor;
    private String nifProveedor;
    private String telefonoProveedor;
    private String emailProveedor;
    private String nombreCategoria;

    public Producto (){

    }

    public Producto(String nombreProducto, int stock) {
        this.nombreProducto = nombreProducto;
        this.stock = stock;
    }

    public Producto(int id_producto, String nombreProducto, double precio, int stock) {
        this.id_producto = id_producto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto(int id_producto, String nombreProveedor, String nifProveedor, String telefonoProveedor, String emailProveedor, String nombreCategoria) {
        this.id_producto = id_producto;
        this.nombreProveedor = nombreProveedor;
        this.nifProveedor = nifProveedor;
        this.telefonoProveedor = telefonoProveedor;
        this.emailProveedor = emailProveedor;
        this.nombreCategoria = nombreCategoria;
    }

    public Producto(int id_producto, String nombreProducto, double precio, int stock, String nombreProveedor, String nifProveedor, String telefonoProveedor, String emailProveedor, String nombreCategoria) {
        this.id_producto = id_producto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.stock = stock;
        this.nombreProveedor = nombreProveedor;
        this.nifProveedor = nifProveedor;
        this.telefonoProveedor = telefonoProveedor;
        this.emailProveedor = emailProveedor;
        this.nombreCategoria = nombreCategoria;
    }


   public String toStringTuneado(){
       return "Nombre -> " + getNombre_producto() + "\n" + "Stock -> " + getStock() + "\n";
   }
}
