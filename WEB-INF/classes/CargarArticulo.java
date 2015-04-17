import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;


public class CargarArticulo extends HttpServlet
{
	String edicion;
	Conexion objCon;
	Connection con;
	Statement stm;
	ResultSet rs;
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
		catch(Exception e){}
		try
		{
			out.println("<table cellspacing=\"0\" cellpadding=\"0\" id=\"tabladeart\">");
			rs=stm.executeQuery("SELECT * FROM marticulo RIGHT JOIN mlibro ON marticulo.codigo_art=mlibro.codigo_art WHERE id_tipo=1;");
			while(rs.next())
			{
				out.println("<form method=\"post\" action=\"BorrarArticulo\" class=\"boart\"><tr><td class=\"unart\"><span class=\"resaltar\">Codigo:</span>"+rs.getString("codigo_art")+"<br><span class=\"resaltar\">Título:</span>"+rs.getString("titulo_art")+"</td><td class=\"unart\"><input type=\"hidden\" value=\""+rs.getString("codigo_art")+"\" name=\"codigo\"/><input type=\"hidden\" value=\""+rs.getString("id_tipo")+"\" name=\"tipo\"/><input type=\"submit\" value=\"borrar\" class=\"boton\"/></td></tr></form>");
			}
			rs=stm.executeQuery("SELECT * FROM marticulo RIGHT JOIN mrevista ON marticulo.codigo_art=mrevista.codigo_art WHERE id_tipo=2;");
			while(rs.next())
			{
				out.println("<form method=\"post\" action=\"BorrarArticulo\" class=\"boart\"><tr><td class=\"unart\"><span class=\"resaltar\">Codigo:</span>"+rs.getString("codigo_art")+"<br><span class=\"resaltar\">Título:</span>"+rs.getString("titulo_art")+"</td><td class=\"unart\"><input type=\"hidden\" value=\""+rs.getString("codigo_art")+"\" name=\"codigo\"/><input type=\"hidden\" value=\""+rs.getString("id_tipo")+"\" name=\"tipo\"/><input type=\"submit\" value=\"borrar\" class=\"boton\"/></td></tr></form>");
			}
			rs=stm.executeQuery("SELECT * FROM marticulo RIGHT JOIN mimagen ON marticulo.codigo_art=mimagen.codigo_art WHERE id_tipo=3;");
			while(rs.next())
			{
				out.println("<form method=\"post\" action=\"BorrarArticulo\" class=\"boart\"><tr><td class=\"unart\"><span class=\"resaltar\">Codigo:</span>"+rs.getString("codigo_art")+"<br><span class=\"resaltar\">Título:</span>"+rs.getString("titulo_art")+"</td><td class=\"unart\"><input type=\"hidden\" value=\""+rs.getString("codigo_art")+"\" name=\"codigo\"/><input type=\"hidden\" value=\""+rs.getString("id_tipo")+"\" name=\"tipo\"/><input type=\"submit\" value=\"borrar\" class=\"boton\"/></td></tr></form>");
			}
			rs=stm.executeQuery("SELECT * FROM marticulo RIGHT JOIN mvideo ON marticulo.codigo_art=mvideo.codigo_art WHERE id_tipo=4;");
			while(rs.next())
			{
				out.println("<form method=\"post\" action=\"BorrarArticulo\" class=\"boart\"><tr><td class=\"unart\"><span class=\"resaltar\">Codigo:</span>"+rs.getString("codigo_art")+"<br><span class=\"resaltar\">Título:</span>"+rs.getString("titulo_art")+"</td><td class=\"unart\"><input type=\"hidden\" value=\""+rs.getString("codigo_art")+"\" name=\"codigo\"/><input type=\"hidden\" value=\""+rs.getString("id_tipo")+"\" name=\"tipo\"/><input type=\"submit\" value=\"borrar\" class=\"boton\"/></td></tr></form>");
			}
			out.println("</table>");
		}
		catch(Exception e)
		{
			out.println("<div class=\"rojo\">Ha ocurrido un error la edicion no se ha dado de alta.</div>");
		}
		
	}
}