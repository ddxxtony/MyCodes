<%-- 
    Document   : getUsersSelect
    Created on : 26-nov-2016, 15:44:07
    Author     : ellui
--%>


<%@page import="Clases.administrativo"%>
<%@page import="Clases.Mecanico"%>
<%@page import="Clases.Cliente"%>
<!-- este jsp reotnra los usuarios segun el tipo que se le pidan (clientes , administradores , mecanicos)-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
                                               
                                                
                                            <% 
                                                
                                                
                                              int i=0;
                                             ///si se va  a eliminar usuario entra aqui 
                                              if(request.getParameter("opc").equals("0")){
                                                  %>
                                                  <label><span class="text-form">Nombre Usuario:</span> 
                                                  <br>
                                                <select name="Drop_user" id="Drop_users">
                                                    
                                                  <%
                                              
                                            if(request.getParameter("Tuser" ).equals("Cliente")){           
                                               Cliente clie=new Cliente();
                                            Cliente [] clientes=clie.Consulta_usuarios();
                                          
                                            
                                            while(i<clientes.length)
                                            {
                                             out.print("<option>"+ clientes[i].nombre+"/"+ clientes[i].usuario+ "</option>");
                                                  
                                             i++;
                                                    
                                            }
                                     }else if(request.getParameter("Tuser" ).equals("Mecanico")){
                                            Mecanico [] Meca=Mecanico.Consultar_Mecanicos();
                                            
                                          
                                            
                                            while(i<Meca.length)
                                            {
                                             out.print("<option>"+ Meca[i].nombre+"/"+ Meca[i].usuario+"</option>");
                                                  
                                             i++;
                                                    
                                            }
                                         //es Administrador
                                     }else{
                                          
                                            administrativo [] admins=administrativo.Consultar_administrativos();
                                          
                                            
                                            while(i<admins.length)
                                            {
                                             out.print("<option>"+ admins[i].nombre+"/"+ admins[i].usuario+ "</option>");
                                                  
                                             i++;
                                                    
                                            }
                                     
                                     }
                                            %>
                                             </select></label>
                                             <br><br><br>
                                               <a class="button"  onClick="fun_elimina_user()">Eliminar Cliente</a>
                                               
                                               <%}else{  
                                               %>
                                                <label><span class="text-form">Nombre Usuario:</span> 
                                                   <br>
                                                <select name="User_mod" id="User_modif" onclick="refreshdiv('funciones/MuestraFormularios','ClientModificacion',this.value,'lleno','TUserM2')" >
                                               <%


//si se va a modificar usuario entra aqui

                                            if(request.getParameter("Tuser" ).equals("Cliente")){           
                                               Cliente clie=new Cliente();
                                            Cliente [] clientes=clie.Consulta_usuarios();
                                          
                                            
                                            while(i<clientes.length)
                                            {
                                             out.print("<option>"+ clientes[i].nombre+"/"+ clientes[i].usuario+ "</option>");
                                                  
                                             i++;
                                                    
                                            }
                                     }else if(request.getParameter("Tuser" ).equals("Mecanico")){
                                            Mecanico [] Meca=Mecanico.Consultar_Mecanicos();
                                            
                                          
                                            
                                            while(i<Meca.length)
                                            {
                                             out.print("<option>"+ Meca[i].nombre+"/"+ Meca[i].usuario+"</option>");
                                                  
                                             i++;
                                                    
                                            }
                                         //es Administrador
                                     }else{
                                          
                                            administrativo [] admins=administrativo.Consultar_administrativos();
                                          
                                            
                                            while(i<admins.length)
                                            {
                                             out.print("<option>"+ admins[i].nombre+"/"+ admins[i].usuario+ "</option>");
                                                  
                                             i++;
                                                    
                                            }
                                     
                                     }%>
                                      </select></label>
                                             <br><br><br>
                                            
                                                           <% }%>
                                     
                                             
    </body>
</html>
