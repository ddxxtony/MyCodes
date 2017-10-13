<%-- 
    Document   : Mysql Update2
    Created on : 16-oct-2015, 10:02:16
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
        <jsp:useBean id="objConn" class="Mysql.MysqlConn"/>
        <%@page session="true"%>
        <%
            String usuario="";
            String clave="";
            int x=0;
           if(request.getParameter("usuario")!=null){
                usuario=request.getParameter("usuario");
                x++;
            }
           if(request.getParameter("clave")!=null){
                clave=request.getParameter("clave");
                x++;
            }
           ///base de datos
           String update="DELETE FROM foro.usuarios where usr= " + "'"+usuario +"' and psw=" +"'"+ clave+"'" ;
          
            //ejecutamos consulta
           int n=0;
           if(x==2){
           n=objConn.Update(update); }
           
           //n indicara cuantos registros cumplen la condicion
          
          
           //desconectamos
       
           //si existe al menos un registro
          
          
            if(n>0){%>
            <jsp:forward page="Borra_usuario.jsp">
               <jsp:param name="mensaje" value="Usuario Borrado con exito"/>
            </jsp:forward>
           
        <% } else{ %>
        <jsp:forward page="Borra_usuario.jsp">
            <jsp:param name="mensaje" value="Hubo un problema no se pudo borrar ususario "/>
        </jsp:forward>
        <% } %>
            
    </body>
</html>