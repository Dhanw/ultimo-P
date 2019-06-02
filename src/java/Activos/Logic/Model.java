/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Logic;

import Activos.Data.Dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose model creado
 */
public class Model {

    private Dao dao;

    private static Model uniqueInstance;

    public static Model instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Model();
        }
        return uniqueInstance;
    }

    public Model() {
        dao = new Dao();
    }

    public Usuario getUsuario(String cuenta, String pass) throws SQLException, Exception {
        return dao.getUsuario(cuenta, pass);
    }

    public void addUsuario(Usuario usuario) throws Exception {
        dao.addUsuario(usuario);
    }

    public Funcionario getFuncionario(int id) throws SQLException, Exception {
        return dao.getFuncionario(id);
    }

    public void addFuncionario(Funcionario funcionario) throws Exception {
        dao.addFuncionario(funcionario);
    }

    public Dependencia getDependencia(int id) throws SQLException, Exception {
        return dao.getDependencia(id);
    }

    public Dependencia getDependencia_fromFuncionario(int id) throws SQLException, Exception {
        return dao.getDependencia_fromFuncionario(id);
    }

    public void addDependencia(Dependencia dependencia) throws Exception {
        dao.addDependencia(dependencia);
    }

    public Bien getBien(int id) throws SQLException, Exception {
        return dao.getBien(id);
    }

    public List<Bien> getBienesPorSolicitud(Solicitud solicitud) throws SQLException {
        return dao.getBienes(solicitud);
    }

    public void addBien(Bien bien) throws Exception {
        dao.addBien(bien);
    }

    public void deleteBien(Bien bien) throws Exception {
        dao.deleteBien(bien);
    }

    public Solicitud getSolicitud(int id) throws SQLException, Exception {
        return dao.getSolicitud(id);
    }

    public List<Solicitud> solicitudesPorDependencia(Dependencia depe) throws SQLException, Exception {
        return dao.getSolicitudes(depe);
    }

    public void eliminarSolicitud(int solicitud) throws SQLException {
        dao.eliminarSolicitud(solicitud);
    }

    public List<Solicitud> SolitudesTipo(String tipo) throws SQLException, Exception {
        int nTipo = 0;

        if ("Compra".equals(tipo) || "COMPRA".equals(tipo) || "compra".equals(tipo)) {
            nTipo = 1;
        } else if ("DONACION".equals(tipo) || "Donacion".equals(tipo) || "donacion".equals(tipo)) {
            nTipo = 2;
        } else if ("Produccion".equals(tipo) || "produccion".equals(tipo) || "PRODUCCION".equals(tipo)) {
            nTipo = 3;
        }
        return dao.SolitudesTipo(nTipo);

    }

     public List<Solicitud> Solitudes_X_Estado(int estado) throws SQLException, Exception {
        return dao.SolitudesEstado(estado);
    }
    
    public List<Solicitud> SolitudesEstado(String estado) throws SQLException, Exception {

        int estadoParseado = 0;
        if ("recibida" == estado || "Recibida".equals(estado) || "RECIBIDA".equals(estado)) {
            estadoParseado = 1;
        } else if ("verificar".equals(estado) || "Por verificar".equals(estado) || "por verificar".equals(estado)) {
            estadoParseado = 2;
        } else if ("rechazada".equals(estado) || "Rechazada".equals(estado) || "RECHAZADA".equals(estado)) {
            estadoParseado = 3;
        } else if ("Espera".equals(estado) || "Espera rotulacion".equals(estado) || "espera rotulacion".equals(estado)) {
            estadoParseado = 4;
        } else if ("Procesada".equals(estado) || "procesada".equals(estado) || "PROCESADA".equals(estado)) {
            estadoParseado = 5;
        }
        return dao.SolitudesEstado(estadoParseado);
    }

    public List<Solicitud> SolicitudesPorComprobante(String comprobante) throws Exception {
        return dao.getByComprobante(comprobante);
    }

    public void addSolicitud(Solicitud sol) throws SQLException, Exception {
        dao.addSolicitud(sol);
    }

    public void updateSolicitud(Solicitud sol) throws SQLException, Exception {
        dao.updateSolicitud(sol);
    }

    public List<Funcionario> getFuncionarios() throws SQLException {
        return dao.getFuncionarios();
    }

    public Puesto getPuestoFromFuncionario(int id) throws SQLException, Exception {
        return dao.getPuestoFromFuncionario(id);
    }

    public List<Funcionario> getFuncionariosFromDependencia(Dependencia depe) throws SQLException {
        return dao.getFuncionariosFromDependencia(depe);
    }

    public Dependencia getDependencia_fromFuncionarioV2(int func) throws Exception {
        return dao.getDependencia_fromFuncionarioV2(func);
    }

    public List<Puesto> getPuestosDisponiblesPorDependencia(Dependencia dep) throws SQLException {
        return dao.getPuestosDisponiblesPorDependencia(dep);
    }

    public List<Puesto> getPuestosDisponibles() throws SQLException, Exception {
        return dao.getPuestosDisponibles();
    }

    public void contratar(Funcionario f, Puesto p) throws SQLException {
        dao.contratar(f, p);
    }

    public Puesto getPuesto(int p) throws Exception {
        return dao.getPuesto(p);
    }

    public void descontratar(Funcionario f, boolean bandera) throws SQLException {
        dao.descontratar(f, bandera);
    }

    public void updateFuncionarioNombre(Funcionario f, String nombre) throws SQLException {
        dao.updateFuncionarioNombre(f, nombre);
    }

    public void updateFuncionarioPuesto(Funcionario f, Puesto p, Puesto o) throws SQLException {
        dao.updateFuncionarioPuesto(f, p, o);
    }

    public void updateDependencia(Dependencia dependencia) throws Exception {
        dao.updateDependencia(dependencia);
    }

    public void eliminarDependencia(int dependencia) throws Exception {
        dao.eliminarDependencia(dependencia);
    }

    public Dependencia getDependencia_FromNombre(String nombre) throws SQLException, Exception {

        return dao.getDependencia_FromNombre(nombre);

    }

    public List<Dependencia> getDependencias() throws SQLException, Exception {
        return dao.getDependencias();
    }
    public boolean isUsuario(int id) throws SQLException {
        return dao.isUsuario(id);
    }
    //--------------------------------------------------------------------METODOS DE CATEGORIA
        public void addCategoria(Categoria cat) throws Exception {
        dao.addCategoria(cat);
    }

    public Categoria getCategoria(int id) throws SQLException {
        return dao.getCategoria(id);
    
    }

    public List<Categoria> getCategorias() throws SQLException {
        return dao.getCategorias();
    }

        public void updateCategoria(Categoria dependencia) throws Exception {
        dao.updateCategoria(dependencia);
    }

    public void eliminarCategoria(int dependencia) throws Exception {
        dao.deleteCategoria(dependencia);
    }
    
    //-----------------------------------------------------
    public List<Solicitud> getSolicitudes() throws SQLException, Exception {
        return dao.getSolicitud();
    }
    
    //---------------------------------------------------------------------------
    public List<Bien> getBienes() throws SQLException, Exception {
        return dao.getBienes();
    }
