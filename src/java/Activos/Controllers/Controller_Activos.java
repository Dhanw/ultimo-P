/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Controllers;

import Activos.Logic.Activo;
import Activos.Logic.Categoria;
import Activos.Logic.Dependencia;
import Activos.Logic.Model;
import Activos.Logic.Puesto;
import Activos.Logic.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "Controller_Activos", urlPatterns = {"/Activos/Prepare_Listado", "/Activo/Filtro_Categoria", "/Activo/Activo_eliminar", "/Activo/Activo_Peditar",
    "/Activo/Activo_Editar"})
public class Controller_Activos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        switch (request.getServletPath()) {
            case "/Activos/Prepare_Listado":
                this.prepareListado(request, response);
                break;
            case "/Activo/Filtro_Categoria":
                this.filtrarPorCategoria(request, response);
                break;
            case "/Activo/Activo_eliminar":
                this.eliminarActivo(request, response);
                break;
            case "/Activo/Activo_Peditar":
                this.prepareEditar(request, response);
                break;
            case "/Activo/Activo_Editar":
                this.editarActivo(request, response);
                break;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
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

    private void prepareListado(HttpServletRequest request, HttpServletResponse response) {
        Usuario use = (Usuario) request.getSession().getAttribute("user");
        try {
            Dependencia dep = Model.instance().getDependencia_fromFuncionarioV3(use.getFuncionario().getID());
            List<Activo> activos = Model.instance().listarActivosPorDependencia(dep.getID());
            List<Categoria> categorias = Model.instance().getCategorias();
            request.setAttribute("activos", activos);
            request.setAttribute("dependencia", dep);
             request.setAttribute("categorias", categorias);
            request.getRequestDispatcher("/Activo/Activo_Listado.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller_Activos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void filtrarPorCategoria(HttpServletRequest request, HttpServletResponse response) {
        try {
            Usuario use = (Usuario) request.getSession().getAttribute("user");
            Dependencia dep = Model.instance().getDependencia_fromFuncionarioV3(use.getFuncionario().getID());
             List<Categoria> categorias = Model.instance().getCategorias();
            String categoria = request.getParameter("categoria");
            List<Activo> activos = Model.instance().listarActivosPorCategoria(dep.getID(), categoria);
            request.setAttribute("activos", activos);
            request.setAttribute("dependencia", dep);
            request.setAttribute("categorias", categorias);
            request.getRequestDispatcher("/Activo/Activo_Listado.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller_Activos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void eliminarActivo(HttpServletRequest request, HttpServletResponse response) {

        try {
            int id = Integer.parseInt(request.getParameter("ID"));
            Model.instance().eliminarActivo(id);
            request.getRequestDispatcher("/Activos/Prepare_Listado").forward(request, response);
        } catch (Exception e) {

        }
    }

    private void prepareEditar(HttpServletRequest request, HttpServletResponse response) {

        try {
            Usuario use = (Usuario) request.getSession().getAttribute("user");
            Dependencia dep = Model.instance().getDependencia_fromFuncionarioV3(use.getFuncionario().getID());
            int id = Integer.parseInt(request.getParameter("ID"));
            Activo activo = Model.instance().getActivo(id);
            List<Categoria> categorias = Model.instance().getCategorias();
            categorias.remove(activo.getCategoria());
            List<Puesto> puestos = Model.instance().getPuestos(dep);

            request.setAttribute("activo", activo);
            request.setAttribute("categorias", categorias);
            request.setAttribute("puestos", puestos);

            request.getRequestDispatcher("/Activo/Activo_Mostrar.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(Controller_Activos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void editarActivo(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id_activo_viejo = Integer.parseInt(request.getParameter("ID_viejo"));
            Activo activo_viejo = Model.instance().getActivo(id_activo_viejo);
            String descripcion = request.getParameter("descripcion");
            int cat_id = Integer.parseInt(request.getParameter("categoria"));
            int puesto_id = Integer.parseInt(request.getParameter("puesto"));
            Puesto puesto = Model.instance().getPuesto(puesto_id);

            if (!descripcion.equals(activo_viejo.getDescripcion())) {
                Model.instance().updateDescripcionActivo(id_activo_viejo, descripcion);
            }
            if (cat_id != activo_viejo.getCategoria().getID()) {
                Model.instance().updateCategoriaActivo(id_activo_viejo, cat_id);
            }
            if (puesto_id != activo_viejo.getPuesto().getID()) {

                Model.instance().updatePuestoActivo(id_activo_viejo, puesto_id);

            }

            request.getRequestDispatcher("/Activos/Prepare_Listado").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(Controller_Activos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
