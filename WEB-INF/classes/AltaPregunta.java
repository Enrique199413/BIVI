import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;
import java.util.*;

public class AltaPregunta extends HttpServlet
{
	Conexion objCon;
	Connection con;
	Statement stm;
	ResultSet rs;
	PrintWriter out;
	String fechac, pregunta, correo, codigo;
	
	public void init() throws ServletException
	{
		fechac="";
		pregunta="";
		correo="";
		codigo="";
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		Calendar fecha = new GregorianCalendar();
		fechac=fecha.get(Calendar.YEAR)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.DATE)+"";
		pregunta=request.getParameter("pregunta");
		correo=request.getParameter("correo");
		codigo=request.getParameter("codigo");
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
			stm.executeUpdate("INSERT INTO mpregunta (codigo_art, des_pregunta, fecha_pregunta, correo_per) VALUES ("+codigo+", '"+pregunta+"', '"+fechac+"', '"+correo+"');");
			out.println("<h1 id=\"nara\">Su pregunta se ha enviado correctamente.</h1>");
		}
		catch(Exception e)
		{
			out.println("<h1 id=\"rojo\">Ha ocurrido un error, su pregunta no se envio. Por favor intente de nuevo más tarde.</h1>");
		}
		try
		{
			objCon.cerrarConexion();
			con.close();
			stm.close();
			rs.close();
			out.close();
		}
		catch(Exception e){}
	}
}