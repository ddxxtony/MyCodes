<%-- 
    Document   : MysqlUpdate
    Created on : 19-oct-2015, 7:37:10
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

          if (session.getAttribute("usuario") == "visitante") {
        %><jsp:forward page="index.jsp">
            <jsp:param name="error"
                       value="es obligatorio identificarse"/>


        </jsp:forward>
        <%  }%>
        <jsp:useBean id="objConn" class="Mysql.MysqlConn"/>
         <jsp:useBean id="cifrar" class="Mysql.Cifrador"/>
         
        <%@page session="true"%>
        <%
            String usuario="";
            String clave="";
            String nclave="";
            String privilegios="";
            
            int x=0;
           if(request.getParameter("usr")!=null){
                usuario=request.getParameter("usr");
                x++;
            }
           if(request.getParameter("priv")!=null){
                privilegios=request.getParameter("priv");
                x++;
            }
           if(request.getParameter("clave")!=null){
                clave=cifrar.sha1(request.getParameter("clave"));
                
                x++;
            }
           if(request.getParameter("nclave")!=null){
                nclave=request.getParameter("nclave");
                x++;
            }
          String update="";
           
          
           if( request.getParameter("tarea").equals("alta")){
               update="INSERT INTO foro.usuarios (usr,psw,priv)VALUES (" + "'"+usuario+"'" + "," + "'"+clave+"',"+
                     "'"+privilegios+"'"+ ")";
           
           }else if( request.getParameter("tarea").equals("baja")){
                 update="DELETE FROM foro.usuarios where usr= " + "'"+usuario +"' and psw=" +"'"+ clave+"'" ;
           
           
           }else if( request.getParameter("tarea").equals("cambia")){
                update="update  foro.usuarios SET usr="+"'"+usuario+"' ,psw="+"'"+nclave+"'" +
                   " where  psw="+"'"+clave+"' and usr="+ "'"+usuario+"'";
           
           
           }
          
 
           //update  usuario.usuarios  set usr='Lui',psw='123' where  psw='12345' and usr='Luiss'
           System.out.println(update);
           int n=0;
           try{
           n=objConn.Update(update); 
           }catch(Exception e){
           n=0;
           }
           
           
           
            session.setAttribute("BTemas","false");
            session.setAttribute("OpcAdmin", "otro");
           //n indicara cuantos registros cumplen la condicion
          
          
           //desconectamos
       
           //si existe al menos un registro
          
          
            if(n>0){%>
            <jsp:forward page="Inicio.jsp">
               <jsp:param name="mensaje" value="Tarea realizada con exito"/>
            </jsp:forward>
           
        <% } else{ %>
        <jsp:forward page="Inicio.jsp">
            <jsp:param name="mensaje" value="Problema al realizar tarea "/>
        </jsp:forward>
        <% } %>
            
    </body>
</html>