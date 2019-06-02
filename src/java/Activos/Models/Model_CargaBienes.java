/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Models;

import Activos.Logic.Activo;
import Activos.Logic.Bien;
import Activos.Logic.Categoria;
import Activos.Logic.Dependencia;
import Activos.Logic.Model;
import Activos.Logic.Puesto;
import java.util.List;

/**
 *
 * @author Jose
 */
public class Model_CargaBienes {

    private Model domainModel;

    public Model_CargaBienes() {
        this.domainModel = new Model();
    }

    public List<Bien> getBienes() throws Exception {
        return this.domainModel.getBienes();
    }

    public List<Puesto> getPuestos(Dependencia dep) throws Exception {
        return this.domainModel.getPuestos(dep);
    }

    public Dependencia getDependencia_fromFuncionarioV3(int id) throws Exception {
        return this.domainModel.getDependencia_fromFuncionarioV3(id);
    }

    public List<Categoria> getCategorias() throws Exception {
        return this.domainModel.getCategorias();
    }
    
    public void add(Activo p) throws Exception {
        this.domainModel.addActivo(p);
    }
}
