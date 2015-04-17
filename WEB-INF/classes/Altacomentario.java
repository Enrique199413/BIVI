import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;
import java.util.*;

public class Altacomentario extends HttpServlet
{
	String fechac, comentario, codigo;
	Conexion objCon;
	Connection con;
	Statement stm;
	PrintWriter out;
	ResultSet rs;
	
	public void init() throws ServletException
	{
		fechac="";
		comentario="";
		codigo="";
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		Calendar fecha = new GregorianCalendar();
		fechac=fecha.get(Calendar.YEAR)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.DATE)+"";
		comentario=request.getParameter("comentario");
		codigo=request.getParameter("codart");
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
			stm.executeUpdate("INSERT INTO mcomentario (codigo_art, des_comentario, fecha_comentario) VALUES ("+codigo+", '"+comentario+"', '"+fechac+"');");
			rs=stm.executeQuery("SELECT des_comentario, fecha_comentario FROM mcomentario WHERE codigo_art='"+codigo+"'");
			if (rs.next())
			{
				out.println("<span class=\"tit2\">Comentarios:</span>");
				rs.beforeFirst();
				while (rs.next())
				{
					out.println("<div class=\"unc\">"+rs.getString("des_comentario")+"<br><div class=\"fechach\">"+rs.getString("fecha_comentario")+"</div></div>");
				}
			}
			else
			{
				out.println("<div class=\"uncn\">No hay ningún comentario aún</div>");
			}
		}
		catch(Exception e)
		{
			out.println("<div id=\"errorc\">Ha ocurrido un error, su comentario no se pudo publicar. Por favor intente de nuevo más tarde.</div>");
		}
		try
		{
			objCon.cerrarConexion();
			con.close();
			stm.close();
			out.close();
			rs.close();
		}
		catch(Exception e)
		{
		}
	}
}