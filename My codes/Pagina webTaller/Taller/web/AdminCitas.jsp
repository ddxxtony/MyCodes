<%-- 
    Document   : AdminCitas
    Created on : 24-nov-2016, 5:05:04
    Author     : ellui
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Clases.Servicio"%>
<%@page import="Clases.Auto"%>
<%@page import="Clases.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <title>Mantenimiento</title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/grid.css" type="text/css" media="screen">   
        <link href="calendario_dw/calendario_dw-estilos.css" type="text/css" rel="STYLESHEET">  
        <script type="text/javascript" src="calendario_dw/jquery-1.4.4.min.js"></script>
        <script type="text/javascript" src="calendario_dw/calendario_dw.js"></script> 


        <script type="text/javascript">
            $(document).ready(function () {
                $(".campofecha").calendarioDW();
            });

        </script> 

        <script type="text/javascript">
            $(document).ready(function () {
                $("#addServi").click(function () {
                    //alert('button clicked');
                    // Add_Service();
                });
            });
            //$("#addService").click(ADD_Service);
        </script>
        <script type="text/javascript">
            var ConDate = 1;
            var ConId = 1;
            var BorraC = 1;
            var ModificarC = 1;
            var AgregarC = 1;
            function mostrarConsultaCitasDate() {
                if (ConDate == 1) {
                    document.getElementById('ConCitaDate').style.display = 'block';
                    ConDate = 1 + ConDate;
                } else {
                    document.getElementById('ConCitaDate').style.display = 'none';
                    ConDate = 1;

                }
            }
            function mostrarConsultaID() {
                if (ConId == 1) {
                    document.getElementById('ConCitaId').style.display = 'block';
                    ConId = 1 + ConId;
                } else {
                    document.getElementById('ConCitaId').style.display = 'none';
                    ConId = 1;

                }
            }

            function mostrarBorrarCita() {
                if (BorraC == 1) {
                    document.getElementById('BorrarCita').style.display = 'block';
                    BorraC = 1 + BorraC;
                } else {
                    document.getElementById('BorrarCita').style.display = 'none';
                    BorraC = 1;

                }
            }

            function   mostrarModificar() {
                if (ModificarC == 1) {
                    document.getElementById('Modificar').style.display = 'block';
                    ModificarC = 1 + ModificarC;
                } else {
                    document.getElementById('Modificar').style.display = 'none';
                    ModificarC = 1;

                }
            }

            function   mostrarAgregar() {
                if (AgregarC == 1) {
                    document.getElementById('Agregar').style.display = 'block';
                    AgregarC = 1 + AgregarC;
                } else {
                    document.getElementById('Agregar').style.display = 'none';
                    ModificarC = 1;

                }
            }

        </script>


        <script language="JavaScript">
            var ajax;
            var DivId;
            function nuevoAjax() {
                var xhttp;
                if (window.XMLHttpRequest) {
                    xhttp = new XMLHttpRequest();
                } else {
                    // code for IE6, IE5
                    xhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                return xhttp;
            }
            function refreshDiv(inputId, Divi, page, tipo) {

                DivId = Divi;
                var fecha = document.getElementById(inputId).value;
                var url = page + ".jsp?fecha=" + fecha + "&tipo=" + tipo;
                ajax = nuevoAjax();
                ajax.onreadystatechange = procesadorRespuesta;
                ajax.open("get", url, true);
                ajax.send(null);

            }



            function procesadorRespuesta() {

                var div = DivId;

                if (ajax.readyState == 4 && ajax.readyState != null) {
                    document.getElementById(div).innerHTML = ajax.responseText; //Parametro = id del div
                    //                              alert("here1             "+DivId)
                    //setTimeout('refreshdiv("chat", "dock")', 1000);

                }

            }
        </script> 
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
            }

            th, td {
                text-align: left;
                padding: 8px;
            }



            th {
                background-color: #4CAF50;
                color: white;
            }
        </style>  
    </head>
    <body id="page3">
        <div class="main-bg">
            <div class="bg">
                <!--==============================header=================================-->
                <header>
                    <div class="main">
                        <div class="wrapper">
                            <h1><a href="index.jsp">logo</a></h1>
                            <div class="fright">
                                <div class="indent">

                                    <% if (session.getAttribute("sessionOk") != null) {%>
                                    <span class="User_name">Bienvenido! <%=session.getAttribute("Snombre")%></span>
                                    <%}%>
                                    <span class="address">Universidad Autonoma de Aguascalientes, Mexico</span>
                                    <span class="phone">Tel: +55 449 923 23 87</span>

                                    <!-- Checamos que la sesion este iniciada-->
                                    <% if (session.getAttribute("sessionOk") != null) {%>
                                    <div style= " position: absolute; top: 125px; left: 1070px;">
                                        <a href="cerrar_sesion.jsp" id='cerrar_sesion'> <IMG SRC="images/salir.png" WIDTH="30" HEIGHT="30"> Salir </a>
                                    </div>
                                    <%} else {%>  
                                    <div style= " position: absolute; top: 98px; left: 950px;">   
                                        <a href="Iniciar_sesion.jsp" id='iniciar_Sesion'> <IMG SRC="images/entrar.png" WIDTH="30" HEIGHT="30"> Entrar </a>
                                    </div>
                                    <%}%>
                                    <div style= " position: absolute; top: 125px; left: 820px;">
                                        <IMG SRC="images/buscar.png" WIDTH="30" HEIGHT="30">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <nav>
                            <%if (session.getAttribute("sessionOk") != null && session.getAttribute("STUser").equals("Administrador")) {%>
                            <ul class="menu">
                                <li><a class="active" href="index.jsp">Inicio</a></li>
                                <li><a href="taller.jsp?#DescripcionNost">Nosotros</a></li>
                                <li><a href="AdminUsers.jsp">usuarios</a></li>
                                <li><a href="AdminCitas.jsp">Citas</a></li>
                                <li><a href="AdminServicios.jsp">Servicios</a></li>
                                <li><a href="AdminAutopartes.jsp">Autopartes</a></li>
                                <li><a href="AdminHerramientas.jsp">Herramientas</a></li>
                            </ul>
                            <%} else if (session.getAttribute("sessionOk") != null && session.getAttribute("STUser").equals("Mecanico")) {%>


                            <ul class="menu">
                                <li><a class="active" href="index.jsp">Inicio</a></li>
                                <li><a href="taller.jsp?#DescripcionNost">Nosotros</a></li>
                                <li><a href="mantenimiento.jsp">Administrar usuarios</a></li>
                                <li><a href="Lavado.jsp">Citas</a></li>
                                <li><a href="precio.jsp">Servicios</a></li>
                                <li><a href="contacto.jsp">Autopartes</a></li>
                            </ul>
                            <%} else {//Si es un cliente o un usuario sin registrar%>
                            <ul class="menu">
                                <li><a class="active" href="index.jsp">Inicio</a></li>
                                <li><a href="taller.jsp?#DescripcionNost">Nosotros</a></li>
                                <li><a href="mantenimiento.jsp">Mantenimiento</a></li>
                                <li><a href="Lavado.jsp">Aspecto Fisico</a></li>
                                <li><a href="precio.jsp">Solicita Servicio</a></li>
                                <li><a href="contacto.jsp">Contacto</a></li>

                            </ul>

                            <%}%>
                        </nav>
                    </div>
                </header>

                <!--==============================content================================-->
                < <section id="content"><div class="ic"></div>
                    <div class="main">
                        <div class="container_12">

                            <h3 class="p1">Seleccione las acciones que desea Realizar</h3>
                            <br>

                            <!--CONSULTAR CITAS POR DIA----> 
                            <h4 class="p2">  <a href="#" onclick="mostrarConsultaCitasDate()">  <img src="images/minus.png"  width="20" height="20" id="adduser">Consultar citas por dia</a></h4>
                            <div class="wrapper" style='display:none;' id='ConCitaDate'>
                                <article class="grid_10">
                                    <div class="indent-left"  id='Formulario'>
                                        <article class="grid_4">
                                            <label><span class="text-form">Ingrese la fecha:</span>

                                                <form id="formfech">
                                                    <input type="text" name="fechaC"  id='fechaC' class="campofecha" size="12">
                                                </form>
                                            </label>
                                        </article>

                                        <article class="grid_4">

                                            <a class="button"  onClick="refreshDiv('fechaC', 'ConsultasDate', 'funciones/MuestrainfoCita', 'consultaf')">Consultar Citas</a>
                                        </article>

                                    </div>
                                </article>


                                <div  id="ConsultasDate">
                                    <article class="grid_10">
                                        <div class="indent-left"  id='Formulario'>


                                        </div>
                                    </article>
                                </div>

                                <!--CONSULTAR CITA ESPECIFICA--  --> 
                            </div>
                            <h4 class="p2" >  <a href="#" onclick="mostrarConsultaID()">  <img src="images/minus.png"  width="20" height="20" id="addSrvice" > Consultar cita Especifica</a></h4>

                            <div class="wrapper p4" style='display:none;'  id='ConCitaId'>

                                <article class="grid_10">
                                    <div class="indent-left"  id='Formulario2'>
                                        <article class="grid_4">
                                            <label><span class="text-form">Ingrese el ID de su cita:</span>
                                                <br>

                                                <input type="text" name="fech"  id='CitaIdCon'  size="12">

                                            </label>
                                        </article>

                                        <article class="grid_4">
                                            <br>
                                            <a class="button"  onClick="refreshDiv('CitaIdCon', 'ConsultasId', 'funciones/MuestrainfoCita', 'consultaid')">Consultar</a>
                                        </article>

                                    </div>
                                </article>

                                <div  id="ConsultasId">
                                    <article class="grid_10">
                                        <div class="indent-left"  id='Formulario'>


                                        </div>
                                    </article>
                                </div>


                                <!-----ELIMINAR CITA ---- -->
                            </div>
                            <h4 class="p2" ><a href="#" onclick="mostrarBorrarCita()">    <img src="images/minus.png"  width="20" height="20" id="addSrvice" > Eliminar cita</a></h4>
                            <div class="wrapper p4" style='display:none;'  id='BorrarCita'>
                                <article class="grid_10">
                                    <div class="indent-left"  id='Formulario2'>
                                        <article class="grid_4">
                                            <label><span class="text-form">Ingrese el ID de su cita:</span>
                                                <br>

                                                <input type="text" name="fechaC"  id='CitaIdBor'  size="12">

                                            </label>
                                        </article>

                                        <article class="grid_4">
                                            <br>
                                            <a class="button"  onClick="refreshDiv('CitaIdBor', 'ResultadoBorrar', 'funciones/MuestrainfoCita', 'BorrarId')">Borrar Cita</a>
                                        </article>

                                    </div>
                                </article>

                                <div  id="ResultadoBorrar">
                                    <article class="grid_10">
                                        <div class="indent-left"  id='Formulario'>


                                        </div>
                                    </article>
                                </div>
                            </div>
      <!--MODIFICAR CITA-->                 

                            <h4 class="p2" ><a href="#" onclick="mostrarModificar()">    <img src="images/minus.png"  width="20" height="20" id="addSrvice" >Modificar cita</a></h4>
                            <div class="wrapper p4" style='display:none;'  id='Modificar'>
                                <article class="grid_10">
                                    <div class="indent-left"  id='Formulario2'>
                                        <article class="grid_4">
                                            <label><span class="text-form">Ingrese el ID de su cita:</span>
                                                <br>

                                                <input type="text" name="fechaC"  id='CitaIdMod'  size="12">

                                            </label>
                                        </article>

                                        <article class="grid_4">
                                            <br>
                                            <a class="button"   onClick="refreshDiv('CitaIdMod', 'citaModificacion', 'funciones/MuestrainfoCita', 'ModificaCit')">Mostrar</a>
                                        </article>

                                    </div>
                                </article>

                                <div class="wrapper p4"   id='citaModificacion' >


                                </div>


                            </div>


    <!--AGREGAR CITA -->                     
                            <h4 class="p2" ><a href="#" onclick="mostrarAgregar()">    <img src="images/minus.png"  width="20" height="20" id="addSrvice" > Agregar cita</a></h4>
                            <div class="wrapper p4" style='display:none;'  id='Agregar'>
                              
                                
                                
                                
                                 <h3 class="p1">Ingresa los siguientes datos</h3>
                                                    <form id="contact-form" method="post" enctype="multipart/form-data">                    
                                                        <fieldset>                                                        
                                                            <label><span class="text-form">Usuario:</span><input name="name" type="text"  /></label>
                                                            <h3 class="p1"><span>Informacion de la Cita</span></h3>
                                                            <label><span class="text-form">Fecha:</span>
                                                              

                                                            <input type="text" name="fechaC"  id='fechaC' class="campofecha" size="12">

                                                            </label>
                                                            <label><span class="text-form">Hora</span>
                                                                <select name="horarioC" id='horarioC' style="width: 279px; height: 25px;">
                                                                    <%   
                                                                        int i=8;                
                                                                        String hora;
                                                                        while(i<=18){
                                                                            hora=""+i+":00";
                                                                            out.print("<option>"+hora+"</option>");
                                                                            hora=""+i+":30"; 
                                                                            out.print("<option>"+hora+"</option>");
                                                                            i++;
 
                                                                        }%>
                                                                </select></label>
                                                                
                                                                 <div id="messageHora" style="text-align: left"><br></div>
                                                           <h3 class="p1" > <span>Informacion de Auto</span></h3>
                                                           <label><span class="text-form">Marca:</span><select name="car_brand" id="MarcaC"> 
                                                                <%               
                                                                        Auto [] auto;
                                                                        auto=Auto.Consulta_Marca();
                  
                                                                        i=0;
                                                                        while(i<auto.length){
                                                                           out.print("<option>"+auto[i].marca+"</option>");
                                                                            i++;
                                                                        }%>
                                                               
                                                               </select></label>
                                                           <label><span class="text-form">Nombre:</span><select  name="car_Nombre" id="nombreC"> 
                                                                      <%  
                                                                          auto=Auto.Consulta_Nombre();
                                                                        i=0;
                                                                        while(i<auto.length){
                                                                           out.print("<option>"+auto[i].nombre+"</option>");
                                                                            i++;
                                                                        }%>
                                                                   
                                                               </select></label>
                                                                <label><span class="text-form">Tipo:</span><select name="car_tipo"  id="TipoC">
                                                                         <% 
                                                                              Auto [] auto3;
                                                                         auto=Auto.Consulta_tipo();
                                                                        i=0;
                                                                        while(i<auto.length){
                                                                           out.print("<option>"+auto[i].tipo+"</option>");
                                                                            i++;
                                                                        }%>
                                                                           
                                                               </select></label>
                                                                   <label><span class="text-form">Modelo:</span><select name="car_model" id="ModeloC">
                                                                         <%   
                                                                     
                                                                        i=0;
                                                                        auto=Auto.Consulta_Modelo();
                                                                        while(i<auto.length){
                                                                           out.print("<option>"+auto[i].modelo+"</option>");
                                                                            i++;
                                                                        }%>
                                                                           
                                                               </select></label>
                                                                        <label>
             
                                                                        </label>
                                                                          <div id="messageInfoC" style="text-align: left"><br></div>
                                                            <div style="padding-left: 3px;">
                                                                <h3 class="p1" ><span>Tipo de Servicio Requerido</span></h3>
                                                                
                                                                <div class="wrapper" id='ServSolicitados'>
                                                                    
                                                                    <%if(session.getAttribute("ListServices")!=null){
                                                                        
                                                                        ArrayList<Servicio> service;
                                                                        service=(ArrayList<Servicio>)request.getSession().getAttribute("ListServices");
                                                                        i=0;
                                                                          out.print("<UL class=\"list-1\">"); 
                                                                        while(i<service.size()){
                                                                                    out.print("<img src=\"images/minus.png\"  width=\"20\" height=\"20\" id=\"addSrvice\" onclick=\"Remove_Service()\">&nbsp;&nbsp;"+service.get(i).nombre+"<br>"); 
                                                                                        i++;


                                                                        }
                                                                        out.print("</UL>");  
                                                                    }%>
                                                                    <!--    <ul class="list-1">
                                                                            <li><a href="infoServ.jsp?idServ=<%//=Servicios[index].id_servicio%>">
                                                                       <%//=//Servicios[index].nombre%></a></li> -->
                                                                       
                                                                </div>
                                                                <Hr>
                                                                <label><span class="text-form">Servicio:</span><select name="servicio" id='Servicio_opc' >
                                                                         <%  
                                                                          Servicio aux=new Servicio();
                                                                         Servicio [] servicios;
                                                                        i=0;
                                                                        servicios=aux.Consultar_servicios(-1);
                                                                        session.setAttribute("servicios",servicios);
                                                                        while(i<servicios.length){
                                                                            %>
                                                                            
                                                                   <option><%=servicios[i].nombre%></option>
                                                                            
                                                                        <%i++; }%>
                                                                           
                                                               </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                               <a >  <img src="images/plus.png"  width="20" height="20" id="addService" onclick="Add_Service()"> </a>   
                                                                </label>
                                                                
                                                            </div>
                                                            <div class="wrapper">
                                                                <div class="extra-wrap">
                                                                    <div class="clear"></div>
                                                                    <div class="buttons">
                                                                        <a class="button" href="#" onClick="document.getElementById('contact-form').reset()">Limpiar</a>
                                                                        <a class="button"  onClick="Registra_cita()">Concretar cita</a>
                                                                    </div> 
                                                                     
                                                                </div>
                                                             
                                                            </div>
                                                                            <div id="messageC" style="text-align: left"></div>
                                                                        
                                                        </fieldset>						
                                                    </form>
                                                  
                            </div>




                        </div>




                    </div>
                </section>
                <!--==============================footer=================================-->
                <footer>
                    <div class="main">
                        <span>ProCarElite &copy; 2016</span>
                        <a rel="nofollow" href="index.jsp" target="_blank">ProCarElite</a> by procarelite.com<br>
                    </div>
                </footer>
            </div>
        </div>
        <script type="text/javascript"> Cufon.now();</script>
    </body>
</html>