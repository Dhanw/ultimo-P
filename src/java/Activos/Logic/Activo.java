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
public class Activo {

    int ID;
    String codigo;
    String categoria;
    String descripcion;
    Puesto puesto;

    public Activo(int ID, String codigo, String categoria, String descripcion, Puesto puesto) {
        this.ID = ID;
        this.codigo = codigo;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.puesto = puesto;
    }

    public Activo(String codigo, String categoria, String descripcion, Puesto puesto) {
        this.codigo = codigo;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.puesto = puesto;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Activo)) {
            return false;
        }
        Activo otro = (Activo)other;
        return otro.getID() == ID;
    }
    
    
}
