import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;


public class AltaTomo extends HttpServlet
{
	String tomo;
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
			tomo=request.getParameter("tomo");
			stm.executeUpdate("INSERT INTO ctomo (descripcion_tomo) VALUES ('"+tomo+"')");
			out.println("<div class=\"naranja\">El tomo se ha dado de alta correctamente.</div>");
		}
		catch(Exception e)
		{
			out.println("<div class=\"rojo\">Ha ocurrido un error el tomo no se ha dado de alta.</div>");
			System.out.println(e);
		}
	}
}