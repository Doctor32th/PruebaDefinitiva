package com.doctor.crudhibernate.controller;

import com.doctor.crudhibernate.models.Pedido;
import com.doctor.crudhibernate.models.Producto;

/**
 * Instanciación del Session Data
 * En esta clase lo que hacemos es instanciar los modelos que participan
 * y añadir sus getters y setters. 
 * @author Doctor
 */
public class SessionData {
    
    private static Producto productoActual;
    private static Pedido pedidoActual;


    public static Producto getProductoActual() {
        return productoActual;
    }

    public static void setProductoActual(Producto productoActual) {
        SessionData.productoActual = productoActual;
    }

    public static Pedido getPedidoActual() {
        return pedidoActual;
    }

    public static void setPedidoActual(Pedido pedidoActual) {
        SessionData.pedidoActual = pedidoActual;
    }
    
    
    
}
