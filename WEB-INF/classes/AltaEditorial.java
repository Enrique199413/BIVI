import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;


public class AltaEditorial extends HttpServlet
{
	String editorial;
	Conexion objCon;
	Connection con;
	Statement stm;
	PrintWriter out;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		try
		{
			response.setContentType("text/html");
			out = response.getWriter();
			objCon = new Conexion();
			con = objCon.conectar();
			stm = con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		try
		{
			editorial=request.getParameter("editorial");
			stm.executeUpdate("INSERT INTO ceditorial (descripcion_editorial) VALUES ('"+editorial+"')");
			out.println("<div class=\"naranja\">La editorial se ha dado de alta correctamente.</div>");
		}
		catch(Exception e)
		{
			out.println("<div class=\"rojo\">Ha ocurrido un error la editorial no se ha dado de alta.</div>");
			System.out.println(e);
		}
	}
}