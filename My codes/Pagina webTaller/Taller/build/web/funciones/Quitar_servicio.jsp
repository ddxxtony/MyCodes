<%-- 
    Document   : Quitar_servicio
    Created on : 23-nov-2016, 0:04:34
    Author     : ellui
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Clases.Servicio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

    System.out.println("quitar servicio");
    ArrayList<Servicio> service;
    String id_ser = request.getParameter("index");

    if (request.getSession().getAttribute("ListServices") == null) {
        service = new ArrayList<Servicio>();
    } else {
        service = (ArrayList<Servicio>) request.getSession().getAttribute("ListServices");
    }

    // int opc=Integer.parseInt( request.getParameter("opc_serv"));
    Servicio[] servicoS = (Servicio[]) session.getAttribute("servicios");
    int i = 0;

    while (i < service.size()) {
        if (service.get(i).id_servicio == Integer.parseInt(id_ser)) {
            service.remove(i);
            break;
        }
        i++;
    }

    i = 0;
    //  out.print("<UL class=\"list-1\">");  

    out.print("<table>");
    session.setAttribute("ListServices", service);
    out.print("<tr>");
    out.print("<td width='20%'>Servicio</td>");
     out.print("<td width='20%'>Precio</td>");
    out.print("</tr>");
    while (i < service.size()) {
        out.print("<tr>");
        out.print("<td width='20%'><img src=\"images/minus.png\"  width=\"20\" height=\"20\" id=\"addService\" onclick='Remove_Service(" + service.get(i).id_servicio + ")' v>&nbsp;&nbsp;" + service.get(i).nombre + "</td>");

        out.print("<td width='20%'>");
        out.print(service.get(i).precio);
        out.print("</td>");
        out.print("</tr>");
        i++;
    }
    // out.print("</UL>");  
    out.print("</table>");

//ArrayList <Servicio>) session.getAttribute("ListaArticulos");

%>