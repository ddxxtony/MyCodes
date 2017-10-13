package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Mysql.ListaTemas;
import java.util.ArrayList;
import Mysql.Temas;

public final class Inicio_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("    <head>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Tatyc</title>\n");
      out.write("        <link rel=\"STYLESHEET\" type=\"text/css\" href=\"CSS/style.css\"></head>\n");
      out.write("\n");
      out.write("    ");
 if (session.getAttribute("BTemas").equals("false")) {
    
      if (true) {
        _jspx_page_context.forward("Temas.jsp");
        return;
      }
      out.write(' ');

        }
      out.write("\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        ");
 ListaTemas temas;
            ListaTemas MVistos;
            ListaTemas MRecientes;
            int k = 0;
            int posicion = 0;
            session.setAttribute("respuesta", "false");
        
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"tux\" >\n");
      out.write("            <table border = 0>\n");
      out.write("                <tr>\n");
      out.write("               <td valign=\"bottom\" > <center><IMG  \n");
      out.write("                        src=\"Images/tux.png\" ></center>  </td> \n");
      out.write("            \n");
      out.write("                <td valign=\"bottom\">\n");
      out.write("                    <p class=\"titulo1\"> Tatyc     </p>\n");
      out.write("                    <p class=\"titulo2\"> La respuesta </p>     \n");
      out.write("                    <p class=\"titulo2\">  a tus dudas </p>\n");
      out.write("                </td>\n");
      out.write("                \n");
      out.write("              \n");
      out.write("           </tr>\n");
      out.write("            </table>\n");
      out.write("            </div>\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        \n");
      out.write("        \n");
      out.write("        <div style=\"  position: absolute;\n");
      out.write("                         z-index:7;\n");
      out.write("                         top:165px;\n");
      out.write("                         left:600px;\n");
      out.write("                         width:100px;\n");
      out.write("                     \">\n");
      out.write("            <a href=\"ReInicio.jsp\"><center>  <IMG src=\"Images/Hom.png\"  width=\"60\" height=\"60\"></center></a>\n");
      out.write("            \n");
      out.write("            \n");
      out.write("            \n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        \n");
      out.write("           <div style=\"  position: absolute;\n");
      out.write("                         z-index:7;\n");
      out.write("                         top:190px;\n");
      out.write("                         left:750px;\n");
      out.write("                         width:150px;\n");
      out.write("                         word-wrap:break-word;\">\n");
      out.write("\n");
      out.write("                        <div class=\"transparente3\">\n");
      out.write("                        </div>\n");
      out.write("             \n");
      out.write("                    <form action=\"Temas.jsp\" method=\"post\"> \n");
      out.write("                   <input type=\"text\" name=\"filtro\" value=\"\" size=\"22\">\n");
      out.write("                   <input type=\"hidden\" name=\"bandFiltro\" value=\"\">\n");
      out.write("                  \n");
      out.write("                      </form>  \n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        \n");
      out.write("          <div style=\"  position: absolute;\n");
      out.write("                         z-index:7;\n");
      out.write("                         top:170px;\n");
      out.write("                         left:660px;\n");
      out.write("                         width:100px;\n");
      out.write("                     \">\n");
      out.write("            <center><IMG src=\"Images/buscar.png\"  width=\"45\" height=\"45\"></center>\n");
      out.write("            \n");
      out.write("            \n");
      out.write("            \n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("      \n");
      out.write("        \n");
      out.write("        \n");
      out.write("        \n");
      out.write("        \n");
      out.write("        <div >\n");
      out.write("        <br><br><br><br><br> <br><br><br><br><br>\n");
      out.write("\n");
      out.write("        <table>\n");
      out.write("            <tr>\n");
      out.write("                <td valign=\"top\">\n");
      out.write("\n");
      out.write("                    <div style=\"                    position: relative;\n");
      out.write("                         z-index:7;\n");
      out.write("                         top:20px;\n");
      out.write("                         left:200px;\n");
      out.write("                         width:800px;\n");
      out.write("                         word-wrap:break-word;\">\n");
      out.write("\n");
      out.write("                        <div class=\"transparente2\">\n");
      out.write("                        </div>\n");
      out.write("                        <p class=\"titulo3\"> Temas <p>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                    ");

                       /* if (request.getParameter("n") != null) {
                            if (Integer.parseInt(request.getParameter("n")) > 0) {
                                temas = (ListaTemas) session.getAttribute("temas");

                                for (int i = 0; i < temas.getLista().size(); i++) {
                                    posicion = (10 * (i + 1));

                    */
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                    <div style=\"                    position:relative;\n");
      out.write("                         z-index:7;\n");
      out.write("                         top:");
      out.print(posicion);
      out.write("px;\n");
      out.write("                         left:200px;\n");
      out.write("                         width:680px;\n");
      out.write("                         word-wrap:break-word;\">\n");
      out.write("\n");
      out.write("                        <div class=\"transparente2\">\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        ");

                           /* out.println("<Br>");
                            String text = temas.getLista().get(i).getTema();
                            out.println(" <p class='titulo5'> <A HREF='auxPage."
                                    + "jsp?idtem=" + i + "&idr="
                                    + temas.getLista().get(i).getId() + "'>"
                                    + text + "</A></p>");

                            out.println("<Hr>");
                            text = temas.getLista().get(i).getConsulta();
                            if (text.length() >= 250) {
                                String Con = text.substring(0, 250);
                                out.println(Con + "");
                                out.print("...");
                            } else {
                                out.println(text + "");
                            }
                            out.println("<Br><br>");
                            if ((Integer.parseInt((String) session.getAttribute(
                                    "idUsuario"))
                                    == temas.getLista().get(i).getIdUsuario())
                                    || (session.getAttribute("privilegios").equals("0"))) {
                        */
      out.write("   \n");
      out.write("\n");
      out.write("                        <form action=\"Borra_Tema.jsp\" method=\"post\"> \n");
      out.write("                       <!--     <input type=\"hidden\" name=\"borraID\" value=\"");
