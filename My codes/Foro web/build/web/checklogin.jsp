<%-- 
    Document   : checklogin
    Created on : 07-oct-2015, 8:32:29
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
        <jsp:useBean id="cifrar" class="Mysql.Cifrador"/>
        <%@page session="true"%>
        <%
            String usuario="";
            String clave="";
            String id="";
            String privilegios="";
           if(request.getParameter("usr")!=""){
                usuario=request.getParameter("usr");
            }
           if(request.getParameter("clave")!=""){
               try{
                   clave=cifrar.sha1(request.getParameter("clave"));
                //clave=cifrar.sha1(request.getParameter("clave"));
            }catch(Exception e){}}
           ///base de datos
           String consulta="select * from foro.usuarios where USR='"+usuario+"' and PSW='"
                   +clave+ "'";
           //ejecutamos consulta
           objConn.Consult(consulta);
           
           //n indicara cuantos registros cumplen la condicion
           int n=0;
           if(objConn.rs!=null){
                try{
               objConn.rs.last();
               privilegios=objConn.rs.getString("priv");
               id=objConn.rs.getString("id");
                n=objConn.rs.getRow();
                }catch(Exception e){
                    }
              // objConn.closeRsStmt();
           }
           //desconectamos
       
           //si existe al menos un registro
          
          
            if(n>0){
               //if(usuario.equals())
            
            //if(usuario.equals("admin") && clave.equals("admin")){
                session.setAttribute("privilegios",privilegios);
                session.setAttribute("usuario",usuario);
                session.setAttribute("inicioSession","true");
                session.setAttribute("BTemas","false");
                session.setAttribute("idUsuario", id);
           
                
        %>
        
        <jsp:forward page="Inicio.jsp"/>
        <% } else{ %>
        <jsp:forward page="index.jsp">
            <jsp:param name="error" value="Usuario y/o clave incorrectos.<br>Vuelve a intentarlo."/>
        </jsp:forward>
        <% } %>
            
    </body>
</html>
