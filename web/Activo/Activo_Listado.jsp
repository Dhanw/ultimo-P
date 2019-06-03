<%-- 
    Document   : Activo_Listado
    Created on : 31/05/2019, 11:56:23 PM
    Author     : jorac
--%>

<%@page import="Activos.Logic.Categoria"%>
<%@page import="Activos.Logic.Activo"%>
<%@page import="java.util.List"%>
<%@page import="Activos.Logic.Dependencia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Funcionarios listado</title>
        <script src="https://www.w3schools.com/lib/w3.js"></script>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/jsbarcode/3.6.0/JsBarcode.all.min.js"></script>

        <%@ include file="/Head.jsp" %>
    </head>
    <body>
        <%@ include file="/Header.jsp" %>

        <div class="container">
            <center>
                <%
                    Usuario use = (Usuario) session.getAttribute("user");
                    Dependencia dep = (Dependencia) request.getAttribute("dependencia");
                    List<Activo> activos = (List<Activo>) request.getAttribute("activos");
                    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");

                %> 
                <h2>Activos <%=dep.getNombre()%></h2>


                <script>
                    <%if (activos.isEmpty()) {%>

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
                                <form action="Activo/Filtro_Categoria" method="POST">
                                    <h4>Buscar por categoria</h4>
                                    <select class="form-control" name="categoria">
                                        <% for (Categoria c : categorias) {%>
                                        <option value="<%=c.getDescripcion()%>"><%=c.getDescripcion()%></option>
                                        <%}%>
                                    </select>
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
                        <th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(2)')" style="cursor:pointer">Codigo</th>
                        <th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(3)')" style="cursor:pointer">Categoria</th>
                        <th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(3)')" style="cursor:pointer">Descripcion</th>
                        <th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(3)')" style="cursor:pointer">Puesto</th>
                        <th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(5)')" style="cursor:pointer">Encargado</th>
                        <th  style="cursor:pointer">Eliminar</th>
                        <th  style="cursor:pointer">Editar</th>
                        <th  style="cursor:pointer">codbarras</th>
                    </tr>

                </thead>
                <tbody id="cuerpo">
                    <%
                        for (Activo a : activos) {

                    %>




                    <tr class="item">
                        <td><%=a.getCodigo()%></td>
                        <td><%=a.getCategoria().getDescripcion()%></td>
                        <td> <%=a.getDescripcion()%></td>
                        <%if (a.getPuesto() != null) {%>
                        <td><%=a.getPuesto().getNombre()%></td>

                        <% } else { %>
                        <td>No asignado</td>
                        <% }%>

                        <%if (a.getPuesto().getFuncionario().getID() != 0) {%>
                        <td><%=a.getPuesto().getFuncionario().getNombre()%></td>

                        <% } else { %>
                        <td>No asignado</td>
                        <% }%>

                        <td><a href="Activo/Activo_eliminar?ID=<%=a.getID()%>"><img width="30px" src="Images/delete.png"/></a></td>
                        <td><a href="Activo/Activo_Peditar?ID=<%=a.getID()%>"><img width="30px" src="Images/editar.png"/></a></td>
                        <td><img src='Images/binoculars.png' onclick="Editar()"></td>
                    </tr>

                    <%}%>
                </tbody>
            </table>
        </div>
        <div class="w3-container">
            <div id="id02" class="w3-modal">
                <div class="w3-modal-content">
                    <div class="w3-container">
                        <span onclick="document.getElementById('id02').style.display = 'none'" class="button display-topright">&times;</span>
                        <div> <svg id="barcode"> </svg>  </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function Editar(id) {
                document.getElementById('id02').style.display = 'block';
                var element = document.getElementById("barcode");
                JsBarcode(element, "des5");
            }
        </script>


    </body>



</html>

