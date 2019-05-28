/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Data;

import Activos.Logic.Funcionario;
import Activos.Logic.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jorac
 */
public class DaoHelper {

    protected  Funcionario getFuncionarioH(ResultSet rs) throws SQLException {
        Funcionario func = null;
        if (rs.next()) {
            func = new Funcionario();
            func.setID(rs.getInt(1));
            func.setIdentificacion(rs.getString(2));
            func.setNombre(rs.getString(3));
        }
        return func;
    }

    protected  Usuario getUsuarioH(ResultSet rs) throws SQLException {
        Usuario user = null;
        if (rs.next()) {
            user = new Usuario();
            user.setID(rs.getInt(1));
            user.setCuenta(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setRol(rs.getInt(4));
//            user.setFuncionario(rs.getInt(5));
        }
        return user;
    }

}
