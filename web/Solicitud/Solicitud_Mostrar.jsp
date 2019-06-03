<%-- 
    Document   : Solicitud_Mostrar
    Created on : Mar 27, 2019, 4:56:28 PM
    Author     : Jose
--%>

<%@page import="static Activos.Logic.Usuario.ADMINISTRADOR_DEPENDENCIA"%>
<%@page import="static Activos.Logic.Usuario.JEFE_OCCB"%>
<%@page import="static Activos.Logic.Usuario.JEFE_RRH"%>
<%@page import="static Activos.Logic.Usuario.REGISTRADOR_BIENES"%>
<%@page import="static Activos.Logic.Usuario.SECRETARIA_OCCB"%>
<%@page import="Activos.Logic.Funcionario"%>
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
<% Usuario predeterminado = (Usuario) session.getAttribute("user");%>
<%if (predeterminado == null) {
        request.getRequestDispatcher("/UserLogin/PrepareLogin").forward(request, response);
    }%>
<% switch (predeterminado.getRol()) {
        case ADMINISTRADOR_DEPENDENCIA:
            break;
        case JEFE_RRH:
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            break;
        case SECRETARIA_OCCB:
            break;
        case JEFE_OCCB:
            break;
        case REGISTRADOR_BIENES:
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            break;
    }%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Solicitudes Mostrar</title>
        <%@ include file="/Head.jsp" %>
        <script src="Javascript/jquery-3.4.1.js" type="text/javascript"></script>
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
            <%if (user.getRol() == Usuario.JEFE_OCCB || user.getRol() == Usuario.SECRETARIA_OCCB) {%>
            <center>
                <form class="form-inline" action="#">
                    <div class="form-group">
                        <label for="Registrador">Registrador:</label>
                        <select class="form-control" id="registrador" name="registrador" disabled>
                            <%if (solicitud.getRegistrador().getID() != 0) {%>
                            <option selected value="<%=solicitud.getRegistrador().getID()%>"> <%=solicitud.getRegistrador().getNombre()%> </option>
                            <%}%>
                            <option value="0"> Sin Asignar </option>
                        </select>
                    </div>
                </form> 
            </center>
            <%}%>
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
                    <%for (Bien b
                                : solicitud.getBienes()) {%>
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
            <form class="form-horizontal" action="#">
                <center>
                    <div class="form-group"id="motivoRechazo">
                        <label for="motivoRechazo"> Motivo de rechazo: </label>
                        <textarea class="form-control" rows="3" id="motivo" value="<%=solicitud.getMotivoRechazo()%>" disabled></textarea>
                        <br><br>
                    </div>
                </center>
            </form>
            <center>
                <a href="Solicitud/Solicitud_listar"><button class="btn btn-primary"> Regresar </button></a>
            </center>
        </div>
        <script>
            function pageLoad(event) {
                HideTextArea();
                if (tieneMotivo()) {
                    ShowTextArea();
                }
            }
            function ShowTextArea() {
                $("#motivoRechazo").show();
            }
            function HideTextArea() {
                $("#motivoRechazo").hide();
            }
            function tieneMotivo() {
                var motivo = $("#motivo").val();
                console.log(motivo);
                return $("#motivo").val().length != 0;
            }
            $(pageLoad);
        </script>
    </body>
</html>
