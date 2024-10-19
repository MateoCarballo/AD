package model.excepciones;

public class ElAutorYaExiste extends Exception{
    public ElAutorYaExiste (String mensaje){
        super(mensaje);
    }
}
