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
        <title>JSP Page</title>
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
                                <h4>Buscar Dependencia</h4>
                                   <input name="tipo" type="text" class="form-control" id="Buscar" placeholder="ID">
                                   <input type="button" class="btn btn-default" value="Buscar" onclick="buscarPorEdad()">
                            </form>
                        </div>
                        
                        <div class="col-md-1"></div>
                        <div class="col-lg-3" name="filtro_tipo">
                            <form class="form-inline" method="POST">
                                <h4>Agregar</h4>
                                 <input type="button" class="btn btn-default" value="Agregar" onclick="AgregarDependencias()">
                            </form>
                        </div>
                        
                        <div class="col-md-1"></div>
                        <div class="col-lg-3" name="filtro_tipo">
                            <form class="form-inline" method="POST">
                               
                                  <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Agregar</button>
                            </form>
                        </div>
                        
                    </div>
                </div>
            </div>
       </div>



            <!--Pop up del actualizar -->
            <div class="w3-container">
                <div id="id01" class="w3-modal">
                    <div class="w3-modal-content">
                        <div class="w3-container">
                            <span onclick="document.getElementById('id01').style.display = 'none'" class="w3-button w3-display-topright">&times;</span>
                            <form  class="form" method="POST">
                                <h4>Actualizar Dependencia</h4>
                                <div class="form-group">
                                    <input name="idf1"  id="idf1" type="text" class="form-control"  placeholder="id" >
                                </div>
                                <br>
                                <div class="form-group">
                                    <input name="nombre1"  id="nombre1" type="text" class="form-control"  placeholder="nombre" >
                                </div>
                                <br>
                                <div class="form-group">
                                    <input name="ubicacion1" id="ubicacion1" type="text" class="form-control" placeholder="ubicacion" >
                                </div>  
                                <br>
                                  <div class="form-group">
                                     <select  id="mySelect1">  
                                     </select>
                                </div>
                                <div class="form-group">
                                    <input type="button" class="btn btn-default" value="actualizar" onclick="actualizar()">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!--Pop up del crear -->

            <div class="w3-container">
                <div id="id02" class="w3-modal">
                    <div class="w3-modal-content">
                        <div class="w3-container">
                            <span onclick="document.getElementById('id02').style.display = 'none'" class="w3-button w3-display-topright">&times;</span>
                            <form  class="form-inline" method="POST"> <!--inicio -->
                                <h4>Ingresar Dependencia</h4>
                                <br>
                                <div class="form">
                                    <input name="nombre2"  id="nombre2" type="text" class="form-control"  placeholder="nombre" >
                                </div>
                                <br>
                                <div class="form-group">
                                    <input name="ubicacion2" id="ubicacion2" type="text" class="form-control" placeholder="ubicacion" >
                                </div>  
                                <br>
                                <div class="form-group">
                                    <input name="Funcionario2" id="idf2" type="text" class="form-control"  placeholder="id" >
                                </div>
                                <br>
                                  <div class="form-group">
                                     <select  id="mySelect" class="form-control"  placeholder="id" name="mySelect">  
                                     </select>
                                </div>
                                <br>
                                <div class="form-group">
                                    <input type="button" class="btn btn-default" value="Guardar" onclick="crearDependencia()">
                                    
                                </div>
                                <br>
                            </form> <!-- Fin  -->
                        </div>
                    </div>
                </div>
            </div>
            
            
            <!--termina pop up -->
            
            <table class="table table-hover">
                <thead><tr><th>Id</th><th>Nombre</th><th>Ubicacion</th><th>Edit</th><th>Delete</th></tr></thead>
                <tbody id="contenido">
                </tbody>
            </table>   
                 <!--termina pop up -->
                 
                 <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Agregar</button>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Ingresar Dependencia</h4>
      </div>
      <div class="modal-body">
         <form  class="form-inline" method="POST"> <!--inicio -->
                               
                                <div class="form">
                                    <input name="nombre2"  id="nombre2" type="text" class="form-control"  placeholder="nombre" >
                                </div>
                                <br>
                                <div class="form-group">
                                    <input name="ubicacion2" id="ubicacion2" type="text" class="form-control" placeholder="ubicacion" >
                                </div>  
                                <br>
                                <div class="form-group">
                                    <input name="Funcionario2" id="idf2" type="text" class="form-control"  placeholder="id" >
                                </div>
                                <br>
                                  <div class="form-group">
                                     <select  id="mySelect" class="form-control"  placeholder="id" name="mySelect">  
                                     </select>
                                </div>
                                <br>
                                <div class="form-group">
                                    <input type="button" class="btn btn-default" value="Guardar" onclick="crearDependencia()">
                                </div>
                                <br>
                            </form> <!-- Fin  -->
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
                 
  <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal1">Editar</button>


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
                                    <input name="nombre1"  id="nombre1" type="text" class="form-control"  placeholder="nombre" >
                                </div>
                                <br>
                                <div class="form-group">
                                    <input name="ubicacion1" id="ubicacion1" type="text" class="form-control" placeholder="ubicacion" >
                                </div>  
                                <br>
                                <div class="form-group">
                                    <input name="Funcionario1" id="idf1" type="text" class="form-control"  placeholder="id" >
                                </div>
                                <br>
                                  <div class="form-group">
                                     <select  id="mySelect1" class="form-control"  placeholder="id" name="mySelect">  
                                     </select>
                                </div>
                                <br>
                                <div class="form-group">
                                    <input type="button" class="btn btn-default" value="Guardar" onclick="crearDependencia()">
                                </div>
                                <br>
                            </form> <!-- Fin  -->
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>

            
        </div>
        <script src="js/jquery.js" type="text/javascript"></script>

        <script>
              function loaded(event){
                  verFuncionarios();
                  verDependencias();
                  verFuncionarios1();
                  
              }
            
  
  
            function AgregarDependencias(){
            document.getElementById('id02').style.display = 'block';
            }
            function verFuncionarios1() {
                $.ajax({
                    type: "GET",
                    url: "CargarFuncionarios",
                    dataType: "json",
                    success:
                            function (obj1) {
                                myFunction1(obj1);
                                },
                    error:
                            function (status) {
                                
                            }
                });
            }
            
            
            function myFunction1( FuncionarioList) {
             var x = document.getElementById("mySelect1");  
             for(var i=0;i<FuncionarioList.length;i++){
             var z = document.createElement("option");
             z.setAttribute("class", "opt");
             z.setAttribute("value", FuncionarioList[i].ID+"");
             var t = document.createTextNode(FuncionarioList[i].ID +"- "+FuncionarioList[i].nombre);
             z.appendChild(t);
             x.appendChild(z);
             document.body.appendChild(x);
             }
            }
            
            function myFunction( FuncionarioList) {
             var x = document.getElementById("mySelect");  
             for(var i=0;i<FuncionarioList.length;i++){
             var z = document.createElement("option");
             z.setAttribute("class", "opt");
             z.setAttribute("value", FuncionarioList[i].ID+"");
             var t = document.createTextNode(FuncionarioList[i].ID +"- "+FuncionarioList[i].nombre);
             z.appendChild(t);
             x.appendChild(z);
             document.body.appendChild(x);
             }
            }
            
            function removeRows1() {
               $(".opt").remove();
           }
            function cargarFuncionarios(FuncionarioList) {
             removeRows1();
                for (var i = 0; i < FuncionarioList.length; i++) {
                    var td = $("<tr class='opt'>");
                    td.html(
                            "<td>" + FuncionarioList[i].ID + "</td>" +
                            "<td>" + FuncionarioList[i].nombre + "</td></tr>"
                            );
                    $("#contenido1").append(td);
                }
            }
            
            function verFuncionarios() {
                $.ajax({
                    type: "GET",
                    url: "CargarFuncionarios",
                    dataType: "json",
                    success:
                            function (obj1) {
                                myFunction(obj1);
                                },
                    error:
                            function (status) {
                                
                            }
                });
            }
            
            
            function verDependencias() {
                $.ajax({
                    type: "GET",
                    url: "CargarDependencias",
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
            function buscarPorEdad() {
                var edad = $("#Buscar").val();
                $.ajax({
                    type: "POST",
                    url: "BuscarDependencia",
                    data: JSON.stringify(edad),
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
                            "<td>" + dependenciasList[i].nombre + "</td>" +
                            "<td>" + dependenciasList[i].ubicacion + "</td>" +
                            "<td><img src='Images/binoculars.png' onclick='Editar(\"" + dependenciasList[i].ID + "\");'></td>" +
                            "<td><img src='Images/delete.png' onclick='Eliminar(\"" + dependenciasList[i].ID + "\");'></td></tr>"
                            );
                    $("#contenido").append(td);
                }
            }
            
            
            function Editar(dependencia) {
                var edad = dependencia;
                $.ajax({
                    type: "POST",
                    url: "EditarDependencia",
                    data: JSON.stringify(edad),
                    dataType: "json",
                    success:
                            function (obj) {
                                mostrar(obj);
                            },
                    error:
                            function (status) {
                                
                            }
                });
                document.getElementById('id01').style.display = 'block';
            }
            function mostrar(per) {
                $("#idf1").val(per.ID);
                $("#nombre1").val(per.nombre);
                $("#ubicacion1").val(per.ubicacion);
            }
            function actualizar() {
                Dependencia = {
                    ID: $("#idf1").val(),
                    nombre: $("#nombre1").val(),
                    ubicacion: $("#ubicacion1").val()
                };
                clean();
                $.ajax({type: "POST",
                    url: "ActualizarDependencia",
                    data: JSON.stringify(Dependencia),
                    dataType: "json",
                    success: 
                            function () {
                                
                            },
                   error: function (jqXHR) {alert(errorMessage(jqXHR.status));}
               });
               verDependencias();
           }
           function Eliminar(id) {
               var edad = id;
               $.ajax({
                   type: "POST",
                   url: "EliminarDependencia",
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
               $("#ubicacion1").val("");
               $("#idf1").val("");
           }
            function clean1() {
               $("#nombre2").val("");
               $("#ubicacion2").val("");
               $("#idf2").val("");
           }
           function crearDependencia() {
               Dependencia = {
                   nombre: $("#nombre2").val(),
                   ubicacion: $("#ubicacion2").val(),
                   id: $("#idf2").val()
               };
               clean1(); //limpia los campos del form
               $.ajax({
                   type: "POST",
                   url: "AgregarDependencia",
                   data: JSON.stringify(Dependencia),
                   dataType: "json",
                   success:
                           function (obj) {
                               
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
