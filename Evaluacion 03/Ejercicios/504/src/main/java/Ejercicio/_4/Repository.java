package Ejercicio._4;


import Ejercicio._4.entity.Departamento;
import Ejercicio._4.entity.Empleado;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@org.springframework.stereotype.Repository
public class Repository implements JpaRepository {
    public void createDpto(Departamento dpto){
        //Crear el departamento en la database
    }
    public void createEmp(Empleado emp){
        //Crear el departamento en la database
    }

    @Override
    public void flush() {

    }

    @Override
    public void deleteAllInBatch(Iterable entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Object getOne(Object o) {
        return null;
    }

    @Override
    public Object getById(Object o) {
        return null;
    }

    @Override
    public Object getReferenceById(Object o) {
        return null;
    }

    @Override
    public List findAll(Example example, Sort sort) {
        return List.of();
    }

    @Override
    public List findAll(Example example) {
        return List.of();
    }

    @Override
    public List saveAllAndFlush(Iterable entities) {
        return List.of();
    }

    @Override
    public Object saveAndFlush(Object entity) {
        return null;
    }

    @Override
    public List saveAll(Iterable entities) {
        return List.of();
    }

    @Override
    public Object save(Object entity) {
        return null;
    }

    @Override
    public Optional findById(Object o) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Object o) {
        return false;
    }

    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public List findAllById(Iterable iterable) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Object o) {

    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public void deleteAllById(Iterable iterable) {

    }

    @Override
    public void deleteAll(Iterable entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional findOne(Example example) {
        return Optional.empty();
    }

    @Override
    public Page findAll(Example example, Pageable pageable) {
        return null;
    }

    @Override
    public long count(Example example) {
        return 0;
    }

    @Override
    public boolean exists(Example example) {
        return false;
    }

    @Override
    public Object findBy(Example example, Function queryFunction) {
        return null;
    }
}
