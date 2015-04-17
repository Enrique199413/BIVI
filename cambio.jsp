<%@page import="java.sql.*"%>
<%@page import="conexion.Conexion"%>

<%!
	Conexion con = new Conexion();
	Connection conexion;
	Statement stat=null;
	ResultSet rs=null;
%>

<html>
	<head>
		<title>Biblioteca Virtual TFJFA</title>
		<script language="javascript" type="text/javascript" src="guion/jquery.js"></script>
		<script language="javascript" type="text/javascript" src="guion/cambiogui.js"></script>
		<LINK REL="StyleSheet" HREF="estilo/style2.css" TYPE="text/css">
		<LINK REL="StyleSheet" HREF="estilo/cambios.css" TYPE="text/css">
	</head>
	
	<body>
		<!--[if gt IE 6]><center><![endif]-->
			
			<div id="header">
				<ul id="ligas">
					<a class="links" href="index.jsp"><li>Biblioteca Virtual</li></a>
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
						<a href="alta.jsp"><div class="elemenu">Alta</div></a>
						<a href="baja.jsp"><div class="elemenu">Baja</div></a>
						<a href="cambio.jsp"><div class="elemenu" id="seleccionado">Cambio</div></a>
						<a href=".jsp"><div class="elemenu">Contacto</div></a>
						<a href=".jsp"><div class="elemenu">Comentarios</div></a>
					</div>
				</div>
				<%
					conexion=con.conectar();
					stat=conexion.createStatement();
				%>
				<div id="contenido">
					<div id="submenu">
						<div class="unesub" id="articulo" onclick="cambiarDiv(this.id)">Articulo</div>
						<div class="unesub" id="idioma" onclick="cambiarDiv(this.id)">Idioma</div>
						<div class="unesub" id="formato" onclick="cambiarDiv(this.id)">Formato</div>
						<div class="unesub" id="programa" onclick="cambiarDiv(this.id)">Programa</div>
						<div class="unesub" id="publicador" onclick="cambiarDiv(this.id)">Publicador</div>
						<div class="unesub" id="forimg" onclick="cambiarDiv(this.id)">Formato de imagen</div>
						<div class="unesub" id="forvid" onclick="cambiarDiv(this.id)">Formato de video</div>
						<div class="unesub" id="editorial" onclick="cambiarDiv(this.id)">Editorial</div>
						<div class="unesub" id="tomo" onclick="cambiarDiv(this.id)">Tomo</div>
						<div class="unesub" id="edicion" onclick="cambiarDiv(this.id)">Edición</div>
						<div class="unesub absub ser" id="principal" onclick="cambiarDiv(this.id)">Instrucciones</div>
					</div>
					<div id="formularios">
						<div id="principald" style="display:block;" class="divderfor">
							Seleccione una pestaña del menú de la izquierda para dar de alta artículos, idiomas, etc. En la base de datos.
						</div>
						<div id="articulod" class="divderfor" style="display:none;">
							<div class="instrucciones">
								Para cambiar artículos, haga clic en el botón cambiar del artículo correspondiente.
							</div>
							<div class="respuesta"></div>
							<div id="contenedorarticulod"></div>
						</div>
						<div id="idiomad" style="display:none;" class="divderfor">
							<div class="instrucciones">
								Para cambiar un idioma selecciónelo y haga clic en el botón cambiar.
							</div>
							<div class="respuesta"></div>
							<div id="contenedoridiomad">
								<form method="post" action="DatosIdioma" class="demas" id="coia">
									<table class="contenidosalls">
										<tr><td>Idioma:</td><td><select name="id">
										<%
											rs=stat.executeQuery("SELECT * FROM cidioma");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_idioma")+"\">"+rs.getString("descripcion_idioma")+"</option>");
											}
										%>
										</select></td></tr>
										<tr><td><input type="submit" value="Cambiar" class="boton"/></td><td></td></tr>
									</table>
								</form>
							</div>
						</div>
						<div id="formatod" style="display:none;" class="divderfor">
							<div class="instrucciones">
								Para borrar un formato selecciónelo y haga clic en el botón borrar.
							</div>
							<div class="respuesta"></div>
							<div id="contenedorformatod">
								<form method="post" action="BorrarFormato" class="demas">
									<table class="contenidosalls">
										<tr><td>Formato:</td><td><select name="id">
										<%
											rs=stat.executeQuery("SELECT * FROM cformato");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_formato")+"\">"+rs.getString("descripcion_formato")+"</option>");
											}
										%>
										</select></td></tr>
										<tr><td><input type="submit" value="Borrar" class="boton"/></td><td></td></tr>
									</table>
								</form>
							</div>
						</div>
						<div id="programad" style="display:none;" class="divderfor">
							<div class="instrucciones">
								Para borrar un programa selecciónelo y haga clic en el botón borrar.
							</div>
							<div class="respuesta"></div>
							<div id="contenedorprogramad">
								<form method="post" action="BorrarPrograma" class="demas">
									<table class="contenidosalls">
										<tr><td>Programa:</td><td><select name="id">
										<%
											rs=stat.executeQuery("SELECT * FROM cprograma");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_programa")+"\">"+rs.getString("descripcion_programa")+"</option>");
											}
										%>
										</select></td></tr>
										<tr><td><input type="submit" value="Borrar" class="boton"/></td><td></td></tr>
									</table>
								</form>
							</div>
						</div>
						<div id="publicadord" style="display:none;" class="divderfor">
							<div class="instrucciones">
								Para borrar un publicador selecciónelo y haga clic en el botón borrar.
							</div>
							<div class="respuesta"></div>
							<div id="contenedorpublicadord">
								<form method="post" action="BorrarPublicador" class="demas">
									<table class="contenidosalls">
										<tr><td>Publicador:</td><td><select name="id">
										<%
											rs=stat.executeQuery("SELECT * FROM cpublicador");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_publicador")+"\">"+rs.getString("descripcion_publicador")+"</option>");
											}
										%>
										</select></td></tr>
										<tr><td><input type="submit" value="Borrar" class="boton"/></td><td></td></tr>
									</table>
								</form>
							</div>
						</div>
						<div id="forimgd" style="display:none;" class="divderfor">
							<div class="instrucciones">
								Para borrar un formato de imagen selecciónelo y haga clic en el botón borrar.
							</div>
							<div class="respuesta"></div>
							<div id="contenedorforimgd">
								<form method="post" action="BorrarFormatoImg" class="demas">
									<table class="contenidosalls">
										<tr><td>Formato:</td><td><select name="id">
										<%
											rs=stat.executeQuery("SELECT * FROM cformato_imagen");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_formato")+"\">"+rs.getString("descripcion_formato")+" ("+rs.getString("extension_formato")+")</option>");
											}
										%>
										</select></td></tr>
										<tr><td><input type="submit" value="Borrar" class="boton"/></td><td></td></tr>
									</table>
								</form>
							</div>
						</div>
						<div id="forvidd" style="display:none;" class="divderfor">
							<div class="instrucciones">
								Para borrar un formato de video selecciónelo y haga clic en el botón borrar.
							</div>
							<div class="respuesta"></div>
							<div id="contenedorforvidd">
								<form method="post" action="BorrarFormatoVid" class="demas">
									<table class="contenidosalls">
										<tr><td>Formato:</td><td><select name="id">
										<%
											rs=stat.executeQuery("SELECT * FROM cformato_video");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_formato")+"\">"+rs.getString("descripcion_formato")+" ("+rs.getString("extension_formato")+")</option>");
											}
										%>
										</select></td></tr>
										<tr><td><input type="submit" value="Borrar" class="boton"/></td><td></td></tr>
									</table>
								</form>
							</div>
						</div>
						<div id="editoriald" style="display:none;" class="divderfor">
							<div class="instrucciones">
								Para borrar una editorial selecciónela y haga clic en el botón borrar.
							</div>
							<div class="respuesta"></div>
							<div id="contenedoreditoriald">
								<form method="post" action="BorrarEditorial" class="demas">
									<table class="contenidosalls">
										<tr><td>Editorial:</td><td><select name="id">
										<%
											rs=stat.executeQuery("SELECT * FROM ceditorial");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_editorial")+"\">"+rs.getString("descripcion_editorial")+"</option>");
											}
										%>
										</select></td></tr>
										<tr><td><input type="submit" value="Borrar" class="boton"/></td><td></td></tr>
									</table>
								</form>
							</div>
						</div>
						<div id="tomod" style="display:none;" class="divderfor">
							<div class="instrucciones">
								Para borrar una editorial selecciónela y haga clic en el botón borrar.
							</div>
							<div class="respuesta"></div>
							<div id="contenedortomod">
								<form method="post" action="BorrarTomo" class="demas">
									<table class="contenidosalls">
										<tr><td>Tomo:</td><td><select name="id">
										<%
											rs=stat.executeQuery("SELECT * FROM ctomo");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_tomo")+"\">"+rs.getString("descripcion_tomo")+"</option>");
											}
										%>
										</select></td></tr>
										<tr><td><input type="submit" value="Borrar" class="boton"/></td><td></td></tr>
									</table>
								</form>
							</div>
						</div>
						<div id="ediciond" style="display:none;" class="divderfor">
							<div class="instrucciones">
								Para borrar una editorial selecciónela y haga clic en el botón borrar.
							</div>
							<div class="respuesta"></div>
							<div id="contenedorediciond">
								<form method="post" action="BorrarEdicion" class="demas">
									<table class="contenidosalls">
										<tr><td>Edición:</td><td><select name="id">
										<%
											rs=stat.executeQuery("SELECT * FROM cedicion");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_edicion")+"\">"+rs.getString("descripcion_edicion")+"</option>");
											}
										%>
										</select></td></tr>
										<tr><td><input type="submit" value="Borrar" class="boton"/></td><td></td></tr>
									</table>
								</form>
							</div>
						</div>
					</div>
					<div class="corte"></div>
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
	</body>
</html>