<%@page import="java.sql.*"%>
<%@page import="conexion.Conexion"%>

<%!
	Conexion con = new Conexion();
	Connection conexion;
	Statement stat=null;
	Statement state=null;
	Statement statem=null;
	Statement stateme=null;
	ResultSet rs=null;
	ResultSet rsn=null;
	ResultSet rsc=null;
	String codigo="";
	String nombre="";
	String desc="";
	String autor="";
	int registro=0;
	int division=0;
	int modulo=0;
	int nodepag=0;
	int i=0;
%>

<html>
	<head>
		<title>Biblioteca Virtual TFJFA</title>
		<LINK REL="StyleSheet" HREF="estilo/style.css" TYPE="text/css">
		<LINK REL="StyleSheet" HREF="estilo/alls.css" TYPE="text/css">
		<script language="javascript" type="text/javascript" src="guion/jquery.js"></script>
		</script>
		<script language="javascript" type="text/javascript" src="guion/videogui.js"></script>
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
						<a href="video.jsp"><div class="elemenu" id="seleccionado" >Videos</div></a>
					</div>
				</div>
				
				<div id="contenido">
					<div id="izquierdo">
						En este apartado podrá consultar videos del Tribunal Federal de Justicia Fiscal y Administrativa.
						Los videos se despliegan por su fecha de publicación a continuación:
						<div id="obras">
						<%
							conexion=con.conectar();
							statem = conexion.createStatement();
							stateme = conexion.createStatement();
							rsc = statem.executeQuery("SELECT COUNT(*) FROM mvideo");
							if(rsc.next())
							{
								registro = rsc.getInt(1);
								division = registro/10;
								modulo = registro%10;
								
								if(modulo==0)
								{
									nodepag=division;
								}
								else
								{
									nodepag=division+1;
								}
								out.println("<input type=\"hidden\" value=\""+nodepag+"\" id=\"esc1\" >");
							}
							rsc = statem.executeQuery("SELECT * FROM marticulo RIGHT JOIN mvideo ON marticulo.codigo_art=mvideo.codigo_art ORDER BY fecha_publicacion DESC LIMIT 10");
							while(rsc.next())
							{
								out.println("<a href=\"articulo.jsp?codart="+rsc.getString("codigo_art")+"\">");
									out.println("<div class=\"unobra\">");
										out.println("<div class=\"imgobmed\">");
											out.println("<img class=\"thumbnail2\" src=\""+rsc.getString("img_art")+"\">");
										out.println("</div>");
										out.println("<div class=\"infoob\">");
											out.println("<span class=\"tit2\">Titulo: </span>"+rsc.getString("titulo_art")+"<br>");
											out.println("<span class=\"tit2\">Autor: </span>"+rsc.getString("autor_art")+"<br>");
												out.println("<span class=\"tit2\">Asunto: </span>"+rsc.getString("asunto_art")+" edición<br>");
												out.println("<span class=\"tit2\">Duración del video: </span>"+rsc.getString("duracion_video")+"<br>");
											out.println("<span class=\"tit2\">Fecha de creación: </span>"+rsc.getString("fecha_creacion_art")+"<br>");
											out.println("<span class=\"tit2\">Descripción: </span>"+rsc.getString("descripcion_art"));
										out.println("</div>");
									out.println("</div>");
								out.println("</a>");
							}
							
						%>
						</div>
						<div id="paginacion">
							<input type="button" class="flechg" id="fiu" onclick="camValBotIzquierdaPrin()" />
							<input type="button" class="flechp" id="fiue" onclick="camValBotIzquierda()" />
							<div id="numpag">
								<%
									if(nodepag>=10)
									{
										for(i=1; i<=10; ++i)
										{
											if(i==1)
											{
												out.println("<input type=\"button\" class=\"nodepag selectpagnum\" id=\"primereb\" value=\""+i+"\" onclick=\"despArticulo(this.value, this.id)\" />");
											}
											if(i>1&&i!=10)
											{
												out.println("<input type=\"button\" class=\"nodepag\" id=\"pag"+i+"\" value=\""+i+"\" onclick=\"despArticulo(this.value, this.id)\"/>");
											}
											if(i==10)
											{
												out.println("<input type=\"button\" class=\"nodepag\" id=\"ultimoeb\" value=\""+i+"\" onclick=\"despArticulo(this.value, this.id)\"/>");
											}
										}
									}
									else
									{
										for(i=1; i<=nodepag; ++i)
										{
											if(i==1)
											{
												out.println("<input type=\"button\" class=\"nodepag selectpagnum\" id=\"primereb\" value=\""+i+"\" onclick=\"despArticulo(this.value, this.id)\" />");
											}
											if(i>1&&i<10)
											{
												out.println("<input type=\"button\" class=\"nodepag\" id=\"pag"+i+"\" value=\""+i+"\" onclick=\"despArticulo(this.value, this.id)\"/>");
											}
											
										}
									}
								%>
							</div>
							<input type="button" class="flechg" id="fdu" onclick="camValBotDerechaFin()" />
							<input type="button" class="flechp" id="fdue" onclick="camValBotDerecha()"/>
						</div>
						<div id="checks">
							Para hacer una búsqueda avanzada escriba sólo en los campos en los cuales desea 
							hacer la búsqueda y deje en blanco los campos en los cuales no desea buscar.
							Si llena campos en los cuales no quiere buscar su búsqueda podría verse afectada.
							<br>
							<br>
							<!--[if gt IE 6]><center><![endif]-->
							<form method="post" action="Busquedavid">
								<table cellspacing="0" cellpadding="0" id="tabch">
									<tr><td class="tder">Título:</td><td><input type="text" id="titulo" name="titulo" class="texto tatex" onkeyup="moPal('palo1', this.id)" onblur="moPal('palo1', this.id)" /></td><td><img src="img/paloma.png" id="palo1" style="display:none" /></td></tr>
									<tr><td class="tder">Título alternativo:</td><td><input type="text" id="tituloalt" name="tituloalt" class="texto tatex" onkeyup="moPal('palo2', this.id)" onblur="moPal('palo2', this.id)" /></td><td><img src="img/paloma.png" id="palo2" style="display:none" /></td></tr>
									<tr><td class="tder">Descripción:</td><td><input type="text" id="descripcio" name="descripcio" class="texto tatex" onkeyup="moPal('palo3', this.id)" onblur="moPal('palo3', this.id)" /></td><td><img src="img/paloma.png" id="palo3" style="display:none" /></td></tr>
									<tr><td class="tder">Asunto:</td><td><input type="text" id="asunto" name="asunto" class="texto tatex" onkeyup="moPal('palo7', this.id)" onblur="moPal('palo7', this.id)" /></td><td><img src="img/paloma.png" id="palo7" style="display:none" /></td></tr>
									<tr><td class="tder">Autor:</td><td><input type="text" id="autor" name="autor" class="texto tatex" onkeyup="moPal('palo8', this.id)" onblur="moPal('palo8', this.id)" /></td><td><img src="img/paloma.png" id="palo8" style="display:none" /></td></tr>
									<tr><td class="tder"><input type="submit" class="boton botex" value="Buscar" /></td><td><input type="reset" class="boton botex" value="Limpiar"/></td><td></td></tr>
								</table>
							</form>
							<!--[if gt IE 6]></center><![endif]-->
						</div>
					</div>
					<div id="derecho">
						<span class="t10">Los 10 más visitados.</span>
						<div id="elementos">
							<%
								stat=conexion.createStatement();
								state=conexion.createStatement();
								rs=stat.executeQuery("SELECT codigo_art, img_art FROM marticulo WHERE id_tipo=4 ORDER BY visitas_art DESC LIMIT 10");
								while(rs.next())
								{
									++i;
									codigo = rs.getString("codigo_art");
									rsn=state.executeQuery("SELECT titulo_art, descripcion_art, autor_art FROM mvideo WHERE codigo_art='"+codigo+"'");
									
									if(rsn.next())
									{
										nombre = rsn.getString("titulo_art");
										desc = rsn.getString("descripcion_art");
										autor = rsn.getString("autor_art");
									}
									out.println("<a href=\"articulo.jsp?codart="+codigo+"\">");
									out.println("<div class=unele onmouseover=mostrar('desc"+i+"') onmouseout=ocultar('desc"+i+"')>");
									out.println("<div class=imagenar>");
									out.println("<img class=\"thumbnail\" src=\""+rs.getString("img_art")+"\">");
									out.println("</div>");
									out.println("<div class=datos>");
									out.println("<span class=tit>Nombre:</span>");
									out.println(nombre);
									out.println("<br>");
									out.println("<span class=tit>Autor:</span>");
									out.println(autor);
									out.println("<br>");
									out.println("</div>");
									out.println("<div id=desc"+i+" class=descripcion style=display:none>");
									out.println("<span class=tit>Descripción:</span>");
									out.println(desc);
									out.println("</div>");
									out.println("<div class=corte></div>");
									out.println("</div>");
									out.println("</a>");
								}
							%>
						</div>
					</div>
					<div class="corte"></div>
				</div>
			</div>
			
			<div id="som3">
				<div id="footer">
					<div id="sobrante">
						<div id="bl"></div>
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
			conexion.close();
			con.cerrarConexion();
			stat.close();
			state.close();
			statem.close();
			stateme.close();
			rs.close();
			rsn.close();
			rsc.close();
		%>
	</body>
</html>