/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Logic;

/**
 *
 * @author jorac
 */
public class Bien {

    int ID;
    String descripcion;
    String marca;
    String modelo;
    double precio;
    int cantidad;
    Solicitud solicitud;

    public Bien() {
        this.ID = 0;
        this.descripcion = "";
        this.marca = "";
        this.modelo = "";
        this.precio = 0;
        this.cantidad = 0;
        this.solicitud = new Solicitud();
    }
    
    public Bien(String descripciones, String marca, String modelo, double precio_cu, int cantidad) {
        this.descripcion = descripciones;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio_cu;
        this.cantidad = cantidad;
    }

    public Bien(int ID, String descripcion, String marca, String modelo, double precio, int cantidad, Solicitud solicitud) {
        this.ID = ID;
        this.descripcion = descripcion;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.solicitud = solicitud;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Bien)) {
            return false;
        }
        Bien otro = (Bien) other;
        return otro.getID() == ID;
    }

}
