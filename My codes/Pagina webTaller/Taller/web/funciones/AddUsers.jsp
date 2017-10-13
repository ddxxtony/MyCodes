<%-- 
    Document   : RegistraUsuario
    Created on : 28-nov-2016, 4:18:26
    Author     : ellui
--%>

<%@page import="Clases.Mecanico"%>
<%@page import="Clases.administrativo"%>
<%@page import="Clases.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <%
         String tipoUser=request.getParameter("tipouser");     
         if(tipoUser.equals("Cliente")){
             
            Cliente client=new Cliente();
            
            boolean registrado=client.nuevo_cliente(request.getParameter("usuario"),request.getParameter("psw"),
                    request.getParameter("name"),request.getParameter("appat"),request.getParameter("apmat"),
                 request.getParameter("calle"),request.getParameter("colonia"),   Integer.parseInt(request.getParameter("ncasa")),
                    request.getParameter("ciudad"),request.getParameter("estado"),   Integer.parseInt( request.getParameter("tel")),
                        request.getParameter("cel"),Integer.parseInt( request.getParameter("ingresos")),request.getParameter("email"));
            %>
               <article class="grid_3">
                 <article class="grid_9">    

             
                                    <div class="wrapper">
                                        <article class="grid_8">
            <!--AGREGAR CLIENTES  -->    <div class="indent-left"  id='Formulario'>
                                                    <h3 class="p1"> Usuario agregado con exito</h3>
            <!--OPCIONES PARA USUARIO -->                                                            
                                                </div>>
                                            </article>
                                    </div>
                                                        
                                    <div id="Resultado">
                                        </div>
                                </article>
        </article>
                
             
             
             
            <%
         }else   if(tipoUser.equals("Administrador")){
             
                 administrativo admin=new administrativo();
            boolean registrado;
           
            admin.Alta_administrativo( request.getParameter("usuario"),request.getParameter("psw"),
                    request.getParameter("name"),request.getParameter("appat"),request.getParameter("apmat"),
                 request.getParameter("calle"),request.getParameter("colonia"),Integer.parseInt(request.getParameter("ncasa")),
                    request.getParameter("ciudad"),request.getParameter("estado"),Integer.parseInt(request.getParameter("tel").toString()),
                      Integer.parseInt(request.getParameter("salrio")),  request.getParameter("tipo"),"3324324",
            Integer.parseInt(request.getParameter("cp")));
            %>

               <article class="grid_3">
                 <article class="grid_9">    

             
                                    <div class="wrapper">
                                        <article class="grid_8">
            <!--AGREGAR CLIENTES  -->    <div class="indent-left"  id='Formulario'>
                                                    <h3 class="p1"> Usuario agregado con exito</h3>
            <!--OPCIONES PARA USUARIO -->                                                            
                                                </div>>
                                            </article>
                                    </div>
                                                        
                                    <div id="Resultado">
                                        </div>
                                </article>
        </article>
                
        
        <%
              
         }else   if(tipoUser.equals("Mecanico")){


             Mecanico mec=new Mecanico();
            
            boolean registrado=mec.Nuevo_mecanico(request.getParameter("usuario"),request.getParameter("psw"),
                    request.getParameter("name"),request.getParameter("appat"),request.getParameter("apmat"),
                 request.getParameter("calle"),request.getParameter("colonia"),   Integer.parseInt(request.getParameter("ncasa")),
                    request.getParameter("ciudad"),request.getParameter("estado"),   Integer.parseInt( request.getParameter("tel")),
                        request.getParameter("especialidad"),Integer.parseInt( request.getParameter("salario")),Integer.parseInt(request.getParameter("cp")));
            %>
               <article class="grid_3">
                 <article class="grid_9">    

             
                                    <div class="wrapper">
                                        <article class="grid_8">
            <!--AGREGAR CLIENTES  -->    <div class="indent-left"  id='Formulario'>
                                                    <h3 class="p1"> Usuario agregado con exito</h3>
            <!--OPCIONES PARA USUARIO -->                                                            
                                                </div>>
                                            </article>
                                    </div>
                                                        
                                    <div id="Resultado">
                                        </div>
                                </article>
        </article>
                
               
             
             
        <% }
         
        %>
     
    </body>
</html>
