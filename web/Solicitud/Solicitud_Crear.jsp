<%-- 
    Document   : Solicitud_Agregar
    Created on : 19/05/2019, 04:27:22 PM
    Author     : wizard
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="Activos.Logic.Bien"%>
<%@page import="Activos.Logic.Solicitud"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Solicitud solicitud = (Solicitud) session.getAttribute("solicitud") == null ? new Solicitud() : (Solicitud) session.getAttribute("solicitud");%>
<%Bien bien = (Bien) session.getAttribute("bien") == null ? new Bien() : (Bien) session.getAttribute("bien");%>
<% Map<String, String> list_errors = (Map<String, String>) request.getAttribute("errors"); %>        
<% Map<String, String[]> user_values = (list_errors == null) ? this.getUserValues(bien) : request.getParameterMap();%>
<%String compra = "", donacion = "";%>
<%switch (solicitud.getTipo()) {
        case 1:
            compra = "Selected";
            break;
        case 2:
            donacion = "Selected";
            break;
        default:
            break;
    }%>
<%String tipoSolicitud = (String) request.getAttribute("tipoSolicitud");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Solicitudes Agregar</title>
        <%@ include file="/Head.jsp" %>
    </head>
    <body>
        <%@ include file="/Header.jsp" %>
        <div class="container">
            <center>
                <h3>Formulario Solicitud Bienes</h3>
            </center>
            <br>
            <center>
                <form class="form-inline" action="Solicitud/Solicitud_guardar">
                    <div class="form-group">
                        <label for="Comprobante">Comprobante:</label>
                        <input type="text" class="form-control" id="comprobante" placeholder="No.Comprobante" name="comprobante" value="<%=solicitud.getComprobante()%>">
                    </div>
                    <div class="form-group">
                        <label for="Fecha">Fecha:</label>
                        <input type="text" class="form-control" id="fecha" placeholder="23-05-1912" name="fecha" value="">
                    </div>
                    <div class="form-group">
                        <label for="Tipo_Comprobante">Tipo:</label>
                        <select class="form-control" id="tipo" name="tipo">
                            <option <%=compra.toString()%> value="1"> Compra </option>
                            <option <%=donacion.toString()%> value="2"> Donacion </option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="Estado_Comprobante">Estado:</label>
                        <input type="text" class="form-control" id="estado" placeholder="Default" name="estado" value="<%=solicitud.getDescripcionEstado()%>" disabled>
                    </div>
                    <button type="submit" class="btn btn-success"> Guardar </button>
                </form>
            </center>
            <br>
            <center>
                <h4>Ingresar un nuevo articulo</h4>
            </center>
            <br>
            <form class="form-inline" action="Solicitud/Solicitud_agregar_bien">
                <div class="form-group <%=check_Errors("descripcion", list_errors)%>">
                    <input type="text" class="form-control" id="descripcion" placeholder="Descripcion" name="descripcion" value="<%=getValue("descripcion", user_values)%>">
                </div>
                <div class="form-group <%=check_Errors("marca", list_errors)%>">
                    <input type="text" class="form-control" id="marca" placeholder="Marca" name="marca" value="<%=getValue("marca", user_values)%>">
                </div>
                <div class="form-group <%=check_Errors("modelo", list_errors)%>">
                    <input type="text" class="form-control" id="modelo" placeholder="Modelo" name="modelo" value="<%=getValue("modelo", user_values)%>">
                </div>
                <div class="form-group <%=check_Errors("precio", list_errors)%>">
                    <input type="text" class="form-control" id="Precio_Unidad" placeholder="Precio Unidad" name="precio" value="<%=getValue("precio", user_values)%>">
                </div>
                <div class="form-group <%=check_Errors("cantidad", list_errors)%>">
                    <input type="text" class="form-control" id="cantidad" placeholder="Cantidad" name="cantidad" value="<%=getValue("cantidad", user_values)%>">
                </div>
                <button type="submit" class="btn btn-primary"> Añadir </button>
            </form>
            <br>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Descripcion</th>
                        <th>Marca</th>
                        <th>Modelo</th>
                        <th>Precio Unitario</th>
                        <th>Cantidad</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <%for (Bien b : solicitud.getBienes()) {%>
                    <tr>
                        <td><%=b.getDescripcion()%></td>
                        <td><%=b.getMarca()%></td>
                        <td><%=b.getModelo()%></td>
                        <td><%=b.getPrecio()%></td>
                        <td><%=b.getCantidad()%></td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="Solicitud/Solicitud_eliminar_bien?tipoSolicitud=agregar&ID=<%=b.getID()%>"><span class="glyphicon glyphicon-remove"></span></a></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
    </body>
</html>
<%!private Map<String, String[]> getUserValues(Bien bien) {
        Map<String, String[]> values = new HashMap();
        values.put("descripcion", new String[]{bien.getDescripcion()});
        values.put("marca", new String[]{bien.getMarca()});
        values.put("modelo", new String[]{bien.getModelo()});
        if (bien.getPrecio() == 0) {
            values.put("precio", new String[]{""});
        } else {
            values.put("precio", new String[]{String.valueOf(bien.getPrecio())});
        }
        if (bien.getCantidad() == 0) {
            values.put("cantidad", new String[]{""});
        } else {
            values.put("cantidad", new String[]{String.valueOf(bien.getCantidad())});
        }
        return values;

    }%>
<%!private String getValue(String field, Map<String, String[]> user_values) {
        return user_values.get(field)[0];

    }%>
<%!private String check_Errors(String field, Map<String, String> list_errors) {
        if (list_errors != null) {
            if (list_errors.get(field) != null) {
                if (list_errors.get(field).equals("RIGHT")) {
                    return "has-success";
                }
                if (list_errors.get(field).equals("WRONG")) {
                    return "has-error";
                }
                if (list_errors.get(field).equals("EMPTY")) {
                    return "has-warning";
                }
            }
        }
        return "";
    }%>