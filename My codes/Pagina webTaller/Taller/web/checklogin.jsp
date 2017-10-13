<%-- 
    Document   : checklogin
    Created on : 07-oct-2015, 8:32:29
    Author     : ellui
--%>
<%@page import="Clases.Cliente"%>
<%-- This page checks that the user exits in the database  and check the password--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <jsp:useBean id="admin" class="Clases.administrativo"/>
        <jsp:useBean id="Mecanico" class="Clases.Mecanico"/>

        <%
            
            String TUser; 
            Cliente Client=new Cliente();
            int n=0;
           if(request.getParameter("TUser")!="" && (request.getParameter("usr")!="") && request.getParameter("password")!="" ){
                TUser=request.getParameter("TUser");
                out.print(TUser);
                        if(TUser.equals("Cliente")){
                            n=Client.check_user(request.getParameter("usr"),request.getParameter("password"),
                                   TUser);
                                   if(n>0){
                                       session.setAttribute("InfoUsr", Client);
                                       session.setAttribute("Snombre",Client.nombre);
                                        session.setAttribute("Semail", Client.email);
                                        session.setAttribute("STUser",TUser);
                                   }
                        }else

                        
                        if(TUser.equals("Mecanico")){
                            n=Mecanico.Consultar_trabajador( request.getParameter("usr"),request.getParameter("password"),
                                    TUser);
                            
                                if(n>0){  
                                    System.out.println(Mecanico.nombre);
                                       session.setAttribute("Snombre", Mecanico.nombre);
                                        session.setAttribute("STUser",TUser);

                                   }
                        }else{
                        

                            n=admin.Consultar_trabajador( request.getParameter("usr"),request.getParameter("password"),
                                    TUser);
                                if(n>0){
                                       session.setAttribute("Snombre", admin.nombre);
                                        session.setAttribute("STUser",TUser);
                                        }

                           }
           }
           if(n>0){
             session.setAttribute("SUser",request.getParameter("usr"));
            session.setAttribute("sessionOk","ok");
            session.setAttribute("SesionType", "Taller");
            response.setContentType("text/html"); 
            response.setHeader("Cache-Control", "no-cache");  
            response.getWriter().print("true");
           
       } else{ 
         out.write("false"); 
     
      
        }%> 
            
