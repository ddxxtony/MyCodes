<%-- 
    Document   : reparacion
    Created on : 16/11/2016, 06:46:43 PM
    Author     : Alejandro Martinez
--%>

<%@page import="Clases.Servicio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Reparaciones</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/grid.css" type="text/css" media="screen">   
    <script src="js/jquery-1.7.1.min.js" type="text/javascript"></script>
    <script src="js/cufon-yui.js" type="text/javascript"></script>
    <script src="js/cufon-replace.js" type="text/javascript"></script>
	<script src="js/Vegur_500.font.js" type="text/javascript"></script> 
    <script src="js/FF-cash.js" type="text/javascript"></script>        
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
<body id="page4">
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
                </div>
            </header>
            
            <!--==============================content================================-->
            <section id="content"><div class="ic"></div>
                <div class="main">
                    <div class="container_12">
                         <h3 class="p1">Contamos con los Siguientes servicios para que tu Automovil siempre luzca como nuevo</h3>
                        <br>
                        <h6 class="p2">Autolavado </h6>
                        <div class="wrapper p4">
                            <%Servicio Serv = new Servicio();
                                         int index = 0;
                                        Servicio[] Servicios = Serv.Consultar_servicios("Autolavado");
                                        if (Servicios != null) {                                          
                                            while (index < Servicios.length) {
                                                
                                            %>
                           <article class="grid_4">                          
                                <div class="wrapper">
                                        <ul class="list-1">
                                            <li><a href="infoServ.jsp?idServ=<%=Servicios[index].id_servicio%>">
                                       <%=Servicios[index].nombre%></a></li> 
                                       </ul>
                                </div>
                            </article>
                                            
                                            <%
                                            index++; }}%>
                        </div>
                        <br>
                        <h6 class="p2">Hojalateria y pintura</h6>
                         <div class="wrapper p4">
                          <%         Serv = new Servicio();
                                       index=0;
                                        Servicio[] ServiciosP = Serv.Consultar_servicios("Hojalateria y pintura");
                                        if (ServiciosP != null) {
                                            while (index < ServiciosP.length) {
                                                
                                            %>
                           <article class="grid_4">                          
                                <div class="wrapper">
                                        <ul class="list-1">
                                            <li><a href="infoServ.jsp?idServ=<%=ServiciosP[index].id_servicio%>">
                                       <%=ServiciosP[index].nombre%></a></li> 
                                       </ul>
                                </div>
                            </article>
                                            
                                            <%
                                            index++; }}%>
                          
                        </div>
                        <div class="container-bot">
                            <div class="container-top">
                                <div class="container">
                                    <div class="wrapper">
                                        <article class="grid_8">
                                            <div class="indent-left">
                                                <h3>Servicio de reparacion</h3>
                                                <h6>Las reparaciones para tu automovil dejalas en manos de los expertos en materia automotriz.</h6>
                                                <p>Contamos con el equipo de la mas alta tecnologia, asi como con el personal mas altamente capacitado para dejar tu automovil como nuevo.</p>
                                                <div class="wrapper margin-bot">
                                                    <figure class="img-indent3"><img src="images/servicios.jpg" alt=""></figure>
                                                    <div class="extra-wrap">
                                                        <h6><strong>Usamos solo los Productos Indicados para su Vehículo </strong></h6>
                                                        <p>Es muy importante que tome en cuenta que cada vehículo lleva un lista de liquidos y refacciones única, como ejemplo sepa usted que existen mas de 100 tipos de aceites de motor, trasmisión, dirección, etc., de modo que sabiendo esto, no dejará que ni en la gasolinera le rellenen los niveles.</p>
                                                        <a class="button" href="#">Read More</a>
                                                    </div>
                                                </div>
                                                <div class="wrapper indent-bot">
                                                    <figure class="img-indent3"><img src="images/htas.jpg" alt=""></figure>
                                                    <div class="extra-wrap">
                                                        <h6><strong>Uso de Herramienta Especial </strong></h6>
                                                        <p>Solo usamos la herramienta indicada para la reparación a efectuar, contamos con scanner original para cada marca, torno, prensa de 50 toneladas, alineadora, balaceadora de ruedas, rampas, soldadora MIG y eléctrica.</p>
                                                        <a class="button" href="#">Read More</a>
                                                    </div>
                                                </div>
                                                        Todos estos servicios sin pagar mas, compruebe nuestros precios y sobre todo nuestra Calidad.                                            </div>
                                        </article>
                                        <article class="grid_4">
                                            <div class="indent-left2">
                                                
                                                
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
