import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;
import java.util.*;
import org.apache.commons.fileupload.*;

public class AltaImagen extends HttpServlet
{
	String codigo, titulo, tituloalt, asunto, autor, fechac, fechap, descripcion, piedeimagen, formatodimg, formato, publicador, programa, rutaimgreal, rutaimg;
	Conexion objCon;
	Connection con;
	Statement stm;
	PrintWriter out;
	ResultSet rs;
	DiskFileUpload upload;
	List items;
	Iterator itr;
	Calendar fecha;
	FileItem item;
	String fieldname;
	File fullFile;
	File savedFile;
	int i, j, k, l;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		i=0;
		j=0;
		k=0;
		l=0;
		rutaimgreal="";
		rutaimg="";
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
		fecha = new GregorianCalendar();
		fechap=fecha.get(Calendar.YEAR)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.DATE)+"";
		
		upload = new DiskFileUpload();
		try
		{
			items = upload.parseRequest(request);
		}
		catch(Exception e){}
		itr = items.iterator();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Biblioteca Virtual TFJFA</title>");
		out.println("<LINK REL=\"StyleSheet\" HREF=\"estilo/iframeresp.css\" TYPE=\"text/css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div id=\"resultadodf\">");
		while(itr.hasNext())
		{
			item = (FileItem) itr.next();
			if(item.isFormField())
			{
				switch(i)
				{
					case 0:
						codigo=item.getString();
					break;
					case 1:
						titulo=item.getString();
					break;
					case 2:
						tituloalt=item.getString();
					break;
					case 3:
						asunto=item.getString();
					break;
					case 4:
						autor=item.getString();
					break;
					case 5:
						piedeimagen=item.getString();
					break;
					case 6:
						fechac=item.getString();
					break;
					case 7:
						descripcion=item.getString();
					break;
					case 8:
						formatodimg=item.getString();
					break;
					case 9:
						formato=item.getString();
					break;
					case 10:
						publicador=item.getString();
					break;
					case 11:
						programa=item.getString();
					break;
					default:
						
					break;
				}
				++i;
			}
			else
			{
				fullFile  = new File(item.getName());
				switch(j)
				{
					case 0:
						try
						{
							k=fullFile.getName().lastIndexOf('.');
							for(k=k; k<fullFile.getName().length(); ++k)
							{
								rutaimgreal=rutaimgreal+fullFile.getName().charAt(k);
							}
							rutaimgreal=codigo+"itr"+rutaimgreal;
							savedFile = new File(getServletContext().getRealPath("/")+"archivo", rutaimgreal);
							item.write(savedFile);
							out.println("<div class=\"naranja\">La imagen se ha enviado correctamente al servidor.</div>");
						}
						catch(Exception e)
						{
							rutaimgreal="";
							out.println("<div class=\"rojo\">La imagen no se ha enviado correctamente al servidor.</div>");
						}
					break;
						
					case 1:
						try
						{
							k=fullFile.getName().lastIndexOf('.');
							for(k=k; k<fullFile.getName().length(); ++k)
							{
								rutaimg=rutaimg+fullFile.getName().charAt(k);
							}
							rutaimg=codigo+"tf"+rutaimg;
							savedFile = new File(getServletContext().getRealPath("/")+"archivo", rutaimg);
							item.write(savedFile);
							out.println("<div class=\"naranja\">El thumbnail de la imagen se ha enviado correctamente al servidor.</div>");
							out.println("<input type=\"button\" class=\"boton\" value=\"Generar thumbnail\" onclick=\"top.genThumbnailImg(\'"+rutaimg+"\')\" />");
						}
						catch(Exception e)
						{
							rutaimg="";
							out.println("<div class=\"rojo\">El thumbnail de la imagen no se ha enviado correctamente al servidor.</div>");
						}
					default:
						
					break;
				}
				++j;
			}
		}
		try
		{
			stm.executeUpdate("INSERT INTO marticulo VALUES ('"+codigo+"', DEFAULT, 'archivo/"+rutaimg+"', "+formato+", "+publicador+", "+programa+", 3)");
			stm.executeUpdate("INSERT INTO mimagen VALUES ('"+codigo+"', '"+fechap+"', 'archivo/"+rutaimgreal+"', '"+piedeimagen+"', '"+titulo+"', '"+tituloalt+"', '"+descripcion+"', '"+asunto+"', '"+fechac+"', '"+autor+"', "+formatodimg+")");
			out.println("<div class=\"naranja\">Los datos se han dado de alta correctamente</div>");
		}
		catch(Exception e)
		{
			out.println("<div class=\"rojo\">Los datos no se dieron de alta correctamente</div>");
			e.printStackTrace();
		}
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}
}