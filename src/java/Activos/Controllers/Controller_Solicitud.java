/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Controllers;

import Activos.Logic.Bien;
import Activos.Logic.Funcionario;
import Activos.Logic.Solicitud;
import Activos.Logic.Usuario;
import Activos.Models.Model_Solicitud;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "Controller_Solicitud", urlPatterns = {"/Solicitud/Solicitud_crear",
    "/Solicitud/Solicitud_agregar_bien", "/Solicitud/Solicitud_eliminar_bien", "/Solicitud/Solicitud_guardar",
    "/Solicitud/Solicitud_mostrar", "/Solicitud/Solicitud_editar", "/Solicitud/Solicitud_eliminar", "/Solicitud/asignarRegistrador"})
public class Controller_Solicitud extends HttpServlet {

    Model_Solicitud modelSolicitud = new Model_Solicitud();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        switch (request.getServletPath()) {
            case "/Solicitud/Solicitud_crear":
                this.crearSolicitud(request, response);
                break;
            case "/Solicitud/Solicitud_agregar_bien":
                this.agregarBien(request, response);
                break;
            case "/Solicitud/Solicitud_eliminar_bien":
                this.eliminarBien(request, response);
                break;
            case "/Solicitud/Solicitud_guardar":
                this.guardarSolicitud(request, response);
                break;
            case "/Solicitud/Solicitud_mostrar":
                this.mostrarSolicitud(request, response);
                break;
            case "/Solicitud/Solicitud_editar":
                this.editarSolicitud(request, response);
                break;
            case "/Solicitud/Solicitud_eliminar":
                this.eliminarSolicitud(request, response);
                break;
            case "/Solicitud/asignarRegistrador":
                this.asignarRegistrador(request, response);
                break;
        }
    }

    private void crearSolicitud(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String estado = (String) request.getSession(true).getAttribute("estado");
        if (estado.equals("enproceso")) {
            request.getSession(true).setAttribute("solicitud", modelSolicitud.getModelAgregar());
            request.getSession(true).setAttribute("bien", modelSolicitud.getBien());
        }
        if (estado.equals("terminado")) {
            this.modelSolicitud.setModelAgregar(new Solicitud());
            request.getSession(true).setAttribute("estado", "enproceso");
            request.getSession(true).setAttribute("tipoSolicitud", "crear");
            request.getSession(true).setAttribute("bien", modelSolicitud.getBien());
            request.getSession(true).setAttribute("solicitud", modelSolicitud.getModelAgregar());
        }
        request.getRequestDispatcher("/Solicitud/Solicitud_Crear.jsp").forward(request, response);
    }

    protected void updateBien(Bien bien, HttpServletRequest request) {
        try {
            bien.setDescripcion(request.getParameter("descripcion"));
            bien.setMarca(request.getParameter("marca"));
            bien.setModelo(request.getParameter("modelo"));
            bien.setPrecio(Double.parseDouble(request.getParameter("precio")));
            bien.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
            bien.setID(modelSolicitud.getContadorBienes());
            this.modelSolicitud.setContadorBienes(bien.getID() + 1);
        } catch (Exception ex) {
        }
    }

    private Boolean is_NULL(HttpServletRequest request) {
        return request.getParameter("descripcion") == null || request.getParameter("marca") == null
                || request.getParameter("modelo") == null || request.getParameter("precio") == null
                || request.getParameter("cantidad") == null;
    }

    private Map<String, String> validate_Fields(HttpServletRequest request) {
        Map<String, String> errores = new HashMap<>();
        String descripcion = request.getParameter("descripcion");
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        String precio = request.getParameter("precio");
        String cantidad = request.getParameter("cantidad");
        if (descripcion.isEmpty()) {
            errores.put("descripcion", "EMPTY");
        } else {
            errores.put("descripcion", "RIGHT");
        }
        if (marca.isEmpty()) {
            errores.put("marca", "EMPTY");
        } else {
            errores.put("marca", "RIGHT");
        }
        if (modelo.isEmpty()) {
            errores.put("modelo", "EMPTY");
        } else {
            errores.put("modelo", "RIGHT");
        }
        if (precio.isEmpty()) {
            errores.put("precio", "EMPTY");
        } else {
            if (Float.parseFloat(precio) < 0) {
                errores.put("precio", "WRONG");
            } else {
                errores.put("precio", "RIGHT");
            }
        }
        if (cantidad.isEmpty()) {
            errores.put("cantidad", "EMPTY");
        } else {
            if (Integer.parseInt(cantidad) < 0) {
                errores.put("cantidad", "WRONG");
            } else {
                errores.put("cantidad", "RIGHT");
            }
        }
        return no_Errors(errores) ? new HashMap<>() : errores;
    }

    private Boolean no_Errors(Map<String, String> errores) {
        for (String str : errores.values()) {
            if (!str.equals("RIGHT")) {
                return false;
            }
        }
        return true;
    }

    private void agregarBien(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!this.is_NULL(request)) {
            String tipo = (String) request.getSession(true).getAttribute("tipoSolicitud");
            this.updateBien(modelSolicitud.getBien(), request);
            Bien bien = modelSolicitud.getBien();
            request.setAttribute("bien", bien);
            Map<String, String> errors = this.validate_Fields(request);
            if (errors.isEmpty()) {
                if (tipo.equals("crear")) {
                    this.modelSolicitud.agregarBien(bien, modelSolicitud.getModelAgregar());
                    request.getSession(true).setAttribute("solicitud", modelSolicitud.getModelAgregar());
                    this.modelSolicitud.setBien(new Bien());
                    request.getSession(true).setAttribute("bien", modelSolicitud.getBien());
                    request.getRequestDispatcher("/Solicitud/Solicitud_Crear.jsp").forward(request, response);
                }
                if (tipo.equals("editar")) {
                    this.modelSolicitud.agregarBien(bien, modelSolicitud.getModelEditar());
                    request.getSession(true).setAttribute("solicitud", modelSolicitud.getModelEditar());
                    this.modelSolicitud.setBien(new Bien());
                    request.getSession(true).setAttribute("bien", modelSolicitud.getBien());
                    request.getRequestDispatcher("/Solicitud/Solicitud_Editar.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errors", errors);
                switch (tipo) {
                    case "crear":
                        request.getRequestDispatcher("/Solicitud/Solicitud_Crear.jsp").forward(request, response);
                        break;
                    case "editar":
                        request.getRequestDispatcher("/Solicitud/Solicitud_Editar.jsp").forward(request, response);
                        break;
                    default:
                        break;
                }
            }
        } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private void eliminarBien(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String tipo = (String) request.getSession(true).getAttribute("tipoSolicitud");
        int id_bien = Integer.parseInt(request.getParameter("ID"));
        if (tipo.equals("crear")) {
            this.modelSolicitud.eliminarBien(id_bien, modelSolicitud.getModelAgregar());
            request.getSession(true).setAttribute("solicitud", modelSolicitud.getModelAgregar());
        }
        if (tipo.equals("editar")) {
            this.modelSolicitud.eliminarBien(id_bien, modelSolicitud.getModelEditar());
            request.getSession(true).setAttribute("solicitud", modelSolicitud.getModelEditar());
        }
        request.getRequestDispatcher("/Solicitud/Solicitud_Crear.jsp").forward(request, response);
    }

    protected void updateSolicitud(Solicitud sol, HttpServletRequest request) throws Exception {
        sol.setComprobante(request.getParameter("comprobante"));
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date parsed = format.parse(request.getParameter("fecha"));
            Date date = new Date(parsed.getTime());
            sol.setFecha(date);
        } catch (Exception ex) {
            LocalDate localdate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Date date = Date.valueOf(localdate.format(formatter));
            sol.setFecha(date);
        }
        String stringTipo = request.getParameter("tipo");
        int tipo = Integer.parseInt(stringTipo);
        sol.setTipo(tipo);
//        String stringEstado = request.getParameter("estado");
//        if (stringEstado != null) {
//            int estado = Integer.parseInt(stringEstado);
//            sol.setEstado(estado);
//        } else {
//            sol.setEstado(Solicitud.RECIBIDA);
//        }
    }

    private void guardarSolicitud(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Usuario user = (Usuario) request.getSession(true).getAttribute("user");
        String tipo = (String) request.getSession(true).getAttribute("tipoSolicitud");
        if (tipo.equals("crear")) {
//            this.modelSolicitud.getModelAgregar().setRegistrador(user.getFuncionario());
            this.updateSolicitud(modelSolicitud.getModelAgregar(), request);
            this.modelSolicitud.guardarSolicitud(modelSolicitud.getModelAgregar(), user.getFuncionario());
            request.getSession(true).setAttribute("estado", "terminado");
            request.getRequestDispatcher("/Solicitud/Solicitud_listar").forward(request, response);
        }
        if (tipo.equals("editar")) {
//            this.modelSolicitud.getModelEditar().setRegistrador(user.getFuncionario());
            this.updateSolicitud(modelSolicitud.getModelEditar(), request);
            this.modelSolicitud.actualizarSolicitud(modelSolicitud.getModelEditar());
            request.getSession(true).setAttribute("estado", "terminado");
            request.getRequestDispatcher("/Solicitud/Solicitud_listar").forward(request, response);
        }
    }

    private void mostrarSolicitud(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id_solicitud = Integer.parseInt(request.getParameter("ID"));
            Solicitud model = modelSolicitud.getSolicitud(id_solicitud);
            this.modelSolicitud.setModelMostrar(model);
            request.getSession(true).setAttribute("solicitud", modelSolicitud.getModelMostrar());
            request.getRequestDispatcher("/Solicitud/Solicitud_Mostrar.jsp").forward(request, response);
        } catch (Exception ex) {
        }
    }

    private void editarSolicitud(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id_solicitud = Integer.parseInt(request.getParameter("ID"));
            Solicitud solicitud = modelSolicitud.getSolicitud(id_solicitud);
            Usuario user = (Usuario) request.getSession(true).getAttribute("user");
            if (solicitud.getEstado() != Solicitud.RECIBIDA && user.getRol() == Usuario.ADMINISTRADOR_DEPENDENCIA) {
                this.mostrarSolicitud(request, response);
            }
            Solicitud model = modelSolicitud.getSolicitud(id_solicitud);
            this.modelSolicitud.setModelEditar(model);
            request.getSession(true).setAttribute("solicitud", modelSolicitud.getModelEditar());
            if (user.getRol() == Usuario.ADMINISTRADOR_DEPENDENCIA) {
                request.getSession(true).setAttribute("tipoSolicitud", "editar");
                request.getSession(true).setAttribute("bien", modelSolicitud.getBien());
                request.getRequestDispatcher("/Solicitud/Solicitud_Editar.jsp").forward(request, response);
            }
            if (user.getRol() == Usuario.SECRETARIA_OCCB) {
                if (solicitud.getEstado() != Solicitud.RECIBIDA && solicitud.getEstado() != Solicitud.POR_VERIFICAR && solicitud.getEstado() != Solicitud.RECHAZADA) {
                    this.mostrarSolicitud(request, response);
                }
                request.getRequestDispatcher("/Solicitud/Solicitud_Aceptar_Rechazar.jsp").forward(request, response);
            }
            if (user.getRol() == Usuario.JEFE_OCCB) {
                if (solicitud.getEstado() != Solicitud.POR_VERIFICAR) {
                    this.mostrarSolicitud(request, response);
                }

                List<Funcionario> registradores = modelSolicitud.getRegistradores();
                request.setAttribute("registradores", registradores);
                request.getRequestDispatcher("/Solicitud/Solicitud_AsignarRegistrador.jsp").forward(request, response);
            }
        } catch (Exception ex) {
        }
    }

    private void eliminarSolicitud(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id_solicitud = Integer.parseInt(request.getParameter("ID"));
        Solicitud solicitud = modelSolicitud.getSolicitud(id_solicitud);
        try {
            if (solicitud.getEstado() == Solicitud.RECIBIDA || solicitud.getEstado() == Solicitud.RECHAZADA) {
                this.modelSolicitud.eliminarSolicitud(id_solicitud);
            }
            request.setAttribute("mensaje", "correcto");
        } catch (SQLException error) {
            request.setAttribute("mensaje", error.getMessage());
        }
        request.getRequestDispatcher("/Solicitud/Solicitud_listar").forward(request, response);
    }

    private void updateRegistrador(HttpServletRequest request) throws Exception {
        String stringIDRegistrador = request.getParameter("registrador");
        int id_registrador = Integer.parseInt(stringIDRegistrador);
        modelSolicitud.updateRegistrador(id_registrador);
    }

    private void asignarRegistrador(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.updateRegistrador(request);
        modelSolicitud.asignarRegistrador();
        request.getRequestDispatcher("/Solicitud/Solicitud_listar").forward(request, response);
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
}
