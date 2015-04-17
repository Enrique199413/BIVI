package conexion;
import java.sql.*;

public class Conexion 
{
	Connection conexion = null;
	String controlador = "com.mysql.jdbc.Driver";
	String ruta = "jdbc:mysql://localhost/bivi";
	
	public Connection conectar ()
	{
		try
		{
			Class.forName(controlador).newInstance();
			conexion = DriverManager.getConnection(ruta, "root", "n0m3l0s3");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			return conexion;
		}
	}
	
	public void cerrarConexion()
	{
		try
		{
			conexion.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}