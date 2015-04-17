import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;


public class BorrarArticulo extends HttpServlet
{
	String codigo;
	Conexion objCon;
	Connection con;
	Statement stm;
	PrintWriter out;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		codigo = request.getParameter("id");
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
			stm.executeUpdate("DELETE FROM marticulo WHERE codigo_art='"+codigo+"'");
			out.println("<div class=\"naranja\">El articulo se ha borrado correctamente.</div>");
		}
		catch(Exception e)
		{
			out.println("<div class=\"rojo\">El articulo no se ha borrado.</div>");
		}
	}
}