import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;


public class DatosIdioma extends HttpServlet
{
	String id;
	Conexion objCon;
	Connection con;
	Statement stm;
	ResultSet rs;
	PrintWriter out;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		id = request.getParameter("id");
		try
		{
			response.setContentType("text/html");
			out = response.getWriter();
			objCon = new Conexion();
			con = objCon.conectar();
			stm = con.createStatement();
			
		}
		catch(Exception e){}
		try
		{
			rs=stm.executeQuery("SELECT * FROM cidioma WHERE id_idioma='"+id+"'");
			if(rs.next())
			{
				out.println("<form method=\"post\" action=\"CambiarIdioma\" class=\"demasd\">");
				out.println("<table class=\"contenidosalls\">");
				out.println("<tr><td>Idioma:</td><td><input type=\"text\" class=\"texto\" name=\"idioma\" value=\""+rs.getString("descripcion_idioma")+"\" /><input type=\"hidden\" name=\"iddc\" value=\""+rs.getString("id_idioma")+"\" /></td></tr>");
				out.println("<tr><td><input type=\"submit\" value=\"Cambiar\" class=\"boton\"/></td><td></td></tr>");
				out.println("</table>");
				out.println("</form>");
			}
		}
		catch(Exception e)
		{
			out.println("<div class=\"rojo\">El idioma no se puede cambiar, intente de nuevo mas tarde.</div>");
		}
	}
}