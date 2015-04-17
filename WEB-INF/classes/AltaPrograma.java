import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;


public class AltaPrograma extends HttpServlet
{
	String programa;
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
			programa=request.getParameter("programa");
			stm.executeUpdate("INSERT INTO cprograma (descripcion_programa) VALUES ('"+programa+"')");
			out.println("<div class=\"naranja\">El programa se ha dado de alta correctamente.</div>");
		}
		catch(Exception e)
		{
			out.println("<div class=\"rojo\">Ha ocurrido un error el programa no se ha dado de alta.</div>");
			System.out.println(e);
		}
	}
}