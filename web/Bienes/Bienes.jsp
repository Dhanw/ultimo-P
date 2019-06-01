<%-- 
    Document   : Departamentos
    Created on : May 25, 2019, 1:41:58 AM
    Author     : Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Categorias</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <%@ include file="/Head.jsp" %>
    </head>
    <body>
        <%@ include file="/Header.jsp" %>
        <div class="container">
            <!--Contenedor Principal commit del joracha -->
            <div class="input-group">
                <div class="col-xs-12">
                    <div class="container">
                        <div class="input-group-btn row">
                            <div class="col-lg-3" name="filtro_comprobante">
                                <form class="form-inline"   method="POST">
                                    <h4> Categoria </h4>
                                    <input name="tipo" type="text" class="form-control" id="Buscar" placeholder="ID">
                                    <input type="button" class="btn btn-default" value="Buscar" onclick="buscarPorEdad()">
                                </form>
                            </div>

                            <div class="col-md-1"></div>
                            <div class="col-lg-3" name="filtro_tipo">
                                <form class="form-inline" method="POST">

                                    <div class="col-md-1"></div>
                                    <div class="col-lg-3" name="filtro_tipo">
                                        <div class="form-inline" method="POST">
                                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">Agregar</button>
                                        </div>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <!--
                int ID;
                String descripcion;
                String marca;
                String modelo;  
            <input type="button" class="btn btn-default" value="cargar" onclick="verDependencias()"> -->
            <table class="table table-hover">
                <thead><tr><th>Id</th><th>Descripcion</th><th>Edit</th><th>Delete</th></tr></thead>
                <tbody id="contenido">
                </tbody>
            </table>   
            <!--termina pop up -->

            <!--<input type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Agregar</input> -->

            <!-- Modal -->
            <div id="myModal" class="modal fade" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Ingresar Bien</h4>
                        </div>
                        <div class="modal-body">
                            <form  class="form-inline" method="POST"> <!--inicio -->

                                <div class="form">
                                    <input name="descripcion1"  id="descripcion1" type="text" class="form-control"  placeholder="nombre" >
                                </div>
                                <br>

                                <div class="form-group">
                                    <input type="button" class="btn btn-default" value="Guardar" onclick="crearBienes()">
                                </div>
                                <br>
                            </form> <!-- Fin  -->
                        </div>
                    </div>

                </div>
            </div>


            <!-- Modal -->
            <div id="myModal1" class="modal fade" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">

                        <div class="modal-header">

                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Actualizar Dependencia</h4>
                        </div>
                        <div class="modal-body">

                            <form  class="form-inline" method="POST"> <!--inicio -->
                                <div class="form">
                                    <input name="idf2"  id="idf2" type="text" class="form-control"  placeholder="id" readonly>
                                </div>
                                <div class="form">
                                    <input name="descripcion2"  id="descripcion2" type="text" class="form-control"  placeholder="nombre" >
                                </div>
                                <br>
                                <div class="form-group">
                                    <input type="button" class="btn btn-default" value="Actualizar" onclick="actualizar()">
                                </div>
                                <br>
                            </form> 
                            <!-- Fin  -->

                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <script src="js/jquery.js" type="text/javascript"></script>

        <script>
                function loaded(event){
                    verDependencias();
         
              }
            function removeRows1() {
               $(".opt").remove();
           }
           
            function verDependencias() {
                $.ajax({
                    type: "GET",
                    url: "CargarCategorias",
                    dataType: "json",
                    success:
                            function (obj) {
                                cargarDependencias(obj);
                                },
                    error:
                            function (status) {
                                
                            }
                });
            }
            
            function cargarDependencias(dependenciasList) {
                removeRows();
                for (var i = 0; i < dependenciasList.length; i++) {
                    var td = $("<tr class='rowContenido'>");
                    td.html(
                            "<td>" + dependenciasList[i].ID + "</td>" +
                            "<td>" + dependenciasList[i].descripcion + "</td>"+
                            "<td><img src='Images/binoculars.png' onclick='Editar(\"" + dependenciasList[i].ID + "\");'></td>" +
                            "<td><img src='Images/delete.png' onclick='Eliminar(\"" + dependenciasList[i].ID + "\");'></td></tr>"
                            );
                    $("#contenido").append(td);
                }
            }
            
          
    
            
            function Editar(id) {
                
                var edad = id;
                $.ajax({
                    type: "POST",
                    url: "EditarBienes",
                    data: JSON.stringify(id),
                    dataType: "json",
                    success:
                            function (obj) {
                                mostrar(obj);
                            },
                    error:
                            function (status) {
                                
                            }
                });
                $("#myModal1").modal('show');
            }
            function mostrar(per) {
                $("#idf2").val(per.ID);
                $("#descripcion2").val(per.descripcion);

                
            }
            function actualizar() {
                
                Categoria = {
                    ID: $("#idf2").val(),
                    descripcion: $("#descripcion2").val()
                    };
                clean();
                $.ajax({type: "POST",
                    url: "ActualizarBienes",
                    data: JSON.stringify(Categoria),
                    dataType: "json",
                    success: verDependencias(),
                   error: function (jqXHR) {alert(errorMessage(jqXHR.status));}
               });
 
           xd();
           }
            function xd() {
                                $('#myModal1').modal('hide')
                                verDependencias();
                          }
             function xd2() {
                                $('#myModal').modal('hide')
    
                          }

           
           
           function Eliminar(id) {
               var edad = id;
               $.ajax({
                   type: "POST",
                   url: "EliminarBienes",
                   data: JSON.stringify(id),
                   dataType: "json",
                   success:
                           function (obj) {
                               
                           },
                   error:
                           function (status) {
                               
                           }
               });
               verDependencias();
           }
           
           function removeRows() {
               $(".rowContenido").remove();
           }
           
           function clean() {
               $("#nombre1").val("");

           }
           
            function clean1() {
               $("#descripcion1").val("");

           }
           
           
           function crearBienes( ) {
               Categoria = {
                   descripcion: $("#descripcion1").val()
               };
               clean1(); //limpia los campos del form
               $.ajax({
                   type: "POST",
                   url: "AgregarBien",
                   data: JSON.stringify(Categoria),
                   dataType: "json",
                   success:
                            function (status) {
                               
                           },
                   error:
                           function (status) {
                               
                           }
               });
               verDependencias(); //recarga la tabla con el dato nuevo, una forma de hacerlo
           }
           function hideForm() {
               $("#div2").hide();
           }
           function showForm() {
               $("#div2").show();
           }
            document.addEventListener("DOMContentLoaded",loaded);
            
        </script>
    </body>
</html>