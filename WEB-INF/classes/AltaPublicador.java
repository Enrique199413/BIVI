import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;


public class AltaPublicador extends HttpServlet
{
	String publicador;
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
			publicador=request.getParameter("publicador");
			stm.executeUpdate("INSERT INTO cpublicador (descripcion_publicador) VALUES ('"+publicador+"')");
			out.println("<div class=\"naranja\">El publicador se ha dado de alta correctamente.</div>");
		}
		catch(Exception e)
		{
			out.println("<div class=\"rojo\">Ha ocurrido un error el publicador no se ha dado de alta.</div>");
			System.out.println(e);
		}
	}
}