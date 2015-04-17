import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;

public class Desplegadoarticulorevista extends HttpServlet
{
	int parametro;
	Conexion objCon;
	Connection con;
	Statement stm;
	Statement stme;
	ResultSet rs;
	ResultSet rste;
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
			rs = stm.executeQuery("SELECT * FROM marticulo RIGHT JOIN mrevista ON marticulo.codigo_art=mrevista.codigo_art ORDER BY fecha_publicacion DESC LIMIT "+((parametro*10)-10)+", 10");
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
							rste = stme.executeQuery("SELECT descripcion_edicion FROM cedicion WHERE id_edicion="+rs.getString("id_edicion"));
							if(rste.next())
							{
								out.println("<span class=\"tit2\">Edición: </span>"+rste.getString("descripcion_edicion")+" edición<br>");
							}
							 
							rste = stme.executeQuery("SELECT descripcion_tomo FROM ctomo WHERE id_tomo="+rs.getString("id_no_revista"));
							if(rste.next())
							{
								out.println("<span class=\"tit2\">No. de revista: </span>"+rste.getString("descripcion_tomo")+"<br>");
							}
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
			rste.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}