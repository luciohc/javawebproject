/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drogaria.util;

/**
 *
 * @author Lucio Henrique da Costa
 */
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {

            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            Configuration configure = configuration.configure();

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
            applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.
            buildSessionFactory((org.hibernate.service.ServiceRegistry) serviceRegistry);
            return sessionFactory;
        } catch (HibernateException ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Falha ao tentar criar o SessionFactory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
