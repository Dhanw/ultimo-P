/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Models;

import Activos.Logic.Categoria;
import Activos.Logic.Dependencia;
import Activos.Logic.Model;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose
 */
public class Model_Bienes {

    private Model domainModel;

    public Model_Bienes() {
        this.domainModel = new Model();
    }

    public void add(Categoria p) throws Exception {
        this.domainModel.addCategoria(p);
    }

    public void update(Categoria p) throws Exception {
           this.domainModel.updateCategoria(p);
    }

    public void delete(int cedula) throws Exception {
           this.domainModel.eliminarCategoria(cedula);
    }
    public Categoria get(int id)throws Exception{
        return this.domainModel.getCategoria(id);
    }   

    public List<Categoria> getCategorias() throws Exception {
        return this.domainModel.getCategorias();
    }

}
