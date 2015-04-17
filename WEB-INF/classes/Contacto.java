import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;

public class Contacto extends HttpServlet
{
	String nombre, correo, comentario;
	Conexion objCon;
	Connection con;
	Statement stm;
	PrintWriter out;
	
	public void init() throws ServletException
	{
		nombre="";
		correo="";
		comentario="";
		try
		{
			objCon = new Conexion();
			con = objCon.conectar();
			stm = con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		nombre=request.getParameter("nombre");
		correo=request.getParameter("correo");
		comentario=request.getParameter("comentario");
		try
		{
			response.setContentType("text/html");
			out = response.getWriter();
			stm.executeUpdate("INSERT INTO mcontacto (nombre_persona, correo_persona, descripcion_contacto) VALUES ('"+nombre+"','"+correo+"','"+comentario+"')");
			objCon.cerrarConexion();
			con.close();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Biblioteca Virtual TFJFA</title>");
			out.println("<LINK REL=StyleSheet HREF=estilo/style.css TYPE=text/css>");
			out.println("<LINK REL=StyleSheet HREF=estilo/contactos.css TYPE=text/css>");
			out.println("</head>");
			out.println("<body>");
			out.println("<!--[if gt IE 6]><center><![endif]-->");
			out.println("<div id=header>");
			out.println("<ul id=ligas>");
			out.println("<a class=links href=contacto.html><li>Contacto</li></a>");
			out.println("<a class=links href=conocenos.html><li class=barrad>Conócenos</li></a>");
			out.println("<a href=http://www.tff.gob.mx/ class=links><li class=barrad>TFJFA</li></a>");
			out.println("</ul>");
			out.println("</div>");
			out.println("<div id=som1>");
			out.println("<div id=cabeza>");
			out.println("<img src=img/logoBV.png align=left>");
			out.println("</div>");
			out.println("</div>");
			out.println("<div id=som2>");
			out.println("<div id=menu>");
			out.println("<div id=cmenu>");
			out.println("<a href=\"index.jsp\"><div class=\"elemenu\">Inicio</div></a>");
			out.println("<a href=\"libro.jsp\"><div class=\"elemenu\">Libros</div></a>");
			out.println("<a href=\"revista.jsp\"><div class=\"elemenu\">Revistas</div></a>");
			out.println("<a href=\"imagen.jsp\"><div class=\"elemenu\">Imágenes</div></a>");
			out.println("<a href=\"video.jsp\"><div class=\"elemenu\">Videos</div></a>");
			out.println("</div>");
			out.println("</div>");
			out.println("<div id=contenido>");
			out.println("<div id=dentro>");
			out.println("<div id=mensaje>");
			out.println("<h1>Contacto</h1>");
			out.println("Para contactar con el administrador de la Biblioteca Virtual del Tribunal Federal de Justicia Fiscal y Administrativa,");
			out.println("por favor llene los datos que se piden a continuación según le convenga. En caso de hacer una pregunta deberá llenar todos");
			out.println("los campos. En caso de hacer una sugerencia, puede o no llenar los campos nombre y correo.");
			out.println("</div>");
			out.println("<br>");
			out.println("<br>");
			out.println("<table class=centrar id=tabla>");
			out.println("<form method=post action=Contacto>");
			out.println("<tr>");
			out.println("<td class=tizq>Nombre:</td>");
			out.println("<td><input type=text class=texto id=nom name=nombre></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=tizq>Correo:</td>");
			out.println("<td><input type=text class=texto id=cor name=correo></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=tizq>Comentario:</td>");
			out.println("<td><textarea class=texta id=com name=comentario></textarea></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=tizq><input type=submit value=Enviar class=boton id=bpbs></td>");
			out.println("<td><input type=reset value=Limpiar class=boton id=bpbs></td>");
			out.println("</tr>");
			out.println("</form>");
			out.println("</table>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<h1 id=nara>¡Su comentario he llegado correctamente al administrador!</h1>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("<div id=som3>");
			out.println("<div id=footer>");
			out.println("<div id=sobrante>");
			out.println("</div>");
			out.println("<div id=division></div>");
			out.println("<div id=let>");
			out.println("Tribunal Federal de Justicia Fiscal y Administrativa");
			out.println("<br>© Centro de Estudios Superiores en Materia de Derecho Fiscal y Administrativo 2012");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("<!--[if gt IE 6]></center><![endif]-->");
			out.println("</body>");
			out.println("</html>");
		}
		catch(Exception e)
		{
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Biblioteca Virtual TFJFA</title>");
			out.println("<LINK REL=StyleSheet HREF=estilo/style.css TYPE=text/css>");
			out.println("<LINK REL=StyleSheet HREF=estilo/contactos.css TYPE=text/css>");
			out.println("</head>");
			out.println("<body>");
			out.println("<!--[if gt IE 6]><center><![endif]-->");
			out.println("<div id=header>");
			out.println("<ul id=ligas>");
			out.println("<a class=links href=contacto.html><li>Contacto</li></a>");
			out.println("<a class=links href=conocenos.html><li class=barrad>Conócenos</li></a>");
			out.println("<a href=http://www.tff.gob.mx/ class=links><li class=barrad>TFJFA</li></a>");
			out.println("</ul>");
			out.println("</div>");
			out.println("<div id=som1>");
			out.println("<div id=cabeza>");
			out.println("<img src=img/logoBV.png align=left>");
			out.println("</div>");
			out.println("</div>");
			out.println("<div id=som2>");
			out.println("<div id=menu>");
			out.println("<div id=cmenu>");
			out.println("<a href=\"index.jsp\"><div class=\"elemenu\">Inicio</div></a>");
			out.println("<a href=\"libro.jsp\"><div class=\"elemenu\">Libros</div></a>");
			out.println("<a href=\"revista.jsp\"><div class=\"elemenu\">Revistas</div></a>");
			out.println("<a href=\"imagen.jsp\"><div class=\"elemenu\">Imágenes</div></a>");
			out.println("<a href=\"video.jsp\"><div class=\"elemenu\">Videos</div></a>");
			out.println("</div>");
			out.println("</div>");
			out.println("<div id=contenido>");
			out.println("<div id=dentro>");
			out.println("<div id=mensaje>");
			out.println("<h1>Contacto</h1>");
			out.println("Para contactar con el administrador de la Biblioteca Virtual del Tribunal Federal de Justicia Fiscal y Administrativa,");
			out.println("por favor llene los datos que se piden a continuación según le convenga. En caso de hacer una pregunta deberá llenar todos");
			out.println("los campos. En caso de hacer una sugerencia, puede o no llenar los campos nombre y correo.");
			out.println("</div>");
			out.println("<br>");
			out.println("<br>");
			out.println("<table class=centrar id=tabla>");
			out.println("<form method=post action=Contacto>");
			out.println("<tr>");
			out.println("<td class=tizq>Nombre:</td>");
			out.println("<td><input type=text class=texto id=nom name=nombre></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=tizq>Correo:</td>");
			out.println("<td><input type=text class=texto id=cor name=correo></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=tizq>Comentario:</td>");
			out.println("<td><textarea class=texta id=com name=comentario></textarea></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=tizq><input type=submit value=Enviar class=boton id=bpbs></td>");
			out.println("<td><input type=reset value=Limpiar class=boton id=bpbs></td>");
			out.println("</tr>");
			out.println("</form>");
			out.println("</table>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<h1 id=rojo>¡Ha ocurrido un error, su mensaje no ha podido llegar al administrador, </h1>");
			out.println("<br>");
			out.println("<h1 id=rojo>intente de nuevo mas tarde!</h1>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("<div id=som3>");
			out.println("<div id=footer>");
			out.println("<div id=sobrante>");
			out.println("</div>");
			out.println("<div id=division></div>");
			out.println("<div id=let>");
			out.println("Tribunal Federal de Justicia Fiscal y Administrativa");
			out.println("<br>© Centro de Estudios Superiores en Materia de Derecho Fiscal y Administrativo 2012");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("<!--[if gt IE 6]></center><![endif]-->");
			out.println("</body>");
			out.println("</html>");
		}
	}
}