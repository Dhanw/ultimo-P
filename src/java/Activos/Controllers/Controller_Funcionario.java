/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Controllers;

import Activos.Logic.Dependencia;
import Activos.Logic.Funcionario;
import Activos.Logic.Model;
import Activos.Logic.Puesto;
import Activos.Logic.Usuario;
import Activos.Models.Model_Funcionario;
import java.io.IOException;
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
@WebServlet(name = "Controller_Funcionario", urlPatterns = {"/Funcionario/Funcionario_listar", "/Funcionario/Funcionario_Crear", "/Funcionario/Agregar_Funcionario",
    "/Funcionario/Funcionario_eliminar", "/Funcionario/Filtro_Funcionario_Nombre", "/Funcionario/Funcionario_editar", "/Funcionario/Editar_Funcionario"})
public class Controller_Funcionario extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        switch (request.getServletPath()) {
            case "/Funcionario/Funcionario_listar": {
                try {
                    this.prepareListado(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(Controller_Funcionario.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            break;
            case "/Funcionario/Funcionario_Crear": {
                try {
                    this.prepareCrear(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(Controller_Funcionario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case "/Funcionario/Agregar_Funcionario": {
                try {
                    this.agregar(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(Controller_Funcionario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "/Funcionario/Funcionario_eliminar": {
                try {
                    this.eliminar(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(Controller_Funcionario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "/Funcionario/Filtro_Funcionario_Nombre": {
                try {
                    this.filtroNombre(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(Controller_Funcionario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            break;
            case "/Funcionario/Funcionario_editar": {
                try {
                    this.prepare_editar(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(Controller_Funcionario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            break;
            case "/Funcionario/Editar_Funcionario": {
                try {
                    this.editar(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(Controller_Funcionario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            break;
            default:
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

    private void prepareListado(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Model_Funcionario> funcionarios = new ArrayList<>();
        Usuario use = (Usuario) request.getSession().getAttribute("user");
        Dependencia dep = Model.instance().getDependencia_fromFuncionarioV2(use.getFuncionario().getID());
        List<Funcionario> todos = Model.instance().getFuncionarios();

        Model_Funcionario tmp = null;
        for (Funcionario f : todos) {
            Puesto puesto = Model.instance().getPuestoFromFuncionario(f.getID());
            boolean user = Model.instance().isUsuario(f.getID());
            if (puesto != null) {
                tmp = new Model_Funcionario(f.getID(), f.getNombre(), f.getIdentificacion(), puesto, user);
            } else {
                tmp = new Model_Funcionario(f.getID(), f.getNombre(), f.getIdentificacion(), null, user);
            }
            funcionarios.add(tmp);
        }

        request.setAttribute("funcionarios", funcionarios);
        request.setAttribute("dependencia", dep);
        request.getRequestDispatcher("/Funcionario/Funcionario_Listado.jsp").forward(request, response);

    }

    private void prepareCrear(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Usuario use = (Usuario) request.getSession().getAttribute("user");
        Dependencia dep = Model.instance().getDependencia_fromFuncionarioV2(use.getFuncionario().getID());
        List<Puesto> puestos = Model.instance().getPuestosDisponibles();
        request.setAttribute("puestos", puestos);
        request.setAttribute("dependencia", dep);
        request.getRequestDispatcher("/Funcionario/Funcionario_Edicion.jsp").forward(request, response);
    }

    private void agregar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Usuario use = (Usuario) request.getSession().getAttribute("user");
        Dependencia dep = Model.instance().getDependencia_fromFuncionarioV2(use.getFuncionario().getID());
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        int p = Integer.parseInt(request.getParameter("puesto"));
        Funcionario fun = new Funcionario(cedula, nombre);
        //Agregamos el funcionario
        Model.instance().addFuncionario(fun);
        Puesto puesto = null;
        //Lo contratamos en caso de que el puesto no sea 0
        if (p != 0) {
            puesto = Model.instance().getPuesto(p);

            Model.instance().contratar(fun, puesto);
        }

        request.getRequestDispatcher("/Funcionario/Funcionario_listar").forward(request, response);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("ID"));
        Funcionario fun = Model.instance().getFuncionario(id);
        Puesto p = Model.instance().getPuestoFromFuncionario(fun.getID());
        boolean b;
        if (p == null) {
            b = false;
        } else {
            b = true;
        }

        Model.instance().descontratar(fun, b);

        request.getRequestDispatcher("/Funcionario/Funcionario_listar").forward(request, response);

    }

    private void filtroNombre(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filtro = request.getParameter("nombre");
        List<Model_Funcionario> funcionarios = new ArrayList<>();
        Usuario use = (Usuario) request.getSession().getAttribute("user");
        Dependencia dep = Model.instance().getDependencia_fromFuncionarioV2(use.getFuncionario().getID());
        List<Funcionario> todos = Model.instance().getFuncionarios();

        Model_Funcionario tmp = null;
        for (Funcionario f : todos) {
            Puesto puesto = Model.instance().getPuestoFromFuncionario(f.getID());
            String a = f.getNombre().toUpperCase();
            String b = filtro.toUpperCase();
            if (a.matches(".*" + b + ".*")) {
               boolean user = Model.instance().isUsuario(f.getID());
            if (puesto != null) {
                tmp = new Model_Funcionario(f.getID(), f.getNombre(), f.getIdentificacion(), puesto, user);
            } else {
                tmp = new Model_Funcionario(f.getID(), f.getNombre(), f.getIdentificacion(), null, user);
            }
                funcionarios.add(tmp);
            }
        }

        request.setAttribute("funcionarios", funcionarios);
        request.setAttribute("dependencia", dep);
        request.getRequestDispatcher("/Funcionario/Funcionario_Listado.jsp").forward(request, response);
    }

    private void prepare_editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {

        String ID = (request.getParameter("ID"));
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        Puesto puesto = Model.instance().getPuesto(Integer.parseInt(request.getParameter("PID")));

        request.setAttribute("cedula", cedula);
        request.setAttribute("ID", ID);
        request.setAttribute("nombre", nombre);
        request.setAttribute("puesto", puesto);

        List<Puesto> puestos = Model.instance().getPuestosDisponibles();
        request.setAttribute("puestos", puestos);

        request.getRequestDispatcher("/Funcionario/Funcionario_Mostrar.jsp").forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {

        int IDf = Integer.parseInt(request.getParameter("ID"));
        Funcionario fun = Model.instance().getFuncionario(IDf);
        String nombre = request.getParameter("nombre");
        Puesto p = Model.instance().getPuestoFromFuncionario(IDf);
        Puesto puesto = Model.instance().getPuesto(Integer.parseInt(request.getParameter("puesto")));

        Model.instance().updateFuncionarioNombre(fun, nombre);
        
        if(p!=null && puesto!= null){
            if(!(p.getID() == puesto.getID())){
                 Model.instance().updateFuncionarioPuesto(fun, puesto, p);
            }
        }
        else{
             Model.instance().updateFuncionarioPuesto(fun, puesto, p);
        }
       

        request.getRequestDispatcher("/Funcionario/Funcionario_listar").forward(request, response);
    }

}
