/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Models;

import Activos.Logic.Bien;
import Activos.Logic.Dependencia;
import Activos.Logic.Funcionario;
import Activos.Logic.Model;
import Activos.Logic.Solicitud;
import Activos.Logic.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wizzard
 */
public class Model_Solicitud {

    private Model domainModel;
    private Solicitud modelAgregar;
    private Solicitud modelEditar;
    private Solicitud modelMostrar;
    private Bien bien;
    private int ContadorBienes;
    private Funcionario registrador;

    public Model_Solicitud() {
        this.domainModel = Model.instance();
        this.modelAgregar = new Solicitud();
        this.modelEditar = new Solicitud();
        this.modelMostrar = new Solicitud();
        this.bien = new Bien();
        this.bien.setSolicitud(modelAgregar);
        this.ContadorBienes = 0;
        this.registrador = new Funcionario();
    }

    public Model_Solicitud(Model domainModel, Solicitud agregar, Solicitud editar, Solicitud mostrar, Bien bien, int ContadorBienes, Funcionario registrador) {
        this.domainModel = domainModel;
        this.modelAgregar = agregar;
        this.modelEditar = editar;
        this.modelMostrar = mostrar;
        this.bien = bien;
        this.ContadorBienes = ContadorBienes;
        this.registrador = registrador;
    }

    public void setDomainModel(Model domainModel) {
        this.domainModel = domainModel;
    }

    public void setModelEditar(Solicitud modelEditar) {
        this.modelEditar = modelEditar;
    }

    public void setModelMostrar(Solicitud modelMostrar) {
        this.modelMostrar = modelMostrar;
    }

    public void setModelAgregar(Solicitud modelAgregar) {
        this.modelAgregar = modelAgregar;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public void setContadorBienes(int ContadorBienes) {
        this.ContadorBienes = ContadorBienes;
    }

    public void setRegistrador(Funcionario registrador) {
        this.registrador = registrador;
    }

    public Model getDomainModel() {
        return domainModel;
    }

    public Solicitud getModelEditar() {
        return modelEditar;
    }

    public Solicitud getModelMostrar() {
        return modelMostrar;
    }

    public Solicitud getModelAgregar() {
        return modelAgregar;
    }

    public Bien getBien() {
        return bien;
    }

    public int getContadorBienes() {
        return ContadorBienes;
    }

    public Funcionario getRegistrador() {
        return registrador;
    }

    public void agregarBien(Bien b, Solicitud s) {
        b.setSolicitud(s);
        s.getBienes().add(b);
    }

    public void eliminarBien(int id_bien, Solicitud s) {
        for (Bien b : s.getBienes()) {
            if (b.getID() == id_bien) {
                s.getBienes().remove(b);
                break;
            }
        }
    }

    public void guardarSolicitud(Solicitud sol, Funcionario f) {
        try {
            Dependencia dep = domainModel.getDependencia_fromFuncionario(f.getID());
            sol.setDependencia(dep);
            List<Bien> bienes = sol.getBienes();
            sol.setBienes(bienes);
            domainModel.addSolicitud(sol);
            for (Bien b : modelAgregar.getBienes()) {
                domainModel.addBien(b);
            }
        } catch (Exception ex) {
        }
    }

    public void actualizarSolicitud(Solicitud sol_new) {
        try {
//            Funcionario regis = sol_new.getRegistrador();
//            sol_new.setRegistrador(regis);
            List<Bien> bienes = sol_new.getBienes();
            sol_new.setBienes(bienes);
            Solicitud sol_old = this.getSolicitud(sol_new.getID());
            domainModel.updateSolicitud(sol_new);
            for (Bien nuevo : modelEditar.getBienes()) {
                if (!sol_old.getBienes().contains(nuevo)) {
                    domainModel.addBien(nuevo);
                }
            }
            for (Bien viejo : sol_old.getBienes()) {
                if (!sol_new.getBienes().contains(viejo)) {
                    domainModel.deleteBien(viejo);
                }
            }
        } catch (Exception ex) {
        }
    }

    public void eliminarSolicitud(int id_solicitud) throws SQLException {
        domainModel.eliminarSolicitud(id_solicitud);
    }

    public Solicitud getSolicitud(int id_solicitud) throws Exception {
        return domainModel.getSolicitud(id_solicitud);
    }

    public void setestadoSolicitud(Solicitud s) {
        Solicitud sol;
        try {
            sol = domainModel.getSolicitud(s.getID());
            sol.setEstado(s.getEstado());
            sol.setMotivoRechazo(s.getMotivoRechazo());
            domainModel.updateSolicitud(sol);
        } catch (Exception ex) {
        }
    }

    public void updateRegistrador(int id_registrador) throws Exception {
        this.setRegistrador(domainModel.getFuncionario(id_registrador));
        if (id_registrador == 0) {
            registrador.setID(-1);
        }
    }

    public void asignarRegistrador() throws Exception {
        modelEditar.setRegistrador(registrador);
        domainModel.updateSolicitud(modelEditar);
    }

    public List<Funcionario> getRegistradores() throws SQLException {
        try {
            List<Usuario> usuarios;
            usuarios = domainModel.getUsuarios();
            List<Funcionario> registradores = new ArrayList();
            for (Usuario u : usuarios) {
                if (u.getRol() == Usuario.REGISTRADOR_BIENES) {
                    registradores.add(u.getFuncionario());
                }
            }
            return registradores;
        } catch (Exception ex) {
            return new ArrayList();
        }
    }

}
