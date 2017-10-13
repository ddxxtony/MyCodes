<%-- 
    Document   : Agrega_servicio
    Created on : 22-nov-2016, 20:50:43
    Author     : ellui
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Clases.Servicio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

    ArrayList<Servicio> service;
    if(request.getSession().getAttribute("ListServices")==null){
         service=new ArrayList<Servicio>();
    }else{
        service=(ArrayList<Servicio>)request.getSession().getAttribute("ListServices");
    }

       
     int opc=Integer.parseInt( request.getParameter("opc_serv"));
     Servicio [] servicoS=(Servicio [])session.getAttribute("servicios");
       int i=0;
       i=0;
       boolean Repetido=false;
       while(i<service.size()){
       if(service.get(i).id_servicio==servicoS[opc].id_servicio){
       Repetido=true;
       }
       i++;
       }
       
       i=0;
    // out.print("<UL class=\"unstyled\" id='SelectedServices'>");
    out.print("<table>");
      out.print("<tr>");
    out.print("<td width='20%'>Servicio</td>");
     out.print("<td width='20%'>Precio</td>");
    out.print("</tr>");
            if(!Repetido){
               
                       service.add(servicoS[opc]);
                      session.setAttribute("ListServices", service);  
                       while(i<service.size()){
                            out.print("<tr>");
                        out.print("<td width='20%'><img src=\"images/minus.png\"  width=\"20\" height=\"20\" id=\"addService\" onclick='Remove_Service("+service.get(i).id_servicio+ ")' v>&nbsp;&nbsp;"+service.get(i).nombre+"</td>");  
                            
                         out.print("<td width='20%'>");
                          out.print(service.get(i).precio);
                          out.print("</td>");
                          out.print("</tr>");
                            i++;
                    }
            }else{
                  while(i<service.size()){
                              out.print("<tr>");
                        out.print("<td width='20%'><img src=\"images/minus.png\"  width=\"20\" height=\"20\" id=\"addService\" onclick='Remove_Service("+service.get(i).id_servicio+ ")' v>&nbsp;&nbsp;"+service.get(i).nombre+"</td>");  
                            
                         out.print("<td width='20%'>");
                          out.print(service.get(i).precio);
                          out.print("</td>");
                          out.print("</tr>");
                            i++;
                    }
            
            }
         //   out.print("</UL>");  
         out.print("</table>");
        

       
 
//ArrayList <Servicio>) session.getAttribute("ListaArticulos");

%>