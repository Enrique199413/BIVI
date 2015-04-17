import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;
import java.util.*;

public class Busquedasimple extends HttpServlet
{
	String cadenareal, palabra, eid;
	Conexion objCon;
	Connection con;  
	Statement stm;
	Statement stme;
	PrintWriter out;
	ResultSet rs;
	ResultSet rse;
	boolean boleano;
	ArrayList<String> array;
	ArrayList<String> spalabra;
	ArrayList<String> ids;
	int i, j, k, l;
	
	public void init() throws ServletException
	{
		cadenareal="";
		palabra="";
		boleano=true;
		i=0;
		j=0;
		k=0;
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
			stme = con.createStatement();
		}catch(Exception e){System.out.println(e);}
		
		palabra="";
		cadenareal=request.getParameter("bus");
		array = new ArrayList<String>();
		spalabra = new ArrayList<String>();
		ids = new ArrayList<String>();

		for(i=0; i<cadenareal.length(); ++i)
		{
			if(i==(cadenareal.length()-1))
			{
				palabra=palabra+cadenareal.charAt(i);
				spalabra.add(palabra);
				palabra="";
			}
			if(cadenareal.charAt(i)==' ')
			{
				spalabra.add(palabra);
				palabra="";
			}
			else
			{
				palabra=palabra+cadenareal.charAt(i);
			}
		}
		
		try
		{
			rs = stm.executeQuery("SELECT codigo_art, titulo_art, nomalternativo_art, descripcion_art, asunto_art, autor_art FROM mlibro");
			while(rs.next())
			{
				array.add(rs.getString("codigo_art")+" "+rs.getString("titulo_art")+rs.getString("nomalternativo_art")+rs.getString("descripcion_art")+rs.getString("asunto_art")+rs.getString("autor_art"));
			}
			rs = stm.executeQuery("SELECT codigo_art, titulo_art, nomalternativo_art, descripcion_art, asunto_art, autor_art FROM mrevista");
			while(rs.next())
			{
				array.add(rs.getString("codigo_art")+" "+rs.getString("titulo_art")+rs.getString("nomalternativo_art")+rs.getString("descripcion_art")+rs.getString("asunto_art")+rs.getString("autor_art"));
			}
			rs = stm.executeQuery("SELECT codigo_art, titulo_art, nomalternativo_art, descripcion_art, asunto_art, autor_art, pie_imagen FROM mimagen");
			while(rs.next())
			{
				array.add(rs.getString("codigo_art")+" "+rs.getString("titulo_art")+rs.getString("nomalternativo_art")+rs.getString("descripcion_art")+rs.getString("asunto_art")+rs.getString("autor_art"));
			}
			rs = stm.executeQuery("SELECT codigo_art, titulo_art, nomalternativo_art, descripcion_art, asunto_art, autor_art FROM mvideo");
			while(rs.next())
			{
				array.add(rs.getString("codigo_art")+" "+rs.getString("titulo_art")+rs.getString("nomalternativo_art")+rs.getString("descripcion_art")+rs.getString("asunto_art")+rs.getString("autor_art"));
			}
			
			for(i=0; i<array.size(); ++i)
			{
				k=0;
				for(j=0; j<spalabra.size(); ++j)
				{
					if(array.get(i).indexOf(spalabra.get(j))!=-1)
					{
						++k;
					}
					if(k==spalabra.size())
					{
						ids.add(formarId(array.get(i)));
						boleano=true;
					}
				}
			}
		}
		catch(Exception e)
		{
			
		}
		
		if(boleano)
		{
			try
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
				out.println("Los resultados de su búsqueda se muestran a continuación:");
				out.println("<div id=\"resultados\">");
				for(i=0; i<ids.size(); ++i)
				{
					rs = stme.executeQuery("SELECT id_tipo FROM marticulo WHERE codigo_art="+ids.get(i));
					if(rs.next())
					{
						switch(rs.getInt("id_tipo"))
						{
							case 1:
								rse = stme.executeQuery("SELECT titulo_art, descripcion_art FROM mlibro WHERE codigo_art="+ids.get(i));
							break;
							case 2:
								rse = stme.executeQuery("SELECT titulo_art, descripcion_art FROM mrevista WHERE codigo_art="+ids.get(i));
							break;
							case 3:
								rse = stme.executeQuery("SELECT titulo_art, descripcion_art FROM mimagen WHERE codigo_art="+ids.get(i));
							break;
							case 4:
								rse = stme.executeQuery("SELECT titulo_art, descripcion_art FROM mvideo WHERE codigo_art="+ids.get(i));
							break;
							default:
								
							break;
						}
						if(rse.next())
						{
							out.println("<a href=\"articulo.jsp?codart="+ids.get(i)+"\"><div class=\"unres\">");
							out.println("<div class=\"images\"><img src=\"img/ipdch.png\"></div>");
							out.println("<div class=\"info\"><span class=\"tit\">Título: </span>"+rse.getString("titulo_art")+"<br><span class=\"tit\">Descripción: </span>"+rse.getString("descripcion_art")+"</div>");
							out.println("<div class=\"corte\"></div>");
							out.println("</div></a>");
						}
					}
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
			catch(Exception e)
			{
				out.println(e+"loco");
			}
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
	
	public String formarId(String completo) throws ServletException
	{
		eid="";
		l=0;
		while(l<completo.length())
		{
			if(completo.charAt(l)==' ')
			{
				return eid;
			}
			else
			{
				eid=eid+completo.charAt(l);
			}
			++l;
		}
		return "";
	}
}