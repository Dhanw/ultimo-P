/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Models;

import Activos.Logic.Dependencia;
import Activos.Logic.Model;
import Activos.Logic.Solicitud;
import static Activos.Logic.Usuario.ADMINISTRADOR_DEPENDENCIA;
import static Activos.Logic.Usuario.SECRETARIA_OCCB;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wizzard
 */
public class Model_SolicitudListado {

    private Model domainModel;
    private Dependencia dependencia;
    private List<Solicitud> solicitudes;

    public Model_SolicitudListado() {
        this.domainModel = Model.instance();
        this.dependencia = new Dependencia();
        this.solicitudes = new ArrayList();
    }

    public Model_SolicitudListado(Model domainModel, Dependencia dependencia, List<Solicitud> solicitudes) {
        this.domainModel = domainModel;
        this.dependencia = dependencia;
        this.solicitudes = solicitudes;
    }

    public void setDomainModel(Model domainModel) {
        this.domainModel = domainModel;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public Model getDomainModel() {
        return domainModel;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void updateDependencia(int id_funcionario, int rol) throws Exception {
        Dependencia d;
        switch (rol) {
            case ADMINISTRADOR_DEPENDENCIA:
                d = domainModel.getDependencia_fromFuncionario(id_funcionario);
                break;
            case SECRETARIA_OCCB:
                d = domainModel.getDependencia_fromFuncionarioV2(id_funcionario);
                break;
            default:
                d = new Dependencia();
                break;
        }

        this.setDependencia(d);
    }

    public List<Solicitud> solicitudesXDependencia() throws Exception {
        return domainModel.solicitudesPorDependencia(dependencia);
    }

    public List<Solicitud> solicitudesXComprobante(String comprobante) throws Exception {
        return domainModel.SolicitudesPorComprobante(comprobante);
    }

    public List<Solicitud> solicitudesXTipo(String tipo) throws Exception {
        return domainModel.SolitudesTipo(tipo);
    }

    public List<Solicitud> solicitudesXEstado(String estado) throws Exception {
        return domainModel.SolitudesEstado(estado);
    }
}
