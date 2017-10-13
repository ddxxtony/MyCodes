<%-- 
    Document   : ConMysql
    Created on : 16-oct-2015, 8:40:17
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
           String update="INSERT INTO foro.usuarios (usr,psw)VALUES (" + "'"+usuario+"'" + "," + "'"+clave+"'"+ ")";
           
            //ejecutamos consulta
           int n=0;
           if(x==2){
           n=objConn.Update(update); }
           
           //n indicara cuantos registros cumplen la condicion
          
          
           //desconectamos
       
           //si existe al menos un registro
          
          
            if(n>0){%>
            <jsp:forward page="Crea_usuario.jsp">
               <jsp:param name="mensaje" value="Usuario Agregado con exito"/>
            </jsp:forward>
           
        <% } else{ %>
        <jsp:forward page="Crea_usuario.jsp">
            <jsp:param name="mensaje" value="Hubo un problema no se pudo agregar ususario "/>
        </jsp:forward>
        <% } %>
            
    </body>
</html>