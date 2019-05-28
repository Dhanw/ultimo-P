<%-- 
    Document   : Funcionario_Mostrar
    Created on : 24/05/2019, 10:43:08 PM
    Author     : jorac
--%>

<%@page import="java.util.List"%>
<%@page import="Activos.Logic.Puesto"%>
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
                    String id = (String)request.getParameter("ID");
                    String nombre = (String) request.getAttribute("nombre");
                    String cedula = (String) request.getAttribute("cedula");
                    Puesto actual = (Puesto) request.getAttribute("puesto");
                    List<Puesto> todos = (List<Puesto>) request.getAttribute("puestos");
                   
                %>
                <h2>Editar Funcionario</h2>
                <div class="row">
                    <br><br>
                    <div class="col-md-12">

                        <form method="GET" class="form-inline" role="form" action="Funcionario/Editar_Funcionario" name="formulario">
                            <table class="form_func" border="2px">
                                <caption><center><h4>Ingresa los datos del nuevo funcionario</h4></center></caption>
                                <tr>
                                    <td><label for="cedula">Identificación : </label></td>

                                    <td>
                                        <input type="text" name="cedula" value="<%=cedula%>" class="form-control" autocomplete="off" readonly>
                                    </td>
                                </tr>


                                <tr>
                                    <td><label for="nombre">Nombre completo : </label></td>

                                    <td>
                                        <input type="text" name="nombre" value="<%=nombre%>" class="form-control" autocomplete="off">
                                    </td>
                                </tr>


                                <tr>
                                    <td><label for="puesto">Puesto : </label></td>

                                    <td>
                                        <select class="form-control" name="puesto">
                                            <%if(actual!=null){%>
                                            <option value="<%=actual.getID()%>"active><%=actual.getNombre()%> </option>
                                            <%}%>
                                            <%for (Puesto p : todos) {%>
                                            <option value="<%=p.getID()%>"><%=p.getNombre()%> (<%=p.getDependencia().getUbicacion()%>) </option>
                                            <% }%>
                                            <%if(actual == null){%>
                                            <option value="0" selected>Ninguno</option>
                                            <%} else{%>
                                            <option value="0">Ninguno</option>
                                            <%}%>
                                        </select>
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="2">
                                        <input name="ID" value="<%=id%>" hidden="">
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
