package com.doctor.crudhibernate.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Doctor32
 * La relación entre Pedido y Producto es bidireccional
 * Clase Pedido. Es la parte del muchos, dando a entender que muchos pedidos
 * tienen un solo producto de la carta.
 * Un producto puede estar en muchos pedidos
 */
@Entity
@Table(name="pedidos")
public class Pedido implements Serializable{
    
    @ManyToOne()
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpedido;
    
    //Realmente no hace falta, pero por seguridad adjuntamos el nombre
    //de cada atributo al nombre que tiene en la base de datos
    @Column(name="estado")
    private String estado;
    
    //Cambiar tipo de dato
    @Column(name="fecha")
    private Date fecha;

    public Pedido() {
    }

    public Pedido(String estado, Date fecha) {
        this.estado = estado;
        this.fecha = fecha;
    }
    
    public Pedido(Integer idpedido, String estado, Date fecha) {
        this.idpedido = idpedido;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getId() {
        return idpedido;
    }

    public void setId(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return idpedido + " | " + estado + " | " + fecha + " | ";
    }    
 
}
