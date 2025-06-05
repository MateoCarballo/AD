package org.example;

import org.hibernate.Session;

public class App {
    public static void main(String[] args) {
        System.out.println("Test");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("Conexión a Hibernate exitosa.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
