package model.validaciones;

import model.excepciones.NombreAutorNoValido;
import model.excepciones.NumeroDePaginasNoValido;

public class Validaciones {

    private Validaciones(){

    }

    /**Valida que el autor cumpla con las normas, que en este caso serán.
     * 1. Siempre empezar por mayúscula.
     * 2. Permitir espacios y guiones medios para los nombres compuestos.
     * @param autor
     * @return
     */
    public static boolean validarNombreAutor(String autor) throws NombreAutorNoValido {
        final String PATRON_REGEX_AUTOR = "^([A-Z][a-záéíóúÁÉÍÓÚñÑ]+)([\s-][A-Z][a-záéíóúÁÉÍÓÚñÑ]+)*$";
        boolean esCorrecto = autor.matches(PATRON_REGEX_AUTOR);
        if(!esCorrecto){
            throw new NombreAutorNoValido("Este nombre de autor no cumple con las normas establecidas -> " + autor);
        }
        return autor.matches(PATRON_REGEX_AUTOR);
    }

    public static boolean validarNumeroDePaginas(String paginas) throws NumeroDePaginasNoValido {
        final String PATRON_REGEX_PAGINAS = "^\\d{5}$";
        if (!paginas.matches(PATRON_REGEX_PAGINAS)){
            throw new NumeroDePaginasNoValido("Revisa que el numero de paginas contenga solo numeros y no supere los 5 digitos -> " + paginas);
        }
        return paginas.matches(PATRON_REGEX_PAGINAS);
    }


    /*
    Pensaba validar los libros añadiendo un patron regex que me permita signos de exclamacion etc. Pero en realidad cada uno
    puede titular su libro como quiera así que ni lo valido por este motivo.
     */
}
