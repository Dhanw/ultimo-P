/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Models;

import Activos.Logic.Model;
import Activos.Logic.Usuario;
import java.sql.SQLException;

/**
 *
 * @author wizzard
 */
public class Model_Login {

    //CONSTANTES
    public static final int INDEFINIDO = 0;
    public static final int ADMINISTRADOR_DEPENDENCIA = 1;
    public static final int SECRETARIA_OCCB = 2;
    public static final int JEFE_OCCB = 3;
    public static final int REGISTRADOR_BIENES = 4;
    public static final int JEFE_RRH = 5;
    public static final int JEFE_OCBB_RHH = 7;

    private Usuario user;
    private Model domainModel;

    public Model_Login() {
        this.user = new Usuario();
        this.domainModel = new Model();
    }

    public Model_Login(Usuario user, Model dao) {
        this.user = user;
        this.domainModel = dao;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public void setDomainModel(Model domainModel) {
        this.domainModel = domainModel;
    }

    public Usuario getUser() {
        return user;
    }

    public Model getDomainModel() {
        return domainModel;
    }

    public Usuario Login(String cuenta, String password) throws SQLException, Exception {
        return domainModel.getUsuario(cuenta, password);
    }
}
