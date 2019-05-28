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
public class Dependencia {

    int ID;
    String nombre;
    String ubicacion;
    Funcionario administrador;

    public Dependencia() {
    }

    public Dependencia(int ID, String nombre, String ubicacion, Funcionario administrador) {
        this.ID = ID;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.administrador = administrador;
    }

    public Dependencia(String nombre, String ubicacion, Funcionario administrador) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.administrador = administrador;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Funcionario getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Funcionario administrador) {
        this.administrador = administrador;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Dependencia)) {
            return false;
        }
        Dependencia otro = (Dependencia) other;
        return otro.getID() == ID;
    }

}
