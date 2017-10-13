<%-- 
    Document   : index
    Created on : 24-oct-2015, 12:51:17
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
       
        
      <%  
         session.setAttribute("privilegios","2");
          String inicioSession="false"; 
         String usuario="visitante";
         String clave="visitante";
         String BanderaTemas="false"; 
         session.setAttribute("inicioSession", inicioSession);
         session.setAttribute("usuario", usuario);
         session.setAttribute("clave", clave);
         session.setAttribute("BTemas", BanderaTemas);
         session.setAttribute("privilegios", 2);
         session.setAttribute("respuesta",BanderaTemas);
         session.setAttribute("OpcAdmin", "otro");
         session.setAttribute("idUsuario", "3");
           
     %>
    </body>
    
    <jsp:forward page="Inicio.jsp"/>
</html>
