import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;


public class AltaEdicion extends HttpServlet
{
	String edicion;
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
			edicion=request.getParameter("edicion");
			stm.executeUpdate("INSERT INTO cedicion (descripcion_edicion) VALUES ('"+edicion+"')");
			out.println("<div class=\"naranja\">La edicion se ha dado de alta correctamente.</div>");
		}
		catch(Exception e)
		{
			out.println("<div class=\"rojo\">Ha ocurrido un error la edicion no se ha dado de alta.</div>");
			System.out.println(e);
		}
	}
}