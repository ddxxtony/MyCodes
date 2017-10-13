<%-- 
    Document   : citaGenerada
    Created on : 27-nov-2016, 23:10:45
    Author     : ellui
--%>

<%@page import="OraclePackage.oracleConection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Clases.Servicio"%>
<%@page import="Clases.Auto"%>
<%@page import="Clases.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="Client" class="Clases.Cliente"/>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Servicio</title>
        <meta charset="utf-8">
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
        <script language="JavaScript">
            var RegistroD = 1;
            var citas = 1;
            BandFEchaCorrecta = false;
            function mostrarCitas() {

                if (citas == 1) {
                    document.getElementById('DivMiscitas').style.display = 'block';
                    citas = 1 + citas;
                } else {
                    document.getElementById('DivMiscitas').style.display = 'none';
                    citas = 1;

                }

            }

            function mostrarRegistro() {

                if (RegistroD == 1) {
                    document.getElementById('DivRegistro').style.display = 'block';
                    RegistroD = 1 + RegistroD;
                } else {
                    document.getElementById('DivRegistro').style.display = 'none';
                    RegistroD = 1;

                }

            }
            var ajax;
            var DivID;
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
            function RecargaDiv1()
            {
                var posMarca = document.getElementById('MarcaC').selectedIndex;
                var opcMarca = document.getElementById("MarcaC").options;

                var posNombre = document.getElementById('nombreC').selectedIndex;
                var opcNombre = document.getElementById("nombreC").options;

                var posTipo = document.getElementById('TipoC').selectedIndex;
                var opcTipo = document.getElementById("TipoC").options;

                var posModelo = document.getElementById('ModeloC').selectedIndex;
                var opcModelo = document.getElementById("ModeloC").options;

                var posCil = document.getElementById('CilindrosC').selectedIndex;
                var opcCil = document.getElementById("CilindrosC").options;

                var url = "funciones/RegistraCita.jsp?MarcaC=" + opcMarca[posMarca].text + "&nombreC=" + opcNombre[posNombre].text +
                        "&TipoC=" + opcTipo[posTipo].text + "&ModeloC=" + opcModelo[posModelo].text + "&frame=1" + "&Cilindros=" + opcCil[posCil].text;
                ajax = nuevoAjax();
                ajax.onreadystatechange = procesadorRespuestaDiv;
                ajax.open("get", url, true);
                ajax.send(null);

            }
            function RecargaDiv2()
            {
                var fecha = document.getElementById("fechaC").value;

                var posHora = document.getElementById("horarioC").selectedIndex;
                var opcHora = document.getElementById("horarioC").options;

                var url = "funciones/RegistraCita.jsp?fecha=" + fecha + "&frame=2" + "&hora=" + opcHora[posHora].text;
                ajax = nuevoAjax();
                ajax.onreadystatechange = procesadorRespuestaDiv;
                ajax.open("get", url, true);
                ajax.send(null);

            }

            function validarFormatoFecha(campo) {
                var RegExPattern = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;
                if ((campo.match(RegExPattern)) && (campo != '')) {
                    return true;
                } else {
                    return false;
                }
            }
            function validarFechaMenorActual(date) {
                var x = new Date();
                var fecha = date.split("/");
                x.setFullYear(fecha[2], fecha[1] - 1, fecha[0]);
                var today = new Date();

                if (x >= today)
                    return false;
                else
                    return true;
            }
            function RecargaDiv0()
            {


                var url = "funciones/RegistraCita.jsp?frame=0"
                ajax = nuevoAjax();
                ajax.onreadystatechange = procesadorRespuestaDiv;
                ajax.open("get", url, true);
                ajax.send(null);

            }


            function getHours() {
                var fecha = document.getElementById("fechaC").value;
                if (validarFormatoFecha(fecha) && !validarFechaMenorActual(fecha)) {
                    var url = "funciones/RegistraCita.jsp?frame=10&FechaC=" + fecha;//LLena Select
                    ajax = nuevoAjax();
                    ajax.onreadystatechange = llenaHours;
                    ajax.open("get", url, true);
                    ajax.send(null);
                } else {
                    alert("Formato de fecha incorrecto")

                }

            }

            function llenaHours() {

                if (ajax.readyState == 4 && ajax.readyState != null) {
                    document.getElementById('horasCita').innerHTML = ajax.responseText //Parametro = id del div
                    //                              alert("here1             "+DivId)
                    //setTimeout('refreshdiv("chat", "dock")', 1000);

                }

            }

            function procesadorRespuestaDiv() {

                if (ajax.readyState == 4 && ajax.readyState != null) {
                    document.getElementById('DivRegistro').innerHTML = ajax.responseText //Parametro = id del div
                    //                              alert("here1             "+DivId)
                    //setTimeout('refreshdiv("chat", "dock")', 1000);

                }

            }
            function Add_Service() {


                var posicion = document.getElementById('Servicio_opc').selectedIndex;
                var opciones = document.getElementById('Servicio_opc').options;

                var url = "funciones/Agrega_servicio.jsp?opc_serv=" + posicion
                ajax = nuevoAjax();
                ajax.onreadystatechange = procesadorRespuesta;
                ajax.open("get", url, true);
                ajax.send(null);

            }

            function Registra_cita() {
                if (confirm("Esta seguro que desea continuar")) {


                    document.getElementById('messageC').innerHTML = '<p><font size=4 color=blue>\n\
                        Espere por favor estamos procesando su solicitud XDXD</font></p>';
                    //Informacion de la cita
                    var fecha = document.getElementById("fechaC").value;

                    var posHora = document.getElementById("horarioC").selectedIndex;
                    var opcHora = document.getElementById("horarioC").options;

                    // informacion del auto;
                    var posMarca = document.getElementById('MarcaC').selectedIndex;
                    var opcMarca = document.getElementById("MarcaC").options;

                    var posNombre = document.getElementById('nombreC').selectedIndex;
                    var opcNombre = document.getElementById("nombreC").options;

                    var posTipo = document.getElementById('TipoC').selectedIndex;
                    var opcTipo = document.getElementById("TipoC").options;

                    var posModelo = document.getElementById('ModeloC').selectedIndex;
                    var opcModelo = document.getElementById("ModeloC").options;

                    var url = "funciones/RegistraCita.jsp?MarcaC=" + opcMarca[posMarca].text + "&nombreC=" + opcNombre[posNombre].text +
                            "&TipoC=" + opcTipo[posTipo].text + "&ModeloC=" + opcModelo[posModelo].text + "frame=1";


                    ajax = nuevoAjax();

                    ajax.onreadystatechange = procesadorRespuestaV2;
                    ajax.open("get", url, true);
                    ajax.send(null);





                }

            }

        
            function Remove_Service(valor) {
                alert(valor);
                var url = "funciones/Quitar_servicio.jsp?index=" + valor;
                ajax = nuevoAjax();
                ajax.onreadystatechange = procesadorRespuesta;
                ajax.open("get", url, true);
                ajax.send(null);

            }


            function procesadorRespuesta() {

                if (ajax.readyState == 4) {

                    //  if (ajax.status==200){ 
                    // document.formusr.usr.value = ajax.responseText;
                    var val = ajax.responseText;
                    document.getElementById('ServSolicitados').innerHTML = val;

                    //} 
                } else {
                    document.getElementById('amit').innerHTML = "Error";

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
                padding: 3px;
            }



            th {
                background-color:#FE642E;
                color: white;
            }
        </style>

    </head>
    <body id="page5">
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
                                        <a href="cerrar_sesion.jsp" id='cerrar_sesion'> <IMG SRC="images/salir.png" WIDTH="30" HEIGHT="30">Salir </a>
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

                            <div class="container-bot">
                                <div class="container-top">
                                    <div class="container">
                                        <div class="wrapper">
                                            <article class="grid_8">
                                                <div class="indent-left"  id='Formulario'>
                                                    <%if (session.getAttribute("sessionOk") != null
                                                                && session.getAttribute("sessionOk").equals("ok")
                                                                && session.getAttribute("STUser").equals("Cliente")) {
                                                            %>
                                                        <h3 class="p1">Cita Registrada con exito!</h3>
                                                        <br>
                                                        <div class="wrapper p4">

                                                        </div>

                                                        <%}%>
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
        <script type="text/javascript"> Cufon.now();</script>
    </body>
</html>
