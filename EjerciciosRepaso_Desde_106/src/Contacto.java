public class Contacto {

    String nombre;
    String apellidos;
    int numero;

    public Contacto() {
    }

    public Contacto(String nombre, String apellidos, int numero) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getNumero() {
        return numero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Contacto{" +'\n'+
                '\t'+"nombre: " + nombre + '\n'+
                '\t'+"apellidos: " + apellidos + '\n'+
                '\t'+"numero: " + numero;
    }
}
