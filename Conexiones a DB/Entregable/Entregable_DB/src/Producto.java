public class Producto {
    private int id_producto;
    private String nombre_producto;
    private double precio;
    private int stock;

    public Producto (){

    }

    public Producto(String nombre_producto, int stock) {
        this.nombre_producto = nombre_producto;
        this.stock = stock;
    }

    public Producto(int id_producto, String nombre_producto, double precio, int stock) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
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
