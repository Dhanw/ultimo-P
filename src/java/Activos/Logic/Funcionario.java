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
public class Funcionario {

    int ID;
    String identificacion;
    String nombre;

    public Funcionario(int ID, String identificacion, String nombre) {
        this.ID = ID;
        this.identificacion = identificacion;
        this.nombre = nombre;
    }

    public Funcionario(String identificacion, String nombre) {
        this.identificacion = identificacion;
        this.nombre = nombre;
    }

    public Funcionario() {
        this.ID = 0;
        this.identificacion = "";
        this.nombre = "";
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Funcionario)) {
            return false;
        }
        Funcionario otro = (Funcionario) other;
        return otro.getID() == ID;
    }
}
