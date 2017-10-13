<%-- 
    Document   : MuestrainfoCita
    Created on : 27-nov-2016, 14:49:13
    Author     : ellui
--%>

<%@page import="Clases.Servicio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Clases.Auto"%>
<%@page import="OraclePackage.oracleConection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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

    <body>
        <br><br>   <br><br>   
    <article class="grid_3">
        <article class="grid_9">    


            <div class="wrapper">
                <article class="grid_8">
                    <!--AGREGAR CLIENTES  -->    <div class="indent-left"  id='Formulario'>

                        <%

                            if (request.getParameter("tipo").equals("consultaf")) {
                                ///ES CONSULTA POR FECHA
                                String fecha = request.getParameter("fecha");
                                oracleConection con = new oracleConection();
                                //TO_DATE('01-03-2011 07:01','DD-MM-YYYY HH24:MI')
                                String consulta = "";
                                consulta = "  select Cita.id_cita,Cita.fecha,Servicio.nombre,es_Para.precio"
                                        + " from Cita,Reserva,Servicio,es_para "
                                        + "where TO_DATE(Fecha,'DD/MM/YYYY') like TO_DATE('" + fecha + "','DD/MM/YYYY')"
                                        + "and Cita.id_cita=Reserva.Cita_id_cita "
                                        + "and Servicio.id_ser=Reserva.Servicio_id_ser "
                                        + "and Servicio.id_ser=es_Para.Servicio_id_ser"
                                        + " and es_Para.Auto_id_auto=Cita.Auto_id_auto";

                                //ejectamos consulta
                                con.Consult(consulta);
                                System.out.println(consulta);
                                //n indicara cuantos registros cumplen la condicion
                                int n = 0;
                                if (con.rs != null) {

                                    try {
                                        con.rs.last();
                                        //privilegios=objConn.rs.getString("priv");
                                        //id=objConn.rs.getString("id");
                                        n = con.rs.getRow();
                                        con.rs.first();

                                        if (n != 0) {//Es trabajador
                        %>
                        <table >
                            <tr>
                                <th>ID Cita</th>
                                <th>Fecha</th>    
                                <th>Servicio</th> 
                                <th>Precio</th>  
                            </tr>





                            <%
                                do {%>
                            <tr>
                                <td><%=con.rs.getString("id_cita")%></td>
                                <td><%=con.rs.getString("fecha")%></td>  
                                <td><%=con.rs.getString("nombre")%></td>
                                <td><%=con.rs.getString("precio")%></td>
                            </tr>
                            <%} while (con.rs.next());

                            %>
                        </table>    
                        <%                                            } else {
                                        out.write("'<p><font size=4 color=red>No se encontro informacion</font></p>'");
                                    }
                                } catch (Exception e) {
                                }
                                con.closeRsStmt();
                            } else {
                                out.write("'<p><font size=4 color=red>No se encontro informacion</font></p>'");
                            }
//////////////////ES CONSULTA POR ID

                        } else if (request.getParameter("tipo").equals("consultaid")) {
                            String fecha = request.getParameter("fecha");
                            oracleConection con = new oracleConection();
                            //TO_DATE('01-03-2011 07:01','DD-MM-YYYY HH24:MI')
                            String consulta = "";
                            consulta = "  select Cita.id_cita,Cita.fecha,Servicio.nombre,es_Para.precio"
                                    + " from Cita,Reserva,Servicio,es_para "
                                    + "where Cita.id_cita=" + fecha
                                    + "and Cita.id_cita=Reserva.Cita_id_cita "
                                    + "and Servicio.id_ser=Reserva.Servicio_id_ser "
                                    + "and Servicio.id_ser=es_Para.Servicio_id_ser"
                                    + " and es_Para.Auto_id_auto=Cita.Auto_id_auto";

                            //ejectamos consulta
                            con.Consult(consulta);
                            System.out.println(consulta);
                            //n indicara cuantos registros cumplen la condicion
                            int n = 0;
                            if (con.rs != null) {

                                try {
                                    con.rs.last();
                                    //privilegios=objConn.rs.getString("priv");
                                    //id=objConn.rs.getString("id");
                                    n = con.rs.getRow();
                                    con.rs.first();

                                    if (n != 0) {//Es trabajador
                        %>
                        <table >
                            <tr>
                                <th>ID Cita</th>
                                <th>Fecha</th>    
                                <th>Servicio</th> 
                                <th>Precio</th>  
                            </tr>





                            <%
                                do {%>
                            <tr>
                                <td><%=con.rs.getString("id_cita")%></td>
                                <td><%=con.rs.getString("fecha")%></td>  
                                <td><%=con.rs.getString("nombre")%></td>
                                <td><%=con.rs.getString("precio")%></td>
                            </tr>
                            <%} while (con.rs.next());

                            %>
                        </table>    
                        <%    } else {
                                            out.write("'<p><font size=4 color=red>No se encontro informacion</font></p>'");
                                        }
                                    } catch (Exception e) {
                                    }
                                    con.closeRsStmt();
                                } else {
                                    out.write("'<p><font size=4 color=red>No se encontro informacion</font></p>'");
                                }

                            } else if (request.getParameter("tipo").equals("BorrarId")) {

                                out.write("'<p><font size=4 color=red>Borrando cita</font></p>'");

                            } else if (request.getParameter("tipo").equals("ModificaCit")) {

                                 // out.write("'<p><font size=4 color=red>Modificando cita</font></p>'");
                                 %>

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
                                                                    <%   int i=8;                
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
                                                                                                       
                                                    
                                 
                                 
                                 
                        <%}
                        %>
                        <br><br><br>
                    </div>
                </article>
            </div>
        </article>
    </article>


</body>
</html>
