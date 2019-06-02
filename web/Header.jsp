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
            <li class="active"><a href="index.jsp"> Principal </a></li>
                <%if (user != null) {%>
                <%if (user.getRol() == Usuario.ADMINISTRADOR_DEPENDENCIA) {%>
            <li><a href="Solicitud/Solicitud_listar"> Solicitudes </a></li>
            <li><a href="Departamento/Dependencias.jsp">Dependencias</a></li>
            <li><a href="Bienes/Bienes.jsp">CategoriaBienes</a></li>
            <li><a href="Registrador/RegistrarBien.jsp">Registrar bien</a></li>
            <li><a href="Activos/Prepare_Listado">Activos</a></li>
                <%}%>
                <%if (user.getRol() == Usuario.JEFE_RRH) {%>
            <li><a href="Funcionario/Funcionario_listar">Listado</a></li>
            <li><a href="Funcionario/Funcionario_Crear">Funcionario</a></li>
            <li><a href="Departamento/Dependencias.jsp">Dependencias</a></li>
            <li><a href="Bienes/Bienes.jsp">Bienes</a></li>
            <li><a href="Activos/Prepare_Listado">Activos</a></li>
                <%}%>
                <%if (user.getRol() == Usuario.SECRETARIA_OCCB) {%>
            <li><a href="Solicitud/Solicitud_listar"> Solicitudes </a></li>
            <li><a href="Bienes/Bienes.jsp">Bienes</a></li>
                <%}%>
                <%if (user.getRol() == Usuario.JEFE_OCCB) {%>
                <%}%>
                <%if (user.getRol() == Usuario.REGISTRADOR_BIENES) {%>
                <%}%>
                <%if (user.getRol() == Usuario.JEFE_OCBB_RHH) {%>
                <%}%>
                <%}%>
        </ul>
        <%if (user != null) {%>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="UserLogin/Logout"><span class="glyphicon glyphicon-log-in"></span> Logout </a></li>
        </ul>
        <%} else {%>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="UserLogin/PrepareLogin"><span class="glyphicon glyphicon-log-in"></span> Login </a></li>
        </ul>
        <%}%>
    </div>
</nav>
