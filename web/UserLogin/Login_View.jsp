<%-- 
    Document   : Login_View
    Created on : 23/03/2019, 03:00:40 PM
    Author     : wizzard
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar Sesion</title>
        <%@ include file="/Head.jsp" %>
    </head>
    <% Usuario usuario = (Usuario) request.getAttribute("user");%>
    <% Map<String, String> list_errors = (Map<String, String>) request.getAttribute("errors"); %>        
    <% Map<String, String[]> user_values = (list_errors == null) ? this.getUserValues(usuario) : request.getParameterMap();%>
    <body>
        <%@ include file="/Header.jsp" %>
        <div class="container">
            <br>
            <br> <br>
            <center>
                <img class="icono" src="Images/login.png" alt="Imagen Login"/>
            </center>
            <br>
            <form method ="POST" name="iniciar_sesion" action="UserLogin/Login">
                <center>
                    <div class="input-group">
                        <div class="col-xs-12">
                            <div class="input-group <%=check_Errors("cuenta", list_errors)%>">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input id="usuario" type="text" class="form-control" name="cuenta" placeholder="Codigo" value="<%=getValue("cuenta", user_values)%>">
                            </div>
                        </div>
                    </div>
                    <div class="input-group">
                        <div class="col-xs-12">
                            <div class="input-group <%=check_Errors("password", list_errors)%>">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input id="password" type="password" class="form-control" name="password" placeholder="Contraseña" value="<%=getValue("password", user_values)%>">
                            </div>
                        </div>
                    </div>
                </center>
                <br>
                <div class="form-group">
                    <center>
                        <button type="submit" class="btn btn-success"> Iniciar Sesion </button>
                    </center>
                </div>
            </form>
        </div>
    </body>
</html>
<%!private Map<String, String[]> getUserValues(Usuario model) {
        Map<String, String[]> values = new HashMap();
        values.put("cuenta", new String[]{model.getCuenta()});
        values.put("password", new String[]{model.getPassword()});
        return values;
    }%>
<%!private String getValue(String field, Map<String, String[]> user_values) {
        return user_values.get(field)[0];
    }%>
<%!private String check_Errors(String field, Map<String, String> list_errors) {
        if (list_errors != null) {
            if (list_errors.get(field) != null) {
                if (list_errors.get(field).equals("RIGHT")) {
                    return "has-success";
                }
                if (list_errors.get(field).equals("WRONG")) {
                    return "has-error";
                }
                if (list_errors.get(field).equals("EMPTY")) {
                    return "has-warning";
                }
            }
        }
        return "";
    }%>
