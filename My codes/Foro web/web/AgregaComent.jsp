<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head> 
   
    <body>
        
        <%if (session.getAttribute("usuario") == "visitante") {
        %><jsp:forward page="index.jsp">
            <jsp:param name="error"
                       value="es obligatorio identificarse"/>


        </jsp:forward>
        <%  }%>

        <jsp:useBean id="objConn" class="Mysql.MysqlConn"/>
        <%@page session="true"%>
        <%
            String IDusuario=(String)session.getAttribute("idUsuario");
            String respuesta="";
            int IDTema=Integer.parseInt(request.getParameter("idTem"));
            String update="";
            
            int x=0;
           if(request.getParameter("Coment")!=null){
                respuesta=request.getParameter("Coment");
      
            }

           
           if(respuesta==""  || respuesta==" "  || respuesta==null || respuesta.length()==0){
           
           }else{
           
            update="INSERT INTO foro.respuestas (idTema,idUsuario,respuesta)VALUES (" + "'"+IDTema+"'" + "," + "'"+ IDusuario+"',"+
                   "'"+ respuesta.replace("\'", "\''")+"')";
           
           }
          
 
           //update  usuario.usuarios  set usr='Lui',psw='123' where  psw='12345' and usr='Luiss'
           System.out.println(update);
           int n=0;
           try{
           n=objConn.Update(update); 
           }catch(Exception e){
           n=0;
           }
           
           //n indicara cuantos registros cumplen la condicion
          session.setAttribute("respuesta","false");
          
           //desconectamos
       
           //si existe al menos un registro
          
          
            if(n>0){%>
            <jsp:forward page="Respuestas.jsp">
               <jsp:param name="mensajeResp" value="Comentario exitoso"/>
            </jsp:forward>
           
        <% } else{ %>
        <jsp:forward page="Respuestas.jsp">
            <jsp:param name="mensajeResp" value="Hubo un problema al realizar comentario"/>
        </jsp:forward>
        <% } %>
            
    </body>
</html>
