<%-- 
    Document   : RegistrarBien
    Created on : May 25, 2019, 1:41:58 AM
    Author     : Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>RegistrarBien</title>
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
                                    <h4> Registrar Bienes </h4>
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
                                            <!--<button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">Agregar</button> -->
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
    double precio;
    int cantidad;
    Solicitud solicitud;
            <input type="button" class="btn btn-default" value="cargar" onclick="verDependencias()"> -->
            <table class="table table-hover">
                <thead><tr><th>Id</th><th>descripcion</th><th>marca</th><th>modelo</th><th>precio</th><th>cantidad</th><th>registrar</th></tr></thead>
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
            <button type="button" class="btn btn-info " data-toggle="modal" data-target="#myModal1">Editar</button> 

            <!-- Modal
                 int ID;
                 String codigo;
                 Categoria categoria;
                 String descripcion;
                 Puesto puesto;
            -->
            <div id="myModal1" class="modal fade" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">

                        <div class="modal-header">

                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Agregar Activo</h4>
                        </div>
                        <div class="modal-body">

                            <form  class="form-inline" method="POST"> <!--inicio -->
                                <div class="form">
                                    <input name="codigo2"  id="codigo2" type="text" class="form-control"  placeholder="codigo" readonly>
                                </div>
                                 <div class="form">
                                    <input name="descripcion2"  id="descripcion2" type="text" class="form-control"  placeholder="descripcion" >
                                </div>
                                 <div class="form">
                                     <select  id="mySelect" class="form-control"  placeholder="id" name="mySelect">  
                                     </select>
                                </div>
                                <div class="form">
                                     <select  id="mySelect1" class="form-control"  placeholder="id" name="mySelect1">  
                                     </select>
                                </div>
                                <br>
                                <div class="form-group">
                                    <input type="button" class="btn btn-default" value="Guardar" onclick="crearActivo()">
                                </div>
                                <br>
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
                    verCategorias();
                    verPuestos();
         
              }
            function removeRows1() {
               $(".opt").remove();
           }
           
            function verDependencias() {
                $.ajax({
                    type: "GET",
                    url: "CargarBienes",
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
            function Funcio_Depe() { // carga el funcionario del 
                 var id = document.getElementById("mySelect1").value;
                $.ajax({
                    type: "POST",
                    url: "Funcionario",
                    data: JSON.stringify(id),
                    dataType: "json",
                    success:
                            function (obj) {
                                crearDependencia(obj);
                            },
                            
                    error:
                            function (status) {
                                
                            }
                });
            }
            
            function Funcio_Depe2() { // carga el funcionario del 
                 var id = document.getElementById("mySelect").value;
                $.ajax({
                    type: "POST",
                    url: "Funcionario",
                    data: JSON.stringify(id),
                    dataType: "json",
                    success:
                            function (obj) {
                                actualizar();
                            },
                            
                    error:
                            function (status) {
                                
                            }
                });
            }
            
            function verPuestos() {
                $.ajax({
                    type: "GET",
                    url: "CargarPuestos",
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
            
              
            function myFunction1( PuestosList) {
              var select = document.getElementById("mySelect1"); 
              for(var i = 0; i < PuestosList.length; i++) {
               var el = document.createElement("option");
               el.textContent = PuestosList[i].ID +"-"+PuestosList[i].nombre ;
               el.value = PuestosList[i].ID;
               select.appendChild(el);
               }
            }
            
            
            function verCategorias() {
                $.ajax({
                    type: "GET",
                    url: "CargarCate",
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
            
            function myFunction( CategoriaList) {
             var select = document.getElementById("mySelect"); 
              for(var i = 0; i < CategoriaList.length; i++) {
               var el = document.createElement("option");
               el.textContent = CategoriaList[i].ID +"-"+CategoriaList[i].descripcion ;
               el.value = CategoriaList[i].ID;
               select.appendChild(el);
               }
            }
            

            function cargarDependencias(BienesList) {
                removeRows();
                for (var i = 0; i < BienesList.length; i++) {
                    var td = $("<tr class='rowContenido'>");
                    td.html(
                            "<td>" + BienesList[i].ID + "</td>" +
                            "<td>" + BienesList[i].descripcion + "</td>"+
                            "<td>" + BienesList[i].marca + "</td>"+
                            "<td>" + BienesList[i].modelo + "</td>"+
                            "<td>" + BienesList[i].precio + "</td>"+
                            "<td>" + BienesList[i].cantidad + "</td>"+
                            "<td><img src='Images/binoculars.png' onclick='Registrar(\"" + BienesList[i].descripcion.substring(0,3) +""+ BienesList[i].ID + "\");'></td></tr>"
                            );BienesList[i].descripcion
                    $("#contenido").append(td);
                }
            }

            function Registrar(id,decp) {  
                var str= $("#descripcion2").val();
                var z= str.substring(0,1);
                $("#codigo2").val(id);
                $("#descripcion2").val("nose");
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
           /*
               String codigo;
               Categoria categoria;
                String descripcion;
                Puesto puesto; */

           function crearActivo() {
               var a = document.getElementById("mySelect1").value;
               var b = document.getElementById("mySelect").value;
              var str= $("#descripcion2").val();
              var z= str.substring(0,4);
              
                  Activo = {
                   codigo: $("#codigo2").val(),
                   categoria: { ID:b.valueOf()},
                   descripcion:$("#descripcion2").val(),
                   puesto:{ID:a.valueOf()}
                   
               };
               clean1(); //limpia los campos del form
               $.ajax({
                   type: "POST",
                   url: "AgregarActivo",
                   data: JSON.stringify(Activo),
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