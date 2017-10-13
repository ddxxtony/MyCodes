<%-- 
    Document   : ReInicio
    Created on : 24-oct-2015, 23:25:56
    Author     : ellui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>re </title>
    </head>
    <body>
        <%
            if(request.getParameter("opcionAdmin")!=null){
        if(request.getParameter("opcionAdmin").equals("alta")){
              session.setAttribute("OpcAdmin", "alta");
                
        } else if(request.getParameter("opcionAdmin").equals("baja")){
                session.setAttribute("OpcAdmin", "baja");
        }else if(request.getParameter("opcionAdmin").equals("cambia"))      
        {
              session.setAttribute("OpcAdmin", "cambia");
        }else {
        session.setAttribute("OpcAdmin", "otro");
            
        }
            }
        
       session.setAttribute("BTemas","false");
   
        
        %>
            <jsp:forward page="Inicio.jsp"/>
    </body>
</html>
