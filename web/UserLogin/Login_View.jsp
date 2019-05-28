<%-- 
    Document   : Login_View
    Created on : 23/03/2019, 03:00:40 PM
    Author     : wizzard
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar Sesion</title>
        <%@ include file="/Head.jsp" %>
    </head>
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
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input id="usuario" type="text" class="form-control" name="cuenta" placeholder="Codigo">
                            </div>
                        </div>
                    </div>
                    <div class="input-group">
                        <div class="col-xs-12">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input id="password" type="password" class="form-control" name="password" placeholder="Contraseña">
                            </div>
                        </div>
                    </div>
                </center>
                <br>
                <div class="form-group">
                    <center>
                        <input type="submit" id="sbt_login" name="sbt_login" value="Iniciar Sesion">
                    </center>
                </div>
            </form>
        </div>
    </body>
</html>
