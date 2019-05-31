<%-- 
    Document   : solicitud
    Created on : Mar 23, 2019, 7:50:41 PM
    Author     : Jose
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Activos.Logic.Solicitud"%>
<%@page import="Activos.Logic.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Dependencia dependencia = (Dependencia) request.getAttribute("dependencia") == null ? new Dependencia() : (Dependencia) request.getAttribute("dependencia");%>
<%List<Solicitud> solicitudes = (List<Solicitud>) request.getAttribute("solicitudes") == null ? new ArrayList() : (List<Solicitud>) request.getAttribute("solicitudes");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Solicitudes Listado</title>
        <%@ include file="/Head.jsp" %>
    </head>
    <body>
        <%@ include file="/Header.jsp" %>
        <div class="container">
            <center>    
                <h3>Solicitudes - (<%=dependencia.getNombre()%>)</h3>
            </center>
            <br>
            <div class="input-group">
                <div class="col-xs-12">
                    <div class="container">
                        <div class="input-group-btn row">
                            <div class="col-lg-3" name="filtro_comprobante">
                                <form action="Solicitud/Filtro_Comprobante" method="POST">
                                    <h4>Buscar Comprobante</h4>
                                    <input name="comprobante" type="text" class="form-control" placeholder="No. Comprobante">
                                    <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                                </form>
                            </div>
                            <div class="col-md-1"></div>
                            <div class="col-lg-3" name="filtro_tipo">
                                <form action="Solicitud/Filtro_tipo" method="POST">
                                    <h4>Buscar por Tipo</h4>
                                    <input name="tipo" type="text" class="form-control" placeholder="Tipo">
                                    <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                                </form>
                            </div>
                            <div class="col-md-1"></div>
                            <div class="col-lg-3" name="filtro_tipo">
                                <form action="Solicitud/Filtro_estado" method="POST">
                                    <h4>Buscar por Estado</h4>
                                    <input name="estado" type="text" class="form-control" placeholder="Estado">
                                    <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Comprobante</th>
                        <th>Fecha</th>
                        <th>Tipo</th>
                        <th>Estado</th>
                        <th>Total</th>
                        <th>Precio</th>
                        <th>Mostrar</th>
                        <th>Editar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Solicitud sol : solicitudes) {
                    %>
                    <tr>
                        <td><%=sol.getComprobante()%></td>
                        <td><%=sol.getFechaString()%></td>
                        <td><%=sol.getDescripcionTipo()%></td>
                        <td><%=sol.getDescripcionEstado()%></td>
                        <td><%=sol.getCantidad()%></td>
                        <td><%=sol.getTotal()%></td>
                        <td> &nbsp;&nbsp; <a href="Solicitud/Solicitud_mostrar?ID=<%=sol.getID()%>"><img width=30px" src="Images/binoculars.png"/></a></td>
                        <td>&nbsp;<a href="Solicitud/Solicitud_editar?ID=<%=sol.getID()%>"><img width=30px" src="Images/editar.png"/></a></td>
                        <td>&nbsp;&nbsp;&nbsp;<a href="Solicitud/Solicitud_eliminar?ID=<%=sol.getID()%>"><img width=30px" src="Images/delete.png"/></a></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <center>
                <form action="Solicitud/Solicitud_crear">
                    <button type="submit" class="btn btn-primary"> Agregar </button>
                </form>
            </center>
        </div>
    </body>
</html>