//--------------------------------------------------------------------------------
    public List<Puesto> getPuestos(Dependencia dep) throws Exception{
        return dao.getPuestos(dep);
    }
    
    public Dependencia getDependencia_fromFuncionarioV3(int id) throws Exception{
    return dao.getDependencia_fromFuncionarioV3(id);
    }

    public void addActivo(Activo p) throws Exception {
        dao.addActivo(p);
    }
    

    public List<Activo> listarActivosPorDependencia(int depe) throws Exception {
        return dao.getActivosFromDependencia(depe);
    }

    public List<Activo> listarActivosPorCategoria(int id, String categoria) throws Exception {
        return dao.getListaActivosPorCategoria(id, categoria);
    }

    public void eliminarActivo(int id) throws SQLException {
        dao.eliminarActivo(id);
    }



    public Activo getActivo(int ac) throws Exception {
        return dao.getActivo(ac);
    }


    public void updateDescripcionActivo(int id_activo, String descrp) throws SQLException {
        dao.updateDescripcionActivo(id_activo, descrp);
    }

    public void updateCategoriaActivo(int id_activo, int cat_id) throws SQLException {
         dao.updateCategoriaActivo(id_activo, cat_id);
    }

    public void updatePuestoActivo(int id_activo, int p) throws SQLException {
        dao.updatePuestoActivo(id_activo, p);
    }
}
