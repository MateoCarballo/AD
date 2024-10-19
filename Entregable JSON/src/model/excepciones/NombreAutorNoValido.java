package model.excepciones;

public class NombreAutorNoValido extends Exception{
    public NombreAutorNoValido (String mensaje){
        super(mensaje);
    }
}
