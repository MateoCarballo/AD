```java
package com.example.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j                 // → crea automáticamente el logger “log”
@RequiredArgsConstructor // → inyecta el SessionFactory vía constructor
public class GenericDao<T, ID extends Serializable> {

    private final Class<T> entityClass;   // p.e. User.class
    private final SessionFactory sessionFactory;

    // ---------- CRUD básicos ----------

    public ID save(T entity) {
        return execute(session -> (ID) session.save(entity));
    }

    public void update(T entity) {
        execute(session -> { session.update(entity); return null; });
    }

    public void delete(T entity) {
        execute(session -> { session.delete(entity); return null; });
    }

    public T findById(ID id) {
        return execute(session -> session.get(entityClass, id));
    }

    public List<T> findAll() {
        String hql = "from " + entityClass.getSimpleName();
        return executeQuery(hql, null);
    }

    // ---------- Consultas genéricas ----------

    /** Ejecuta HQL con parámetros dinámicos (opcionalmente nulos) */
    public List<T> executeQuery(String hql, Map<String, Object> params) {
        return execute(session -> {
            Query<T> query = session.createQuery(hql, entityClass);
            if (params != null) {
                params.forEach(query::setParameter);
            }
            return query.list();
        });
    }

    /** Devuelve un único resultado (o null) */
    public T executeSingle(String hql, Map<String, Object> params) {
        return execute(session -> {
            Query<T> query = session.createQuery(hql, entityClass);
            if (params != null) {
                params.forEach(query::setParameter);
            }
            return query.uniqueResult();
        });
    }

    // ---------- Helper de manejo de sesión ----------

    private <R> R execute(Function<Session, R> action) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            R result = action.apply(session);
            tx.commit();
            return result;
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            log.error("Error de base de datos", e);
            throw e;
        }
    }
}
```