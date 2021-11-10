package com.doctor.crudhibernate.controller;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Doctor
 */
public class HibernateUtil {

    private static final SessionFactory sf;

    /**
     * Englobamos todo lo estatico en el static. Este recurso no necesita
     * de un close ya que se cerrará cuando el programa se cierre
     */
    static { 

        try {
            sf = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            System.out.println("Error al crear SessionFactory");
            System.out.println(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sf;
    }

}
