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
            <form class="form-horizontal" action="#">
                <center>
                    <div class="form-group"id="motivoRechazo">
                        <label for="motivoRechazo">Motivo de rechazo :</label>
                        <textarea class="form-control" rows="3" id="motivo" value="<%=solicitud.getMotivoRechazo()%>"></textarea>
                        <br><br>
                    </div>
                </center>
            </form>
            <form class="form-inline" action="#">
                <center>
                    <div class="form-group">
                        <button id="aceptar" type="button" class="btn btn-success" onclick="Aceptar(<%=solicitud.getID()%>)" > Aceptar </button>
                    </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="form-group">
                        <button id="rechazar" type="button" class="btn btn-danger" onclick="Rechazar()" ondblclick="Confirmar(<%=solicitud.getID()%>)"> Rechazar </button>
                    </div>
                </center>
            </form>
        </div>
        <script>
            function pageLoad(event) {
                $("#aceptar").click(Aceptar);
                $("#rechazar").click(Rechazar);
                HideTextArea();
            }
            function Aceptar(id_solicitud) {
                Solicitud = {
                    ID: id_solicitud,
                    motivoRechazo: $("#motivo").val()
                };
                $.ajax({type: "PUT",
                    url: "api/Solicitud/aceptar",
                    data: JSON.stringify(Solicitud),
                    contentType: "application/json",
                    success: Redirigir
                });
            }
            function Rechazar() {
                ShowTextArea();
            }
            function Confirmar(id_solicitud) {
                Solicitud = {
                    ID: id_solicitud,
                    motivoRechazo: $("#motivo").val()
                };
                $.ajax({type: "PUT",
                    url: "api/Solicitud/rechazar",
                    data: JSON.stringify(Solicitud),
                    contentType: "application/json",
                    success: Redirigir
                });
            }
            function ShowTextArea() {
                $("#motivoRechazo").show();
            }
            function HideTextArea() {
                $("#motivoRechazo").hide();
            }
            function Redirigir() {
                window.location.replace("Solicitud/Solicitud_listar");
            }
            $(pageLoad);
        </script>
    </body>
</html>