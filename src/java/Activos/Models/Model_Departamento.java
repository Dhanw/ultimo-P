/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Models;

import Activos.Logic.Dependencia;
import Activos.Logic.Funcionario;
import Activos.Logic.Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jose
 */
public class Model_Departamento {
      private Model domainModel;
    
      public Model_Departamento() {
        this.domainModel = new Model();
    }
      
    public void add(Dependencia p) throws Exception{
       this.domainModel.addDependencia(p);
    }

    public void update(Dependencia p) throws Exception{
        
        this.domainModel.updateDependencia(p);
    }
    
     public  void delete(int cedula) throws Exception{
      this.domainModel.eliminarDependencia(cedula);
    }
     
    public  List<Dependencia> list() throws Exception{
        return this.domainModel.getDependencias();
    }
    
    public  List<Dependencia> list(String nombre) throws Exception{
        List<Dependencia> result = new ArrayList();
        result=(List<Dependencia>) domainModel.getDependencia_FromNombre(nombre);
        return result;
    }    
    
    public Dependencia get(int id)throws Exception{
        return this.domainModel.getDependencia(id);
    }   
        
      public Funcionario getFuncionario(int id)throws Exception{
        return this.domainModel.getFuncionario(id);
    }   
        
     public  List<Funcionario> listaFunc() throws Exception{
        List<Funcionario> result = new ArrayList();
        result=(List<Funcionario>) domainModel.getFuncionarios();
        return result;
    }    
    
}
