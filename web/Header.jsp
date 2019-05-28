<%-- 
    Document   : Header
    Created on : 23/03/2019, 02:50:50 PM
    Author     : wizzard
--%>
<%@page import="Activos.Logic.Usuario"%>
<% Usuario user = (Usuario) session.getAttribute("user");%> 
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">Sistema Gestor de Activos</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
                <%if (user != null) {%>
                <%if (user.getRol() == Usuario.ADMINISTRADOR_DEPENDENCIA) {%>
            <li><a href="Solicitud/Solicitud_listar">Listado</a></li>
            <li><a href="Solicitud/Solicitud_crear">Edicion</a></li>
            <li><a href="Departamento/Dependencias.jsp">Dependencias</a></li>
                <%}%>
                <%if (user.getRol() == Usuario.JEFE_RRH) {%>
            <li><a href="Funcionario/Funcionario_listar">Listado</a></li>
            <li><a href="Funcionario/Funcionario_Crear">Añadir Funcionario</a></li>
            <li><a href="Departamento/Dependencias.jsp">Dependencias</a></li>
             <%}%>
        </ul>
                
        <ul class="nav navbar-nav navbar-right">
            <li><a href="UserLogin/Logout"><span class="glyphicon glyphicon-log-in"></span>Logout</a></li>
        </ul>
        <%}%>
    </div>
</nav>
