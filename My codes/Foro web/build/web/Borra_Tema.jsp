<%-- 
    Document   : Borra_Tema
    Created on : 25-oct-2015, 12:54:50
    Author     : ellui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head> 
   
    <body>
          <%  if (session.getAttribute("usuario") == "visitante") {
        %><jsp:forward page="index.jsp">
            <jsp:param name="error"
                       value="es obligatorio identificarse"/>


        </jsp:forward>
        <%  }%>

        <jsp:useBean id="objConn" class="Mysql.MysqlConn"/>
        <%@page session="true"%>
        <%
            String idTema="";
            
            int x=0;
           if(request.getParameter("borraID")!=null){
                idTema=request.getParameter("borraID");
                x++;
            }
          String update="";
          update="DELETE FROM foro.tema_con where Id= " + "'"+idTema +"'" ;
 
          
             int n=0;
           try{
           n=objConn.Update(update); 
           }catch(Exception e){
   
           }
           //update  usuario.usuarios  set usr='Lui',psw='123' where  psw='12345' and usr='Luiss'
           System.out.println(update);
          
           
           update="DELETE FROM foro.respuestas where idTema= " + "'"+idTema +"'" ;
           
            int m=0;
           try{
           m=objConn.Update(update); 
           }catch(Exception e){
           
           }
           
           
            session.setAttribute("BTemas","false");

          
            if(n>0 || m>0){%>
            <jsp:forward page="Inicio.jsp">
               <jsp:param name="mensajeBorraT" value="Tema Borrado con exito"/>
            </jsp:forward>  
        <% } else{ %>
        <jsp:forward page="Inicio.jsp">
            <jsp:param name="mensajeBorraT" value="Problema al Borrar tema "/>
        </jsp:forward>
        <% } %>
            
    </body>
</html>