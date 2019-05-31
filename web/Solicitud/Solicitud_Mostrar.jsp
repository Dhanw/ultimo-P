<%-- 
    Document   : Solicitud_Mostrar
    Created on : Mar 27, 2019, 4:56:28 PM
    Author     : Jose
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
                        <input type="text" class="form-control" id="fecha" placeholder="2019/01/01" name="fecha" value="<%=solicitud.getFechaString()%>" disabled>
                    </div>
                    <div class="form-group">
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
                        <label for="Tipo_Comprobante">Tipo:</label>
                        <select class="form-control" id="tipo" name="tipo" disabled>
                            <option <%=compra.toString()%> value="1"> Compra </option>
                            <option <%=donacion.toString()%> value="2"> Donacion </option>
                        </select>
                    </div>
                    <div class="form-group">
                        <%String recibida = "", verificar = "", rechazada = "", rotulacion = "", procesada = "";%>
                        <%switch (solicitud.getEstado()) {
                                case 1:
                                    recibida = "Selected";
                                    break;
                                case 2:
                                    verificar = "Selected";
                                    break;
                                case 3:
                                    rechazada = "Selected";
                                    break;
                                case 4:
                                    rotulacion = "Selected";
                                    break;
                                case 5:
                                    procesada = "Selected";
                                    break;
                                default:
                                    break;
                            }%>
                        <label for="Estado_Comprobante">Estado:</label>
                        <select class="form-control" id="estado" name="estado" disabled>
                            <option <%=recibida.toString()%> value="1"> Recibida </option>
                            <option <%=verificar.toString()%> value="2"> Por Verificar </option>
                            <option <%=rechazada.toString()%> value="3"> Rechazada </option>
                            <option <%=rotulacion.toString()%> value="4"> Rotulacion </option>
                            <option <%=procesada.toString()%> value="5"> Donacion </option>
                        </select>
                    </div>
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
            <center>
                <a href="Solicitud/Solicitud_listar"><button class="btn btn-primary"> Regresar </button></a>
            </center>
        </div>
    </body>
</html>
