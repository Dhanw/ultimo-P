/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Models;

import Activos.Logic.Dependencia;
import Activos.Logic.Puesto;

/**
 *
 * @author jorac
 */
public class Model_Funcionario {
    int id;
    String nombre;
    String cedula;
    Puesto puesto;


    public Model_Funcionario(int id, String nombre, String cedula, Puesto puesto) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.puesto = puesto;
    }

    public Dependencia getDependencia() {
        return puesto.getDependencia();
    }

    public void setDependencia(Dependencia dependencia) {
        puesto.setDependencia(dependencia);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }
    
    
    
}
