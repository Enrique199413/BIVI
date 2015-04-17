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
		<script language="javascript" type="text/javascript" src="guion/jquery.Jcrop.min.js"></script>
		<script language="javascript" type="text/javascript" src="guion/altagui.js"></script>
		<LINK REL="StyleSheet" HREF="estilo/style2.css" TYPE="text/css">
		<LINK REL="StyleSheet" HREF="estilo/altas.css" TYPE="text/css">
		<link rel="stylesheet" href="estilo/jquery.Jcrop.min.css" type="text/css" />
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
						<a href="alta.jsp"><div class="elemenu" id="seleccionado">Alta</div></a>
						<a href="baja.jsp"><div class="elemenu">Baja</div></a>
						<a href="cambio.jsp"><div class="elemenu">Cambio</div></a>
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
						<div class="unesub" id="libro" onclick="cambiarDiv(this.id)">Libro</div>
						<div class="unesub" id="revista" onclick="cambiarDiv(this.id)">Revista</div>
						<div class="unesub" id="imagen" onclick="cambiarDiv(this.id)">Imagen</div>
						<div class="unesub" id="video" onclick="cambiarDiv(this.id)">Video</div>
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
						<div id="librod" class="divderfor" style="display:none;">
							<div class="inspad">
								Para agregar un nuevo libro llene los campos que se presentan a continuación, los campos marcados con * son obligatorios.
							</div>
							<div class="tdform">
								<form target="upresp" action="AltaLibro" method="post" enctype="MULTIPART/FORM-DATA">
									<table class="tfor">
										<tr><td class="alider">Código del artículo:</td><td><input type="text" class="texto" name="codigoart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Título del artículo:</td><td><input type="text" class="texto" name="tituloart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Título alternativo artículo:</td><td><input type="text" class="texto" name="tituloaltart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Asunto:</td><td><input type="text" class="texto" name="asutoart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Autor:</td><td><input type="text" class="texto" name="autorart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Fecha de creación:</td><td><input type="text" class="texto" name="fechcreacionart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Descripción:</td><td><textarea class="texta" name="descart"></textarea></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Editorial:</td><td><select class="combo" name="editorialart">
										<%
											rs=stat.executeQuery("SELECT * FROM ceditorial");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_editorial")+"\">"+rs.getString("descripcion_editorial")+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Edición:</td><td><select class="combo" name="edicionart">
										<%
											rs=stat.executeQuery("SELECT * FROM cedicion");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_edicion")+"\">"+rs.getString("descripcion_edicion")+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Tomo:</td><td><select class="combo" name="tomoart">
										<%
											rs=stat.executeQuery("SELECT * FROM ctomo");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_tomo")+"\">"+rs.getString("descripcion_tomo")+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Formato:</td><td><select class="combo" name="formatoart">
										<%
											rs=stat.executeQuery("SELECT * FROM cformato");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_formato")+"\">"+rs.getString("descripcion_formato")+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Publicador:</td><td><select class="combo" name="publicadorart">
										<%
											rs=stat.executeQuery("SELECT * FROM cpublicador");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_publicador")+"\">"+rs.getString("descripcion_publicador")+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Programa:</td><td><select class="combo" name="programaart">
										<%
											rs=stat.executeQuery("SELECT * FROM cprograma");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_programa")+"\">"+rs.getString("descripcion_programa")+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider" colspan="2">Seleccione los idiomas en los cuales este la obra:</td><td class="ast">*</td><td></td></tr>
										<%
											rs=stat.executeQuery("SELECT * FROM cidioma");
											while(rs.next())
											{
												out.println("<tr><td class=\"alider\">"+rs.getString("descripcion_idioma")+":</td><td><input type=\"checkbox\" class=\"checkb\" value=\""+rs.getString("id_idioma")+"\" name=\"idioma"+rs.getString("id_idioma")+"\"/></td><td class=\"ast\"></td><td></td></tr>");
											}
										%>
										<tr><td class="alider">Archivo del libro:</td><td><input type="file" name="archrec"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Thumbnail del recurso:</td><td><input type="file" name="archthumb" id="imgt" /></td><td class="ast"></td><td></td></tr>
										<tr><td colspan="4"><input type="submit" class="boton" value="Dar de alta" /><input type="reset" class="boton" value="Limpiar" /></td></tr>
									</table>
								</form>
								<br>
								<div id="conif">
									<iframe id="upresp" name="upresp" src="ifr.html" frameborder="0" scrolling="false"></iframe>
								</div>
								<div id="jcrimg" style="display:none;">
									<input type="button" value="Ajustar y cortar" class="boton" onclick="verm()">
									<div id="centtar"><img src="" id="target" /></div>
									<div style="width:200px;height:200px;overflow:hidden;margin:auto;margin-top:10px;">
										<img id="preview" src="" class="jcrop-preview" />
									</div>
									<form action="Crop" method="post" id="cropimg">
										<input type="hidden" name="x1" id="x1"/>
										<input type="hidden" name="y1" id="y1"/>
										<input type="hidden" name="x2" id="x2"/>
										<input type="hidden" name="y2" id="y2"/>
										<input type="hidden" name="w" id="w"/>
										<input type="hidden" name="h" id="h"/>
										<input type="hidden" name="nombrearch" class="esnom"/>
										<input type="submit" class="boton" value="Cortar y enviar"/>
									</form>
									<div class="respcrop"></div>
								</div>
							</div>
						</div>
							
						<div id="revistad" style="display:none;" class="divderfor">
							<div class="inspad">
								Para agregar una nueva revista llene los campos que se presentan a continuación, los campos marcados con * son obligatorios.
							</div>
							<div class="tdform">
								<form target="upresprev" action="AltaRevista" method="post" enctype="MULTIPART/FORM-DATA">
									<table class="tfor">
										<tr><td class="alider">Código del artículo:</td><td><input type="text" class="texto" name="codigoart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Título del artículo:</td><td><input type="text" class="texto" name="tituloart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Título alternativo artículo:</td><td><input type="text" class="texto" name="tituloaltart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Asunto:</td><td><input type="text" class="texto" name="asutoart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Autor:</td><td><input type="text" class="texto" name="autorart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Fecha de creación:</td><td><input type="text" class="texto" name="fechcreacionart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Descripción:</td><td><textarea class="texta" name="descart"></textarea></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Editorial:</td><td><select class="combo" name="editorialart">
										<%
											rs=stat.executeQuery("SELECT * FROM ceditorial");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_editorial")+"\">"+rs.getString("descripcion_editorial")+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Edición:</td><td><select class="combo" name="edicionart">
										<%
											rs=stat.executeQuery("SELECT * FROM cedicion");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_edicion")+"\">"+rs.getString("descripcion_edicion")+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">No. de revista:</td><td><select class="combo" name="tomoart">
										<%
											rs=stat.executeQuery("SELECT * FROM ctomo");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_tomo")+"\">"+rs.getString("descripcion_tomo")+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Formato:</td><td><select class="combo" name="formatoart">
										<%
											rs=stat.executeQuery("SELECT * FROM cformato");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_formato")+"\">"+rs.getString("descripcion_formato")+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Publicador:</td><td><select class="combo" name="publicadorart">
										<%
											rs=stat.executeQuery("SELECT * FROM cpublicador");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_publicador")+"\">"+rs.getString("descripcion_publicador")+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Programa:</td><td><select class="combo" name="programaart">
										<%
											rs=stat.executeQuery("SELECT * FROM cprograma");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_programa")+"\">"+rs.getString("descripcion_programa")+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider" colspan="2">Seleccione los idiomas en los cuales esta revista:</td><td class="ast">*</td><td></td></tr>
										<%
											rs=stat.executeQuery("SELECT * FROM cidioma");
											while(rs.next())
											{
												out.println("<tr><td class=\"alider\">"+rs.getString("descripcion_idioma")+":</td><td><input type=\"checkbox\" class=\"checkb\" value=\""+rs.getString("id_idioma")+"\" name=\"idioma"+rs.getString("id_idioma")+"\"/></td><td class=\"ast\"></td><td></td></tr>");
											}
										%>
										<tr><td class="alider">Archivo de la revista:</td><td><input type="file" name="archrec"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Thumbnail del recurso:</td><td><input type="file" name="archthumb" id="imgt" /></td><td class="ast"></td><td></td></tr>
										<tr><td colspan="4"><input type="submit" class="boton" value="Dar de alta" /><input type="reset" class="boton" value="Limpiar" /></td></tr>
									</table>
								</form>
								<br>
								<div id="conifrev">
									<iframe id="upresprev" name="upresprev" src="ifr.html" frameborder="0" scrolling="false"></iframe>
								</div>
								<div id="jcrimgrev" style="display:none;">
									<input type="button" value="Ajustar y cortar" class="boton" onclick="vermrev()">
									<div id="centtar"><img src="" id="targetrev" /></div>
									<div style="width:200px;height:200px;overflow:hidden;margin:auto;margin-top:10px;">
										<img id="previewrev" src="" class="jcrop-preview" />
									</div>
									<form action="Crop" method="post" id="cropimgrev">
										<input type="hidden" name="x1" id="x1rev"/>
										<input type="hidden" name="y1" id="y1rev"/>
										<input type="hidden" name="x2" id="x2rev"/>
										<input type="hidden" name="y2" id="y2rev"/>
										<input type="hidden" name="w" id="wrev"/>
										<input type="hidden" name="h" id="hrev"/>
										<input type="hidden" name="nombrearch" class="esnom"/>
										<input type="submit" class="boton" value="Cortar y enviar"/>
									</form>
									<div class="respcrop"></div>
								</div>
							</div>
						</div>
						<div id="imagend" style="display:none;" class="divderfor">
							<div class="inspad">
								Para agregar una nueva imagen llene los campos que se presentan a continuación, los campos marcados con * son obligatorios.
							</div>
							<div class="tdform">
								<form target="uprespimg" action="AltaImagen" method="post" enctype="MULTIPART/FORM-DATA">
									<table class="tfor">
										<tr><td class="alider">Código del artículo:</td><td><input type="text" class="texto" name="codigoart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Título del artículo:</td><td><input type="text" class="texto" name="tituloart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Título alternativo artículo:</td><td><input type="text" class="texto" name="tituloaltart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Asunto:</td><td><input type="text" class="texto" name="asutoart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Autor:</td><td><input type="text" class="texto" name="autorart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Pie de imagen:</td><td><input type="text" class="texto" name="piedeimg"/></td><td class="ast"></td><td></td></tr>
										<tr><td class="alider">Fecha de creación:</td><td><input type="text" class="texto" name="fechcreacionart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Descripción:</td><td><textarea class="texta" name="descart"></textarea></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Formato de imagen:</td><td><select class="combo" name="editorialart">
										<%
											rs=stat.executeQuery("SELECT * FROM cformato_imagen");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_formato")+"\">"+rs.getString("descripcion_formato")+" ("+rs.getString("extension_formato")+")"+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Formato:</td><td><select class="combo" name="formatoart">
										<%
											rs=stat.executeQuery("SELECT * FROM cformato");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_formato")+"\">"+rs.getString("descripcion_formato")+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Publicador:</td><td><select class="combo" name="publicadorart">
										<%
											rs=stat.executeQuery("SELECT * FROM cpublicador");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_publicador")+"\">"+rs.getString("descripcion_publicador")+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Programa:</td><td><select class="combo" name="programaart">
										<%
											rs=stat.executeQuery("SELECT * FROM cprograma");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_programa")+"\">"+rs.getString("descripcion_programa")+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>			
										<tr><td class="alider">Imagen en tamaño real:</td><td><input type="file" name="archrec"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Thumbnail del recurso:</td><td><input type="file" name="archthumb" id="imgt" /></td><td class="ast"></td><td></td></tr>
										<tr><td colspan="4"><input type="submit" class="boton" value="Dar de alta" /><input type="reset" class="boton" value="Limpiar" /></td></tr>
									</table>
								</form>
								<br>
								<div id="conifrev">
									<iframe id="uprespimg" name="uprespimg" src="ifr.html" frameborder="0" scrolling="false"></iframe>
								</div>
								<div id="jcrimgimg" style="display:none;">
									<input type="button" value="Ajustar y cortar" class="boton" onclick="vermimg()">
									<div id="centtar"><img src="" id="targetimg" /></div>
									<div style="width:200px;height:200px;overflow:hidden;margin:auto;margin-top:10px;">
										<img id="previewimg" src="" class="jcrop-preview" />
									</div>
									<form action="Crop" method="post" id="cropimgimg">
										<input type="hidden" name="x1" id="x1img"/>
										<input type="hidden" name="y1" id="y1img"/>
										<input type="hidden" name="x2" id="x2img"/>
										<input type="hidden" name="y2" id="y2img"/>
										<input type="hidden" name="w" id="wimg"/>
										<input type="hidden" name="h" id="himg"/>
										<input type="hidden" name="nombrearch" class="esnom"/>
										<input type="submit" class="boton" value="Cortar y enviar"/>
									</form>
									<div class="respcrop"></div>
								</div>
							</div>
						</div>
						<div id="videod" style="display:none;" class="divderfor">
							<div class="inspad">
								Para agregar un nuevo video llene los campos que se presentan a continuación, los campos marcados con * son obligatorios.
							</div>
							<div class="tdform">
								<form target="uprespvid" action="AltaVideo" method="post" enctype="MULTIPART/FORM-DATA">
									<table class="tfor">
										<tr><td class="alider">Código del artículo:</td><td><input type="text" class="texto" name="codigoart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Título del artículo:</td><td><input type="text" class="texto" name="tituloart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Título alternativo artículo:</td><td><input type="text" class="texto" name="tituloaltart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Asunto:</td><td><input type="text" class="texto" name="asutoart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Autor:</td><td><input type="text" class="texto" name="autorart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Duración del video:</td><td><input type="text" class="texto" name="duracionvid"/></td><td class="ast"></td><td></td></tr>
										<tr><td class="alider">Fecha de creación:</td><td><input type="text" class="texto" name="fechcreacionart"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Descripción:</td><td><textarea class="texta" name="descart"></textarea></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Formato de video:</td><td><select class="combo" name="editorialart">
										<%
											rs=stat.executeQuery("SELECT * FROM cformato_video");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_formato")+"\">"+rs.getString("descripcion_formato")+" ("+rs.getString("extension_formato")+")"+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Formato:</td><td><select class="combo" name="formatoart">
										<%
											rs=stat.executeQuery("SELECT * FROM cformato");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_formato")+"\">"+rs.getString("descripcion_formato")+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Publicador:</td><td><select class="combo" name="publicadorart">
										<%
											rs=stat.executeQuery("SELECT * FROM cpublicador");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_publicador")+"\">"+rs.getString("descripcion_publicador")+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Programa:</td><td><select class="combo" name="programaart">
										<%
											rs=stat.executeQuery("SELECT * FROM cprograma");
											while(rs.next())
											{
												out.println("<option value=\""+rs.getString("id_programa")+"\">"+rs.getString("descripcion_programa")+"</option>");
											}
										%>
										</select></td><td class="ast">*</td><td></td></tr>			
										<tr><td class="alider">Video:</td><td><input type="file" name="archrec"/></td><td class="ast">*</td><td></td></tr>
										<tr><td class="alider">Thumbnail del recurso:</td><td><input type="file" name="archthumb" id="imgt" /></td><td class="ast"></td><td></td></tr>
										<tr><td colspan="4"><input type="submit" class="boton" value="Dar de alta" /><input type="reset" class="boton" value="Limpiar" /></td></tr>
									</table>
								</form>
								<br>
								<div id="conifrev">
									<iframe id="uprespvid" name="uprespvid" src="ifr.html" frameborder="0" scrolling="false"></iframe>
								</div>
								<div id="jcrimgvid" style="display:none;">
									<input type="button" value="Ajustar y cortar" class="boton" onclick="vermvid()">
									<div id="centtar"><img src="" id="targetvid" /></div>
									<div style="width:200px;height:200px;overflow:hidden;margin:auto;margin-top:10px;">
										<img id="previewvid" src="" class="jcrop-preview" />
									</div>
									<form action="Crop" method="post" id="cropimgvid">
										<input type="hidden" name="x1" id="x1vid"/>
										<input type="hidden" name="y1" id="y1vid"/>
										<input type="hidden" name="x2" id="x2vid"/>
										<input type="hidden" name="y2" id="y2vid"/>
										<input type="hidden" name="w" id="wvid"/>
										<input type="hidden" name="h" id="hvid"/>
										<input type="hidden" name="nombrearch" class="esnom"/>
										<input type="submit" class="boton" value="Cortar y enviar"/>
									</form>
									<div class="respcrop"></div>
								</div>
							</div>
						</div>
						<div id="idiomad" style="display:none;" class="divderfor">
							<div class="inspad">
								Para agregar un nuevo idioma llene los campos que se presentan a continuación, los campos marcados con * son obligatorios.
							</div>
							<div class="tdform">
								<form action="AltaIdioma" method="post" class="demasformularios">
									<table class="tfor">
										<tr><td class="alider">Idioma:</td><td><input type="text" class="texto" name="idioma"/></td><td class="ast">*</td><td></td></tr>
										<tr><td colspan="4"><input type="submit" class="boton" value="Dar de alta" /><input type="reset" class="boton" value="Limpiar" /></td></tr>
									</table>
								</form>
								<div class="respall"></div>
							</div>
						</div>
						<div id="formatod" style="display:none;" class="divderfor">
							<div class="inspad">
									Para agregar un nuevo formato llene los campos que se presentan a continuación, los campos marcados con * son obligatorios.
							</div>
								<div class="tdform">
									<form action="AltaFormato" method="post" class="demasformularios">
										<table class="tfor">
											<tr><td class="alider">Formato:</td><td><input type="text" class="texto" name="formato"/></td><td class="ast">*</td><td></td></tr>
											<tr><td colspan="4"><input type="submit" class="boton" value="Dar de alta" /><input type="reset" class="boton" value="Limpiar" /></td></tr>
										</table>
									</form>
								<div class="respall"></div>
							</div>
						</div>
						<div id="programad" style="display:none;" class="divderfor">
							<div class="inspad">
									Para agregar un programa nuevo llene los campos que se presentan a continuación, los campos marcados con * son obligatorios.
							</div>
								<div class="tdform">
									<form action="AltaPrograma" method="post" class="demasformularios">
										<table class="tfor">
											<tr><td class="alider">Programa:</td><td><input type="text" class="texto" name="programa"/></td><td class="ast">*</td><td></td></tr>
											<tr><td colspan="4"><input type="submit" class="boton" value="Dar de alta" /><input type="reset" class="boton" value="Limpiar" /></td></tr>
										</table>
									</form>
								<div class="respall"></div>
							</div>
						</div>
						<div id="publicadord" style="display:none;" class="divderfor">
							<div class="inspad">
									Para agregar un nuevo publicador llene los campos que se presentan a continuación, los campos marcados con * son obligatorios.
							</div>
								<div class="tdform">
									<form action="AltaPublicador" method="post" class="demasformularios">
										<table class="tfor">
											<tr><td class="alider">Publicador:</td><td><input type="text" class="texto" name="publicador"/></td><td class="ast">*</td><td></td></tr>
											<tr><td colspan="4"><input type="submit" class="boton" value="Dar de alta" /><input type="reset" class="boton" value="Limpiar" /></td></tr>
										</table>
									</form>
								<div class="respall"></div>
							</div>
						</div>
						<div id="forimgd" style="display:none;" class="divderfor">
							<div class="inspad">
								Para agregar un nuevo formato de imagen llene los campos que se presentan a continuación, los campos marcados con * son obligatorios.
							</div>
								<div class="tdform">
									<form action="AltaFormatoImg" method="post" class="demasformularios">
										<table class="tfor">
											<tr><td class="alider">Formato de imagen:</td><td><input type="text" class="texto" name="formato"/></td><td class="ast">*</td><td></td></tr>
											<tr><td class="alider">Extensión de formato de imagen:</td><td><input type="text" class="texto" name="extension"/></td><td class="ast">*</td><td></td></tr>
											<tr><td colspan="4"><input type="submit" class="boton" value="Dar de alta" /><input type="reset" class="boton" value="Limpiar" /></td></tr>
										</table>
									</form>
								<div class="respall"></div>
							</div>
						</div>
						<div id="forvidd" style="display:none;" class="divderfor">
							<div class="inspad">
								Para agregar un nuevo formato de video llene los campos que se presentan a continuación, los campos marcados con * son obligatorios.
							</div>
								<div class="tdform">
									<form action="AltaFormatoVid" method="post" class="demasformularios">
										<table class="tfor">
											<tr><td class="alider">Formato de video:</td><td><input type="text" class="texto" name="formato"/></td><td class="ast">*</td><td></td></tr>
											<tr><td class="alider">Extensión de formato de video:</td><td><input type="text" class="texto" name="extension"/></td><td class="ast">*</td><td></td></tr>
											<tr><td colspan="4"><input type="submit" class="boton" value="Dar de alta" /><input type="reset" class="boton" value="Limpiar" /></td></tr>
										</table>
									</form>
								<div class="respall"></div>
							</div>
						</div>
						<div id="editoriald" style="display:none;" class="divderfor">
							<div class="inspad">
								Para agregar una nueva editorial llene los campos que se presentan a continuación, los campos marcados con * son obligatorios.
							</div>
								<div class="tdform">
									<form action="AltaEditorial" method="post" class="demasformularios">
										<table class="tfor">
											<tr><td class="alider">Editorial:</td><td><input type="text" class="texto" name="editorial"/></td><td class="ast">*</td><td></td></tr>
											<tr><td colspan="4"><input type="submit" class="boton" value="Dar de alta" /><input type="reset" class="boton" value="Limpiar" /></td></tr>
										</table>
									</form>
								<div class="respall"></div>
							</div>
						</div>
						<div id="tomod" style="display:none;" class="divderfor">
							<div class="inspad">
									Para agregar un tomo nuevo llene los campos que se presentan a continuación, los campos marcados con * son obligatorios.
							</div>
								<div class="tdform">
									<form action="AltaTomo" method="post" class="demasformularios">
										<table class="tfor">
											<tr><td class="alider">Tomo:</td><td><input type="text" class="texto" name="tomo"/></td><td class="ast">*</td><td></td></tr>
											<tr><td colspan="4"><input type="submit" class="boton" value="Dar de alta" /><input type="reset" class="boton" value="Limpiar" /></td></tr>
										</table>
									</form>
								<div class="respall"></div>
							</div>
						</div>
						<div id="ediciond" style="display:none;" class="divderfor">
							<div class="inspad">
								Para agregar una edición nueva llene los campos que se presentan a continuación, los campos marcados con * son obligatorios.
							</div>
								<div class="tdform">
									<form action="AltaEdicion" method="post" class="demasformularios">
										<table class="tfor">
											<tr><td class="alider">Edición:</td><td><input type="text" class="texto" name="edicion"/></td><td class="ast">*</td><td></td></tr>
											<tr><td colspan="4"><input type="submit" class="boton" value="Dar de alta" /><input type="reset" class="boton" value="Limpiar" /></td></tr>
										</table>
									</form>
								<div class="respall"></div>
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