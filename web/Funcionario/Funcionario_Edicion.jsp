<%--
    Document   : Funcionario_Edicion
    Created on : 24/05/2019, 10:43:16 PM
    Author     : jorac
--%>

<%@page import="Activos.Logic.Puesto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="Activos.Logic.Dependencia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Funcionarios Edicion</title>
        <%@ include file="/Head.jsp" %>
    </head>
    <body>
        <%@ include file="/Header.jsp" %>
        <div class="container">
            <center>
                <%
                    Usuario use = (Usuario) session.getAttribute("user");

                    Dependencia dep = (Dependencia) request.getAttribute("dependencia");
                    List<Puesto> puestos = (List<Puesto>) request.getAttribute("puestos");

                %>
                <h2>Agregar Funcionario - (<%=dep.getNombre()%>)</h2>
                <div class="row">
                    <br><br>
                    <div class="col-md-12">

                        <form method="POST" class="form-inline" role="form" action="Funcionario/Agregar_Funcionario" name="formulario">
                            <table class="form_func" border="2px">
                                <caption><center><h4>Ingresa los datos del nuevo funcionario</h4></center></caption>
                                <tr>
                                    <td><label for="cedula">Identificación : </label></td>

                                    <td>
                                        <input type="text" name="cedula" class="form-control" autocomplete="off">
                                    </td>
                                </tr>


                                <tr>
                                    <td><label for="nombre">Nombre completo : </label></td>

                                    <td>
                                        <input type="text" name="nombre" class="form-control" autocomplete="off">
                                    </td>
                                </tr>


                                <tr>
                                    <td><label for="puesto">Puesto : </label></td>

                                    <td>
                                        <select class="form-control" name="puesto">
                                            <%for (Puesto p : puestos) {%>
                                            <option value="<%=p.getID()%>"><%=p.getNombre()%> (<%=p.getDependencia().getUbicacion()%>) </option>
                                            <% }%>
                                            <option value="0">Ninguno</option>
                                        </select>
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="2">
                                        <input type="submit" name="enviar" value="ENVIAR">
                                    </td>

                                </tr>
                            </table>
                        </form>


                    </div>


                </div>

            </center>
        </div>
    </body>
</html>
