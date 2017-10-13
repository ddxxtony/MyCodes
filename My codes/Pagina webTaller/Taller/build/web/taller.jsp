<%-- 
    Document   : principal
    Created on : 16/11/2016, 06:44:53 PM
    Author     : Alejandro Martinez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Clases.Servicio"%>
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
                            <%} else if (session.getAttribute("sessionOk") != null && session.getAttribute("STUser").equals("Cliente")) {%>
                            <ul class="menu">
                                <li><a class="active" href="index.jsp">Inicio</a></li>
                                <li><a href="taller.jsp?#DescripcionNost">Nosotros</a></li>
                                <li><a href="mantenimiento.jsp">Mantenimiento</a></li>
                                <li><a href="Lavado.jsp">Aspecto Fisico</a></li>
                                <li><a href="precio.jsp">Solicita Servicio</a></li>
                                <li><a href="contacto.jsp">Contacto</a></li>

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
                        <div class="slider-wrapper">
                            <div class="slider">
                                <ul class="items">
                                    <li>
                                        <img src="images/slider-img1.jpg" alt="" />
                                    </li>
                                    <li>
                                        <img src="images/slider-img2.jpg" alt="" />
                                    </li>
                                    <li>
                                        <img src="images/slider-img3.jpg" alt="" />
                                    </li>
                                </ul>
                            </div>
                            <a class="prev" href="#">prev</a>
                            <a class="next" href="#">next</a>
                        </div>
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
                                            <h4>Mantenimiento Correctivo</h4>

                                            <a class="button" href="#">Ver Mas</a>
                                        </div>
                                    </div>
                                </article>
                                <article class="grid_4">
                                    <div class="wrapper">
                                        <figure class="img-indent"><img src="images/page1-img2.png" alt=""></figure>
                                        <div class="extra-wrap">
                                            <h4>Mantenimiento preventivo</h4>

                                            <a class="button" href="#">Ver mas</a>
                                        </div>
                                    </div>
                                </article>
                                <article class="grid_4">
                                    <div class="wrapper">
                                        <figure class="img-indent"><img src="images/page1-img3.png" alt=""></figure>
                                        <div class="extra-wrap">
                                            <h4>Hojalateria y pintura</h4>
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
                                                <div class="indent-left">
                                                    <h2 id='DescripcionNost'>Bienvenido!</h2>
                                                    <p class="prev-indent-bot"><strong>ProCarElite</strong>, somos una empresa dedicada a la venta  compra de automoviles nuevos y usados, tambien contamos con el taller automotriz mas altamente calificado del pais, con personal totalmente capacitado y actulizado para ofrecerte el mejor de los servicios.</p>
                                                </div>
                                                <div class="wrapper">
                                                    <div class="grid_4 alpha">
                                                        <div class="indent-left">
                                                            <div class="maxheight indent-bot">
                                                                <h3>Acerca de Nosotros</h3>
                                                                <p class="prev-indent-bot"><a class="link-1" href="#">Nuestra empresa</a> es una de las mas altamente capacitadas en el area automotriz, tanto en la compra y venta de automoviles nuevos y/o usados, como en el mas especializado taller.</p>
                                                                Contamos con el personal mas altamente capacitado, te aseguramos que el trato sera plenamente satisfactorio.
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="grid_4 omega">
                                                        <div class="indent-left2">
                                                            <div class="maxheight indent-bot">
                                                                <h3 class="p0">Nuestros Servicios</h3>
                                                                <ul class="list-1">
                                                                    <% Servicio Servi = new Servicio();
                                                                        Servicio[] Servicios = Servi.Consultar_servicios(2);
                                                                        if (Servicios != null) {
                                                                            int index = 0;
                                                                            while (index < Servicios.length) {
                                                                    %>
                                                                    <li><a href="infoServ.jsp?idServ=<%=Servicios[index].id_servicio%>">
                                                                            <%=Servicios[index].nombre%></a></li> 

                                                                    <%
                                                                                index++;
                                                                            }
                                                                        }
                                                                    %>
                                                                </ul>
                                                            </div>
                                                            <a class="button" href="mantenimiento.jsp">Ver Mas</a>
                                                        </div>
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
                                                            <p class="p1"><strong>Emergencias 24 Horas</strong></p>
                                                            <p class="color-1 p0">Lunes - Viernes 7:30 am - 6:00 pm</p>
                                                            <p class="color-1 p1">Sabado 7:30 am - 5:00 pm</p>
                                                            Disponible toda la noche
                                                        </div>
                                                    </div>
                                                    <figure class="indent-bot">
                                                        <iframe width="260" height="202" frameborder="0" style="border:0" src="https://www.google.com/maps/embed/v1/place?key=AIzaSyDal5R91JhyjAkVCXyRmbZw0m75aLRk0us&q=Universidad+Autónoma+de+Aguascalientes" allowfullscreen></iframe>                                                </figure>
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
        <script type="text/javascript"> Cufon.now();</script>
        <script type="text/javascript">
            $(window).load(function () {
                $('.slider')._TMS({
                    duration: 1000,
                    easing: 'easeOutQuint',
                    preset: 'simpleFade',
                    slideshow: 7000,
                    banners: false,
                    pauseOnHover: true,
                    pagination: false,
                    pagNums: false,
                    nextBu: '.next',
                    prevBu: '.prev'
                });
            });
        </script>
    </body>
</html>
