<%-- 
    Document   : index
    Created on : 24-oct-2015, 12:51:17
    Author     : ellui
--%>

<%@page import="Mysql.ListaTemas"%>
<%@page import="Mysql.ListaRespuestas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   
    <head>
        
   
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tatyc</title>
         <link rel="STYLESHEET" type="text/css" href="CSS/style.css"></head>
    
    <% if (session.getAttribute("respuesta").equals("false")){
               %><jsp:forward page="BRespuesta.jsp">
                   <jsp:param name="idr" value="<%=session.getAttribute("idr") %>"/>
               </jsp:forward>                
                 <%     
             }%>
    
    
         
    <body>
        
        <% ListaRespuestas respuestas= new ListaRespuestas();
        ListaTemas MVistos;
            ListaTemas MRecientes;
            int k=0;
       String f="false";
       session.setAttribute("BTemas",f);
       session.setAttribute("idTema",(String)request.getParameter("idtem"));
       session.setAttribute("respuesta", f);
             int posicion=0;
       %>
       
        <div class="tux" >
           <table border = 0>
               <tr>
                <td valign="bottom"><center><IMG  src="Images/tux.png" ></center></td>   
                <td valign="bottom">
                <p class="titulo1"> Tatyc     </p>
                <p class="titulo2"> La respuesta </p>     
                <p class="titulo2">  a tus dudas </p>
                <td>
            </tr>
         </table> 
         </div> 
       <br><br><br><br><br> <br><br><br><br><br><br><br><br><br>
               
        <div style="  position: absolute;
                         z-index:7;
                         top:165px;
                         left:850px;
                         width:100px;
                     ">
                <a href="ReInicio.jsp"><center>  <IMG src="Images/Hom.png"  width="60" height="60"></center></a>
            
      
        </div>
        
        
         
        
    <Table>
        <tr>
            <td  valign="top">
                
       <% 
       
                    ListaTemas temas=(ListaTemas)session.getAttribute("temas");%>
        <div style="                    position: relative;
					z-index:7;
					top:0px;
				         left:200px;
					width:680px;
					word-wrap:break-word;">
					
				<div class="transparente2">
				</div>
                                  <%  
                                
                                        out.println("<Br><br>");
                                        String text=temas.getLista().get(Integer.parseInt(((String)session.getAttribute("idtem"))))
                                                .getTema();
                                      out.println("<A HREF='Respuestas.jsp?idtem="+temas.getLista().get(Integer.
                                              parseInt((String)session.getAttribute("idtem"))).getId()+
                                              "'>"+text+"</A>");
                                       
                                      out.println("<Hr><Br>");
                                      text=temas.getLista().get(Integer.parseInt(
                                            (String)session.getAttribute("idtem"))).getConsulta();
                                      
                                          out.println(text+""); 
                                      
                                      out.println("<Br><br>");
                                        out.println("<Br><br>");
                                    out.println("<br><br>");%>
        </div>       
       
          <div style="                 position: relative;
					z-index:7;
					top:0px;
				         left:200px;
					width:800px;
					word-wrap:break-word;">
					
				<div class="transparente2">
				</div>
                                 <p class="titulo3"> Respuestas <p>
        </div>
     
        <%       if (request.getParameter("n")!=null){
               if (Integer.parseInt(request.getParameter("n"))>0){
               respuestas=(ListaRespuestas)session.getAttribute("respuestasL");
         
              for(int i=0;i<respuestas.getLista().size();i++){ 
                     posicion=(10*(i+1)); %>
                                                            
                 
       <div style="                    position: relative;
					z-index:7;
					top:<%=posicion%>px;
				        left:200px;
					width:680px;
					word-wrap:break-word;">
					
				<div class="transparente2">
				</div>
                                <%   
                                    out.println("<br><br>");
                                    String Respuesta=respuestas.getLista().get(i).getRespuesta();
                                    out.println(Respuesta);
                                    out.println("<br><br>");
                                       
                                      
                            if ((Integer.parseInt((String) session.getAttribute(
                                    "idUsuario"))
                                       == respuestas.getLista().get(i).getIdUsuario())
                                    || ( session.getAttribute("privilegios").equals("0"))) {
                        %>   

                                    <form action="Borra_comentario.jsp" method="post"> 
                                        <input type="hidden" name="borraID" value="<%=respuestas.getLista().get(i).getId()%>"><br>
                                        <p><input type="submit" value="Borrrar Comentario"> </p>
                                    </form>
                                 
                        <%}%> 
                             
                             
           
        </div>
              <%}%>
             
       <%   }}%>
       
         <% if(session.getAttribute("privilegios").equals("0") || 
               
       session.getAttribute("privilegios").equals("1"))     {%>
      <div style="                 position: relative;
					z-index:7;
					top:<%=posicion-15%>px;
				         left:200px;
					width:800px;
					word-wrap:break-word;">
					
				<div class="transparente2">
				</div>
                                <p class="titulo2"> Deje su comentario <p>
                                <form action="AgregaComent.jsp" method="post">
                                 <textarea name="Coment" rows="10" cols="77"></textarea>
                            
                                 <input type="hidden" name="idTem" value="<%=request.getParameter("idr")%>">
                                 <p><input type="submit" value="Comentar"> </p>
                                <br>
                                  </form>
                                
                              </div>  
                               
       <%}%>
        
        
        
        
               
                    
                    
                    <%//////////////////////////////////////////////////////////////////////////////////////////////////%>
        <td valign="top">
        <div style="                    position:relative;
					z-index: 5;
					top:0px;
				        left:200px;
					width:200px;
                                        padding: .5em;
					word-wrap:break-word;">
					
				<div class="transparente1">
                                </div>
            
            
            
             <p class="titulo2"> Bienvenido <p>
                 <hr>
                 <%
              out.println( session.getAttribute("usuario")); 
              if(request.getParameter("mensajeResp")!=null){
                         out.println("<font color='blue'>" +
                                            request.getParameter("mensajeResp") + "</font>");
                            
                        }   
              if( session.getAttribute("inicioSession").equals("true")){
                  if(request.getParameter("mensajeBorraC")!=null){
                         out.println("<font color='blue'>" +
                                            request.getParameter("mensajeBorraC") + "</font>");
                            
                        }    
                            %>         
              <br><br>
                      <form action="index.jsp" method="post"> 
                       <p><input type="submit" value="Cerrar session"> </p>
                    </form>
           <%}%>
          </div>
              
              
           <div style="                 position:relative;
					z-index: 5;
					top:10px;
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
                 int cont=0;
                        if(session.getAttribute("UltimosTemas")!=null ){
                 MRecientes = (ListaTemas) session.getAttribute("UltimosTemas");

                            if (MRecientes.getLista().size() > 0) {
                                k = MRecientes.getLista().size() - 1;
                                while (k >= 0 && cont<5) {
                        %>
                           <UL>
                            
                           <% out.println( "<LI> <a href='auxPage.jsp?idr="+cont+"&auxTema=trueT'>"+MRecientes.getLista().get(cont).getTema()+" </a></Li>" ); %>       
                        </UL>

                        <%  k--;
                            cont++;
                                }

                            }}
                        %>
            
          </div>   
              
           <div style="                 position:relative;
					z-index: 5;
					top:22px;
				        left:200px;
					width:200px;
                                        padding: .5em;
					word-wrap:break-word;">
					
				<div class="transparente1">
                                </div>
            
            
               <hr>
             <p class="titulo2"> Mas Visitados<p>
                 <hr>
                 
                  <%    if( session.getAttribute("TemasMasV")!=null){ 
                      cont=0;
                        MVistos = (ListaTemas) session.getAttribute("TemasMasV");
                                
                            if (MVistos.getLista().size() > 0) {
                                k =0;
                                while (k <MVistos.getLista().size()  && cont<5) {
                        %>
                        <UL>
                           <%out.println( "<LI> <a href='auxPage.jsp?idr="+cont+"&auxTema=trueV'>"+MVistos.getLista().get(k).getTema()+" </Li>" ); %>            
                        </UL>

                        <%  k++;
                        cont++;
                                }

                            }}
                        %>
                 
                 
            </div>  
              
              
        </td>
        </tr>
        </table>
       <br><br><br><br>  <br><br><br> <br><br><br>
    </body>
</html>

