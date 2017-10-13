<%-- 
    Document   : Temas
    Created on : 24-oct-2015, 12:45:18
    Author     : ellui
--%>

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
       
         <jsp:useBean id="Obj" class="Mysql.ListaTemas"/>
        <%@page session="true"%>
        <%
            ArrayList <Temas> lista=new ArrayList<Temas>();
             ArrayList <Temas> lista2=new ArrayList<Temas>();
              ArrayList <Temas> lista3=new ArrayList<Temas>();
            //ListaTemas Obj=new ListaTemas();
            Temas temas=new Temas();
           String usuario="";
           String filtro="";
           String clave="";
           String tem="";
           String privilegio="";
           usuario=(String)session.getAttribute("usuario");
           clave=(String)session.getAttribute("clave");
           String bandera="true";
           session.setAttribute("BTemas", bandera);
           
             String consulta="select * from foro.tema_con";
           if(request.getParameter("bandFiltro")!=null){
               filtro=request.getParameter("filtro");
           }
         consulta+=" where Tema like '%"+filtro+"%'";
           //ejecutamos consulta
           objConn.Consult(consulta);
           //n indicara cuantos registros cumplen la condicion
//           SELECT * FROM foro.tema_con  order by date_format(fecha,'%y/%m/%d') asc;
         //  select * from foro.tema_con where tema like 'sdsdfd%';
           int n=0;
           int k=0;
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
              // new String(request.getParameter("mensaje").getBytes("ISO-8859-1"),"UTF-8");
               temas=new Temas();
               tem=new String(objConn.rs.getString("Tema").getBytes("ISO-8859-1"),"UTF-8");
               temas.setTema(tem);
               temas.setId(Integer.parseInt(objConn.rs.getString("id")));
               tem=new String(objConn.rs.getString("consulta").getBytes("ISO-8859-1"),"UTF-8");
               temas.setConsulta(tem);
               temas.setFecha(objConn.rs.getString("fecha"));
               temas.setIdUsuario(Integer.parseInt(objConn.rs.getString("idUsuario")));
               temas.setNvisitas(Integer.parseInt(objConn.rs.getString("nVisitas")));
               lista.add(temas);
              
            
               
            
           }while(objConn.rs.next()); 
           
           }           
           Obj.setLista(lista);
           session.setAttribute("temas", Obj);
           ////////////////////////////////////////////////////////////////
           //////////////////////////////////////////////////////Buscamos los ultimos temas///////////////////////////////////////////////
          consulta="SELECT * FROM foro.tema_con  order by date_format(fecha,'%y/%m/%d') desc;";
          
           ListaTemas Obj1=new ListaTemas();
          objConn.Consult(consulta);
           n=0;
           if(objConn.rs!=null){
                try{
               objConn.rs.last();
                n=objConn.rs.getRow();
                }catch(Exception e){
                    }
             
           }
            if(n>0){
     
           objConn.rs.first();
           
           k=0;
           do{
              // new String(request.getParameter("mensaje").getBytes("ISO-8859-1"),"UTF-8");
               temas=new Temas();
               tem=new String(objConn.rs.getString("Tema").getBytes("ISO-8859-1"),"UTF-8");
               temas.setTema(tem);
               temas.setId(Integer.parseInt(objConn.rs.getString("id")));
               lista2.add(temas);
               k++;
           }while(objConn.rs.next() && k<=5); 
           
           }           
           Obj1.setLista(lista2);
           session.setAttribute("UltimosTemas", Obj1);
 /////////////////////////////////////////////////////////////////////////////////////////////////////
          consulta="SELECT * FROM foro.tema_con  order by nVisitas desc;";
           ListaTemas Obj2=new ListaTemas();
          objConn.Consult(consulta);
          n=0;
          if(objConn.rs!=null){
                try{
               objConn.rs.last();
               n=objConn.rs.getRow();
               }catch(Exception e){
                    }
             
           }
            if(n>0){
        
           objConn.rs.first();
           
           k=0;
           do{
              // new String(request.getParameter("mensaje").getBytes("ISO-8859-1"),"UTF-8");
               temas=new Temas();
               tem=new String(objConn.rs.getString("Tema").getBytes("ISO-8859-1"),"UTF-8");
               temas.setTema(tem);
               temas.setId(Integer.parseInt(objConn.rs.getString("id")));
               lista3.add(temas);
               k++;
           }while(objConn.rs.next() && k<=5); 
           
           }           
           Obj2.setLista(lista3);
           session.setAttribute("TemasMasV", Obj2);
           
           
           
           
           
           
           
           
           //desconectamos
           
           
         objConn.closeRsStmt();
           //si existe al menos un registro
          
          
            
                
        %>
        
        <jsp:forward page="Inicio.jsp">
            <jsp:param name="n" value="<%=n%>"/>
                
        </jsp:forward>
    </body>
</html>