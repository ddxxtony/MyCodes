<%-- 
    Document   : BRespuesta
    Created on : 24-oct-2015, 17:22:13
    Author     : ellui
--%>

<%@page import="Mysql.Respuestas"%>
<%@page import="Mysql.ListaTemas"%>
<%@page import="Mysql.Temas"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta Temas</title>
    </head> 
   
    <body>
        <jsp:useBean id="objConn" class="Mysql.MysqlConn"/>
       
         <jsp:useBean id="Obj" class="Mysql.ListaRespuestas"/>
        <%@page session="true"%>
        <%
            ArrayList <Respuestas> lista=new ArrayList<Respuestas>();
            //ListaTemas Obj=new ListaTemas();
            Respuestas rsp=new Respuestas();
           String usuario="";
           String clave="";
           String privilegio="";
           usuario=(String)session.getAttribute("usuario");
           clave=(String)session.getAttribute("clave");
           String bandera="true";
           session.setAttribute("respuesta", bandera);
           
           String consulta="select * from foro.respuestas where idTema="+Integer.parseInt(request.getParameter("idr"));
           //ejecutamos consulta
           objConn.Consult(consulta);
           //n indicara cuantos registros cumplen la condicion
           int n=0;
           if(objConn.rs!=null){
                try{
               objConn.rs.last();
                n=objConn.rs.getRow();
                }catch(Exception e){
                    }
             
           }
           if(n>0){
           objConn.rs.first();
           do{
               rsp=new Respuestas();
               rsp.setId(Integer.parseInt(objConn.rs.getString("id")));
               rsp.setIdTema(Integer.parseInt(objConn.rs.getString("idTema")));
               rsp.setIdUsuario(Integer.parseInt(objConn.rs.getString("idUsuario")));
               rsp.setRespuesta(objConn.rs.getString("respuesta"));
               lista.add(rsp);
            
               
            
           }while(objConn.rs.next()); 
           
           }           
           Obj.setLista(lista);
           session.setAttribute("respuestasL", Obj);
           //desconectamos
         objConn.closeRsStmt();
           //si existe al menos un registro
          System.out.println(n+" "+consulta);
          
         
                
        %>
        
        <jsp:forward page="Respuestas.jsp">
            <jsp:param name="n" value="<%=n%>"/>
                
        </jsp:forward>
    </body>
</html>