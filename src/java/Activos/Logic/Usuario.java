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
public class Usuario {

    // Usuarios por tipo
    public static final int INDEFINIDO = 0;
    public static final int ADMINISTRADOR_DEPENDENCIA = 1;
    public static final int SECRETARIA_OCCB = 2;
    public static final int JEFE_OCCB = 3;
    public static final int REGISTRADOR_BIENES = 4;
    public static final int JEFE_RRH = 5;
    public static final int JEFE_OCBB_RHH = 7;

    int ID;
    String cuenta;
    String password;
    int rol;
    Funcionario funcionario;

    public Usuario() {
        this.ID = 0;
        this.cuenta = "";
        this.password = "";
        this.rol = 1;
        this.funcionario = new Funcionario();
    }

    public Usuario(int ID, String cuenta, String password, int rol, Funcionario funcionario) {
        this.ID = ID;
        this.cuenta = cuenta;
        this.password = password;
        this.rol = rol;
        this.funcionario = funcionario;
    }

    public Usuario(String cuenta, String password, int rol) {
        this.cuenta = cuenta;
        this.password = password;
        this.rol = rol;
    }

    public Usuario(String cuenta, String password, int rol, Funcionario funcionario) {
        this.cuenta = cuenta;
        this.password = password;
        this.rol = rol;
        this.funcionario = funcionario;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getDescripcionRol() {
        switch (rol) {
            case ADMINISTRADOR_DEPENDENCIA:
                return "Administrador de la dependencia";
            case SECRETARIA_OCCB:
                return "Secretaria OCCB";
            case JEFE_OCCB:
                return "Jefe OCCB";
            case REGISTRADOR_BIENES:
                return "Registrador de bienes";
            case JEFE_RRH:
                return "Jefe RRH";
            case JEFE_OCBB_RHH:
                return "Jefe OCBB y RHH";
        }
        return "Indefinido";
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Usuario)) {
            return false;
        }
        Usuario otro = (Usuario) other;
        return otro.getID() == ID;
    }
}
