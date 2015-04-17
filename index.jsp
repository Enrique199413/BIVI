<%@page import="java.sql.*"%>
<%@page import="conexion.Conexion"%>

<%!
	Conexion con = new Conexion();
	Connection conexion;
	Statement stat=null;
	Statement state=null;
	ResultSet rs=null;
	ResultSet rsn=null;
	String codigo="";
	String nombre="";
	String desc="";
	String ruta="";
	int tipo=0;
	int i=0;
%>

<html>
	<head>
		<title>Biblioteca Virtual TFJFA</title>
		<script language="javascript" type="text/javascript" src="guion/indexgui.js"></script>
		</script>
		<LINK REL="StyleSheet" HREF="estilo/style.css" TYPE="text/css">
		<LINK REL="StyleSheet" HREF="estilo/indexs.css" TYPE="text/css">
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
						<a href="index.jsp"><div class="elemenu" id="seleccionado" >Inicio</div></a>
						<a href="libro.jsp"><div class="elemenu">Libros</div></a>
						<a href="revista.jsp"><div class="elemenu">Revistas</div></a>
						<a href="imagen.jsp"><div class="elemenu">Imágenes</div></a>
						<a href="video.jsp"><div class="elemenu">Videos</div></a>
					</div>
				</div>
				
				<div id="contenido">
					<div id="bienv">
						<h1>¡Bienvenido!</h1>
						<p>Esta es la nueva página de la Biblioteca Virtual del Tribunal Federal de Justicia Fiscal y 
						Administrativa en la cual podrás consultar las publicaciones y obras con información del Tribunal.
						La información se puede encontrar en imágenes, videos y texto.</p>
						<center><div id="logoTFJFA"><a href="http://www.tff.gob.mx/"><img src="img/tfjfalogo.png"/></a></div></center>
						<br>
						<br>
						<h1>Búsqueda</h1>
						<p>Para realizar una búsqueda teclee el texto que desea buscar y después haga clic en el botón buscar.</p>
						<form method="post" action="Busquedasimple">
							<input type="text" class="texto" id="bs" name="bus"/><input type="submit" value="Buscar" class="boton" id="bpbs"/>
						</form>
						<p>Para realizar una búsqueda avanzada debe ingresar a la sección indicada del menú, dependiendo del 
						tipo de información que desea consultar, dentro de cada sección hay una sección de búsqueda avanzada.</p>
					</div>
					
					<div id="top10">
						<h1 class="t10">Los 10 más visitados.</h1>
						<div id="elementos">
							<%
								conexion=con.conectar();
								stat=conexion.createStatement();
								state=conexion.createStatement();
								rs=stat.executeQuery("SELECT codigo_art, id_tipo, img_art FROM marticulo ORDER BY visitas_art DESC LIMIT 10");
								while(rs.next())
								{
									++i;
									tipo = rs.getInt("id_tipo");
									codigo = rs.getString("codigo_art");
									ruta = rs.getString("img_art");
									switch(tipo)
									{
										case 1:
											rsn=state.executeQuery("SELECT titulo_art, descripcion_art FROM mlibro WHERE codigo_art='"+codigo+"'");
										break;
										case 2:
											rsn=state.executeQuery("SELECT titulo_art, descripcion_art FROM mrevista WHERE codigo_art='"+codigo+"'");
										break;
										case 3:
											rsn=state.executeQuery("SELECT titulo_art, descripcion_art FROM mimagen WHERE codigo_art='"+codigo+"'");
										break;
										case 4:
											rsn=state.executeQuery("SELECT titulo_art, descripcion_art FROM mvideo WHERE codigo_art='"+codigo+"'");
										break;
									}
									
									if(rsn.next())
									{
										nombre = rsn.getString("titulo_art");
										desc = rsn.getString("descripcion_art");
									}
									
									out.println("<a href=\"articulo.jsp?codart="+codigo+"\">");
									out.println("<div class=unele onmouseover=mostrar('desc"+i+"') onmouseout=ocultar('desc"+i+"')>");
									out.println("<div class=imagenar>");
									out.println("<img src="+ruta+" class=\"thumbnail\" align=left>");
									out.println("</div>");
									out.println("<div class=datos>");
									out.println("<span class=tit>Nombre:</span>");
									out.println(nombre);
									out.println("<br>");
									out.println("<span class=tit>Tipo:</span>");
									switch(tipo)
									{
										case 1:
											out.println("Libro");
										break;
										case 2:
											out.println("Revista");
										break;
										case 3:
											out.println("Imagen");
										break;
										case 4:
											out.println("Video");
										break;
										default:
											out.println("Información no disponible");
										break;
									}
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
					<div class="corte">
					</div>
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
			con.cerrarConexion();
			conexion.close();
			stat.close();
			state.close();
			rs.close();
			rsn.close();
		%>
	</body>
</html>