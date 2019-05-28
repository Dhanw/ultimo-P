/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Controllers;

import Activos.Logic.Bien;
import Activos.Logic.Dependencia;
import Activos.Logic.Model;
import Activos.Logic.Solicitud;
import Activos.Logic.Usuario;
import Activos.Models.Model_SolicitudEdicion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jorac
 */
@WebServlet(name = "Controller_Solicitud", urlPatterns = {"/Solicitud/Solicitud_listar", "/Solicitud/Solicitud_crear", "/Solicitud/Solicitud_mostrar", "/Solicitud/Solicitud_eliminar", "/Solicitud/Solicitud_editar",
    "/Solicitud/Filtro_Comprobante", "/Solicitud/Filtro_tipo", "/Solicitud/Filtro_estado", "/Solicitud/Solicitud_eliminar_bien", "/Solicitud/Solicitud_agregar_bien", "/Solicitud/Solicitud_guardar"})
public class Controller_Solicitud extends HttpServlet {
     Model_SolicitudEdicion modelEdicion = new Model_SolicitudEdicion();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            switch (request.getServletPath()) {
                case "/Solicitud/Solicitud_listar":
                    this.listarSolicitudes(request, response, "todas");
                    break;
                case "/Solicitud/Solicitud_crear":
                    this.crearSolicitud(request, response);
                    break;
                case "/Solicitud/Solicitud_mostrar":
                    this.mostrarSolicitud(request, response);
                    break;
                case "/Solicitud/Solicitud_eliminar":
                    this.eliminarSolicitud(request, response);
                    break;
                case "/Solicitud/Solicitud_editar":
                    this.editarSolicitud(request, response);
                    break;
                case "/Solicitud/Filtro_Comprobante":
                    this.listarSolicitudes(request, response, "comprobante");
                    break;
                case "/Solicitud/Filtro_tipo":
                    this.listarSolicitudes(request, response, "tipo");

                case "/Solicitud/Filtro_estado":
                    this.listarSolicitudes(request, response, "estado");
                case "/Solicitud/Solicitud_agregar_bien":
                    this.agregarBien(request, response);
                    break;
                case "/Solicitud/Solicitud_eliminar_bien":
                    this.eliminarBien(request, response);
                    break;
                case "/Solicitud/Solicitud_guardar":
                    this.guardarSolicitud(request, response);
                    break;
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller_Solicitud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller_Solicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void crearSolicitud(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String estado = (String) request.getSession(true).getAttribute("estado");
        if (estado.equals("enproceso")) {
            request.getSession(true).setAttribute("solicitud", modelEdicion.getSolicitud());
        }
        if (estado.equals("terminado")) {
            this.modelEdicion.setSolicitud(new Solicitud());
            request.getSession(true).setAttribute("estado", "enproceso");
            request.getSession(true).setAttribute("solicitud", modelEdicion.getSolicitud());
        }
        request.getRequestDispatcher("/Solicitud/Solicitud_Edicion.jsp").forward(request, response);
    }

    private void mostrarSolicitud(HttpServletRequest request, HttpServletResponse response) {
        Solicitud model = new Solicitud();
        Solicitud modelConsultar = null;
        try {
            modelConsultar = Model.instance().getSolicitud(Integer.parseInt(request.getParameter("ID")));
            request.setAttribute("model", modelConsultar);
            request.getRequestDispatcher("/Solicitud/Solicitud_Mostrar.jsp").
                    forward(request, response);
        } catch (Exception ex) {
        }

    }

    void updateModelId(Solicitud model, HttpServletRequest request) {
        model.setID(Integer.parseInt(request.getParameter("nombre")));
    }

    private void editarSolicitud(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void eliminarSolicitud(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id_solicitud = Integer.parseInt(request.getParameter("ID"));
        try {
            Model.instance().eliminarSolicitud(id_solicitud);
            request.setAttribute("mensaje", "correcto");
        } catch (SQLException error) {
            request.setAttribute("mensaje", error.getMessage());
        }
        request.getRequestDispatcher("/Solicitud/Solicitud_listar").forward(request, response);
    }

    private void listarSolicitudes(HttpServletRequest request, HttpServletResponse response, String filtro) throws ServletException, IOException {

        try {
            Usuario use = (Usuario) request.getSession().getAttribute("user");
            Dependencia dep = Model.instance().getDependencia_fromFuncionario(use.getFuncionario().getID());
            request.setAttribute("depe", dep);
            List<Solicitud> todas = Model.instance().solicitudesPorDependencia(dep);
            List<Solicitud> solicitudes = new ArrayList<>();
            switch (filtro) {
                case "todas":
                    solicitudes = todas;
                    break;
                case "comprobante":
                    solicitudes = (List<Solicitud>) Model.instance().SolicitudesPorComprobante((String) request.getParameter("comprobante"));
                    solicitudes.retainAll(todas);
                    break;
                case "tipo":
                    String tipo = request.getParameter("tipo");
                    solicitudes = (List<Solicitud>) Model.instance().SolitudesTipo(tipo);
                    solicitudes.retainAll(todas);
                    break;
                case "estado":
                    String estado = request.getParameter("estado");
                    solicitudes = (List<Solicitud>) Model.instance().SolitudesEstado(estado);
                    solicitudes.retainAll(todas);
                    break;
            }

            request.setAttribute("soli", solicitudes);
            request.getRequestDispatcher("/Solicitud/Solicitud_Listado.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller_Solicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarBien(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.updateBien(modelEdicion.getSolicitud(), modelEdicion.getBien(), request);
        this.modelEdicion.setBien(new Bien());
        request.getRequestDispatcher("/Solicitud/Solicitud_Edicion.jsp").forward(request, response);
    }

    private void eliminarBien(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id_bien = Integer.parseInt(request.getParameter("ID"));
        this.modelEdicion.eliminarBien(id_bien);
        request.getRequestDispatcher("/Solicitud/Solicitud_Edicion.jsp").forward(request, response);
    }

    private void guardarSolicitud(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Usuario user = (Usuario) request.getSession(true).getAttribute("user");
        this.modelEdicion.getSolicitud().setRegistrador(user.getFuncionario());
        this.updateSolicitud(modelEdicion.getSolicitud(), request);
        this.modelEdicion.guardarSolicitud(modelEdicion.getSolicitud());
        request.getSession(true).setAttribute("estado", "terminado");
        request.getRequestDispatcher("/Solicitud/Solicitud_listar").forward(request, response);
    }

    protected void updateBien(Solicitud sol, Bien bien, HttpServletRequest request) throws Exception {
        bien.setID(modelEdicion.getContadorBienes());
        this.modelEdicion.setContadorBienes(bien.getID() + 1);
        bien.setSolicitud(sol);
        bien.setDescripcion(request.getParameter("descripcion"));
        bien.setMarca(request.getParameter("marca"));
        bien.setModelo(request.getParameter("modelo"));
        bien.setPrecio(Double.parseDouble(request.getParameter("precio")));
        bien.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        sol.getBienes().add(bien);
    }

    protected void updateSolicitud(Solicitud sol, HttpServletRequest request) throws Exception {
        sol.setComprobante(request.getParameter("comprobante"));
        // sol.setFecha((Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fecha")));
        int tipo = 0;
        try {
            tipo = Integer.parseInt(request.getParameter("tipo"));
        } catch (Exception ex) {
            sol.setTipo(tipo);
        }
    }

}
