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
import Activos.Logic.Usuario;
import Activos.Models.Model_Bienes;
import Activos.Models.Model_CargaBienes;
import Activos.Models.Model_Solicitud;
import Activos.Models.Model_SolicitudListado;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jose
 */
@WebServlet(name = "Controller_Registrador", urlPatterns = {"/AgregarActivo","/CargarBienes","/CargarPuestos","/CargarCate"})
public class Controller_Registrador extends HttpServlet {

    private Model_CargaBienes model = new Model_CargaBienes();

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
        response.setContentType("text/html;charset=UTF-8");

        switch (request.getServletPath()) {
            case "/AgregarBien":
                this.doAgregarBien(request, response);
                break;
            case "/CargarBienes":
                this.doCargarPersonas(request, response);
                break;
            case "/BuscarDependencia":
                this.doBuscarPorEdad(request, response);
                break;
            case "/EditarBienes":
                this.doEditarBienes(request, response);
                break;
            case "/AgregarActivo":
                this.doAgregarActivo(request, response);
                break;
            case "/CargarCate":
                this.doCargarCategorias(request, response);
                break;

            case "/CargarPuestos":
                this.doCargarPuestos(request, response);
                break;
        }
    }

    public void doCargarPuestos(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        Usuario use = (Usuario) request.getSession().getAttribute("user");
        Dependencia dep = Model.instance().getDependencia_fromFuncionarioV3(use.getFuncionario().getID());
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        if (this.model.getPuestos(dep).size() > 0) {
            out.write(gson.toJson(this.model.getPuestos(dep)));
            response.setStatus(200); // ok with content
        } else {
            response.setStatus(401); //Bad request
        }
    }

   public void doCargarPersonas(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception{
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        if (this.model.getBienes().size() > 0) {
            out.write(gson.toJson(this.model.getBienes()));
            response.setStatus(200); // ok with content
        } else {
            response.setStatus(401); //Bad request
        }
    }

    public void doCargarCategorias(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        if (this.model.getCategorias().size() > 0) {
            out.write(gson.toJson(this.model.getCategorias()));
            response.setStatus(200); // ok with content
        } else {
            response.setStatus(401); //Bad request
        }
    }

    public void doAgregarActivo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Gson gson = new Gson();
            BufferedReader aux = request.getReader();
            Activo s = gson.fromJson(aux, Activo.class);
            this.model.add(s);
            response.setStatus(200); // ok with content
        } catch (Exception e) {
            System.out.println("Error en doAgregarDependencia");
        }
    }

    public void doEditarBienes(HttpServletRequest request, HttpServletResponse response) throws IOException {
//       try{
//            BufferedReader aux = request.getReader();
//            Gson gson = new Gson();
//            String s = gson.fromJson(aux, String.class);
//            int id = Integer.parseInt(s);
//            PrintWriter out = response.getWriter();
//            Categoria lista=this.model.get(id);
//            out.write(gson.toJson(lista));        
//            response.setStatus(200); // ok with content
//        }catch(Exception e){
//            response.setStatus(401); //Bad request
//        }
    }

    public void doAgregarBien(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        try {
//            Gson gson = new Gson();
//            BufferedReader aux = request.getReader();
//            Categoria s = gson.fromJson(aux, Categoria.class);
//            this.model.add(s);
//            response.setStatus(200); // ok with content
//        } catch (Exception e) {
//            System.out.println("Error en doAgregarDependencia");
//        }
    }

//    public void doCargarBienes(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception{
//        PrintWriter out = response.getWriter();
//        Gson gson = new Gson();
//        
//        if (this.model.list().size() > 0) {
//            out.write(gson.toJson(this.model.list()));
//            response.setStatus(200); // ok with content
//        } else {
//            response.setStatus(401); //Bad request
//        }
//}
    public void doBuscarPorEdad(HttpServletRequest request, HttpServletResponse response) {
//        try{
//            BufferedReader aux = request.getReader();
//            Gson gson = new Gson();
//            String s = gson.fromJson(aux, String.class);
//            
//            int edad = Integer.parseInt(s);
//            
//            PrintWriter out = response.getWriter();
//            Dependencia dep=this.model.get(edad);
//            List<Dependencia> lista = new ArrayList<>();
//            lista.add(dep);
//            out.write(gson.toJson(lista));        
//            response.setStatus(200); // ok with content
//        }catch(Exception e){
//            response.setStatus(401); //Bad request
//        }
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
            Logger.getLogger(Controller_Registrador.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Controller_Registrador.class.getName()).log(Level.SEVERE, null, ex);
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

}
