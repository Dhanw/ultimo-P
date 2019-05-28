/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Controllers;

import Activos.Logic.Usuario;
import static Activos.Logic.Usuario.ADMINISTRADOR_DEPENDENCIA;
import static Activos.Logic.Usuario.JEFE_RRH;
import Activos.Models.Model_Login;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wizzard
 */
@WebServlet(name = "Controller_Login", urlPatterns = {"/UserLogin/PrepareLogin", "/UserLogin/Login", "/UserLogin/Logout"})
public class Controller_Login extends HttpServlet {

    private Model_Login model = new Model_Login();

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
            case "/UserLogin/PrepareLogin":
                this.prepareLogin(request, response);
                break;
            case "/UserLogin/Login":
                this.Login(request, response);
                break;
            case "/UserLogin/Logout":
                this.Logout(request, response);
                break;
            default:
                break;
        }
    }

    void updateModel(Usuario user, HttpServletRequest request) {
        user.setCuenta(request.getParameter("cuenta"));
        user.setPassword(request.getParameter("password"));
    }

    protected void prepareLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario user = model.getUser();
        request.setAttribute("user", user);
        request.getRequestDispatcher("/UserLogin/Login_View.jsp").forward(request, response);
    }

    private void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Busca el usuario en la DB, si no lo encuentra se regresa al login
        this.updateModel(model.getUser(), request);
        try {
            Usuario sesion = model.Login(request.getParameter("cuenta"), request.getParameter("password"));
            if (sesion != null) {
                request.getSession(true).setAttribute("user", sesion);
                request.getSession(true).setAttribute("estado", "terminado");
                this.seleccionador(request, response, sesion.getRol());
            } else {
                request.getRequestDispatcher("/UserLogin/PrepareLogin").forward(request, response);
            }
        } catch (Exception ex) {
        }
    }

    protected void Logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.invalidate();
        request.getRequestDispatcher("/UserLogin/Login_View.jsp").forward(request, response);
    }

    private void seleccionador(HttpServletRequest request, HttpServletResponse response, int tipo) throws ServletException, IOException {
        switch (tipo) {
            case ADMINISTRADOR_DEPENDENCIA:
                request.getRequestDispatcher("/Solicitud/Solicitud_listar").forward(request, response);
                break;
            case JEFE_RRH:
                request.getRequestDispatcher("/Funcionario/Funcionario_listar").forward(request, response);
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

}
