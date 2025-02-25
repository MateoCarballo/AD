package Ejercicio402;

public class
Libro {

    private int publicacion;
    private int edidicion;
    private String titulo;
    private String nombreAutor;
    private String apellidoAutor;
    private String editorial;
    private int paginas;
    private boolean edicionElectronica;

    public Libro(int publicacion,
                 int edidicion,
                 String titulo,
                 String nombreAutor,
                 String apellidoAutor,
                 String editorial,
                 int paginas,
                 boolean edicionElectronica) {

        this.publicacion = publicacion;
        this.edidicion = edidicion;
        this.titulo = titulo;
        this.nombreAutor = nombreAutor;
        this.apellidoAutor = apellidoAutor;
        this.editorial = editorial;
        this.paginas = paginas;
        this.edicionElectronica = edicionElectronica;
    }

    public int getPublicacion() {
        return publicacion;
    }

    public int getEdidicion() {
        return edidicion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public String getApellidoAutor() {
        return apellidoAutor;
    }

    public String getEditorial() {
        return editorial;
    }

    public int getPaginas() {
        return paginas;
    }

    public boolean isEdicionElectronica() {
        return edicionElectronica;
    }
}
