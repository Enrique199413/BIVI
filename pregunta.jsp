<%@page import="java.sql.*"%>
<%@page import="conexion.Conexion"%>

<%!
	Conexion con = new Conexion();
	Connection conexion;
	Statement stm=null;
	ResultSet rs=null;
	int codart;
%>

<%
	try
	{
		codart = Integer.parseInt(request.getParameter("codart"));
	}
	catch(Exception e)
	{
		response.sendRedirect("index.jsp");
	}
	
	conexion = con.conectar();
	stm = conexion.createStatement();
	rs=stm.executeQuery("SELECT codigo_art FROM marticulo WHERE codigo_art="+codart);
	if(rs.next())
	{
		codart=codart;
	}
	else
	{
		response.sendRedirect("index.jsp");
	}
%>

<html>
	<head>
		<title>Biblioteca Virtual TFJFA</title>
		<LINK REL="StyleSheet" HREF="estilo/style.css" TYPE="text/css">
		<LINK REL="StyleSheet" HREF="estilo/contactos.css" TYPE="text/css">
		<script language="javascript" type="text/javascript" src="guion/jquery.js"></script>
		</script>
		<script language="javascript" type="text/javascript" src="guion/preguntagui.js"></script>
		</script>
	</head>
	
	<body>
		<!--[if gt IE 6]><center><![endif]-->
			
			<div id="header">
				<ul id="ligas">
					<a class="links" href="contacto.html"><li>Contacto</li></a>
					<a class="links" href="conocenos.html"><li class="barrad">Conócenos</li></a>
					<a href="http://www.tff.gob.mx/" class="links"><li class="barrad">TFJFA</li></a>
				</ul>
			</div>
			
			<div id="som1">
				<div id="cabeza">
					<img src="img/logoBV.png" align="left">
				</div>
			</div>
			
			<div id="som2">
				<div id="menu">
					<div id="cmenu">
						<a href="index.jsp"><div class="elemenu">Inicio</div></a>
						<a href="libro.jsp"><div class="elemenu">Libros</div></a>
						<a href="revista.jsp"><div class="elemenu">Revistas</div></a>
						<a href="imagen.jsp"><div class="elemenu">Imágenes</div></a>
						<a href="video.jsp"><div class="elemenu">Videos</div></a>
					</div>
				</div>
				
				<div id="contenido">
					<div id="dentro">
						<div id="mensaje">
							<h1>Preguntas</h1>
							Para hacer un pregunta llene los dos campos que se presentan a continuación, es necesario su correo electrónico 
							para poder enviarle una respuesta. En cuanto el administrador vea su pregunta se pondrá en contacto con usted.
						</div>
						<br>
						<br>
						<table class="centrar" id="tabla">
							<form method="post" action="AltaPregunta" id="formupre">
								<tr>
									<td class="tizq">Correo:</td>
									<td><input type="text" class="texto" id="cor" name="correo"></td>
								</tr>
								<tr>
									<td class="tizq">Pregunta:</td>
									<td><textarea class="texta" id="com" name="pregunta"></textarea></td>
								</tr>
								<tr>
									<td class="tizq"><input type="submit" value="Enviar" class="boton" id="bpbs"></td>
									<td><input type="reset" value="Limpiar" class="boton" id="bpbs"><input type="hidden" value="<%=codart%>" name="codigo"></td>
								</tr>
							</form>
						</table>
						<br>
						<br>
						<br>
						<div id="comentarior">
							<h1 id="agra">¡Le contestaremos lo más breve posible!</h1>
						</div>
					</div>
				</div>
			</div>
			
			<div id="som3">
				<div id="footer">
					<div id="sobrante">
						
					</div>
					
					<div id="division"></div>
		
					<div id="let">
						Tribunal Federal de Justicia Fiscal y Administrativa
						<br>© Centro de Estudios Superiores en Materia de Derecho Fiscal y Administrativo 2012
					</div>
				</div>
			</div>
		<!--[if gt IE 6]></center><![endif]-->
		<%
			con.cerrarConexion();
			conexion.close();
			stm.close();
			rs.close();
		%>
	</body>
</html>