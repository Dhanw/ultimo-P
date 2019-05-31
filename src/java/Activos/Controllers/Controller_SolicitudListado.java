/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Controllers;

import Activos.Logic.Solicitud;
import Activos.Logic.Usuario;
import Activos.Models.Model_SolicitudListado;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wizard
 */
@WebServlet(name = "Controller_SolicitudListado", urlPatterns = {"/Solicitud/Solicitud_listar",
    "/Solicitud/Filtro_Comprobante", "/Solicitud/Filtro_tipo", "/Solicitud/Filtro_estado"})
public class Controller_SolicitudListado extends HttpServlet {

    Model_SolicitudListado modelsSolicitudListado = new Model_SolicitudListado();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        switch (request.getServletPath()) {
            case "/Solicitud/Solicitud_listar":
                this.listarSolicitudes(request, response, "todas");
                break;
            case "/Solicitud/Filtro_Comprobante":
                this.listarSolicitudes(request, response, "comprobante");
                break;
            case "/Solicitud/Filtro_tipo":
                this.listarSolicitudes(request, response, "tipo");
                break;
            case "/Solicitud/Filtro_estado":
                this.listarSolicitudes(request, response, "estado");
                break;
        }
    }

    private void updateModel(HttpServletRequest request) {
        try {
            Usuario user = (Usuario) request.getSession().getAttribute("user");
            modelsSolicitudListado.updateDependencia(user.getFuncionario().getID());
        } catch (Exception ex) {
        }
        request.setAttribute("dependencia", modelsSolicitudListado.getDependencia());
    }

    private void listarSolicitudes(HttpServletRequest request, HttpServletResponse response, String filtro) throws ServletException, IOException {
        try {
            updateModel(request);
            String comprobante = request.getParameter("comprobante"), tipo = request.getParameter("tipo"), estado = request.getParameter("estado");
            List<Solicitud> todas = modelsSolicitudListado.solicitudesXDependencia();
            List<Solicitud> solicitudes = null;
            switch (filtro) {
                case "todas":
                    solicitudes = todas;
                    break;
                case "comprobante":
                    solicitudes = modelsSolicitudListado.solicitudesXComprobante(comprobante);
                    solicitudes.retainAll(todas);
                    break;
                case "tipo":
                    solicitudes = modelsSolicitudListado.solicitudesXTipo(tipo);
                    solicitudes.retainAll(todas);
                    break;
                case "estado":
                    solicitudes = modelsSolicitudListado.solicitudesXEstado(estado);
                    solicitudes.retainAll(todas);
                    break;
            }
            request.setAttribute("solicitudes", solicitudes);
            request.getRequestDispatcher("/Solicitud/Solicitud_Listado.jsp").forward(request, response);
        } catch (Exception ex) {
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
        processRequest(request, response);
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
        processRequest(request, response);
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
}
