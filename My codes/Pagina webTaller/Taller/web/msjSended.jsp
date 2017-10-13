<%-- 
    Document   : contacto
    Created on : 16/11/2016, 06:45:35 PM
    Author     : Alejandro Martinez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Locations</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/grid.css" type="text/css" media="screen">   
    <script src="js/jquery-1.7.1.min.js" type="text/javascript"></script>
    <script src="js/cufon-yui.js" type="text/javascript"></script>
    <script src="js/cufon-replace.js" type="text/javascript"></script>
	<script src="js/Vegur_500.font.js" type="text/javascript"></script> 
    <script src="js/FF-cash.js" type="text/javascript"></script>        
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
            
            
            function Send_mail() {
                
                
                var Nombre = document.getElementById("names").value;
                var Email = document.getElementById("email").value;
                var telefono=document.getElementById("phone").value;
                var message=document.getElementById("message").value;
            
                if (Nombre.length >= 1 && Email.length >= 1
                        && telefono.length >= 1 && message.length >= 1) {
                    ajax = nuevoAjax();

                    var url = 'sendMail.jsp?Nombre=' + Nombre + '&Email=' +Email+
                            "&Telefono="+telefono+"&mensaje="+message;
                    ajax.onreadystatechange = procesadorRespuesta;
                    ajax.open("post", url, true);
                    ajax.send(null);
                  document.getElementById('close2').innerHTML = "<p><font size=4 color=blue>Enviado Mensaje</font></p>";
                } else {

                    document.getElementById('close2').innerHTML = "<p><font size=4 color=red>No puede haber campos vacios</font></p>";
                
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
                        document.getElementById('close2').innerHTML = '<p>Email enviado correctamente</p>';
                      //  location.href = "taller.jsp";
                    }
                    else {
                        document.getElementById('close2').innerHTML = "<p><font size=4 color=red>Error al Enviar Mensaje\n\
        <br>Intentelo Mas tarde</font></p>";
                    }
                    //} 
                }
            }
        </script> 
</head>
<body id="page6">
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
                                <a href="Iniciar_sesion.jsp" id='iniciar_Sesion'> <IMG SRC="images/entrar.png" WIDTH="30" HEIGHT="30">Entrar </a>
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
                            <li><a href="nosotros.jsp?#DescripcionNost">Nosotros</a></li>
                            <li><a href="mantenimiento.jsp">Mantenimiento</a></li>
                            <li><a href="reparacion.jsp">Aspecto Fisico</a></li>
                            <li><a href="precio.jsp">Solicita Servicio</a></li>
                            <li><a href="contacto.jsp">Contacto</a></li>
                        </ul>
                    </nav>
                </div>
            </header>
            
            <!--==============================content================================-->
            <section id="content"><div class="ic"></div>
                <div class="main">
                    <div class="container_12">
                        <div class="wrapper p5">
                            <article class="grid_4">
                                <div class="wrapper">
                                    <figure class="img-indent"><img src="images/page1-img1.png" alt=""></figure>
                                    <div class="extra-wrap">
                                        <h4>Reparacion de Motor</h4>
                                        <a class="button" href="#">Read More</a>
                                    </div>
                                </div>
                            </article>
                            <article class="grid_4">
                                <div class="wrapper">
                                    <figure class="img-indent"><img src="images/page1-img2.png" alt=""></figure>
                                    <div class="extra-wrap">
                                        <h4>Alineacion y Balanceo</h4>
                                        <a class="button" href="#">Ver Mas</a>
                                    </div>
                                </div>
                            </article>
                            <article class="grid_4">
                                <div class="wrapper">
                                    <figure class="img-indent"><img src="images/page1-img3.png" alt=""></figure>
                                    <div class="extra-wrap">
                                        <h4>Cambio de Aceite</h4>
                                        <a class="button" href="#">Ver Mas</a>
                                    </div>
                                </div>
                            </article>
                        </div>
                        <div class="container-bot">
                            <div class="container-top">
                                <div class="container">
                                    <div class="wrapper">
                                        <article class="grid_8">
                                            <div class="UserForm">
                                            <div class="indent-left" >
                                                <h3 class="p1">Mensaje enviado Correctamente</h3>
                                                
                                            </div>
                                            </div>
                                        </article>
                                        <article class="grid_4">
                                            <div class="indent-left2 indent-top">
                                                <div class="box p4">
                                                    <div class="padding">
                                                        <div class="wrapper">
                                                            <figure class="img-indent"><img src="images/page1-img4.png" alt=""></figure>
                                                            <div class="extra-wrap">
                                                                <h3 class="p0">Horario:</h3>
                                                            </div>
                                                        </div>
                                                        <p class="p1"><strong>Lunes - Sabado</strong></p>
                                                        <p class="color-1 p0">Lunes - Sabado: 7:30 am - 6:00 pm</p>
                                                        <p class="color-1 p1">Sabado: 7:30 am - 5:00 pm</p>
                                                        Te Esperamos!
                                                    </div>
                                                </div>
                                                <figure class="indent-bot">
                                                    <iframe width="260" height="202" frameborder="0" style="border:0" src="https://www.google.com/maps/embed/v1/place?key=AIzaSyDal5R91JhyjAkVCXyRmbZw0m75aLRk0us&q=Universidad+Autónoma+de+Aguascalientes" allowfullscreen></iframe>                                                
                                                </figure>                                                    
                                               
                                                <div class="indent-left">
                                                    <dl class="main-address">
                                                         <dt>Universidad Autonoma de Aguascalientes<br> Aguascalientes, Mexico.</dt>
                                                        <dd><span>Telefono: </span> +55 449 923 23 87</dd>
                                                        <dd><span>FAX:</span>  +55 449 923 23 87</dd>
                                                        <dd><span>E-mail:</span><a href="#">taller@taller.org</a></dd>
                                                    </dl>
                                                </div>
                                            </div>
                                        </article>
                                    </div>
                                </div>
                            </div>
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
	<script type="text/javascript"> Cufon.now(); </script>
</body>
</html>
