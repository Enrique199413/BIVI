import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;
import java.util.*;

public class CambioRevista extends HttpServlet
{
	String codigo, titulo, tituloalt, asunto, autor, fechac, descripcion, editorial, edicion, tomo, formato, publicador, programa;
	ArrayList<String> idioma;
	Conexion objCon;
	Connection con;
	Statement stm;
	PrintWriter out;
	ResultSet rs;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		idioma= new ArrayList<String>();
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
		codigo=request.getParameter("codigoart");
		titulo=request.getParameter("tituloart");
		tituloalt=request.getParameter("tituloaltart");
		asunto=request.getParameter("asutoart");
		autor=request.getParameter("autorart");
		fechac=request.getParameter("fechcreacionart");
		descripcion=request.getParameter("descart");
		editorial=request.getParameter("editorialart");
		edicion=request.getParameter("edicionart");
		tomo=request.getParameter("tomoart");
		formato=request.getParameter("formatoart");
		publicador=request.getParameter("publicadorart");
		programa=request.getParameter("programaart");
		try
		{
			stm.executeUpdate("UPDATE marticulo SET id_formato="+formato+", id_publicador="+publicador+", id_programa="+programa+" WHERE codigo_art='"+codigo+"'");
			stm.executeUpdate("UPDATE mrevista SET id_editorial="+editorial+", titulo_art='"+titulo+"', nomalternativo_art='"+tituloalt+"', descripcion_art='"+descripcion+"', asunto_art='"+asunto+"', fecha_creacion_art='"+fechac+"', autor_art='"+autor+"', id_edicion="+edicion+", id_no_revista="+tomo+" WHERE codigo_art='"+codigo+"'");
			out.println("<div class=\"naranja\">Los datos se han cambiado correctamente.</div>");
		}
		catch(Exception e)
		{
			out.println("<div class=\"rojo\">Los datos no se han cambiado.</div>");
			e.printStackTrace();
		}
	}
}