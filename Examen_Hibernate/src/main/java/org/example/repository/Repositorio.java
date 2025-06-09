package org.example.repository;

import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface Repositorio <T,ID>{
    void guardar(T t);
    List<T> encontrarTodos();
    Optional<T> encontrarPorId(ID id);
    void actualizar(T t);
    void eliminar(T t);
}
