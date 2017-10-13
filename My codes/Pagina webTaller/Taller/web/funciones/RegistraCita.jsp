<%-- 
    Document   : RegistraCita
    Created on : 27-nov-2016, 15:34:23
    Author     : ellui
--%>

<%@page import="Clases.Cita"%>
<%@page import="Clases.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Clases.Servicio"%>
<%@page import="Clases.Auto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>

    <head>
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
    </head>
    <body>

    <article class="grid_9">    

        <%

            if (request.getParameter("frame").equals("1")) {

                Auto aut = new Auto();
                String Sauto = aut.Consulta_auto(request.getParameter("nombreC"), request.getParameter("ModeloC"), request.getParameter("MarcaC"), request.getParameter("TipoC"), request.getParameter("Cilindros"));
                if (Sauto != null && !Sauto.equals("")) {

                    session.setAttribute("IdAuto", Sauto);
                    //mostramos el siguiente div
        %>



        <!-- AVANZA A LA SIGUIENTE LOS VALORES ESTAN BIEN-->              
        <div class="wrapper" id="DivRegistro3">
            <form id="contact-form" method="post" enctype="multipart/form-data">                    
                <fieldset>  
                    <h3 class="p1"><span>Informacion de la Cita</span></h3>

                    <label><span class="text-form">Fecha:</span>
                        <input type="text" name="fechaC"  id='fechaC' class="campofecha" placeholder="Like 30/11/2016"  onblur="getHours()" size="12">
                    </label>
                    <div id="horasCita">
                        <label><span class="text-form">Hora</span>
                            <select name="horarioC" id='horarioC' style="width: 279px; height: 25px;">
                                <%   int i = 8;
                                    String hora;
                                    while (i <= 18) {
                                        hora = "" + i + ":00";
                                        out.print("<option>" + hora + "</option>");
                                        hora = "" + i + ":30";
                                        out.print("<option>" + hora + "</option>");
                                        i++;

                                    }%>
                            </select></label>
                    </div>
                </fieldset>	

            </form>
            <article class="grid_2">
                <div style="padding-left: -1000px;">
                    <div class="buttons">

                        <a href="#" class="button"  onClick="RecargaDiv0()">Atras</a>
                    </div> 
                </div>
            </article>
            <article class="grid_2">
                <div style="padding-left: -1000px;">
                    <div class="buttons">

                        <a href="#" class="button"  onClick="RecargaDiv2()">Siguiente</a>
                    </div> 
                </div>
            </article>

            <div  id="Resultado">
                <article class="grid_10">
                    <div class="indent-left"  id='Formulario'>


                    </div>
                </article>
            </div>
        </div>





        <!-- SE QUEDA EN LA MISMA-->                       

        <% } else {
        %>
        <div class="wrapper">
            <article class="grid_8">
                <!--AGREGAR CLIENTES  -->    <div class="indent-left"  id='Formulario'>
                    <form id="contact-form" method="post" enctype="multipart/form-data">                    
                        <fieldset>                                                        
                            <h3 class="p1" > <span>Informacion de Auto</span></h3>
                            <label><span class="text-form">Marca:</span><select name="car_brand" id="MarcaC"> 
                                    <%
                                        Auto[] auto;
                                        auto = Auto.Consulta_Marca();

                                        int i = 0;
                                        while (i < auto.length) {
                                            out.print("<option>" + auto[i].marca + "</option>");
                                            i++;
                                        }%>

                                </select></label>
                            <label><span class="text-form">Nombre:</span><select  name="car_Nombre" id="nombreC"> 
                                    <%
                                        auto = Auto.Consulta_Nombre();
                                        i = 0;
                                        while (i < auto.length) {
                                            out.print("<option>" + auto[i].nombre + "</option>");
                                            i++;
                                        }%>

                                </select></label>
                            <label><span class="text-form">Tipo:</span><select name="car_tipo"  id="TipoC">
                                    <%
                                        Auto[] auto3;
                                        auto = Auto.Consulta_tipo();
                                        i = 0;
                                        while (i < auto.length) {
                                            out.print("<option>" + auto[i].tipo + "</option>");
                                            i++;
                                        }%>

                                </select></label>
                            <label><span class="text-form">Modelo:</span><select name="car_model" id="ModeloC">
                                    <%

                                        i = 0;
                                        auto = Auto.Consulta_Modelo();
                                        while (i < auto.length) {
                                            out.print("<option>" + auto[i].modelo + "</option>");
                                            i++;
                                        }%>

                                </select></label>
                            <label><span class="text-form">Cilindros:</span><select name="car_model" id="CilindrosC" style="width: 120px; height: 25px;">
                                    <option>4</option>
                                    <option>6</option>
                                    <option>8</option>

                                </select></label>
                            <label>

                            </label>
                            <article class="grid_7">
                                <div class="buttons">

                                    <a href="#" class="button"  onClick="RecargaDiv1()">Siguiente</a>
                                </div> 
                            </article>
                        </fieldset>						
                    </form>
                    <div  id="Resultado">
                        <article class="grid_10">
                            <div class="indent-left"  id='Formulario'>

                                <%        out.write("'<p><font size=4 color=red>Verifique los datos ingresados</font></p>'");%>
                            </div>
                        </article>
                    </div>









                    <!-- FORMULARIO PARA CAPTURAR LOS SERVICIOS  QUE VA A REQUERIR EL CLEINTE-->    
                    <%}
                    } else if (request.getParameter("frame").equals("2")) {
                        session.setAttribute("fechaC", request.getParameter("fecha"));
                        session.setAttribute("horaC", request.getParameter("hora"));
                    %>
                    <div style="padding-left: 3px;">
                        <h3 class="p1" ><span>Tipo de Servicio Requerido</span></h3>

                        <div class="wrapper" id='ServSolicitados'>

                            <% int i = 0;
                                if (session.getAttribute("ListServices") != null) {

                                    ArrayList<Servicio> service;
                                    service = (ArrayList<Servicio>) request.getSession().getAttribute("ListServices");
                                    i = 0;
                                    out.print("<table>");
                                    out.print("<tr>");
                                    out.print("<td width='20%'>Servicio</td>");
                                    out.print("<td width='20%'>Precio</td>");
                                    out.print("</tr>");
                                    while (i < service.size()) {
                                        out.print("<tr>");
                                        out.print("<td width='20%'><img src=\"images/minus.png\"  width=\"20\" height=\"20\" id=\"addService\" onclick='Remove_Service(" + service.get(i).id_servicio + ")' v>&nbsp;&nbsp;" + service.get(i).nombre + "</td>");

                                        out.print("<td width='20%'>");
                                        out.print(service.get(i).precio);
                                        out.print("</td>");
                                        out.print("</tr>");
                                        i++;

                                    }
                                    out.print("</table>");
                                }%>
                            <!--    <ul class="list-1">
                                    <li><a href="infoServ.jsp?idServ=<%//=Servicios[index].id_servicio%>">
                            <%//=//Servicios[index].nombre%></a></li> -->

                        </div>
                        <Hr>
                        <label><span class="text-form">Servicio:</span><select name="servicio" id='Servicio_opc' >
                                <%  
                                    Servicio aux = new Servicio();
                                    String auto = session.getAttribute("IdAuto").toString();
                                    Servicio[] servicios;
                                    i = 0;
                                    servicios = aux.Consultar_servicios(auto, 234);
                                    session.setAttribute("servicios", servicios);
                                    if (servicios != null) {
                                        while (i < servicios.length) {
                                %>

                                <option><%=servicios[i].nombre%></option>

                                <%i++;
                                        }
                                    }%>

                            </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a >  <img src="images/plus.png"  width="20" height="20" id="addService" onclick="Add_Service()"> </a>   
                        </label>

                    </div>

                    <br><br><br>
                        <div id="messageC">
                            </div>
                    <article class="grid_3">
                        <div style="padding-left: -1000px;">
                            <div class="buttons">

                                <a class="button" href="#" onClick="RecargaDiv1()">Regresar</a>
                            </div> 
                        </div>
                    </article>
                    <article class="grid_3">
                        <div style="padding-left: -1000px;">
                            <div class="buttons">

                                <a class="button"  onClick="Registra_cita()">Concretar cita</a>
                            </div> 
                        </div>
                    </article>







                    <!-- SOLO REGRESAMOS EL SELECT DE HORAS DISPONIBLES-->    

                    <%  } else if (request.getParameter("frame").equals("10")) {%>



                    <label><span class="text-form">Hora</span>
                        <select name="horarioC" id='horarioC' style="width: 279px; height: 25px;">

                            <%

                                int i = 8;
                                String hora;
                                while (i <= 18) {
                                    hora = "" + i + ":00";
                                    out.print("<option>" + hora + "</option>");
                                    hora = "" + i + ":30";
                                    out.print("<option>" + hora + "</option>");
                                    i++;

                                }%>
                        </select></label>


                    <%   } else if (request.getParameter("frame").equals("100")) {
                        if (session.getAttribute("ListServices") != null) {

                                    ArrayList<Servicio> service;
                                    service = (ArrayList<Servicio>) request.getSession().getAttribute("ListServices");
                                        Auto aut=new Auto();
                                      String fechac=  session.getAttribute("fechaC").toString();
                                       String horac=  session.getAttribute("horaC").toString();
                                        Cliente clie=(Cliente)session.getAttribute("InfoUsr");
                                        String idCoche=session.getAttribute("IdAuto").toString();

                                   int i=0;
                                   Cita cita=new Cita();
                                    while (i < service.size()) {
                                        cita.alta_cita(fechac, horac,clie.usuario,idCoche);
                                       // out.print("<td width='20%'><img src=\"images/minus.png\"  width=\"20\" height=\"20\" id=\"addService\" onclick='Remove_Service(" + service.get(i).id_servicio + ")' v>&nbsp;&nbsp;" + service.get(i).nombre + "</td>");

                                   i++;

                                    }
                    %>
                                        
                    
                        `           <h3 class="p1" ><span>Cita generada con exito</span></h3>
                    
                    
                    <%                  } } else {
                    %>

                    <div class="wrapper">
                        <article class="grid_8">
                            <!--AGREGAR CLIENTES  -->    <div class="indent-left"  id='Formulario'>
                                <form id="contact-form" method="post" enctype="multipart/form-data">                    
                                    <fieldset>                                                        
                                        <h3 class="p1" > <span>Informacion de Auto</span></h3>
                                        <label><span class="text-form">Marca:</span><select name="car_brand" id="MarcaC"> 
                                                <%
                                                    Auto[] auto;
                                                    auto = Auto.Consulta_Marca();

                                                    int i = 0;
                                                    while (i < auto.length) {
                                                        out.print("<option>" + auto[i].marca + "</option>");
                                                        i++;
                                                    }%>

                                            </select></label>
                                        <label><span class="text-form">Nombre:</span><select  name="car_Nombre" id="nombreC"> 
                                                <%
                                                    auto = Auto.Consulta_Nombre();
                                                    i = 0;
                                                    while (i < auto.length) {
                                                        out.print("<option>" + auto[i].nombre + "</option>");
                                                        i++;
                                                    }%>

                                            </select></label>
                                        <label><span class="text-form">Tipo:</span><select name="car_tipo"  id="TipoC">
                                                <%
                                                    Auto[] auto3;
                                                    auto = Auto.Consulta_tipo();
                                                    i = 0;
                                                    while (i < auto.length) {
                                                        out.print("<option>" + auto[i].tipo + "</option>");
                                                        i++;
                                                    }%>

                                            </select></label>
                                        <label><span class="text-form">Modelo:</span><select name="car_model" id="ModeloC">
                                                <%

                                                    i = 0;
                                                    auto = Auto.Consulta_Modelo();
                                                    while (i < auto.length) {
                                                        out.print("<option>" + auto[i].modelo + "</option>");
                                                        i++;
                                                    }%>

                                            </select></label>
                                        <label><span class="text-form">Cilindros:</span><select name="car_model" id="CilindrosC" style="width: 120px; height: 25px;">
                                                <option>4</option>
                                                <option>6</option>
                                                <option>8</option>

                                            </select></label>
                                        <label>

                                        </label>
                                        <article class="grid_7">
                                            <div class="buttons">

                                                <a href="#" class="button"  onClick="RecargaDiv1()">Siguiente</a>
                                            </div> 
                                        </article>
                                    </fieldset>						
                                </form>
                                <div  id="Resultado">
                                    <article class="grid_10">
                                        <div class="indent-left"  id='Formulario'>


                                        </div>
                                    </article>
                                </div>


                                <%}%>                        
                                <br><br><br>
                            </div>
                        </article>
                    </div>
            </article>
    </article>

</body>
</html>
