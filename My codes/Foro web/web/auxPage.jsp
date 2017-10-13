<%-- 
  Document   : index
    Created on : 24-oct-2015, 12:51:17
    Author     : ellui
--%>

<%@page import="Mysql.ListaTemas"%>
<%@page import="Mysql.posVector"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>re </title>
    </head>
    <body>
         <jsp:useBean id="objConn" class="Mysql.MysqlConn"/>
         <jsp:useBean id="cifrar" class="Mysql.Cifrador"/>
          <%@page session="true"%>
        <%
            
            
            
        if(request.getParameter("auxTema")!=null){
      
        
       int idTem=0;
       posVector pos=new posVector();
       ListaTemas tema1;
       ListaTemas tema2;
       if(request.getParameter("auxTema").equals("trueT")){
        
      tema1=(ListaTemas)session.getAttribute("UltimosTemas");
       tema2=(ListaTemas) session.getAttribute("temas");
        }else{
       tema1=(ListaTemas) session.getAttribute("TemasMasV");
       tema2=(ListaTemas) session.getAttribute("temas");
       }
      
       idTem=pos.Getposicion(tema1, tema2, Integer.parseInt(request.getParameter("idr")));
       
        session.setAttribute("idr",request.getParameter("idr"));
        String id=""+idTem;
       session.setAttribute("idtem", id);
       
           
          
       }else{ 
            int x=0;
        
          String update="select * from foro.tema_con where Id='"+request.getParameter("idr")+"'";
          System.out.println(update);
           
          int n=0;
         
           try{
           objConn.Consult(update); 
           }catch(Exception e){
               n=0;
           }
         
           x=Integer.parseInt(objConn.rs.getString("nVisitas"));
           
          x=x+1;
           
          
          update="update  foro.tema_con SET nVisitas="+"'"+x+"'"+
                   " where  Id="+"'"+request.getParameter("idr")+"'";
           
           
                 n=0;
         
           try{
           n=objConn.Update(update); 
           }catch(Exception e){
               n=0;
           }
          
 
           //update  usuario.usuarios  set usr='Lui',psw='123' where  psw='12345' and usr='Luiss'
      
           
           
        
           
        
       session.setAttribute("idr",request.getParameter("idr"));
       session.setAttribute("idtem", request.getParameter("idtem"));
       }
        
        %>
            <jsp:forward page="Respuestas.jsp"/>
    </body>
</html>