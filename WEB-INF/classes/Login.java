import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import conexion.Conexion;
import java.util.*;

public class Login extends HttpServlet
{
	String usuario, contrasena;
	Conexion objCon;
	Connection con;
	Statement stm;
	PrintWriter out;
	ResultSet rs;
	HttpSession sesionCli;
	
	public void init() throws ServletException
	{
		usuario="";
		contrasena="";
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		usuario=request.getParameter("usuario");
		contrasena=request.getParameter("pass");
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
			rs=stm.executeQuery("SELECT COUNT(*) FROM cadministrador WHERE AES_DECRYPT((SELECT password FROM cadministrador WHERE administrador='"+usuario+"'), '"+usuario+"')='"+contrasena+"'");
			if (rs.next())
			{
				if(rs.getInt(1)>0)
				{
					sesionCli = request.getSession(true);
					sesionCli.setAttribute("id", sesionCli.getId());
					out.println("1");
				}
				else
				{
					out.println("<div class=\"rojo\">Usuario o contraseña incorrectos.</div>");
				}
			}
			else
			{
				out.println("<div class=\"rojo\">Ha ocurrido un error inténtelo de nuevo mas tarde.</div>");
			}
		}
		catch(Exception e)
		{
			out.println("<div class=\"rojo\">Ha ocurrido un error, no se pudieron comprobar sus datos.</div>");
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
}