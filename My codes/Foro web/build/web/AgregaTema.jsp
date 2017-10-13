<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head> 

    <body>
        <jsp:useBean id="objConn" class="Mysql.MysqlConn"/>
        <%@page session="true"%>


        <%

          if (session.getAttribute("usuario") == "visitante") {
        %><jsp:forward page="index.jsp">
            <jsp:param name="error"
                       value="es obligatorio identificarse"/>


        </jsp:forward>
        <%  }%>

        <%
            String IDusuario = (String) session.getAttribute("idUsuario");
            String consulta = "";
            String Tema = "";
            String Nvisitas = "0";
            String Fecha = "";
            String update = "";
            Calendar fecha = new GregorianCalendar();
            int mes = fecha.get(Calendar.MONTH);
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            int año = fecha.get(Calendar.YEAR);
            
            Fecha = año + "-" + mes + "-" + dia;

            int x = 0;
            if (request.getParameter("consul") != null) {
                consulta = request.getParameter("consul");

            }
            if (request.getParameter("tem") != null) {
                Tema = request.getParameter("tem");

            }

            if (consulta == "" || Tema == "" || consulta == " " || Tema == " " || Tema.length() == 0 || consulta.length() == 0) {

            } else {

                update = "INSERT INTO foro.tema_con (fecha,idUsuario,Consulta,Tema,nVisitas)VALUES (" + "'" + Fecha + "'" + "," + "'" + IDusuario + "',"
                        + "'" + consulta.replace("\'", "\''") + "'," + "'" + Tema.replace("\'", "\''") + "'" + ",'" + Nvisitas + "'" + ")";

            }

            //update  usuario.usuarios  set usr='Lui',psw='123' where  psw='12345' and usr='Luiss'
            System.out.println(update);
            int n = 0;
            try {
                n = objConn.Update(update);
            } catch (Exception e) {
                n = 0;
            }

            session.setAttribute("BTemas", "false");
            session.setAttribute("OpcAdmin", "otro");
           //n indicara cuantos registros cumplen la condicion

           //desconectamos
           //si existe al menos un registro
            if (n > 0) {%>
        <jsp:forward page="Inicio.jsp">
            <jsp:param name="mensajeTema" value="Consulta Publicada con exito ahora espere a que alguien responda"/>
        </jsp:forward>

        <% } else { %>
        <jsp:forward page="Inicio.jsp">
            <jsp:param name="mensajeTema" value="Hubo un problema al publicar su Consulta"/>
        </jsp:forward>
        <% }%>

    </body>
</html>