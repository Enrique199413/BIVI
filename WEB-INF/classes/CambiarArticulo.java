import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;


public class CambiarArticulo extends HttpServlet
{
	String codigo; 
	int tipo;
	Conexion objCon;
	Connection con;
	Statement stm;
	ResultSet rs;
	PrintWriter out;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		codigo = request.getParameter("codigo");
		tipo = Integer.parseInt(request.getParameter("tipo"));
		try
		{
			response.setContentType("text/html");
			out = response.getWriter();
			objCon = new Conexion();
			con = objCon.conectar();
			stm = con.createStatement();
			
		}
		catch(Exception e){}
		out.println("<br>");
		try
		{
			switch(tipo)
			{
				case 1:
					rs=stm.executeQuery("SELECT * FROM mlibro WHERE codigo_art='"+codigo+"'");
					if(rs.next())
					{
						out.println("<form action=\"CambioLibro\" method=\"post\" class=\"camarte\">");
						out.println("<table class=\"tfor\">");
						out.println("<tr><td class=\"alider\">Código del artículo:</td><td><input type=\"text\" class=\"texto\" name=\"codigoart\" value=\""+rs.getString("codigo_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Título del artículo:</td><td><input type=\"text\" class=\"texto\" name=\"tituloart\" value=\""+rs.getString("titulo_art")+"\" /></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Título alternativo artículo:</td><td><input type=\"text\" class=\"texto\" name=\"tituloaltart\" value=\""+rs.getString("nomalternativo_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Asunto:</td><td><input type=\"text\" class=\"texto\" name=\"asutoart\" value=\""+rs.getString("asunto_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Autor:</td><td><input type=\"text\" class=\"texto\" name=\"autorart\" value=\""+rs.getString("autor_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Fecha de creación:</td><td><input type=\"text\" class=\"texto\" name=\"fechcreacionart\" value=\""+rs.getString("fecha_creacion_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Descripción:</td><td><textarea class=\"texta\" name=\"descart\">"+rs.getString("descripcion_art")+"</textarea></td><td class=\"ast\">*</td><td></td></tr>");
					}
					out.println("<tr><td class=\"alider\">Editorial:</td><td><select class=\"combo\" name=\"editorialart\">");
					rs=stm.executeQuery("SELECT * FROM ceditorial");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_editorial")+"\">"+rs.getString("descripcion_editorial")+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td class=\"alider\">Edición:</td><td><select class=\"combo\" name=\"edicionart\">");
					rs=stm.executeQuery("SELECT * FROM cedicion");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_edicion")+"\">"+rs.getString("descripcion_edicion")+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td class=\"alider\">Tomo:</td><td><select class=\"combo\" name=\"tomoart\">");
					rs=stm.executeQuery("SELECT * FROM ctomo");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_tomo")+"\">"+rs.getString("descripcion_tomo")+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td class=\"alider\">Formato:</td><td><select class=\"combo\" name=\"formatoart\">");
					rs=stm.executeQuery("SELECT * FROM cformato");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_formato")+"\">"+rs.getString("descripcion_formato")+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td class=\"alider\">Publicador:</td><td><select class=\"combo\" name=\"publicadorart\">");
					rs=stm.executeQuery("SELECT * FROM cpublicador");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_publicador")+"\">"+rs.getString("descripcion_publicador")+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td class=\"alider\">Programa:</td><td><select class=\"combo\" name=\"programaart\">");
					rs=stm.executeQuery("SELECT * FROM cprograma");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_programa")+"\">"+rs.getString("descripcion_programa")+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td class=\"alider\" colspan=\"2\">Seleccione los idiomas en los cuales este la obra:</td><td class=\"ast\">*</td><td></td></tr>");
					rs=stm.executeQuery("SELECT * FROM cidioma");
					while(rs.next())
					{
						out.println("<tr><td class=\"alider\">"+rs.getString("descripcion_idioma")+":</td><td><input type=\"checkbox\" class=\"checkb\" value=\""+rs.getString("id_idioma")+"\" name=\"idioma"+rs.getString("id_idioma")+"\"/></td><td class=\"ast\"></td><td></td></tr>");
					}
					out.println("<tr><td colspan=\"4\"><input type=\"submit\" class=\"boton\" value=\"Cambiar\" /></td></tr>");
					out.println("</table>");
					out.println("</form>");
				break;
				case 2:
					rs=stm.executeQuery("SELECT * FROM mrevista WHERE codigo_art='"+codigo+"'");
					if(rs.next())
					{
						out.println("<form action=\"CambioRevista\" method=\"post\" class=\"camarte\">");
						out.println("<table class=\"tfor\">");
						out.println("<tr><td class=\"alider\">Código del artículo:</td><td><input type=\"text\" class=\"texto\" name=\"codigoart\" value=\""+rs.getString("codigo_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Título del artículo:</td><td><input type=\"text\" class=\"texto\" name=\"tituloart\" value=\""+rs.getString("titulo_art")+"\" /></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Título alternativo artículo:</td><td><input type=\"text\" class=\"texto\" name=\"tituloaltart\" value=\""+rs.getString("nomalternativo_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Asunto:</td><td><input type=\"text\" class=\"texto\" name=\"asutoart\" value=\""+rs.getString("asunto_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Autor:</td><td><input type=\"text\" class=\"texto\" name=\"autorart\" value=\""+rs.getString("autor_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Fecha de creación:</td><td><input type=\"text\" class=\"texto\" name=\"fechcreacionart\" value=\""+rs.getString("fecha_creacion_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Descripción:</td><td><textarea class=\"texta\" name=\"descart\">"+rs.getString("descripcion_art")+"</textarea></td><td class=\"ast\">*</td><td></td></tr>");
					}
					out.println("<tr><td class=\"alider\">Editorial:</td><td><select class=\"combo\" name=\"editorialart\">");
					rs=stm.executeQuery("SELECT * FROM ceditorial");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_editorial")+"\">"+rs.getString("descripcion_editorial")+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td class=\"alider\">Edición:</td><td><select class=\"combo\" name=\"edicionart\">");
					rs=stm.executeQuery("SELECT * FROM cedicion");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_edicion")+"\">"+rs.getString("descripcion_edicion")+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td class=\"alider\">No. de revista:</td><td><select class=\"combo\" name=\"tomoart\">");
					rs=stm.executeQuery("SELECT * FROM ctomo");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_tomo")+"\">"+rs.getString("descripcion_tomo")+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td class=\"alider\">Formato:</td><td><select class=\"combo\" name=\"formatoart\">");
					rs=stm.executeQuery("SELECT * FROM cformato");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_formato")+"\">"+rs.getString("descripcion_formato")+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td class=\"alider\">Publicador:</td><td><select class=\"combo\" name=\"publicadorart\">");
					rs=stm.executeQuery("SELECT * FROM cpublicador");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_publicador")+"\">"+rs.getString("descripcion_publicador")+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td class=\"alider\">Programa:</td><td><select class=\"combo\" name=\"programaart\">");
					rs=stm.executeQuery("SELECT * FROM cprograma");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_programa")+"\">"+rs.getString("descripcion_programa")+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td class=\"alider\" colspan=\"2\">Seleccione los idiomas en los cuales este la obra:</td><td class=\"ast\">*</td><td></td></tr>");
					rs=stm.executeQuery("SELECT * FROM cidioma");
					while(rs.next())
					{
						out.println("<tr><td class=\"alider\">"+rs.getString("descripcion_idioma")+":</td><td><input type=\"checkbox\" class=\"checkb\" value=\""+rs.getString("id_idioma")+"\" name=\"idioma"+rs.getString("id_idioma")+"\"/></td><td class=\"ast\"></td><td></td></tr>");
					}
					out.println("<tr><td colspan=\"4\"><input type=\"submit\" class=\"boton\" value=\"Cambiar\" /></td></tr>");
					out.println("</table>");
					out.println("</form>");
				break;
				case 3:
					rs=stm.executeQuery("SELECT * FROM mimagen WHERE codigo_art='"+codigo+"'");
					if(rs.next())
					{
						out.println("<form action=\"CambioImagen\" method=\"post\" class=\"camarte\">");
						out.println("<table class=\"tfor\">");
						out.println("<tr><td class=\"alider\">Código del artículo:</td><td><input type=\"text\" class=\"texto\" name=\"codigoart\" value=\""+rs.getString("codigo_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Título del artículo:</td><td><input type=\"text\" class=\"texto\" name=\"tituloart\" value=\""+rs.getString("titulo_art")+"\" /></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Título alternativo artículo:</td><td><input type=\"text\" class=\"texto\" name=\"tituloaltart\" value=\""+rs.getString("nomalternativo_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Asunto:</td><td><input type=\"text\" class=\"texto\" name=\"asutoart\" value=\""+rs.getString("asunto_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Autor:</td><td><input type=\"text\" class=\"texto\" name=\"autorart\" value=\""+rs.getString("autor_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Pie de imagen:</td><td><input type=\"text\" class=\"texto\" name=\"piedeimg\" value=\""+rs.getString("pie_imagen")+"\"/></td><td class=\"ast\"></td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Fecha de creación:</td><td><input type=\"text\" class=\"texto\" name=\"fechcreacionart\" value=\""+rs.getString("fecha_creacion_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Descripción:</td><td><textarea class=\"texta\" name=\"descart\">"+rs.getString("descripcion_art")+"</textarea></td><td class=\"ast\">*</td><td></td></tr>");
					}
					
					
					out.println("<tr><td class=\"alider\">Formato de imagen:</td><td><select class=\"combo\" name=\"fordeimg\">");
					rs=stm.executeQuery("SELECT * FROM cformato_imagen");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_formato")+"\">"+rs.getString("descripcion_formato")+" ("+rs.getString("extension_formato")+")"+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td class=\"alider\">Formato:</td><td><select class=\"combo\" name=\"formatoart\">");
					rs=stm.executeQuery("SELECT * FROM cformato");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_formato")+"\">"+rs.getString("descripcion_formato")+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td class=\"alider\">Publicador:</td><td><select class=\"combo\" name=\"publicadorart\">");
					rs=stm.executeQuery("SELECT * FROM cpublicador");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_publicador")+"\">"+rs.getString("descripcion_publicador")+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td class=\"alider\">Programa:</td><td><select class=\"combo\" name=\"programaart\">");
					rs=stm.executeQuery("SELECT * FROM cprograma");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_programa")+"\">"+rs.getString("descripcion_programa")+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td colspan=\"4\"><input type=\"submit\" class=\"boton\" value=\"Cambiar\" /></td></tr>");
					out.println("</table>");
					out.println("</form>");
				break;
				case 4:
					rs=stm.executeQuery("SELECT * FROM mvideo WHERE codigo_art='"+codigo+"'");
					if(rs.next())
					{
						out.println("<form action=\"CambioVideo\" method=\"post\" class=\"camarte\">");
						out.println("<table class=\"tfor\">");
						out.println("<tr><td class=\"alider\">Código del artículo:</td><td><input type=\"text\" class=\"texto\" name=\"codigoart\" value=\""+rs.getString("codigo_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Título del artículo:</td><td><input type=\"text\" class=\"texto\" name=\"tituloart\" value=\""+rs.getString("titulo_art")+"\" /></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Título alternativo artículo:</td><td><input type=\"text\" class=\"texto\" name=\"tituloaltart\" value=\""+rs.getString("nomalternativo_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Asunto:</td><td><input type=\"text\" class=\"texto\" name=\"asutoart\" value=\""+rs.getString("asunto_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Autor:</td><td><input type=\"text\" class=\"texto\" name=\"autorart\" value=\""+rs.getString("autor_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Duración del video:</td><td><input type=\"text\" class=\"texto\" name=\"duracionvid\" value=\""+rs.getString("duracion_video")+"\"/></td><td class=\"ast\"></td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Fecha de creación:</td><td><input type=\"text\" class=\"texto\" name=\"fechcreacionart\" value=\""+rs.getString("fecha_creacion_art")+"\"/></td><td class=\"ast\">*</td><td></td></tr>");
						out.println("<tr><td class=\"alider\">Descripción:</td><td><textarea class=\"texta\" name=\"descart\">"+rs.getString("descripcion_art")+"</textarea></td><td class=\"ast\">*</td><td></td></tr>");
					}
					out.println("<tr><td class=\"alider\">Formato de video:</td><td><select class=\"combo\" name=\"fordvid\">");
					rs=stm.executeQuery("SELECT * FROM cformato_video");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_formato")+"\">"+rs.getString("descripcion_formato")+" ("+rs.getString("extension_formato")+")"+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td class=\"alider\">Formato:</td><td><select class=\"combo\" name=\"formatoart\">");
					rs=stm.executeQuery("SELECT * FROM cformato");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_formato")+"\">"+rs.getString("descripcion_formato")+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td class=\"alider\">Publicador:</td><td><select class=\"combo\" name=\"publicadorart\">");
					rs=stm.executeQuery("SELECT * FROM cpublicador");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_publicador")+"\">"+rs.getString("descripcion_publicador")+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td class=\"alider\">Programa:</td><td><select class=\"combo\" name=\"programaart\">");
					rs=stm.executeQuery("SELECT * FROM cprograma");
					while(rs.next())
					{
						out.println("<option value=\""+rs.getString("id_programa")+"\">"+rs.getString("descripcion_programa")+"</option>");
					}
					out.println("</select></td><td class=\"ast\">*</td><td></td></tr>");
					out.println("<tr><td colspan=\"4\"><input type=\"submit\" class=\"boton\" value=\"Cambiar\" /></td></tr>");
					out.println("</table>");
					out.println("</form>");
				break;
				default:
					
				break;
			}
		}
		catch(Exception e)
		{
			out.println("<div class=\"rojo\">El articulo no se pudo desplegar.</div>");
			e.printStackTrace();
		}
	}
}