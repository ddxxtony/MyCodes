<%-- 
    Document   : index
    Created on : 24-oct-2015, 12:51:17
    Author     : ellui
--%>

<%@page import="Mysql.ListaTemas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Mysql.Temas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tatyc</title>
        <link rel="STYLESHEET" type="text/css" href="CSS/style.css"></head>

    <% if (session.getAttribute("BTemas").equals("false")) {
    %><jsp:forward page="Temas.jsp"/> <%
        }%>

    <body>

        <% ListaTemas temas;
            ListaTemas MVistos;
            ListaTemas MRecientes;
            int k = 0;
            int posicion = 0;
            session.setAttribute("respuesta", "false");
        %>

        <div class="tux" >
            <table border = 0>
                <tr>
               <td valign="bottom" > <center><IMG  
                        src="Images/tux.png" ></center>  </td> 
            
                <td valign="bottom">
                    <p class="titulo1"> Tatyc     </p>
                    <p class="titulo2"> La respuesta </p>     
                    <p class="titulo2">  a tus dudas </p>
                </td>
                
              
           </tr>
            </table>
            </div>
        
        
        
        
        <div style="  position: absolute;
                         z-index:7;
                         top:165px;
                         left:600px;
                         width:100px;
                     ">
            <a href="ReInicio.jsp"><center>  <IMG src="Images/Hom.png"  width="60" height="60"></center></a>
            
            
            
        </div>
        
        
           <div style="  position: absolute;
                         z-index:7;
                         top:190px;
                         left:750px;
                         width:150px;
                         word-wrap:break-word;">

                        <div class="transparente3">
                        </div>
             
                    <form action="Temas.jsp" method="post"> 
                   <input type="text" name="filtro" value="" size="22">
                   <input type="hidden" name="bandFiltro" value="">
                  
                      </form>  
        </div>
        
        
          <div style="  position: absolute;
                         z-index:7;
                         top:170px;
                         left:660px;
                         width:100px;
                     ">
            <center><IMG src="Images/buscar.png"  width="45" height="45"></center>
            
            
            
        </div>
        
      
        
        
        
        
        <div >
        <br><br><br><br><br> <br><br><br><br><br>

        <table>
            <tr>
                <td valign="top">

                    <div style="                    position: relative;
                         z-index:7;
                         top:20px;
                         left:200px;
                         width:800px;
                         word-wrap:break-word;">

                        <div class="transparente2">
                        </div>
                        <p class="titulo3"> Temas <p>
                    </div>



                    <%
                       /* if (request.getParameter("n") != null) {
                            if (Integer.parseInt(request.getParameter("n")) > 0) {
                                temas = (ListaTemas) session.getAttribute("temas");

                                for (int i = 0; i < temas.getLista().size(); i++) {
                                    posicion = (10 * (i + 1));

                    */%>


                    <div style="                    position:relative;
                         z-index:7;
                         top:<%=posicion%>px;
                         left:200px;
                         width:680px;
                         word-wrap:break-word;">

                        <div class="transparente2">
                        </div>

                        <%
                           /* out.println("<Br>");
                            String text = temas.getLista().get(i).getTema();
                            out.println(" <p class='titulo5'> <A HREF='auxPage."
                                    + "jsp?idtem=" + i + "&idr="
                                    + temas.getLista().get(i).getId() + "'>"
                                    + text + "</A></p>");

                            out.println("<Hr>");
                            text = temas.getLista().get(i).getConsulta();
                            if (text.length() >= 250) {
                                String Con = text.substring(0, 250);
                                out.println(Con + "");
                                out.print("...");
                            } else {
                                out.println(text + "");
                            }
                            out.println("<Br><br>");
                            if ((Integer.parseInt((String) session.getAttribute(
                                    "idUsuario"))
                                    == temas.getLista().get(i).getIdUsuario())
                                    || (session.getAttribute("privilegios").equals("0"))) {
                        */%>   

                        <form action="Borra_Tema.jsp" method="post"> 
                       <!--     <input type="hidden" name="borraID" value="<%//=temas.getLista().get(i).getId()%>"><br> -->
                            <p><input  type="submit" value="Borrrar Tema"> </p>
                            </form>
                        <br>
                        <%}%> 




                    </div>
                    <%}%>

                    <%  }
                        }%>

                    <% //if (session.getAttribute("privilegios").equals("0")
                        //        || session.getAttribute("privilegios").equals("1")) {%>
                    <%//posicion = posicion + 10;%>
                    <div style="         position: relative;
                         z-index:7;
                         top:<%=//posicion%>px;
                         left:200px;
                         width:800px;
                         word-wrap:break-word;">

                        <div class="transparente2">
                        </div>
                        <br><br>
                        <p class="titulo2"> Agregar nueva consulta <p>
                        <form action="AgregaTema.jsp" method="post"> 
                            Tema:<br><input type="text" name="tem" value="" size="75"><br>
                            Consulta:<br>
                            <textarea name="consul" rows="10" cols="77"></textarea>
                            <input type="hidden" name="usrId" value="alta"><br>
                            <p><input type="submit" value="Enviar Consulta"> </p>

                        </form>
                        <p><font color="red"><% if (request.getParameter("mensajeTema") != null) {
                               // out.println(request.getParameter("mensajeTema"));
                            //}

                            %></font></p>

                        <br><br>

                    </div>
                    <%//}%>



                    <%-- /////////////////////////////////////////Barra derecha/////////////////////////////////////////////////////--%>






                </td>

                <td valign="top">

                    <div style="                    position:relative;
                         z-index: 5;
                         top:60px;
                         left:200px;
                         width:200px;
                         padding: .5em;
                         word-wrap:break-word;">

                        <div class="transparente1">
                        </div>



                        <p class="titulo2"> Bienvenido <p>
                        <hr>
                        <% /* if (session.getAttribute("inicioSession").
                                 //   equals("false")) {
                                //if (request.getParameter("error") != null) {
                                  //  out.println("<font color='red'>"
                                    //        + request.getParameter("error") + "</font>");
                      //          }
                        //            */
                        %>
                        <br>
                        <P>Iniciar Sesion</p>
                        <form action="checklogin.jsp" method="post"> 
                            Usuario:<input type="text" name="usr" value=""><br>
                            Clave:<input type="password" name="clave" value=""><br>
                            <p><input type="submit" value="Iniciar Sesion"> </p>
                        </form>


                        <% /* } else {
                            out.println(session.getAttribute("usuario"));
                            if (request.getParameter("mensajeTema") != null) {
                                out.println("<font color='blue'>"
                                        + request.getParameter("mensajeTema") + "</font>");
                            }

                            //mensajeBorraT
                            if (request.getParameter("mensajeBorraT") != null) {
                                out.println("<font color='blue'>"
                                        + request.getParameter("mensajeBorraT") + "</font>");
                            }*/
                        %> 

                        <br><br>
                        <form action="index.jsp" method="post"> 
                            <p><input type="submit" value="Cerrar session"> </p>
                        </form>
                        <%//}%>

                    </div>


                    <div style="                 position:relative;
                         z-index: 5;
                         top:70px;
                         left:200px;
                         width:200px;
                         padding: .5em;
                         word-wrap:break-word;">

                        <div class="transparente1">
                        </div>


                        <hr>
                        <p class="titulo2"> Ultimos Temas <p>
                        <hr>

                          <% 
                        //int cont=0;
                        /*if(session.getAttribute("UltimosTemas")!=null ){
                            MRecientes = (ListaTemas) session.getAttribute("UltimosTemas");
                            
                            if ( MRecientes.getLista().size() > 0) {
                                k = MRecientes.getLista().size() - 1;
                                while (k >= 0 && cont<5 ) { */
                        %>
                        <UL>
                            
                           <% //out.println( "<LI> <a href='auxPage.jsp?idr="+cont+"&auxTema=trueT'>"+MRecientes.getLista().get(cont).getTema()+" </a></Li>" ); %>       
                        </UL>

                        <% // k--;
                       //     cont++;
                             //   }

                     //       }}
                        %>


                    </div>


                    <div style="                 position:relative;
                         z-index: 5;
                         top:80px;
                         left:200px;
                         width:200px;
                         padding: .5em;
                         word-wrap:break-word;">

                        <div class="transparente1">
                        </div>


                        <hr>
                        <p class="titulo2"> Mas Visitados<p>
                        <hr>

                        <%//  if( session.getAttribute("TemasMasV")!=null){     
                           //cont=0;
                            //MVistos = (ListaTemas) session.getAttribute("TemasMasV");
                                
                            //if (MVistos.getLista().size() > 0) {
                              //  k=0;
                               // while (k <MVistos.getLista().size() && cont<5) {
                        %>
                        <UL>
                        <%//out.println( "<LI> <a href='auxPage.jsp?idr="+cont+"&auxTema=trueV'>"+MVistos.getLista().get(k).getTema()+" </Li>" ); %>         
                        </UL>

                        <%  //k++;
                           //cont++;
//                                }

                           // }}
                        %>

                    </div>  

                </td>
            </tr>
        </table>

        <%///////////////////////////////// Herramientas de administrador

            //if (session.getAttribute("privilegios").equals("0")) {%> 
        <%    if (session.getAttribute("OpcAdmin").equals("alta")) { %>



        <div style="                 position:absolute;
             z-index: 5;
             top:20px;
             left:1020px;
             width:200px;
             padding: .5em;
             word-wrap:break-word;">

            <div class="transparente1">
            </div>

            Ingrese los siguientes datos
            <form action="OpcUsuarios.jsp" method="post"> 
                Usuario:<input type="text" name="usr" value=""><br>
                Clave:<input type="text" name="clave" value=""><br>
                Privilegios(0 o 1):<input type="text" name="priv" value=""><br>
                <input type="hidden" name="tarea" value="alta"><br>
                <input type="submit" value="Dar de Alta"> 
            </form>
            <a href="ReInicio.jsp?opcionAdmin=Re">Regresar</a>


        </div> 



        <%   } else if (session.getAttribute("OpcAdmin").equals("baja")) { %>

        <div style="                 position:absolute;
             z-index: 5;
             top:20px;
             left:1020px;
             width:200px;
             padding: .5em;
             word-wrap:break-word;">

            <div class="transparente1">
            </div>

            <p>Ingrese el usuario que desea borrar asi como la clave  </p>
            <form action="OpcUsuarios.jsp" method="post"> 
                Usuario:<input type="text" name="usr" value=""><br>
                Clave:<input type="text" name="clave" value=""><br>
                <input type="hidden" name="tarea" value="baja"><br>
                <input type="submit" value="Dar de Baja"> 
            </form>
            <a href="ReInicio.jsp?opcionAdmin=Re">Regresar</a>

        </div> 




        <%  } else if (session.getAttribute("OpcAdmin").equals("cambia")) { %>      
        <div style="                 position:absolute;
             z-index: 5;
             top:05px;
             left:1020px;
             width:200px;
             padding: .5em;
             word-wrap:break-word;">

            <div class="transparente1">
            </div>


            <p>Ingrese Los siguientes datos  </p>
            <form action="OpcUsuarios.jsp" method="post"> 
                Usuario:<input type="text" name="usr" value=""><br>
                Clave:<input type="text" name="clave" value=""><br>
                Nueva Clave:<input type="text" name="nclave" value=""><br>
                <input type="hidden" name="tarea" value="cambia"><br>
                <input type="submit" value="Cambiar"> 
            </form>
            <a href="ReInicio.jsp?opcionAdmin=Re">Regresar</a>

        </div> 
        <% //} else {%>         

        <div style="                 position:absolute;
             z-index: 5;
             top:20px;
             left:1020px;
             width:200px;
             padding: .5em;
             word-wrap:break-word;">

            <div class="transparente1">
            </div>


            <hr>
            <p class="titulo4">Herramientas Administrdor <p>
            <p><font color="red"><%// if (request.getParameter("mensaje") != null) {
              //      out.println(request.getParameter("mensaje"));
                //}
                %></font></p>
            <UL>
                <LI><a href="ReInicio.jsp?opcionAdmin=alta">Dar de alta un usuario </a>
                <LI><a href="ReInicio.jsp?opcionAdmin=baja">Borrar usuario</a> 
                <LI><a href="ReInicio.jsp?opcionAdmin=cambia">Cambiar cotraseña de usuario</a> 
            </UL>
            <hr>
        </div>   



        <% //}   %>

        <% }////////////////////////////////////////////////////////////////////////%>

        <br><br><br><br>    
    </body>

</html>
