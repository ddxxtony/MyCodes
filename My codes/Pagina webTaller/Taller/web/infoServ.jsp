<%-- 
    Document   : mantenimiento
    Created on : 16/11/2016, 06:46:16 PM
    Author     : Alejandro Martinez
--%>

<%@page import="Clases.Servicio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Mantenimiento</title>
    <meta charset="utf-8">
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
		$(document).ready(function() { 
			$(".lightbox").append("<span></span>");
			$("a[data-gal^='prettyVideo']").prettyPhoto({animation_speed:'normal',theme:'facebook',slideshow:false, autoplay_slideshow: false});
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
                        <div class="container-bot">
                            <div class="container-top">
                                <div class="container">
                                    <div class="wrapper">
                                        <article class="grid_11">
                                            <div class="indent-left">
                                                <%if (request.getParameter("idServ")!=null){
                                                 Servicio Serv = new Servicio();
                                                 Servicio[] Servicios= Serv.Consultar_servicio_id(request.getParameter("idServ"));
                                                   %>
                                                <h3><%= Servicios[0].nombre%></h3>
                                                <br>
                                                <p class="indent-bot"><%=Servicios[0].descripcion%></p>
                                                <br>
                                                <figure class="indent-bot"><img src="images/page3-img1.png" alt=""></figure>
                                                
                                              <%  }%>
                                            </div>
                                        </article>
                                       <!-- <article class="grid_8">
                                            
                                        </article> -->
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