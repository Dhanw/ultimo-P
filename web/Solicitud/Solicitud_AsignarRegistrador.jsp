<%-- 
    Document   : Solicitud_Agregar
    Created on : 19/05/2019, 04:27:22 PM
    Author     : wizard
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Activos.Logic.Funcionario"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="Activos.Logic.Bien"%>
<%@page import="Activos.Logic.Solicitud"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Solicitud solicitud = (Solicitud) session.getAttribute("solicitud") == null ? new Solicitud() : (Solicitud) session.getAttribute("solicitud");%>
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
<%List<Funcionario> registradores = (List<Funcionario>) request.getAttribute("registradores") == null ? new ArrayList() : (List<Funcionario>) request.getAttribute("registradores");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Solicitudes Edicion</title>
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
                <form class="form-inline" action="#">
                    <div class="form-group">
                        <label for="Comprobante">Comprobante:</label>
                        <input type="text" class="form-control" id="comprobante" placeholder="No.Comprobante" name="comprobante" value="<%=solicitud.getComprobante()%>" disabled>
                    </div>
                    <div class="form-group">
                        <label for="Fecha">Fecha:</label>
                        <input type="text" class="form-control" id="fecha" placeholder="23-05-1912" name="fecha" value="<%=solicitud.getFechaString()%>" disabled>
                    </div>
                    <div class="form-group">
                        <label for="Tipo_Comprobante">Tipo:</label>
                        <select class="form-control" id="tipo" name="tipo" disabled>
                            <option <%=compra.toString()%> value="1"> Compra </option>
                            <option <%=donacion.toString()%> value="2"> Donacion </option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="Estado_Comprobante">Estado:</label>
                        <input type="text" class="form-control" id="estado" placeholder="Default" name="estado" value="<%=solicitud.getDescripcionEstado()%>" disabled>
                    </div>
                </form>
            </center>
            <br>
            <center>
                <form class="form-inline" action="Solicitud/asignarRegistrador">
                    <div class="form-group">
                        <label for="Registrador">Registrador:</label>
                        <select class="form-control" id="registrador" name="registrador">
                            <option value="0"> Sin Asignar </option>
                            <%for (Funcionario f : registradores) {%>
                            <%if (solicitud.getRegistrador().getID() == f.getID()) {%>
                            <option selected value="<%=f.getID()%>"> <%=f.getNombre()%> </option>
                            <%} else {%>
                            <option value="<%=f.getID()%>"> <%=f.getNombre()%> </option>
                            <%}%>
                            <%}%>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-success"> Asignar </button>
                </form> 
            </center>
            <br>
            <center>
                <h4>Lista de Articulos</h4>
            </center>
            <br>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Descripcion</th>
                        <th>Marca</th>
                        <th>Modelo</th>
                        <th>Precio Unitario</th>
                        <th>Cantidad</th>
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
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
    </body>
</html>