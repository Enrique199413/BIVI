<%!
	HttpSession sesionCli = null;
%>

<%
	sesionCli=request.getSession(false);
	if(sesionCli!=null && sesionCli.getAttribute("id")==sesionCli.getId())
	{
		response.sendRedirect("alta.jsp");
	}
%>

<html>
	<head>
		<title>Biblioteca Virtual TFJFA</title>
		<link href="img/favicon.ico" type="image/x-icon" rel="shortcut icon" />
		<LINK REL="StyleSheet" HREF="estilo/administracions.css" TYPE="text/css">
		<script language="javascript" type="text/javascript" src="guion/jquery.js"></script>
		<script language="javascript" type="text/javascript" src="guion/administraciongui.js"></script>
	</head>
	
	<body>
		<center>
			<div id="somcentral">
				<div id="dcentral">
					Para iniciar como administrador, llene los siguientes campos y haz clic en el botón entrar.<br><br>
					<form method="post" action="Login" id="log">
						<center>
						<table id="tabla">	
							<tr><td class="derecha">Usuario:</td><td><input type="text" name="usuario" /></td></tr>
							<tr><td class="derecha">Password:</td><td><input type="password" name="pass" /></td></tr>
							<tr><td class="centr"><input type="submit" value="Entrar" class="boton" /></td><td class="centr"><input type="reset" value="Limpiar"  class="boton" /></td></tr>
						</table>
						</center>
					</form>
					<div id="respuesta">
						<table id="reccon">
							<tr><td>Recuperar contraseña</td><td id="resp"></td></tr>
						</table>
					</div>
				</div>
			</div>
		</center>
	</body>
</html>