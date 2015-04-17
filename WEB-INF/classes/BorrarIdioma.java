import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;


public class BorrarIdioma extends HttpServlet
{
	String id;
	Conexion objCon;
	Connection con;
	Statement stm;
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
			stm.executeUpdate("DELETE FROM cidioma WHERE id_idioma='"+id+"'");
			out.println("<div class=\"naranja\">El idioma se ha borrado correctamente.</div>");
		}
		catch(Exception e)
		{
			out.println("<div class=\"rojo\">El idioma no se ha borrado.</div>");
		}
	}
}