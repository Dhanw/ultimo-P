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
import java.util.List;

/**
 *
 * @author wizzard
 */
public class Model_SolicitudEdicion {

    private Model domainModel;
    private Solicitud solicitud;
    private Bien bien;
    private int ContadorBienes;

    public Model_SolicitudEdicion() {
        this.domainModel = Model.instance();
        this.solicitud = new Solicitud();
        this.bien = new Bien();
        this.bien.setSolicitud(solicitud);
        this.ContadorBienes = 0;
    }

    public Model_SolicitudEdicion(Model domainModel, Solicitud solicitud, Bien bien, int ContadorBienes) {
        this.domainModel = domainModel;
        this.solicitud = solicitud;
        this.bien = bien;
        this.ContadorBienes = ContadorBienes;
    }

    public void setDomainModel(Model domainModel) {
        this.domainModel = domainModel;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public void setContadorBienes(int ContadorBienes) {
        this.ContadorBienes = ContadorBienes;
    }

    public Model getDomainModel() {
        return domainModel;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public Bien getBien() {
        return bien;
    }

    public int getContadorBienes() {
        return ContadorBienes;
    }

    public void eliminarBien(int id_bien) {
        for (Bien b : solicitud.getBienes()) {
            if (b.getID() == id_bien) {
                solicitud.getBienes().remove(b);
                break;
            }
        }
    }

    public void guardarSolicitud(Solicitud sol) {
        try {
            Funcionario regis = sol.getRegistrador();
            Dependencia dep = domainModel.getDependencia(regis.getID());
            sol.setDependencia(dep);
            List<Bien> bienes = sol.getBienes();
            sol.setBienes(bienes);
            domainModel.addSolicitud(sol);
            for (Bien b : solicitud.getBienes()) {
                domainModel.addBien(b);
            }
        } catch (Exception ex) {

        }
    }

}
