import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;

public class Desplegadoarticulovideo extends HttpServlet
{
	int parametro;
	Conexion objCon;
	Connection con;
	Statement stm;
	Statement stme;
	ResultSet rs;
	PrintWriter out;
	
	public void init() throws ServletException
	{
		parametro=0;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			objCon = new Conexion();
			con = objCon.conectar();
			stm = con.createStatement();
			stme= con.createStatement();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		parametro=Integer.parseInt(request.getParameter("pagina"));
		try
		{
			response.setContentType("text/html");
			out = response.getWriter();
			
			rs = stm.executeQuery("SELECT * FROM marticulo RIGHT JOIN mvideo ON marticulo.codigo_art=mvideo.codigo_art ORDER BY fecha_publicacion DESC LIMIT "+((parametro*10)-10)+", 10");
			while(rs.next())
			{
				out.println("<a href=\"articulo.jsp?codart="+rs.getString("codigo_art")+"\">");
					out.println("<div class=\"unobra\">");
						out.println("<div class=\"imgobmed\">");
							out.println("<img class=\"thumbnail2\" src=\""+rs.getString("img_art")+"\">");
						out.println("</div>");
						out.println("<div class=\"infoob\">");
							out.println("<span class=\"tit2\">Titulo: </span>"+rs.getString("titulo_art")+"<br>");
							out.println("<span class=\"tit2\">Autor: </span>"+rs.getString("autor_art")+"<br>");
								out.println("<span class=\"tit2\">Asunto: </span>"+rs.getString("asunto_art")+" edición<br>");
								out.println("<span class=\"tit2\">Duración del video: </span>"+rs.getString("duracion_video")+"<br>");
							out.println("<span class=\"tit2\">Fecha de creación: </span>"+rs.getString("fecha_creacion_art")+"<br>");
							out.println("<span class=\"tit2\">Descripción: </span>"+rs.getString("descripcion_art")+"<br>");
						out.println("</div>");
					out.println("</div>");
				out.println("</a>");
			}
			out.close();
			objCon.cerrarConexion();
			con.close();
			stm.close();
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}