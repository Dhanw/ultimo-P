<%-- 
    Document   : Activo_mostrar
    Created on : 01/06/2019, 08:03:04 PM
    Author     : jorac
--%>

<%@page import="Activos.Logic.Activo"%>
<%@page import="Activos.Logic.Puesto"%>
<%@page import="java.util.List"%>
<%@page import="Activos.Logic.Categoria"%>
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
                    Activo activo = (Activo) request.getAttribute("activo");
                    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
                    List<Puesto> puestos = (List<Puesto>) request.getAttribute("puestos");


                %>
                <h2>Editar Activo</h2>

                <div class="row">
                    <br><br>
                    <div class="col-md-12">

                        <form method="GET" class="form-inline" role="form" action="Activo/Activo_Editar" name="formulario">
                            <table class="form_func" border="2px">
                                <caption><center><h4>Ingresa los datos del nuevo Activo</h4></center></caption>
                                <tr>
                                    <td><label for="cedula">Consecutivo : </label></td>

                                    <td>
                                        <input type="text" name="ID" value="<%=activo.getID()%>" class="form-control" autocomplete="off" readonly>
                                    </td>
                                </tr>


                                <tr>
                                    <td><label for="nombre">Codigo : </label></td>

                                    <td>
                                        <input type="text" name="codigo" value="<%=activo.getCodigo()%>" class="form-control" autocomplete="off" readonly>
                                    </td>
                                </tr>

                                <tr>
                                    <td><label for="nombre">Descripcion : </label></td>

                                    <td>
                                        <input type="text" name="descripcion" value="<%=activo.getDescripcion()%>" class="form-control" autocomplete="off">
                                    </td>
                                </tr>


                                <tr>
                                    <td><label for="nombre">Categoria : </label></td>

                                    <td>
                                        <select class="form-control" name="categoria">
                                            <%if (activo.getCategoria() != null) {%>
                                            <option value="<%=activo.getCategoria().getID()%>"active><%=activo.getCategoria().getDescripcion()%> </option>
                                            <%}%>
                                            <%for (Categoria c : categorias) {%>
                                            <option value="<%=c.getID()%>"><%=c.getDescripcion()%></option>
                                            <% }%>
                                        </select>
                                    </td>
                                </tr>



                                <tr>
                                    <td><label for="puesto">Encargado : </label></td>

                                    <td>
                                        <select class="form-control" name="puesto">
                                            <%if (activo.getPuesto() != null) {%>
                                            <option value="<%=activo.getPuesto().getID()%>"active><%=activo.getPuesto().getNombre()%>
                                             <%if(activo.getPuesto().getFuncionario().getID() != 0){ %> (<%=activo.getPuesto().getFuncionario().getNombre()%>)<%}
                                            else{%>(Nadie)<%}%>
                                            </option>
                                            <%}%>
                                            <%for (Puesto p : puestos) {%>
                                            <option value="<%=p.getID()%>"><%=p.getNombre()%>
                                            <%if(p.getFuncionario().getID() != 0){ %> (<%=p.getFuncionario().getNombre()%>)<%}
                                            else{%>(Nadie)<%}%>
                                            
                                            </option>
                                            <% }%>
                                       
                                        </select>
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="2">
                                        <input name="ID_viejo" value="<%=activo.getID()%>" hidden="">
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
