import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;


public class CambiarIdioma extends HttpServlet
{
	String id;
	String descripcion;
	Conexion objCon;
	Connection con;
	Statement stm;
	PrintWriter out;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		id = request.getParameter("iddc");
		descripcion = request.getParameter("idioma");
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
			stm.executeUpdate("UPDATE cidioma SET descripcion_idioma='"+descripcion+"' WHERE id_idioma='"+id+"'");
			out.println("<div class=\"naranja\">El idioma se ha cambiado correctamente.</div>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			out.println("<div class=\"rojo\">El idioma no se ha cambiado.</div>");
		}
	}
}