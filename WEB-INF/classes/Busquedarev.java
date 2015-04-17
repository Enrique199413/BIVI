import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;
import java.util.*;

public class Busquedarev extends HttpServlet
{
	String titulo, tituloalt, descripcion, editorial, edicion, noderev, asunto, autor, where;
	Conexion objCon;
	Connection con;  
	Statement stm;
	PrintWriter out;
	ResultSet rs;
	boolean boleano;
	
	public void init() throws ServletException
	{
		titulo="";
		tituloalt="";
		descripcion="";
		editorial="";
		edicion="";
		noderev="";
		asunto="";
		autor="";
		where="";
		boleano=true;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		boleano=false;
		try
		{
			response.setContentType("text/html");
			out = response.getWriter();
			objCon = new Conexion();
			con = objCon.conectar();
			stm = con.createStatement();
		}
		catch(Exception e){System.out.println(e);}
		
		where="";
		titulo=request.getParameter("titulo");
		tituloalt=request.getParameter("tituloalt");
		descripcion=request.getParameter("descripcio");
		editorial=request.getParameter("editorial");
		edicion=request.getParameter("edicion");
		noderev=request.getParameter("ndrev");
		asunto=request.getParameter("asunto");
		autor=request.getParameter("autor");
		
		if(!titulo.equals(""))
		{
			where=where+"titulo_art LIKE \"%"+titulo+"%\" AND ";
			boleano=true;
		}
		if(!tituloalt.equals(""))
		{
			where=where+"nomalternativo_art LIKE \"%"+tituloalt+"%\" AND ";
			boleano=true;
		}
		if(!descripcion.equals(""))
		{
			where=where+"descripcion_art LIKE \"%"+descripcion+"%\" AND ";
			boleano=true;
		}
		if(!editorial.equals(""))
		{
			where=where+"descripcion_editorial LIKE \"%"+editorial+"%\" AND ";
			boleano=true;
		}
		if(!edicion.equals(""))
		{
			where=where+"descripcion_edicion LIKE \"%"+edicion+"%\" AND ";
			boleano=true;
		}
		if(!noderev.equals(""))
		{
			where=where+"descripcion_tomo LIKE \"%"+noderev+"%\" AND ";
			boleano=true;
		}
		if(!asunto.equals(""))
		{
			where=where+"asunto_art LIKE \"%"+asunto+"%\" AND ";
			boleano=true;
		}
		if(!autor.equals(""))
		{
			where=where+"autor_art LIKE \"%"+autor+"%\" AND ";
			boleano=true;
		}
		if(boleano)
		{
			where = where.substring(0,where.length()-5);
			try
			{
				rs = stm.executeQuery("SELECT codigo_art, titulo_art, descripcion_art FROM mrevista LEFT JOIN ceditorial ON mrevista.id_editorial=ceditorial.id_editorial LEFT JOIN cedicion ON mrevista.id_edicion=cedicion.id_edicion LEFT JOIN ctomo ON mrevista.id_no_revista=ctomo.id_tomo WHERE "+where);
				if(rs.next())
				{
					rs.beforeFirst();
					out.println("<html>");
					out.println("<head>");
					out.println("<title>Biblioteca Virtual TFJFA</title>");
					out.println("<script language=\"javascript\" type=\"text/javascript\" src=\"guion/jquery.js\"></script>");
					out.println("</script>");
					out.println("<script language=\"javascript\" type=\"text/javascript\" src=\"guion/busquedagui.js\"></script>");
					out.println("</script>");
					out.println("<LINK REL=\"StyleSheet\" HREF=\"estilo/style.css\" TYPE=\"text/css\">");
					out.println("<LINK REL=\"StyleSheet\" HREF=\"estilo/busquedas.css\" TYPE=\"text/css\">");
					out.println("</head>");
					
					out.println("<body>");
					out.println("<!--[if gt IE 6]><center><![endif]-->");
					
					out.println("<div id=\"header\">");
					out.println("<ul id=\"ligas\">");
					out.println("<a class=\"links\" href=\"contacto.html\"><li>Contacto</li></a>");
					out.println("<a class=\"links\" href=\"conocenos.html\"><li class=\"barrad\">Conócenos</li></a>");
					out.println("<a href=\"http://www.tff.gob.mx/\" class=\"links\"><li class=\"barrad\">TFJFA</li></a>");
					out.println("</ul>");
					out.println("</div>");
					
					out.println("<div id=\"som1\">");
					out.println("<div id=\"cabeza\">");
					out.println("<img src=\"img/logoBV.png\" align=\"left\">");
					out.println("</div>");
					out.println("</div>");
					
					out.println("<div id=\"som2\">");
					out.println("<div id=\"menu\">");
					out.println("<div id=\"cmenu\">");
					out.println("<a href=\"index.jsp\"><div class=\"elemenu\">Inicio</div></a>");
					out.println("<a href=\"libro.jsp\"><div class=\"elemenu\">Libros</div></a>");
					out.println("<a href=\"revista.jsp\"><div class=\"elemenu\">Revistas</div></a>");
					out.println("<a href=\"imagen.jsp\"><div class=\"elemenu\">Imágenes</div></a>");
					out.println("<a href=\"video.jsp\"><div class=\"elemenu\">Videos</div></a>");
					out.println("</div>");
					out.println("</div>");
					
					out.println("<div id=\"contenido\">");
					out.println("Los resultados de su búsqueda se muestran a continuación:");
					out.println("<div id=\"resultados\">");
					while(rs.next())
					{
						out.println("<a href=\"articulo.jsp?codart="+rs.getString("codigo_art")+"\"><div class=\"unres\">");
						out.println("<div class=\"images\"><img src=\"img/ipdch.png\"></div>");
						out.println("<div class=\"info\"><span class=\"tit\">Título: </span>"+rs.getString("titulo_art")+"<br><span class=\"tit\">Descripción: </span>"+rs.getString("descripcion_art")+"</div>");
						out.println("<div class=\"corte\"></div>");
						out.println("</div></a>");
					}
					out.println("</div>");
					out.println("<div class=\"corte\"></div>");
					out.println("</div>");
					out.println("</div>");
					
					out.println("<div id=\"som3\">");
					out.println("<div id=\"footer\">");
					out.println("<div id=\"sobrante\">");
					
					out.println("</div>");
					
					out.println("<div id=\"division\"></div>");
					
					out.println("<div id=\"let\">");
					out.println("Tribunal Federal de Justicia Fiscal y Administrativa");
					out.println("<br>© Centro de Estudios Superiores en Materia de Derecho Fiscal y Administrativo 2012");
					out.println("</div>");
					out.println("</div>");
					out.println("</div>");
					out.println("<!--[if gt IE 6]></center><![endif]-->");
					out.println("</body>");
					out.println("</html>");
				}
				else
				{
					out.println("<html>");
					out.println("<head>");
					out.println("<title>Biblioteca Virtual TFJFA</title>");
					out.println("<script language=\"javascript\" type=\"text/javascript\" src=\"guion/jquery.js\"></script>");
					out.println("</script>");
					out.println("<script language=\"javascript\" type=\"text/javascript\" src=\"guion/busquedagui.js\"></script>");
					out.println("</script>");
					out.println("<LINK REL=\"StyleSheet\" HREF=\"estilo/style.css\" TYPE=\"text/css\">");
					out.println("<LINK REL=\"StyleSheet\" HREF=\"estilo/busquedas.css\" TYPE=\"text/css\">");
					out.println("</head>");
					
					out.println("<body>");
					out.println("<!--[if gt IE 6]><center><![endif]-->");
					
					out.println("<div id=\"header\">");
					out.println("<ul id=\"ligas\">");
					out.println("<a class=\"links\" href=\"contacto.html\"><li>Contacto</li></a>");
					out.println("<a class=\"links\" href=\"conocenos.html\"><li class=\"barrad\">Conócenos</li></a>");
					out.println("<a href=\"http://www.tff.gob.mx/\" class=\"links\"><li class=\"barrad\">TFJFA</li></a>");
					out.println("</ul>");
					out.println("</div>");
					
					out.println("<div id=\"som1\">");
					out.println("<div id=\"cabeza\">");
					out.println("<img src=\"img/logoBV.png\" align=\"left\">");
					out.println("</div>");
					out.println("</div>");
					
					out.println("<div id=\"som2\">");
					out.println("<div id=\"menu\">");
					out.println("<div id=\"cmenu\">");
					out.println("<a href=\"index.jsp\"><div class=\"elemenu\">Inicio</div></a>");
					out.println("<a href=\"libro.jsp\"><div class=\"elemenu\">Libros</div></a>");
					out.println("<a href=\"revista.jsp\"><div class=\"elemenu\">Revistas</div></a>");
					out.println("<a href=\"imagen.jsp\"><div class=\"elemenu\">Imágenes</div></a>");
					out.println("<a href=\"video.jsp\"><div class=\"elemenu\">Videos</div></a>");
					out.println("</div>");
					out.println("</div>");
					
					out.println("<div id=\"contenido\">");
					out.println("<div id=\"nhr\">");
					out.println("No se encontraron artículos.");
					out.println("</div>");
					out.println("</div>");
					out.println("</div>");
					
					out.println("<div id=\"som3\">");
					out.println("<div id=\"footer\">");
					out.println("<div id=\"sobrante\">");
					
					out.println("</div>");
					
					out.println("<div id=\"division\"></div>");
					
					out.println("<div id=\"let\">");
					out.println("Tribunal Federal de Justicia Fiscal y Administrativa");
					out.println("<br>© Centro de Estudios Superiores en Materia de Derecho Fiscal y Administrativo 2012");
					out.println("</div>");
					out.println("</div>");
					out.println("</div>");
					out.println("<!--[if gt IE 6]></center><![endif]-->");
					out.println("</body>");
					out.println("</html>");
				}
				while(rs.next())
				{
					try
					{
						out.println(rs.getInt("codigo_art"));
					}
					catch(Exception e){}
				}
			}
			catch(Exception e)
			{
				out.println(e);
			}
		}
		else
		{
			try
			{
				response.sendRedirect("revista.jsp");
			}
			catch(Exception e){};
		}
		try
		{
			objCon.cerrarConexion();
			con.close();
			stm.close();
			out.close();
			rs.close();
		}
		catch(Exception e){}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		try
		{
			response.sendRedirect("index.jsp");
		}
		catch(Exception e){};
	}
}