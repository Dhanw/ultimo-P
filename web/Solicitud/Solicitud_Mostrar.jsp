<%-- 
    Document   : Solicitud_Mostrar
    Created on : Mar 27, 2019, 4:56:28 PM
    Author     : Jose
--%>

<%@page import="Activos.Logic.Bien"%>
<%@page import="Activos.Logic.Solicitud"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Solicitud Mostrar</title>
        <%@ include file="/Head.jsp" %>
    </head>
    <body>
        <%@ include file="/Header.jsp" %>
        <%Solicitud sol=(Solicitud)request.getAttribute("model"); %>
        <div class="container">
            <center>
                <h2>Formulario Comprobante  <%= sol.getDescripcionEstado()%> </h2>
            </center>
            <br> <br>
            <form class="form-inline" action="/Registrar_Comprobante">
                <div class="form-group">
                    <label for="Comprobante">Comprobante:</label>
                    <input type="text" class="form-control"  value="<%=sol.getComprobante()%> " readonly="readonly" id="comprobante" placeholder="" name="comprobante">
                </div>
                <div class="form-group">
                    <label for="Fecha">Fecha:</label>
                    <input type="text" class="form-control" value="<%=sol.getFecha()%> " readonly="readonly" id="fecha" placeholder="" name="fecha">
                </div>
                <label for="Tipo_Comprobante">Tipo:</label>
                <input type="text" class="form-control" value="<%= sol.getDescripcionTipo()%> " readonly="readonly" id="tipo" placeholder="" name="tipo">
            </form>
            <br> <br> <br>
            <form class="form-inline" action="/Registrar_Comprobante">
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
                        <%
                            for (Bien b : sol.getBienes()) {
                        %>
                        <tr>
                            <td><%= b.getDescripcion()%></td>
                            <td><%= b.getMarca()%></td>
                            <td><%= b.getModelo()%></td>
                            <td><%= b.getPrecio()%></td>
                            <td><%= b.getCantidad()%></td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
                <center>
                    <a href="Solicitud/Solicitud_listar"> regresar </a>
                </center>
            </form>
        </div>
    </body>
</html>
