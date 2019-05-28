<%-- 
    Document   : Solicitud_Edicion
    Created on : 23/03/2019, 08:16:02 PM
    Author     : wizzard
--%>

<%@page import="Activos.Logic.Bien"%>
<%@page import="Activos.Logic.Solicitud"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Solicitud solicitud = (Solicitud) session.getAttribute("solicitud") == null ? new Solicitud() : (Solicitud) session.getAttribute("solicitud");%>
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
                <h2>Formulario Solicitud Bienes</h2>
            </center>
            <br> <br>
            <center>
                <form class="form-inline" action="Solicitud/Solicitud_guardar">
                    <div class="form-group">
                        <label for="Comprobante">Comprobante:</label>
                        <input type="text" class="form-control" id="comprobante" placeholder="No.Comprobante" name="comprobante" value="<%=solicitud.getComprobante()%>">
                    </div>
                    <div class="form-group">
                        <label for="Fecha">Fecha:</label>
                        <input type="text" class="form-control" id="fecha" placeholder="2019/01/01" name="fecha" value="<%=solicitud.getFecha().toString()%>">
                    </div>
                    <label for="Tipo_Comprobante">Tipo:</label>
                    <%if (solicitud.getTipo() == 0) {%>
                    <select class="form-control" id="tipo">
                        <option selected value="0" >Compra</option>
                        <option value="1">Donacion</option>
                    </select>
                    <%} else {%>
                    <select class="form-control" id="tipo">
                        <option value="0">Compra</option>
                        <option selected value="1">Donacion</option>
                    </select>
                    <%}%>
                    <button type="submit" class="btn btn-default">Guardar</button>
                </form>
            </center>
            <br> <br>
            <center>
                <h4>Ingresar un nuevo articulo</h4>
            </center>
            <form class="form-inline" action="Solicitud/Solicitud_agregar_bien">
                <div class="form-group">
                    <input type="text" class="form-control" id="descripcion" placeholder="Descripcion" name="descripcion" value="">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="marca" placeholder="Marca" name="marca" value="">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="modelo" placeholder="Modelo" name="modelo" value="">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="Precio_Unidad" placeholder="Precio Unidad" name="precio" value="">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="cantidad" placeholder="Cantidad" name="cantidad" value="">
                </div>
                <button type="submit" class="btn btn-default">Añadir</button>
            </form>
            <br> <br>
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
                        <td><a href="Solicitud/Solicitud_eliminar_bien?ID=<%=b.getID()%>">Eliminar</a></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
    </body>

</html>
