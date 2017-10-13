<%-- 
    Document   : MuestraFormularios
    Created on : 24-nov-2016, 10:41:54
    Author     : ellui
--%>

<%@page import="Clases.Mecanico"%>
<%@page import="Clases.Cliente"%>
<%@page import="Clases.administrativo"%>
<%@page import="OraclePackage.oracleConection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%
         String selectUser=request.getParameter("SelectedUser");
         String [] User = selectUser.split("/");   
            
         if(request.getParameter("forma").equals("lleno")){
            
            if(request.getParameter("Usuario").equals("Cliente"))  {
                Cliente client=new Cliente(User[1]);
                   
        %>
              <article class="grid_3">
                 <article class="grid_9">    

             
                                    <div class="wrapper">
                                        <article class="grid_8">
            <!--AGREGAR CLIENTES  -->    <div class="indent-left"  id='Formulario'>
                                                    <h3 class="p1"> Modifique siguientes datos</h3>
            <!--OPCIONES PARA USUARIO -->            <form id="contact-form" method="post" enctype="multipart/form-data">                    
                                                        <fieldset>                                                        
                                                            <label><span class="text-form">Nombre:</span><input id="name" type="text"  placeholder="Usuario" value="<%=client.nombre%>" /></label>
                                                            <label><span class="text-form">Apellido Paterno:</span><input id="appat" type="text" placeholder="Usuario" value="<%=client.appat%>" /></label>   
                                                            <label><span class="text-form">Apellido Materno:</span><input id="appmat" type="text" placeholder="Usuario" value="<%=client.apmat%>"/></label>
                                                            <label><span class="text-form">Calle:</span><input id="calle" type="text" placeholder="Usuario" value="<%=client.calle%>"/></label>
                                                            <label><span class="text-form">Colonia:</span><input id="colonia" type="text" placeholder="Usuario" value="<%=client.colonia%>"/></label>
                                                            <label><span class="text-form">Numero de Casa:</span><input id="Ncasa" type="text" placeholder="Usuario" value="<%=client.numero%>"/></label>
                                                            <label><span class="text-form">Ciudad:</span><input id="Ciudad" type="text" placeholder="Usuario" value="<%=client.Ciudad%>"/></label>
                                                            <label><span class="text-form">Estado:</span><input id="Estado" type="text" placeholder="Usuario" value="<%=client.Estado%>"/></label>
                                                            <label><span class="text-form">Telefono:</span><input id="tel" type="text" placeholder="Usuario" value="<%=client.telefono%>"/></label>
                                                            <label><span class="text-form">Celular:</span><input id="cel" type="text" placeholder="Usuario" value="<%=client.celular%>"/></label>
                                                            <label><span class="text-form">Ingresos:</span><input id="ingresos" type="text" placeholder="Usuario" value="<%=client.ingresos%>"/></label>
                                                            <label><span class="text-form">Correo:</span><input id="email" type="text" placeholder="Usuario" value="<%=client.email%>"/></label>
                                                            <h3 class="p2"><span>Informacion de Inicio de Sesion</span></h3>
                                                            <label><span class="text-form">Usuario :</span><input id="user" type="text" placeholder="Usuario"  value="<%=client.usuario%>"/></label>
                                                            <label><span class="text-form">Contraseña :</span><input id="psw1" type="password" placeholder="Usuario" value="<%=client.Psw%>"/></label>
                                                            <label><span class="text-form">Confirmar Contraseña :</span><input id="psw2" type="password" placeholder="Usuario" value="<%=client.Psw%>"/></label>
                                                        </fieldset>	
                                                        <br>
                                                         <a class="button"  onClick="Registra_cita()">Modifica Cliente</a>
                                                    </form>                                                  
                                                </div>>
                                            </article>
                                    </div>
                                                        
                                    <div id="Resultado">
                                        </div>
                                </article>
        </article>
        
        <%}else if(request.getParameter("Usuario").equals("Administrador"))  {
        
            administrativo admin=new administrativo(User[1],456);

            
          %>
                <article class="grid_3">
                 <article class="grid_9">    

             
                                    <div class="wrapper">
                                        <article class="grid_8">
            <!--AGREGAR CLIENTES  -->    <div class="indent-left"  id='Formulario'>
                                                    <h3 class="p1"> Modifique siguientes datos</h3>
            <!--OPCIONES PARA USUARIO -->            <form id="contact-form" method="post" enctype="multipart/form-data">                    
                                                        <fieldset>                                                        
                                                            <label><span class="text-form">Nombre:</span><input id="name" type="text"  placeholder="Usuario" value="<%=admin.nombre%>" /></label>
                                                            <label><span class="text-form">Apellido Paterno:</span><input id="appat" type="text" placeholder="Usuario" value="<%=admin.appat%>" /></label>   
                                                            <label><span class="text-form">Apellido Materno:</span><input id="appmat" type="text" placeholder="Usuario" value="<%=admin.apmat%>"/></label>
                                                            <label><span class="text-form">Calle:</span><input id="calle" type="text" placeholder="Usuario" value="<%=admin.Calle%>"/></label>
                                                            <label><span class="text-form">Colonia:</span><input id="colonia" type="text" placeholder="Usuario" value="<%=admin.colonia%>"/></label>
                                                            <label><span class="text-form">Numero de Casa:</span><input id="Ncasa" type="text" placeholder="Usuario" value="<%=admin.numero%>"/></label>
                                                            <label><span class="text-form">Ciudad:</span><input id="Ciudad" type="text" placeholder="Usuario" value="<%=admin.ciudad%>"/></label>
                                                            <label><span class="text-form">Estado:</span><input id="Estado" type="text" placeholder="Usuario" value="<%=admin.estado%>"/></label>
                                                             <label><span class="text-form">Codigo postal:</span><input id="Ciudad" type="text" placeholder="Usuario" value="<%=admin.cp%>"/></label>
                                                            <label><span class="text-form">Telefono:</span><input id="tel" type="text" placeholder="Usuario" value="<%=admin.telefono%>"/></label>
                                                            <label><span class="text-form">Salario:</span><input id="Ciudad" type="text" placeholder="Usuario" value="<%=admin.salario%>"/></label>
                                                            <label><span class="text-form">Tipo de Administrativo:</span><input id="Ciudad" type="text" placeholder="Usuario" value="<%=admin.Tipo_administrativo%>"/></label>
                                                            <h3 class="p2"><span>Informacion de Inicio de Sesion</span></h3>
                                                            <label><span class="text-form">Usuario :</span><input id="user" type="text" placeholder="Usuario"  value="<%=admin.usuario%>"/></label>
                                                            <label><span class="text-form">Contraseña :</span><input id="psw1" type="password" placeholder="Usuario" value="<%=admin.pass%>"/></label>
                                                            <label><span class="text-form">Confirmar Contraseña :</span><input id="psw2" type="password" placeholder="Usuario" value="<%=admin.pass%>"/></label>
                                                        </fieldset>	
                                                        <br>
                                                         <a class="button"  onClick="Registra_cita()">Modifica Cliente</a>
                                                    </form>                                                  
                                                </div>>
                                            </article>
                                    </div>
                                                        
                                    <div id="Resultado">
                                        </div>
                                </article>
        </article>

       <% }else if(request.getParameter("Usuario").equals("Mecanico"))  {
        
            Mecanico mec=new Mecanico(User[1],456);
       %>
          <article class="grid_3">
                 <article class="grid_9">    

             
                                    <div class="wrapper">
                                        <article class="grid_8">
            <!--AGREGAR CLIENTES  -->    <div class="indent-left"  id='Formulario'>
                                                    <h3 class="p1"> Modifique siguientes datos</h3>
            <!--OPCIONES PARA USUARIO -->            <form id="contact-form" method="post" enctype="multipart/form-data">                    
                                                        <fieldset>                                                        
                                                            <label><span class="text-form">Nombre:</span><input id="name" type="text"  placeholder="Usuario" value="<%=mec.nombre%>" /></label>
                                                            <label><span class="text-form">Apellido Paterno:</span><input id="appat" type="text" placeholder="Usuario" value="<%=mec.appat%>" /></label>   
                                                            <label><span class="text-form">Apellido Materno:</span><input id="appmat" type="text" placeholder="Usuario" value="<%=mec.apmat%>"/></label>
                                                            <label><span class="text-form">Calle:</span><input id="calle" type="text" placeholder="Usuario" value="<%=mec.Calle%>"/></label>
                                                            <label><span class="text-form">Colonia:</span><input id="colonia" type="text" placeholder="Usuario" value="<%=mec.colonia%>"/></label>
                                                            <label><span class="text-form">Numero de Casa:</span><input id="Ncasa" type="text" placeholder="Usuario" value="<%=mec.numero%>"/></label>
                                                            <label><span class="text-form">Ciudad:</span><input id="Ciudad" type="text" placeholder="Usuario" value="<%=mec.ciudad%>"/></label>
                                                            <label><span class="text-form">Estado:</span><input id="Estado" type="text" placeholder="Usuario" value="<%=mec.estado%>"/></label>
                                                             <label><span class="text-form">Codigo postal:</span><input id="Ciudad" type="text" placeholder="Usuario" value="<%=mec.cp%>"/></label>
                                                            <label><span class="text-form">Telefono:</span><input id="tel" type="text" placeholder="Usuario" value="<%=mec.telefono%>"/></label>
                                                            <label><span class="text-form">Salario:</span><input id="Ciudad" type="text" placeholder="Usuario" value="<%=mec.salario%>"/></label>
                                                            <label><span class="text-form">Especialidad:</span><input id="Ciudad" type="text" placeholder="Usuario" value="<%=mec.specialidad%>"/></label>
                                                            <h3 class="p2"><span>Informacion de Inicio de Sesion</span></h3>
                                                            <label><span class="text-form">Usuario :</span><input id="user" type="text" placeholder="Usuario"  value="<%=mec.usuario%>"/></label>
                                                            <label><span class="text-form">Contraseña :</span><input id="psw1" type="password" placeholder="Usuario" value="<%=mec.pass%>"/></label>
                                                            <label><span class="text-form">Confirmar Contraseña :</span><input id="psw2" type="password" placeholder="Usuario" value="<%=mec.pass%>"/></label>
                                                        </fieldset>	
                                                        <br>
                                                         <a class="button"  onClick="Registra_cita()">Modifica Cliente</a>
                                                    </form>                                                  
                                                </div>>
                                            </article>
                                    </div>
                                                        
                                    <div id="Resultado">
                                        </div>
                                </article>
        </article>

        
       <% }
////SE MUESTRA FORMULARIO VACIO///    
        }else{


        if(request.getParameter("Usuario").equals("Cliente"))  {
                   
        %>
              <article class="grid_3">
                 <article class="grid_9">    

             
                                    <div class="wrapper">
                                        <article class="grid_8">
            <!--AGREGAR CLIENTES  -->    <div class="indent-left"  id='Formulario'>
                                                    <h3 class="p1"> Ingresa los siguientes datos</h3>
            <!--OPCIONES PARA USUARIO -->            <form id="contact-form" method="post" enctype="multipart/form-data">                    
                                                        <fieldset>                                                        
                                                            <label><span class="text-form">Nombre:</span><input id="name"   type="text" /></label>
                                                            <label><span class="text-form">Apellido Paterno:</span><input id="appat" type="text"  /></label>   
                                                            <label><span class="text-form">Apellido Materno:</span><input id="appmat" type="text" /></label>
                                                            <label><span class="text-form">Calle:</span><input id="calle" type="text" /></label>
                                                            <label><span class="text-form">Colonia:</span><input id="colonia" type="text" /></label>
                                                            <label><span class="text-form">Numero de Casa:</span><input id="Ncasa" type="text" /></label>
                                                            <label><span class="text-form">Ciudad:</span><input id="Ciudad" type="text" /></label>
                                                            <label><span class="text-form">Estado:</span><input id="Estado" type="text" /></label>
                                                            <label><span class="text-form">Telefono:</span><input id="tel" type="text" /></label>
                                                            <label><span class="text-form">Celular:</span><input id="cel" type="text" /></label>
                                                            <label><span class="text-form">Ingresos:</span><input id="ingresos" type="text" /></label>
                                                            <label><span class="text-form">Correo:</span><input id="email" type="text" /></label>
                                                            <h3 class="p2"><span>Informacion de Inicio de Sesion</span></h3>
                                                            <label><span class="text-form">Usuario :</span><input id="user" type="text" /></label>
                                                            <label><span class="text-form">Contraseña :</span><input id="psw1" type="password" /></label>
                                                            <label><span class="text-form">Confirmar Contraseña :</span><input id="psw2" type="password" /></label>
                                                        </fieldset>	
                                                        <br>
                                                         <a class="button"  onClick="Registra_usuario('Cliente')">Agregar Cliente</a>
                                                    </form>                                                  
                                                </div>>
                                            </article>
                                    </div>
                     
                                    <div id="Resultado">
                                        </div>
                                </article>
        </article>
        
        <%}else if(request.getParameter("Usuario").equals("Administrador"))  {
        
         

            
          %>
                <article class="grid_3">
                 <article class="grid_9">    

             
                                    <div class="wrapper">
                                        <article class="grid_8">
            <!--AGREGAR CLIENTES  -->    <div class="indent-left"  id='Formulario'>
                                                    <h3 class="p1"> Ingresa los siguientes datos</h3>
            <!--OPCIONES PARA USUARIO -->            <form id="contact-form" method="post" enctype="multipart/form-data">                    
                                                        <fieldset>                                                        
                                                            <label><span class="text-form">Nombre:</span><input id="name"  type="text" value="administrador"/></label>
                                                            <label><span class="text-form">Apellido Paterno:</span><input id="appat" type="text"  /></label>   
                                                            <label><span class="text-form">Apellido Materno:</span><input id="appmat" type="text"/></label>
                                                            <label><span class="text-form">Calle:</span><input id="calle" type="text" /></label>
                                                            <label><span class="text-form">Colonia:</span><input id="colonia" type="text" /></label>
                                                            <label><span class="text-form">Numero de Casa:</span><input id="Ncasa" type="text" /></label>
                                                            <label><span class="text-form">Ciudad:</span><input id="Ciudad" type="text" /></label>
                                                            <label><span class="text-form">Estado:</span><input id="Estado" type="text" /></label>
                                                             <label><span class="text-form">Codigo postal:</span><input id="cp" type="text" /></label>
                                                            <label><span class="text-form">Telefono:</span><input id="tel" type="text" /></label>
                                                            <label><span class="text-form">Salario:</span><input id="salario" type="text" /></label>
                                                            <label><span class="text-form">Tipo de Administrativo:</span><input id="tipo" type="text" /></label>
                                                            <h3 class="p2"><span>Informacion de Inicio de Sesion</span></h3>
                                                            <label><span class="text-form">Usuario :</span><input id="user" type="text" /></label>
                                                            <label><span class="text-form">Contraseña :</span><input id="psw1" type="password" /></label>
                                                            <label><span class="text-form">Confirmar Contraseña :</span><input id="psw2" type="password"/></label>
                                                        </fieldset>	
                                                        <br>
                                                         <a class="button"  onClick="Registra_usuario('Administrador')">Registra administrador</a>
                                                    </form>                                                  
                                                </div>>
                                            </article>
                                    </div>
                     
                                    <div id="Resultado">
                                        </div>
                                </article>
        </article>

       <% }else if(request.getParameter("Usuario").equals("Mecanico"))  {
        
     
       %>
          <article class="grid_3">
                 <article class="grid_9">    

             
                                    <div class="wrapper">
                                        <article class="grid_8">
            <!--AGREGAR CLIENTES  -->    <div class="indent-left"  id='Formulario'>
                                                    <h3 class="p1"> Ingresa los siguientes datos</h3>
            <!--OPCIONES PARA USUARIO -->            <form id="contact-form" method="post" enctype="multipart/form-data">                    
                                                        <fieldset>                                                        
                                                            <label><span class="text-form">Nombre:</span><input id="name"  type="text" value="Mecanico" /></label>
                                                            <label><span class="text-form">Apellido Paterno:</span><input id="appat"  type="text"  /></label>   
                                                            <label><span class="text-form">Apellido Materno:</span><input id="appmat" type="text"/></label>
                                                            <label><span class="text-form">Calle:</span><input id="calle" type="text" /></label>
                                                            <label><span class="text-form">Colonia:</span><input id="colonia" type="text" /></label>
                                                            <label><span class="text-form">Numero de Casa:</span><input id="Ncasa" type="text" /></label>
                                                            <label><span class="text-form">Ciudad:</span><input id="Ciudad" type="text" /></label>
                                                            <label><span class="text-form">Estado:</span><input id="Estado" type="text" /></label>
                                                             <label><span class="text-form">Codigo postal:</span><input id="cp" /></label>
                                                            <label><span class="text-form">Telefono:</span><input id="tel" type="text"/></label>
                                                            <label><span class="text-form">Salario:</span><input id="salario" type="text" /></label>
                                                            <label><span class="text-form">Especialidad:</span><input id="especialidad" type="text" /></label>
                                                            <h3 class="p2"><span>Informacion de Inicio de Sesion</span></h3>
                                                            <label><span class="text-form">Usuario :</span><input id="user" type="text"/></label>
                                                            <label><span class="text-form">Contraseña :</span><input id="psw1" type="password" /></label>
                                                            <label><span class="text-form">Confirmar Contraseña :</span><input id="psw2" type="password" /></label>
                                                        </fieldset>	
                                                        <br>
                                                         <a class="button"  onClick="Registra_usuario('Mecanico')">Registr mecanico</a>
                                                    </form>                                                  
                                                </div>>
                                            </article>
                                    </div>
                     
                                    <div id="Resultado">
                                        </div>
                                </article>
        </article>

        
       <% }
        
        
        }%>
    </body>
</html>
