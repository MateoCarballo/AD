package Ejercicio309.database;

public interface OperacionesInt {
    boolean existsClient(String s);
    boolean isBorrowed(String idBook);
    void addLoan(String code, String idClient);
}
