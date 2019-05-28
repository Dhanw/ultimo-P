<%-- 
    Document   : Funcionario_Listado
    Created on : 24/05/2019, 10:42:55 PM
    Author     : jorac
--%>

<%@page import="Activos.Models.Model_Funcionario"%>
<%@page import="java.util.List"%>
<%@page import="Activos.Logic.Funcionario"%>
<%@page import="Activos.Logic.Dependencia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Funcionarios listado</title>
        <script src="https://www.w3schools.com/lib/w3.js"></script>
         <%@ include file="/Head.jsp" %>
    </head>
    <body>
        <%@ include file="/Header.jsp" %>
       <div class="container">
            <center>
                <%
                    Usuario use = (Usuario) session.getAttribute("user");

                    Dependencia dep = (Dependencia) request.getAttribute("dependencia");
                    List<Model_Funcionario> funcionarios = (List<Model_Funcionario>) request.getAttribute("funcionarios");
                        
                %> 
                <h2>Funcionarios</h2>
                
                        
         <script>
             <%if(funcionarios.isEmpty()){%>
                 
                 alert("No existe.");
                 
             <%}%>
             
     
         </script>
         
            </center>
            <br>
            <div class="input-group">
                <div class="col-xs-12">
                    <div class="container">
                    <div class="input-group-btn row">
                        <div class="col-lg-3" name="filtro_funcionario">
                            <form action="Funcionario/Filtro_Funcionario_Nombre" method="POST">
                                <h4>Buscar por nombre completo</h4>
                                <input name="nombre" type="text" class="form-control" placeholder="Nombre"  autocomplete="off">
                                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                            </form>
                        </div>
                    </div>
                        </div>
                </div>
            </div>
            <table id="myTable" class="table table-hover">
                <thead>
                    <tr>
                        <th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(2)')" style="cursor:pointer">Cedula</th>
                        <th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(3)')" style="cursor:pointer">Nombre</th>
                        <th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(4)')" style="cursor:pointer">Puesto</th>
                        <th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(5)')" style="cursor:pointer">Dependencia</th>
                        <th  style="cursor:pointer">Eliminar</th>
                        <th  style="cursor:pointer">Editar</th>
                </thead>
                <tbody>
                    <%
                        for (Model_Funcionario f : funcionarios) {
                    %>
                    <tr class="item">
                        <td><%=f.getCedula()%></td>
                        <td><%=f.getNombre()%></td>
                       <% if(f.getPuesto() != null){ %>
                        <td><%=f.getPuesto().getNombre()%></td>
                        <td><%=f.getPuesto().getDependencia().getNombre()%></td>
                        <% } else{%>
                        <td>Sin asignar</td>
                        <td>Sin asignar</td>
                        <%} %>
                        <td><a href="Funcionario/Funcionario_eliminar?ID=<%=f.getId()%>"><img width=30px" src="Images/delete.png"/></a></td>
                        <% int p = 0; if(f.getPuesto() != null) p = f.getPuesto().getID(); %>
                        <td><a href="Funcionario/Funcionario_editar?ID=<%=f.getId()%>&cedula=<%=f.getCedula()%>&nombre=<%=f.getNombre()%>&PID=<%=p%>"><img width=30px" src="Images/content.png"/></a></td>
                    </tr>
                    <%} %>
                </tbody>
            </table>
        </div>
    </body>
</html>