//=temas.getLista().get(i).getId()
      out.write("\"><br> -->\n");
      out.write("                            <p><input  type=\"submit\" value=\"Borrrar Tema\"> </p>\n");
      out.write("                            </form>\n");
      out.write("                        <br>\n");
      out.write("                        ");
}
      out.write(" \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("\n");
      out.write("                    ");
  }
                        }
      out.write("\n");
      out.write("\n");
      out.write("                    ");
 //if (session.getAttribute("privilegios").equals("0")
                        //        || session.getAttribute("privilegios").equals("1")) {
      out.write("\n");
      out.write("                    ");
//posicion = posicion + 10;
      out.write("\n");
      out.write("                    <div style=\"         position: relative;\n");
      out.write("                         z-index:7;\n");
      out.write("                         top:");
      out.print(//posicion);
      out.write("px;\n");
      out.write("                         left:200px;\n");
      out.write("                         width:800px;\n");
      out.write("                         word-wrap:break-word;\">\n");
      out.write("\n");
      out.write("                        <div class=\"transparente2\">\n");
      out.write("                        </div>\n");
      out.write("                        <br><br>\n");
      out.write("                        <p class=\"titulo2\"> Agregar nueva consulta <p>\n");
      out.write("                        <form action=\"AgregaTema.jsp\" method=\"post\"> \n");
      out.write("                            Tema:<br><input type=\"text\" name=\"tem\" value=\"\" size=\"75\"><br>\n");
      out.write("                            Consulta:<br>\n");
      out.write("                            <textarea name=\"consul\" rows=\"10\" cols=\"77\"></textarea>\n");
      out.write("                            <input type=\"hidden\" name=\"usrId\" value=\"alta\"><br>\n");
      out.write("                            <p><input type=\"submit\" value=\"Enviar Consulta\"> </p>\n");
      out.write("\n");
      out.write("                        </form>\n");
      out.write("                        <p><font color=\"red\">");
 if (request.getParameter("mensajeTema") != null) {
                               // out.println(request.getParameter("mensajeTema"));
                            //}

                            
      out.write("</font></p>\n");
      out.write("\n");
      out.write("                        <br><br>\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                    ");
//}
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                    ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                </td>\n");
      out.write("\n");
      out.write("                <td valign=\"top\">\n");
      out.write("\n");
      out.write("                    <div style=\"                    position:relative;\n");
      out.write("                         z-index: 5;\n");
      out.write("                         top:60px;\n");
      out.write("                         left:200px;\n");
      out.write("                         width:200px;\n");
      out.write("                         padding: .5em;\n");
      out.write("                         word-wrap:break-word;\">\n");
      out.write("\n");
      out.write("                        <div class=\"transparente1\">\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                        <p class=\"titulo2\"> Bienvenido <p>\n");
      out.write("                        <hr>\n");
      out.write("                        ");
 /* if (session.getAttribute("inicioSession").
                                 //   equals("false")) {
                                //if (request.getParameter("error") != null) {
                                  //  out.println("<font color='red'>"
                                    //        + request.getParameter("error") + "</font>");
                      //          }
                        //            */
                        
      out.write("\n");
      out.write("                        <br>\n");
      out.write("                        <P>Iniciar Sesion</p>\n");
      out.write("                        <form action=\"checklogin.jsp\" method=\"post\"> \n");
      out.write("                            Usuario:<input type=\"text\" name=\"usr\" value=\"\"><br>\n");
      out.write("                            Clave:<input type=\"password\" name=\"clave\" value=\"\"><br>\n");
      out.write("                            <p><input type=\"submit\" value=\"Iniciar Sesion\"> </p>\n");
      out.write("                        </form>\n");
      out.write("\n");
      out.write("\n");
      out.write("                        ");
 /* } else {
                            out.println(session.getAttribute("usuario"));
                            if (request.getParameter("mensajeTema") != null) {
                                out.println("<font color='blue'>"
                                        + request.getParameter("mensajeTema") + "</font>");
                            }

                            //mensajeBorraT
                            if (request.getParameter("mensajeBorraT") != null) {
                                out.println("<font color='blue'>"
                                        + request.getParameter("mensajeBorraT") + "</font>");
                            }*/
                        
      out.write(" \n");
      out.write("\n");
      out.write("                        <br><br>\n");
      out.write("                        <form action=\"index.jsp\" method=\"post\"> \n");
      out.write("                            <p><input type=\"submit\" value=\"Cerrar session\"> </p>\n");
      out.write("                        </form>\n");
      out.write("                        ");
//}
      out.write("\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("                    <div style=\"                 position:relative;\n");
      out.write("                         z-index: 5;\n");
      out.write("                         top:70px;\n");
      out.write("                         left:200px;\n");
      out.write("                         width:200px;\n");
      out.write("                         padding: .5em;\n");
      out.write("                         word-wrap:break-word;\">\n");
      out.write("\n");
      out.write("                        <div class=\"transparente1\">\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("                        <hr>\n");
      out.write("                        <p class=\"titulo2\"> Ultimos Temas <p>\n");
      out.write("                        <hr>\n");
      out.write("\n");
      out.write("                          ");
 
                        //int cont=0;
                        /*if(session.getAttribute("UltimosTemas")!=null ){
                            MRecientes = (ListaTemas) session.getAttribute("UltimosTemas");
                            
                            if ( MRecientes.getLista().size() > 0) {
                                k = MRecientes.getLista().size() - 1;
                                while (k >= 0 && cont<5 ) { */
                        
      out.write("\n");
      out.write("                        <UL>\n");
      out.write("                            \n");
      out.write("                           ");
 //out.println( "<LI> <a href='auxPage.jsp?idr="+cont+"&auxTema=trueT'>"+MRecientes.getLista().get(cont).getTema()+" </a></Li>" ); 
      out.write("       \n");
      out.write("                        </UL>\n");
      out.write("\n");
      out.write("                        ");
 // k--;
                       //     cont++;
                             //   }

                     //       }}
                        
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("                    <div style=\"                 position:relative;\n");
      out.write("                         z-index: 5;\n");
      out.write("                         top:80px;\n");
      out.write("                         left:200px;\n");
      out.write("                         width:200px;\n");
      out.write("                         padding: .5em;\n");
      out.write("                         word-wrap:break-word;\">\n");
      out.write("\n");
      out.write("                        <div class=\"transparente1\">\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("                        <hr>\n");
      out.write("                        <p class=\"titulo2\"> Mas Visitados<p>\n");
      out.write("                        <hr>\n");
      out.write("\n");
      out.write("                        ");
//  if( session.getAttribute("TemasMasV")!=null){     
                           //cont=0;
                            //MVistos = (ListaTemas) session.getAttribute("TemasMasV");
                                
                            //if (MVistos.getLista().size() > 0) {
                              //  k=0;
                               // while (k <MVistos.getLista().size() && cont<5) {
                        
      out.write("\n");
      out.write("                        <UL>\n");
      out.write("                        ");
//out.println( "<LI> <a href='auxPage.jsp?idr="+cont+"&auxTema=trueV'>"+MVistos.getLista().get(k).getTema()+" </Li>" ); 
      out.write("         \n");
      out.write("                        </UL>\n");
      out.write("\n");
      out.write("                        ");
  //k++;
                           //cont++;
//                                }

                           // }}
                        
      out.write("\n");
      out.write("\n");
      out.write("                    </div>  \n");
      out.write("\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("        </table>\n");
      out.write("\n");
      out.write("        ");
///////////////////////////////// Herramientas de administrador

            //if (session.getAttribute("privilegios").equals("0")) {
      out.write(" \n");
      out.write("        ");
    if (session.getAttribute("OpcAdmin").equals("alta")) { 
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        <div style=\"                 position:absolute;\n");
      out.write("             z-index: 5;\n");
      out.write("             top:20px;\n");
      out.write("             left:1020px;\n");
      out.write("             width:200px;\n");
      out.write("             padding: .5em;\n");
      out.write("             word-wrap:break-word;\">\n");
      out.write("\n");
      out.write("            <div class=\"transparente1\">\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            Ingrese los siguientes datos\n");
      out.write("            <form action=\"OpcUsuarios.jsp\" method=\"post\"> \n");
      out.write("                Usuario:<input type=\"text\" name=\"usr\" value=\"\"><br>\n");
      out.write("                Clave:<input type=\"text\" name=\"clave\" value=\"\"><br>\n");
      out.write("                Privilegios(0 o 1):<input type=\"text\" name=\"priv\" value=\"\"><br>\n");
      out.write("                <input type=\"hidden\" name=\"tarea\" value=\"alta\"><br>\n");
      out.write("                <input type=\"submit\" value=\"Dar de Alta\"> \n");
      out.write("            </form>\n");
      out.write("            <a href=\"ReInicio.jsp?opcionAdmin=Re\">Regresar</a>\n");
      out.write("\n");
      out.write("\n");
      out.write("        </div> \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        ");
   } else if (session.getAttribute("OpcAdmin").equals("baja")) { 
      out.write("\n");
      out.write("\n");
      out.write("        <div style=\"                 position:absolute;\n");
      out.write("             z-index: 5;\n");
      out.write("             top:20px;\n");
      out.write("             left:1020px;\n");
      out.write("             width:200px;\n");
      out.write("             padding: .5em;\n");
      out.write("             word-wrap:break-word;\">\n");
      out.write("\n");
      out.write("            <div class=\"transparente1\">\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <p>Ingrese el usuario que desea borrar asi como la clave  </p>\n");
      out.write("            <form action=\"OpcUsuarios.jsp\" method=\"post\"> \n");
      out.write("                Usuario:<input type=\"text\" name=\"usr\" value=\"\"><br>\n");
      out.write("                Clave:<input type=\"text\" name=\"clave\" value=\"\"><br>\n");
      out.write("                <input type=\"hidden\" name=\"tarea\" value=\"baja\"><br>\n");
      out.write("                <input type=\"submit\" value=\"Dar de Baja\"> \n");
      out.write("            </form>\n");
      out.write("            <a href=\"ReInicio.jsp?opcionAdmin=Re\">Regresar</a>\n");
      out.write("\n");
      out.write("        </div> \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        ");
  } else if (session.getAttribute("OpcAdmin").equals("cambia")) { 
      out.write("      \n");
      out.write("        <div style=\"                 position:absolute;\n");
      out.write("             z-index: 5;\n");
      out.write("             top:05px;\n");
      out.write("             left:1020px;\n");
      out.write("             width:200px;\n");
      out.write("             padding: .5em;\n");
      out.write("             word-wrap:break-word;\">\n");
      out.write("\n");
      out.write("            <div class=\"transparente1\">\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("            <p>Ingrese Los siguientes datos  </p>\n");
      out.write("            <form action=\"OpcUsuarios.jsp\" method=\"post\"> \n");
      out.write("                Usuario:<input type=\"text\" name=\"usr\" value=\"\"><br>\n");
      out.write("                Clave:<input type=\"text\" name=\"clave\" value=\"\"><br>\n");
      out.write("                Nueva Clave:<input type=\"text\" name=\"nclave\" value=\"\"><br>\n");
      out.write("                <input type=\"hidden\" name=\"tarea\" value=\"cambia\"><br>\n");
      out.write("                <input type=\"submit\" value=\"Cambiar\"> \n");
      out.write("            </form>\n");
      out.write("            <a href=\"ReInicio.jsp?opcionAdmin=Re\">Regresar</a>\n");
      out.write("\n");
      out.write("        </div> \n");
      out.write("        ");
 //} else {
      out.write("         \n");
      out.write("\n");
      out.write("        <div style=\"                 position:absolute;\n");
      out.write("             z-index: 5;\n");
      out.write("             top:20px;\n");
      out.write("             left:1020px;\n");
      out.write("             width:200px;\n");
      out.write("             padding: .5em;\n");
      out.write("             word-wrap:break-word;\">\n");
      out.write("\n");
      out.write("            <div class=\"transparente1\">\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("            <hr>\n");
      out.write("            <p class=\"titulo4\">Herramientas Administrdor <p>\n");
      out.write("            <p><font color=\"red\">");
// if (request.getParameter("mensaje") != null) {
              //      out.println(request.getParameter("mensaje"));
                //}
                
      out.write("</font></p>\n");
      out.write("            <UL>\n");
      out.write("                <LI><a href=\"ReInicio.jsp?opcionAdmin=alta\">Dar de alta un usuario </a>\n");
      out.write("                <LI><a href=\"ReInicio.jsp?opcionAdmin=baja\">Borrar usuario</a> \n");
      out.write("                <LI><a href=\"ReInicio.jsp?opcionAdmin=cambia\">Cambiar cotraseña de usuario</a> \n");
      out.write("            </UL>\n");
      out.write("            <hr>\n");
      out.write("        </div>   \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        ");
 //}   
      out.write("\n");
      out.write("\n");
      out.write("        ");
 }////////////////////////////////////////////////////////////////////////
      out.write("\n");
      out.write("\n");
      out.write("        <br><br><br><br>    \n");
      out.write("    </body>\n");
      out.write("\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
