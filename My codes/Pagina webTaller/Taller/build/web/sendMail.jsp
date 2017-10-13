<%-- 
    Document   : checklogin
    Created on : 07-oct-2015, 8:32:29
    Author     : ellui
--%>

<%@page import="javax.mail.MessagingException"%>
<%-- This page checks that the user exits in the database  and check the password--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Mail.JavaEmail"%>


        <% 
       JavaEmail javaEmail = new JavaEmail();
	javaEmail.setMailServerProperties();
        String cabecera="Mensaje de un cliente del Taller ";
       String nombre=request.getParameter("Nombre");
        String  mail=request.getParameter("Email");
       int  Telefono=Integer.parseInt(request.getParameter("Telefono"));
       String Mensaje=request.getParameter("mensaje");
      String cuerpo=" "+cabecera+"\n\nNombre:" +nombre+"\n Email: "+mail+"\n Telefono:  "+Telefono+"\n Mensaje: \n\n"+Mensaje;
     System.out.println(cuerpo);
        boolean envio=true;
       //enviarEmail("tecno.world.mundo.in.ingles@gmail.com", "Cliente Quiere contactar", cabecera+"\n\nNombre: "
            //  +nombre+"\n Email: "+mail+"\n Telefono:  "+String.valueOf(Telefono)+" \n Mensaje: \n\n"+Mensaje);
           //correo.enviarEmail("elluis.com@live.com","holamundo","dsfsf");
  
           javaEmail.createEmailMessage(mail,cuerpo);
		try {
			javaEmail.sendEmail();
                        envio=true;
			//status = "success";
			//message = "Email sent Successfully!";
		} catch (MessagingException me) {
                        envio=false;
			//status = "error";
			// message = "Error in Sending Email!";
		}
     if(envio){
      
       response.getWriter().print("true");
      } else{
      out.write("false"); 
      
      }
       
       
        %>
