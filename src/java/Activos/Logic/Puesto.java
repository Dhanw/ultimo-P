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
public class Puesto {
    int ID;
    String nombre;
    Funcionario funcionario;
    Dependencia dependencia;

    public Puesto(int ID, String nombre, Funcionario funcionario, Dependencia dependencia) {
        this.ID = ID;
        this.nombre = nombre;
        this.funcionario = funcionario;
        this.dependencia = dependencia;
    }

    public Puesto(String nombre, Funcionario funcionario, Dependencia dependencia) {
        this.nombre = nombre;
        this.funcionario = funcionario;
        this.dependencia = dependencia;
    }

    public Puesto() {
        
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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }
    
    @Override
        public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Puesto)) {
            return false;
        }
        Puesto otro = (Puesto) other;
        return otro.getID() == ID;
    }
}
