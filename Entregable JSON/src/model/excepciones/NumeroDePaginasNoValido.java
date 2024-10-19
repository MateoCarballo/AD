package model.excepciones;

public class NumeroDePaginasNoValido extends Exception{
    public NumeroDePaginasNoValido(String mensaje){
        super(mensaje);
    }
}
