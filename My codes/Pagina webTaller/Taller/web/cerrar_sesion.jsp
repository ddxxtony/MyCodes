<%-- 
    Document   : cerrar_sesion
    Created on : 19-nov-2016, 22:58:53
    Author     : ellui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
session.invalidate();
 

%>
<jsp:forward page="taller.jsp"/>
        