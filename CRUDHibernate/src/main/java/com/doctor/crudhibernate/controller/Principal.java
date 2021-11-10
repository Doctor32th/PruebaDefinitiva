package com.doctor.crudhibernate.controller;

import com.doctor.crudhibernate.models.Pedido;
import com.doctor.crudhibernate.models.Producto;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Doctor
 */
public class Principal {

    /**
     * Esta clase no solo englobará al menu, también llevará 
     * Los métodos que vamos a implementar con las consultas adjuntas
     * De manera que las clases interaccionen entre sí gracias a la foreign
     * key que establecimos en la base de datos y la relación one-to-many
     * @param args 
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        java.util.Date utilDate = new java.util.Date();
        long lnMilisegundos = utilDate.getTime();
        java.sql.Date fechaSQL = new java.sql.Date(lnMilisegundos);

        boolean salir = false;
        int opcion;

        while (!salir) {

            System.out.println("****************************");
            System.out.println("      CHOCOLATE FACTORY     ");
            System.out.println("****************************");
            System.out.println("        BIENVENIDO!!        ");
            System.out.println("============================");
            System.out.println("-----1. Crear pedido-----");
            System.out.println("-----2. Eliminar pedido-----");
            System.out.println("-----3. Listar pedidos-----");
            System.out.println("-----4. Listar pedidos de hoy-----");
            System.out.println("-----5. Mostrar Carta-----");
            System.out.println("-----6. Salir-----");
            System.out.println("============================");

            try {
                System.out.println("Elige una opción, por favor");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        //Creamos la sesion y la abrimos. No hace falta cerrarla
                        //gracias al static del HibernateUtil
                        Session s1 = HibernateUtil.getSessionFactory().openSession();
                        Query consulta1 = s1.createQuery("FROM Producto");
                        
                        consulta1.list().forEach((e) -> System.out.println(e));
                        
                        System.out.println("Por favor, inserte el id del producto");
                        
                        int id_producto = sc.nextInt();
                        Pedido p = new Pedido();
                        p.setFecha(fechaSQL);
                        p.setEstado("pendiente");
                        //En caso de ser int, 0 y 1 es
                        //En caso de ser con string sería pendiente y recogido
                        //Si hacemos ese cambio en la bd debemos aplicarlo 
                        //tambien en el modelo
                        p.setProducto((Producto) consulta1.list().get(id_producto));
                        Transaction tsr = s1.beginTransaction();
                        s1.save(p);
                        tsr.commit();
                        
                        System.out.println("Creando pedido...");
                        Thread.sleep(2000);
                        System.out.println("Su pedido se ha creado con éxito");
                        break;
                    case 2:
                        Session s2 = HibernateUtil.getSessionFactory().openSession();
                        Query consulta2 = s2.createQuery("FROM Pedido");
                        consulta2.list().forEach((e) -> System.out.println(e));
                        
                        System.out.println("Por favor, introduzca la id del"
                                + " producto que desea borrar");
                        int id_producto2 = sc.nextInt();
                        
                        Query consulta3 = s2.createQuery("DELETE FROM Pedido WHERE id =:id");
                        consulta3.setParameter("id", id_producto2);
                        
                        Transaction ts2 = s2.beginTransaction();
                        ts2.commit();
                        //en delete y update no hay save pero si commit
                        System.out.println("Eliminando pedido...");
                        Thread.sleep(2000);
                        System.out.println("Su pedido ha sido eliminado"
                                + " con éxito");
                        break;
                    case 3:
                        Session s3 = HibernateUtil.getSessionFactory().openSession();
                        Query consulta4 = s3.createQuery("FROM Pedido");
                        System.out.println("Listando todos sus pedidos...");
                        Thread.sleep(4000);
                        consulta4.list().forEach((e) -> System.out.println(e));
                        break;
                    case 4:
                        Session s4 = HibernateUtil.getSessionFactory().openSession();
                        //Cambiar la query, siguiente linea
                        Query consulta = s4.createQuery("FROM Pedidos"
                                + " WHERE fecha =:fecha AND estado =:estado ");
                        
                        consulta.setParameter("fecha", "estado");
                        consulta.list().forEach((e) -> System.out.println(e));
                        Transaction ts4 = s4.beginTransaction();
                        ts4.commit();
                        System.out.println("Listando todos sus pedidos"
                                + " de hoy...");
                        Thread.sleep(2000);
                        break;
                    case 5:
                        Session s5 = HibernateUtil.getSessionFactory()
                                .openSession();
                        Query consulta5 = s5.createQuery("FROM Producto");
                        System.out.println("Mostrando carta de"
                                + " desayunos...");
                        Thread.sleep(4000);
                        break;
                    case 6:
                        System.out.println("Saliendo del programa."
                                + " Por favor, espere...");
                        Thread.sleep(3000);
                        salir = true;
                        break;
                    default:
                        System.out.println("La opción a elegir no figura"
                                + " en el menú actual. "
                                + "Inténtelo de nuevo");
                }
            } catch (InputMismatchException | InterruptedException ex) {
                System.out.println("Debe insertar un número para elegir");
                sc.next();
            }
        }
        
    }
    
}
