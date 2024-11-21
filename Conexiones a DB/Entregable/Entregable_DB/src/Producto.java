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

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombreProducto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombreProducto = nombre_producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

   public String toStringTuneado(){
       return "Nombre -> " + getNombre_producto() + "\n" + "Stock -> " + getStock() + "\n";
   }
}
