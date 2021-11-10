package com.doctor.crudhibernate.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Doctor32
 * Clase producto. Esta clase lleva la parte del uno. 
 * Un producto está en un pedido
 * Todos los modelos que vayamos creando deben implementar serializable
 */

@Entity
@Table(name="productos")
public class Producto implements Serializable{
    
    /**
     * Esta relación hará que gracias al EAGER, se puedan cargar todas
     * las tareas de una sola vez
     */
    @OneToMany(mappedBy = "producto", fetch = FetchType.EAGER)
    private List<Pedido> pedidos;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idproducto;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="precio")
    private Integer precio;

    public Producto() {
    }

    public Producto(Integer idproducto, String nombre, Integer precio) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Integer getId() {
        return idproducto;
    }

    public void setId(Long id) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public List<Pedido> getPs() {
        return pedidos;
    }

    public void setPs(List<Pedido> ps) {
        this.pedidos = ps;
    }

    @Override
    public String toString() {
        return idproducto + " | " + nombre + " | " + precio;
    }

}
