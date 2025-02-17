package org.example;

import org.hibernate.Session;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        AppBuena ab = new AppBuena(session);


        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }

}
