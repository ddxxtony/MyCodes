<%-- 
    Document   : principal
    Created on : 16/11/2016, 06:44:53 PM
    Author     : Alejandro Martinez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Taller Automotriz</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/grid.css" type="text/css" media="screen">   
    <script src="js/jquery-1.7.1.min.js" type="text/javascript"></script>
    <script src="js/cufon-yui.js" type="text/javascript"></script>
    <script src="js/cufon-replace.js" type="text/javascript"></script>
    <script src="js/Vegur_500.font.js" type="text/javascript"></script> 
    <script src="js/FF-cash.js" type="text/javascript"></script>      
    <script src="js/tms-0.3.js" type="text/javascript"></script>
    <script src="js/tms_presets.js" type="text/javascript"></script>
    <script src="js/jquery.easing.1.3.js" type="text/javascript"></script>
    <script src="js/jquery.equalheights.js" type="text/javascript"></script>
     <script type="text/javascript">
            $(document).ready(function () {
                $("#hideBox").click(function () {
                    $("#elementBox").hide();
                });
                $("#showBox").click(function () {
                    $("#elementBox").show();
                });
            });
        </script>
          <script language="JavaScript">
            var ajax;
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
            function Iniciar_sesion() {
                var x = document.formusr.psw.value;
                var y = document.formusr.usr.value;
                
                if (y.length >= 1 && x.length >= 1) {


                    ajax = nuevoAjax();
                    var x
                    var posicion=document.getElementById('TUser').selectedIndex;
                    var opciones = document.getElementById("TUser").options;
                    
                    var url = 'checklogin.jsp?usr=' + document.formusr.usr.value + '&password=' + document.formusr.psw.value+
                            "&TUser="+opciones[posicion].text;
                    ajax.onreadystatechange = procesadorRespuesta;
                    ajax.open("post", url, true);
                    ajax.send(null);
                  document.getElementById('close2').innerHTML = '<p><font size=4 color=blue>Validando datos</font></p>';
                } else {

                    document.getElementById('close2').innerHTML = '<p><font size=4 color=red>No puede haber campos vacios</font></p>';
                }
            }

            function keyPress(e) {

                var key = e.keyCode || e.which;
                if (key == 13) {
                    Iniciar_sesion;

                } else {
                }


            }

            function procesadorRespuesta() {
                if (ajax.readyState == 4) {
                    //if (ajax.status==200){ 
                    // document.formusr.usr.value = ajax.responseText;
                    if (ajax.responseText.indexOf("true") != -1) {
                        document.getElementById('close2').innerHTML = '<p>Bienvenido!</p>';
                        location.href = "taller.jsp";
                    }
                    else {
                        document.getElementById('close2').innerHTML = '<p><font size=4 color=red>Contraseña erronea</font></p>';
                    }
                    //} 
                }
            }
        </script> 


</head>
<body id="page1">
    
   
    
	<div class="main-bg">
        <div class="bg">
            <!--==============================header=================================-->
           
            
            <header>
                <div class="main">
                    <div class="wrapper">
                        <h1><a href="index.jsp">logo</a></h1>
                        <div class="fright">
                                <div class="indent">
                                  <% if(session.getAttribute("sessionOk")!=null){%>
                                  <span class="User_name">Bienvenido! <%=session.getAttribute("Snombre")%></span>
                                  <%}%>
                                <span class="address">Universidad Autonoma de Aguascalientes, Mexico</span>
                                <span class="phone">Tel: +55 449 923 23 87</span>
                               
                                    <!-- Checamos que la sesion este iniciada-->
                                <% if(session.getAttribute("sessionOk")!=null){%>
                                      <div style= " position: absolute; top: 125px; left: 1070px;">
                                    <a href="cerrar_sesion.jsp" id='cerrar_sesion'> <IMG SRC="images/salir.png" WIDTH="30" HEIGHT="30"> Salir </a>
                                </div>
                               <%}else{%>  
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
                        <ul class="menu">
                            <li><a class="active" href="index.jsp">Inicio</a></li>
                            <li><a href="taller.jsp?#DescripcionNost">Nosotros</a></li>
                            <li><a href="mantenimiento.jsp">Mantenimiento</a></li>
                            <li><a href="Lavado.jsp">Aspecto Fisico</a></li>
                            <li><a href="precio.jsp">Solicita Servicio</a></li>
                            <li><a href="contacto.jsp">Contacto</a></li>
                        </ul>
                    </nav>
                    <div class="slider-wrapper">
                        <%-- Iniciar Sericion--%>
                            <div id="elementBox">   
                                     <div id="close" style="z-index: 100px; float: right; text-align: right"><a href="#" id="hide" style='color: red'><span class="icon-cancel-circle"></span></a></div>
                                     <form id="formusr" name="formusr" method ="get">
                                         <table>
                                             <tr> <td>Usuario: </td>
                                                 <td> <input type="text"  name="usr"></td><br> </tr>
                                             <tr> <td>Contraseña: </td>
                                                 <td><input type="password"  name="psw" ></td> </tr>
                                             <tr> <td>Tipo de usuario : </td>
                                                 <td><select id='TUser'>
                                                    <option>Cliente</option>
                                                    <option>Mecanico</option>
                                                    <option>Administrador</option>
                                                    </select></td> </tr>
                                             <tr> <td></td>
                                                 <td> <input type="button" value="Iniciar Sesión" onclick="Iniciar_sesion()"></td> </tr> 
                                         </table>
                                         <div id="close2" style="text-align: left"></div>
                                         <br>
                                     </form>
                                 </div>
                    </div>
                </div>
            </header>
            
            <!--==============================content================================-->
            <section id="content"><div class="ic"></div>
               
            </section>
            
            <!--==============================footer=================================-->
            <footer>
                <div class="main">
                    <span>ProCarElite &copy; 2016</span>
                    <a rel="nofollow" href="index.jsp" target="_blank">ProCarElite</a> by procarelite.com<br></a>.
                </div>
            </footer>
        </div>
    </div>
	<script type="text/javascript"> Cufon.now(); </script>
</body>
</html>
