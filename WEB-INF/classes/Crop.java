import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;

public class Crop extends HttpServlet
{
	int x1;
	int y1;
	int x2;
	int y2;
	int w;
	int h;
	int i;
	String nombre, ruta, extension;
	PrintWriter esc;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			response.setContentType("text/html");
			esc = response.getWriter();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		x1=Integer.parseInt(request.getParameter("x1"));
		y1=Integer.parseInt(request.getParameter("y1"));
		x2=Integer.parseInt(request.getParameter("x2"));
		y2=Integer.parseInt(request.getParameter("y2"));
		w=Integer.parseInt(request.getParameter("w"));
		h=Integer.parseInt(request.getParameter("h"));
		nombre=request.getParameter("nombrearch");
		ruta="";
		ruta=getServletContext().getRealPath("/")+"archivo\\"+nombre;
		ruta=ruta.replace('\\', '/');
		i=ruta.lastIndexOf(".")+1;
		extension="";
		for(i=i; i<ruta.length(); ++i)
		{
			extension=extension+ruta.charAt(i);
		}
		System.out.println(ruta);
		ruta=ruta.trim();
		try
		{
			BufferedImage image=ImageIO.read(new File(ruta));
			BufferedImage out=image.getSubimage(x1,y1,w,h);
			ImageIO.write(out, extension,new File(ruta));
			esc.println("<div class=\"naranja\">Su imagen se ha recortado y enviado correctamente.</div>");
		}
		catch(Exception e)
		{
			esc.println("<div class=\"rojo\">Su imagen no se ha recortado y no se ha enviado al servidor.</div>");
		}
		
	}
}