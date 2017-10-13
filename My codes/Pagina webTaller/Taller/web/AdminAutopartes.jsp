<%-- 
    Document   : AdminUsers
    Created on : 24-nov-2016, 5:04:26
    Author     : ellui
--%>


<!DOCTYPE html>
<%@page import="Clases.Cliente"%>
<html lang="en">
    
    <head>
        <title>Mantenimiento</title>
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/grid.css" type="text/css" media="screen">   
        <link rel="stylesheet" href="css/prettyPhoto.css" type="text/css" media="screen">
        <script src="js/jquery-1.7.1.min.js" type="text/javascript"></script>
        <script src="js/cufon-yui.js" type="text/javascript"></script>
        <script src="js/cufon-replace.js" type="text/javascript"></script>
        <script src="js/Vegur_500.font.js" type="text/javascript"></script> 
        <script src="js/FF-cash.js" type="text/javascript"></script>      
        <script src="js/jquery.prettyPhoto.js" type="text/javascript"></script> 
        <script type="text/javascript">
            

            $(document).ready(function () {
                $(".lightbox").append("<span></span>");
                $("a[data-gal^='prettyVideo']").prettyPhoto({animation_speed: 'normal', theme: 'facebook', slideshow: false, autoplay_slideshow: false});
            });
        </script>  

        <!--[if lt IE 8]>
    <div style=' clear: both; text-align:center; position: relative;'>
        <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
                <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
        </a>
    </div>
        <![endif]-->
        <!--[if lt IE 9]>
                    <script type="text/javascript" src="js/html5.js"></script>
            <link rel="stylesheet" href="css/ie.css" type="text/css" media="screen">
            <![endif]-->
        <script type="text/javascript">
            $(document).ready(function () {
                $("#sds").click(function () {
                    $("#element").hide();
                });
                $("#show").click(function () {
                    $("#element").show();
                });
            });
        </script>

        <script type="text/javascript">
            var MostAgregar=1;
            var MostEliminar=1;
            var MostModificar=1;
            function mostrarAgregar() {
                if(MostAgregar==1){
                      document.getElementById('Agregar').style.display = 'block';
                      MostAgregar=1+MostAgregar;
                }else{
                    document.getElementById('Agregar').style.display = 'none';
                    MostAgregar=1;
                    
                }
            }
            function mostrarModificar() {
                 if(MostModificar==1){
                      document.getElementById('Modificar').style.display = 'block';
                      MostModificar=1+MostModificar;
                }else{
                    document.getElementById('Modificar').style.display = 'none';
                    MostModificar=1;
                    
                }
            }
            
                 function mostrarBorrar() {
                      if(MostEliminar==1){
                      document.getElementById('Eliminar').style.display = 'block';
                      MostEliminar=1+MostEliminar;
                }else{
                    document.getElementById('Eliminar').style.display = 'none';
                    MostEliminar=1;
                    
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
            
            
            
          function  Registra_Cliente(){
                
                
            }
            //(id del div donde se muestran los resultados, tipo de usuario seleccionado)
            function getUsers(iddiv,Tuser,opc){
                var nocacheurl="funciones/getUsersSelect.jsp?Tuser="+Tuser+"&opc="+opc;
                ajax=nuevoAjax();
                
                ajax.onreadystatechange = function () {
                if (ajax.readyState == 4 && ajax.readyState != null) {
                    document.getElementById(iddiv).innerHTML = ajax.responseText; //Parametro = id del div
                    //setTimeout('refreshdiv("chat", "dock")', 1000);
                    }
                }
                 ajax.open("GET", nocacheurl, true); //parametros (metodo, url, asincorno=true)
                 ajax.send(null);
               
            }
            //first parameter is the page that will be
             //showed in the div , wich is the second one,
            // the third is the opcion that u
            // ser selected 
            // and the last one is the user 
            //forma es lleno o vacio
            function refreshdiv(pag,id,opcion,FormaForm,select){
                
               var posicion=document.getElementById(select).selectedIndex;//de aqui sacamos el tipo de usuario
               var opciones = document.getElementById(select).options;
               DivId=id;
               var nocacheurl = ""+pag + ".jsp?SelectedUser=" +opcion + "&Usuario="+opciones[posicion].text+
                       "&forma="+FormaForm;
               ajax=nuevoAjax();
                ajax.onreadystatechange = onReadyRefreshDiv;
                 ajax.open("GET", nocacheurl, true); //parametros (metodo, url, asincorno=true)
                     ajax.send(null);
            }
            
            
            function onReadyRefreshDiv(){

               var div=DivId;
                
               if (ajax.readyState == 4 && ajax.readyState != null) {
                    document.getElementById(div).innerHTML = ajax.responseText; //Parametro = id del div
                    //                              alert("here1             "+DivId)
                    //setTimeout('refreshdiv("chat", "dock")', 1000);
                    if(div=="dock"){ //If para mover la srcollbar al final 
                      //  objDiv = document.getElementById(div);
                        //objDiv.scrollTop = objDiv.scrollHeight;
                    }
                }
                
            }
            


           
        </script>

        </head>
    <body id="page3" >
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
                            <%} else {%>
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

                <!--==============================CONTENIDO================================-->
                <section id="content"><div class="ic"></div>
                    <div class="main">
                        <div class="container_12">

                            <h3 class="p1">Seleccione las acciones que desea Realizar</h3>
                            <br>
<!--AGREGAR SERVICIO -->
                            <h4 class="p2">  <a href="#" onclick="mostrarAgregar()">  <img src="images/minus.png"  width="20" height="20" id="adduser"> Agregar autoparte</a></h4>
                            <div class="wrapper" style='display:none;' id='Agregar'>
                               
                                    <article class="grid_4">
                                         <label><span class="text-form">Seleccione el tipo de usuario:</span> </label>
                                        <br>
                                                     <select id='TUserAd' onclick="refreshdiv('funciones/MuestraFormularios','FomAdd',this.value,'Vacio','TUserAd')">
                                                    <option style="background-color: orange">Cliente</option>
                                                    <option>Mecanico</option>
                                                    <option>Administrador</option>
                                                    </select>
                                    </article>
                                
                                   
                                <br>
                                <br>
                                <br>
                                <article class="grid_10">                          
                                    <div class="wrapper" id="FomAdd">
                                        <article class="grid_10">
            <!--AGREGAR-  -->    <div class="indent-left"  id='Formulario'>
                                                    <h3 class="p1">Ingresa los siguientes datos</h3>
            <!--OPCIONES  -->            <form id="contact-form" method="post" enctype="multipart/form-data">                    
                                                        <fieldset>             
                                                          
                                                            <label><span class="text-form">Nombre:</span><input name="name" type="text"  /></label> 
                                                            <label><span class="text-form">Apellido Paterno:</span><input name="appat"  /></label>   
                                                            <label><span class="text-form">Apellido Materno:</span><input name="appmat" /></label>
                                                            <label><span class="text-form">Calle:</span><input name="phone" type="text"  /></label>
                                                            <label><span class="text-form">Colonia:</span><input name="phone" type="text" /></label>
                                                            <label><span class="text-form">Numero de Casa:</span><input name="phone" type="text"  /></label>
                                                            <label><span class="text-form">Ciudad:</span><input name="phone" type="text"  /></label>
                                                            <label><span class="text-form">Estado:</span><input name="phone" type="text" /></label>
                                                            <label><span class="text-form">Telefono:</span><input name="phone" type="text" /></label>
                                                            <label><span class="text-form">Celular:</span><input name="phone" type="text"  /></label>
                                                            <label><span class="text-form">Ingresos:</span><input name="phone" type="text" /></label>
                                                            <label><span class="text-form">Correo:</span><input name="email" type="text"  /></label>
                                                            <h3 class="p2"><span>Informacion de Inicio de Sesion</span></h3>
                                                            <label><span class="text-form">Usuario :</span><input name="email" type="text" /></label>
                                                            <label><span class="text-form">Contraseña :</span><input name="email" type="text" /></label>
                                                            <label><span class="text-form">Confirmar Contraseña :</span><input name="email" type="text" /></label>
                                                        </fieldset>	
                                                        <br>
                                                         <a class="button"  onClick="Registra_Cliente()">Agregar Usuario</a>
                                                         
                                                    </form>                         
                                                        <br>
                                                 <div class="indent-left"  id='MsjAddCliente' >
                                                    

                                                  </div>
                                                </div>
                                            </article>
                                    </div>
                                </article>


                            </div>
                            <h4 class="p2" >  <a href="#" onclick="mostrarBorrar()">  <img src="images/minus.png"  width="20" height="20" id="addSrvice">Borrar autoparte</a></h4>
                               
              <div class="wrapper p4" style='display:none;'  id='Eliminar'>
                                      
                                      
                                      
                                      
                                 <article class="grid_3">
                                         <label><span class="text-form">Seleccione el tipo de usuario:</span> </label>
                                        <br>
                                                    <select id='TUserM'  onchange="getUsers('opcUsers',this.value,'0')">
                                                    <option>Cliente</option>
                                                    <option>Mecanico</option>
                                                    <option>Administrador</option>
                                                    </select>
                                    </article>
 <!--ELIMINAR SERVICIO-->
                                <article class="grid_4">                          
                                    <div class="wrapper" id="opcUsers">
                                            <label><span class="text-form">Nombre Usuario:</span> 
                                                <br>
                                                <select name="car_model" id="ModeloC">
                                            <% Cliente clie=new Cliente();
                                                Cliente [] clientes=clie.Consulta_usuarios();
                                          
                                            int i=0;
                                            while(i<2)
                                            {
                                   out.print("<option>"+ clientes[i].nombre+"/"+ clientes[i].usuario+ "</option>");
                                                  
                                             i++;
                                                    
                                            }
                                            %>
                                             </select></label>
                                             <br><br><br>
                                              <a class="button"  onClick="fun_elimina_user()">Eliminar Cliente</a>
                                                
                                    </div>
                                           
                                </article>
                             

 <!-----MODIFICAR SERVICIO ---- -->
                            </div>
                            <h4 class="p2" ><a href="#" onclick="mostrarModificar()">    <img src="images/minus.png"  width="20" height="20" id="addSrvice" > Modificar Autoparte</a></h4>
                             <div class="wrapper p4" style='display:none;'  id='Modificar'>
                                     <article class="grid_3">
                                         <label><span class="text-form">Seleccione el tipo de usuario:</span> </label>
                                        <br>
                                                    <select id='TUserM2'  onchange="getUsers('opcUsers2',this.value,'1')">
                                                    <option>Cliente</option>
                                                    <option>Mecanico</option>
                                                    <option>Administrador</option>
                                                    </select>
                                    </article>
                                 
                                  <article class="grid_4">                          
                                    <div class="wrapper" id="opcUsers2">
                                            <label><span class="text-form">Nombre Usuario:</span> 
                                                <br>
                                                <select name="User_mod" id="User_modif" onclick="refreshdiv('funciones/MuestraFormularios','ClientModificacion',this.value,'lleno','TUserM2')">
                                            <% clie=new Cliente();
                                             
                                          
                                             i=0;
                                            while(i<2)
                                            {
                                               out.print("<option>"+ clientes[i].nombre+"/"+ clientes[i].usuario+ "</option>");
                                                  
                                             i++;
                                                    
                                            }
                                            %>
                                             </select></label>
                                             <br><br><br>
                                              
 

                                    </div>
                                </article>
                                             
                                 
                                   <div class="wrapper p4"   id='ClientModificacion' >
                    
             
                                     </div>
                                </div>
                 
                            
                             <h4 class="p2" ><a href="#" onclick="mostrarModificar()">    <img src="images/minus.png"  width="20" height="20" id="addSrvice">Surtir Autoparte</a></h4>
                             <div class="wrapper p4" style='display:none;'  id='Modificar'>
                                     <article class="grid_3">
                                         <label><span class="text-form">Seleccione el tipo de usuario:</span> </label>
                                        <br>
                                                    <select id='TUserM2'  onchange="getUsers('opcUsers2',this.value,'1')">
                                                    <option>Cliente</option>
                                                    <option>Mecanico</option>
                                                    <option>Administrador</option>
                                                    </select>
                                    </article>
                                 
                                  <article class="grid_4">                          
                                    <div class="wrapper" id="opcUsers2">
                                            <label><span class="text-form">Nombre Usuario:</span> 
                                                <br>
                                                <select name="User_mod" id="User_modif" onclick="refreshdiv('funciones/MuestraFormularios','ClientModificacion',this.value,'lleno','TUserM2')">
                                            <% clie=new Cliente();
                                             
                                          
                                             i=0;
                                            while(i<2)
                                            {
                                               out.print("<option>"+ clientes[i].nombre+"/"+ clientes[i].usuario+ "</option>");
                                                  
                                             i++;
                                                    
                                            }
                                            %>
                                             </select></label>
                                             <br><br><br>
                                              
 

                                    </div>
                                </article>
                                             
                                 
                                   <div class="wrapper p4"   id='ClientModificacion' >
                    
             
                                     </div>
                                </div>
                               
                               
                                
                           
                     </div>
                                             
    <!-----Consultar SERVICIO ---- -->                                          
                    <h4 class="p2" ><a href="#" onclick="mostrarModificar()">    <img src="images/minus.png"  width="20" height="20" id="addSrvice" > Consultar Autoparte</a></h4>
                             <div class="wrapper p4" style='display:none;'  id='Modificar'>
                                     <article class="grid_3">
                                         <label><span class="text-form">Seleccione el tipo de usuario:</span> </label>
                                        <br>
                                                    <select id='TUserM2'  onchange="getUsers('opcUsers2',this.value,'1')">
                                                    <option>Cliente</option>
                                                    <option>Mecanico</option>
                                                    <option>Administrador</option>
                                                    </select>
                                    </article>
                                 
                                  <article class="grid_4">                          
                                    <div class="wrapper" id="opcUsers2">
                                            <label><span class="text-form">Nombre Usuario:</span> 
                                                <br>
                                                <select name="User_mod" id="User_modif" onclick="refreshdiv('funciones/MuestraFormularios','ClientModificacion',this.value,'lleno','TUserM2')">
                                            <% clie=new Cliente();
                                             
                                          
                                             i=0;
                                            while(i<2)
                                            {
                                               out.print("<option>"+ clientes[i].nombre+"/"+ clientes[i].usuario+ "</option>");
                                                  
                                             i++;
                                                    
                                            }
                                            %>
                                             </select></label>
                                             <br><br><br>
                                              
 

                                    </div>
                                </article>
                                             
                                 
                                   <div class="wrapper p4"   id='ClientModificacion' >
                    
             
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