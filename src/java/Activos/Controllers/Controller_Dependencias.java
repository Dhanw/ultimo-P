/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Controllers;

import Activos.Logic.Dependencia;
import Activos.Logic.Funcionario;
import Activos.Models.Model_Departamento;
import com.google.gson.Gson;
import Activos.Models.Model_Login;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Jose
 */
@WebServlet(name = "Controller_Dependencias", urlPatterns = {"/Funcionario","/CargarFuncionarios","/EliminarDependencia","/AgregarDependencia","/CargarDependencias","/BuscarDependencia","/EditarDependencia","/BuscarDependencia","/ActualizarDependencia"})
public class Controller_Dependencias extends HttpServlet {
    

     private Model_Departamento model = new Model_Departamento();
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
        
        switch(request.getServletPath()){
            case "/AgregarDependencia":
                this.doAgregarPersona(request, response);
                break;
            case "/CargarDependencias":
                this.doCargarPersonas(request, response);
                break;
            case "/BuscarDependencia":
                this.doBuscarPorEdad(request, response);
                break;
            case "/EditarDependencia":
                this.doEditarDependencia(request, response);
                break;
            case "/ActualizarDependencia":
                this.doActualizarDependencia(request, response);
                break;
            case "/EliminarDependencia":
                this.doEliminarDependencia(request, response);
                break;
            case "/CargarFuncionarios":
                this.doCargarFuncionarios(request, response);
                break;
            case "/Funcionario":
                this.doCargarFuncionario(request, response);
                break;
        }
    }
    
    
    
        public void doCargarFuncionario(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        try{
            BufferedReader aux = request.getReader();
            Gson gson = new Gson();
            String s = gson.fromJson(aux, String.class);
            
            int edad = Integer.parseInt(s);
            
            PrintWriter out = response.getWriter();
           Funcionario dep= this.model.getFuncionario(edad);
            out.write(gson.toJson(dep));        
            response.setStatus(200); // ok with content
        }catch(Exception e){
            response.setStatus(401); //Bad request
        }
    }
    
    
    public void doCargarFuncionarios(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        
        if (this.model.listaFunc().size() > 0) {
            out.write(gson.toJson(this.model.listaFunc()));
            response.setStatus(200); // ok with content
        } else {
            response.setStatus(401); //Bad request
        }
    }
    
     public void doEliminarDependencia(HttpServletRequest request, HttpServletResponse response) throws IOException {
       try {
            Gson gson = new Gson();
            BufferedReader aux = request.getReader();
            String s = gson.fromJson(aux, String.class);
            int id = Integer.parseInt(s);
            this.model.delete(id);
            response.setStatus(200); // ok with content
        } catch (Exception e) {
            System.out.println("Error en doAgregarDependencia");
        }
    }
    
       public void doActualizarDependencia(HttpServletRequest request, HttpServletResponse response) throws IOException {
       try {
            Gson gson = new Gson();
            BufferedReader aux = request.getReader();
            Dependencia depe = gson.fromJson(aux, Dependencia.class);
            this.model.update(depe);
            response.setStatus(200); // ok with content
        } catch (Exception e) {
            System.out.println("Error en doAgregarDependencia");
        }
    }

    
       public void doEditarDependencia(HttpServletRequest request, HttpServletResponse response) throws IOException {
       try{
            BufferedReader aux = request.getReader();
            Gson gson = new Gson();
            String s = gson.fromJson(aux, String.class);
            int id = Integer.parseInt(s);
            PrintWriter out = response.getWriter();
            Dependencia lista=this.model.get(id);
            
            out.write(gson.toJson(lista));        
            response.setStatus(200); // ok with content
        }catch(Exception e){
            response.setStatus(401); //Bad request
        }
    }
    
    public void doAgregarPersona(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Gson gson = new Gson();
            BufferedReader aux = request.getReader();
            Dependencia depe = gson.fromJson(aux, Dependencia.class);
            this.model.add(depe);
            response.setStatus(200); // ok with content
        } catch (Exception e) {
            System.out.println("Error en doAgregarDependencia");
        }
    }
    
    public void doCargarPersonas(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception{
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        
        if (this.model.list().size() > 0) {
            out.write(gson.toJson(this.model.list()));
            response.setStatus(200); // ok with content
        } else {
            response.setStatus(401); //Bad request
        }
    }
    
    public void doBuscarPorEdad(HttpServletRequest request, HttpServletResponse response){
        try{
            BufferedReader aux = request.getReader();
            Gson gson = new Gson();
            String s = gson.fromJson(aux, String.class);
            
            int edad = Integer.parseInt(s);
            
            PrintWriter out = response.getWriter();
            Dependencia dep=this.model.get(edad);
            List<Dependencia> lista = new ArrayList<>();
            lista.add(dep);
            out.write(gson.toJson(lista));        
            response.setStatus(200); // ok with content
        }catch(Exception e){
            response.setStatus(401); //Bad request
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
             Logger.getLogger(Controller_Dependencias.class.getName()).log(Level.SEVERE, null, ex);
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
             Logger.getLogger(Controller_Dependencias.class.getName()).log(Level.SEVERE, null, ex);
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
