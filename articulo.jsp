<%@page import="java.sql.*"%>
<%@page import="conexion.Conexion"%>

<%!
	Conexion con = new Conexion();
	Connection conexion;
	Statement stm=null;
	ResultSet rs=null;
	ResultSet rse=null;
	int parametro;
	String imagenurl;
	int idformato;
	int idpublicador;
	int idprograma;
	int idtipo;
	String primero;
	
	String ruta, fecpublicacion, tituloarticulo, nombrealterna, descripcionart, asuntoart, fechcreacion, autorart, piefoto, duracionvid;
	int ideditorial, idedicion, idtomonr, idformat;
%>

<%
	try
	{
		parametro = Integer.parseInt(request.getParameter("codart"));
	}
	catch(Exception e)
	{
		response.sendRedirect("index.jsp");
	}
	
	conexion = con.conectar();
	stm = conexion.createStatement();
	rs=stm.executeQuery("SELECT img_art, id_formato, id_publicador, id_programa, id_tipo FROM marticulo WHERE codigo_art="+parametro);
	if(rs.next())
	{
		imagenurl=rs.getString("img_art");
		idformato=rs.getInt("id_formato");
		idpublicador=rs.getInt("id_publicador");
		idprograma=rs.getInt("id_programa");
		idtipo=rs.getInt("id_tipo");
	}
	else
	{
		response.sendRedirect("index.jsp");
	}
	if(idtipo==3)
	{
		rs=stm.executeQuery("SELECT ruta_imagen FROM mimagen WHERE codigo_art="+parametro);
		if(rs.next())
		{
			primero=rs.getString("ruta_imagen");
		}
	}
%>
<html>
	<head>
		<title>Biblioteca Virtual TFJFA</title>
		<LINK REL="StyleSheet" HREF="estilo/style.css" TYPE="text/css">
		<LINK REL="StyleSheet" HREF="estilo/articulos.css" TYPE="text/css">
		<script language="javascript" type="text/javascript" src="guion/jquery.js"></script>
		</script>
		<script language="javascript" type="text/javascript" src="guion/articulogui.js"></script>
		</script>
	</head>
	
	<body>
		<div id="sombrat" style="display:none" onclick="ocultarImg()" >
			<img src="<%=primero%>" id="laim">
		</div>
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
					<div id="imgr">
						<img src="<%=imagenurl%>" class="thumbnail3">
					</div>
					<div id="informacion">
						<%
							switch(idtipo)
							{
								case 1:
									rs=stm.executeQuery("SELECT * FROM mlibro WHERE codigo_art="+parametro);
									if(rs.next())
									{
										fecpublicacion=rs.getString("fecha_publicacion");
										ruta=rs.getString("ruta_texto");
										tituloarticulo=rs.getString("titulo_art");
										nombrealterna=rs.getString("nomalternativo_art");
										descripcionart=rs.getString("descripcion_art");
										asuntoart=rs.getString("asunto_art");
										fechcreacion=rs.getString("fecha_creacion_art");
										autorart=rs.getString("autor_art");
										ideditorial=rs.getInt("id_editorial");
										idedicion=rs.getInt("id_edicion");
										idtomonr=rs.getInt("id_tomo");
										
										
										out.println("<span class=\"tit2\">Titulo: </span>"+tituloarticulo+"<br>");
										out.println("<span class=\"tit2\">Titulo alternativo: </span>"+nombrealterna+"<br>");
										out.println("<span class=\"tit2\">Autor: </span>"+autorart+"<br>");
										rse=stm.executeQuery("SELECT descripcion_editorial FROM ceditorial WHERE id_editorial="+ideditorial);
										if(rse.next())
										{
											out.println("<span class=\"tit2\">Editorial: </span>"+rse.getString("descripcion_editorial")+"<br>");
										}
										
										rse=stm.executeQuery("SELECT descripcion_edicion FROM cedicion WHERE id_edicion="+idedicion);
										if(rse.next())
										{
											out.println("<span class=\"tit2\">Edicion: </span>"+rse.getString("descripcion_edicion")+" edición<br>");
										}
										
										rse=stm.executeQuery("SELECT descripcion_tomo FROM ctomo WHERE id_tomo="+idtomonr);
										if(rse.next())
										{
											out.println("<span class=\"tit2\">Tomo: </span>"+rse.getString("descripcion_tomo")+"<br>");
										}
										out.println("<span class=\"tit2\">Asunto: </span>"+asuntoart+"<br>");
										out.println("<span class=\"tit2\">Fecha de creación: </span>"+fechcreacion+"<br>");
										out.println("<span class=\"tit2\">Fecha de publicación : </span>"+fecpublicacion+"<br>");
										out.println("<span class=\"tit2\">Descripción: </span>"+descripcionart+"<br><br>");
										out.println("<a href=\""+ruta+"\"><input type=\"button\" class=\"boton\" value=\"Ver libro\" id=\"ver\"></a>");
									}
								break;
								case 2:
									rs=stm.executeQuery("SELECT * FROM mrevista WHERE codigo_art="+parametro);
									if(rs.next())
									{
										fecpublicacion=rs.getString("fecha_publicacion");
										ruta=rs.getString("ruta_texto");
										tituloarticulo=rs.getString("titulo_art");
										nombrealterna=rs.getString("nomalternativo_art");
										descripcionart=rs.getString("descripcion_art");
										asuntoart=rs.getString("asunto_art");
										fechcreacion=rs.getString("fecha_creacion_art");
										autorart=rs.getString("autor_art");
										ideditorial=rs.getInt("id_editorial");
										idedicion=rs.getInt("id_edicion");
										idtomonr=rs.getInt("id_no_revista");
										
										out.println("<span class=\"tit2\">Titulo: </span>"+tituloarticulo+"<br>");
										out.println("<span class=\"tit2\">Titulo alternativo: </span>"+nombrealterna+"<br>");
										out.println("<span class=\"tit2\">Autor: </span>"+autorart+"<br>");
										rse=stm.executeQuery("SELECT descripcion_editorial FROM ceditorial WHERE id_editorial="+ideditorial);
										if(rse.next())
										{
											out.println("<span class=\"tit2\">Editorial: </span>"+rse.getString("descripcion_editorial")+"<br>");
										}
										
										rse=stm.executeQuery("SELECT descripcion_edicion FROM cedicion WHERE id_edicion="+idedicion);
										if(rse.next())
										{
											out.println("<span class=\"tit2\">Edicion: </span>"+rse.getString("descripcion_edicion")+" edición<br>");
										}
										
										rse=stm.executeQuery("SELECT descripcion_tomo FROM ctomo WHERE id_tomo="+idtomonr);
										if(rse.next())
										{
											out.println("<span class=\"tit2\">No. de revista: </span>"+rse.getString("descripcion_tomo")+"<br>");
										}
										out.println("<span class=\"tit2\">Asunto: </span>"+asuntoart+"<br>");
										out.println("<span class=\"tit2\">Fecha de creación: </span>"+fechcreacion+"<br>");
										out.println("<span class=\"tit2\">Fecha de publicación : </span>"+fecpublicacion+"<br>");
										out.println("<span class=\"tit2\">Descripción: </span>"+descripcionart+"<br><br>");
										out.println("<a href=\""+ruta+"\"><input type=\"button\" class=\"boton\" value=\"Ver revista\" id=\"ver\"></a>");
									}
								break;
								case 3:
									rs=stm.executeQuery("SELECT * FROM mimagen WHERE codigo_art="+parametro);
									if(rs.next())
									{
										fecpublicacion=rs.getString("fecha_publicacion");
										ruta=rs.getString("ruta_imagen");
										tituloarticulo=rs.getString("titulo_art");
										nombrealterna=rs.getString("nomalternativo_art");
										descripcionart=rs.getString("descripcion_art");
										asuntoart=rs.getString("asunto_art");
										fechcreacion=rs.getString("fecha_creacion_art");
										autorart=rs.getString("autor_art");
										piefoto=rs.getString("pie_imagen");
										idformat=rs.getInt("id_formato");
										
										out.println("<span class=\"tit2\">Titulo: </span>"+tituloarticulo+"<br>");
										out.println("<span class=\"tit2\">Titulo alternativo: </span>"+nombrealterna+"<br>");
										out.println("<span class=\"tit2\">Autor: </span>"+autorart+"<br>");
										out.println("<span class=\"tit2\">Pie de foto: </span>"+piefoto+"<br>");
										rse=stm.executeQuery("SELECT descripcion_formato, extension_formato FROM cformato_imagen WHERE id_formato="+idformat);
										if(rse.next())
										{
											out.println("<span class=\"tit2\">Formato de imagen: </span>"+rse.getString("descripcion_formato")+"<br>");
											out.println("<span class=\"tit2\">Extensión de imagen: </span>"+rse.getString("extension_formato")+"<br>");
										}
										
										out.println("<span class=\"tit2\">Asunto: </span>"+asuntoart+"<br>");
										out.println("<span class=\"tit2\">Fecha de creación: </span>"+fechcreacion+"<br>");
										out.println("<span class=\"tit2\">Fecha de publicación : </span>"+fecpublicacion+"<br>");
										out.println("<span class=\"tit2\">Descripción: </span>"+descripcionart+"<br><br>");
										out.println("<a href=\"#\"><input type=\"button\" class=\"boton\" value=\"Ver imagen\" id=\"ver\" onclick=\"vReal(3)\"></a>");
									}
								break;
								case 4:
									rs=stm.executeQuery("SELECT * FROM mvideo WHERE codigo_art="+parametro);
									if(rs.next())
									{
										fecpublicacion=rs.getString("fecha_publicacion");
										ruta=rs.getString("ruta_video");
										tituloarticulo=rs.getString("titulo_art");
										nombrealterna=rs.getString("nomalternativo_art");
										descripcionart=rs.getString("descripcion_art");
										asuntoart=rs.getString("asunto_art");
										fechcreacion=rs.getString("fecha_creacion_art");
										autorart=rs.getString("autor_art");
										duracionvid=rs.getString("duracion_video");
										idformat=rs.getInt("id_formato");
										
										out.println("<span class=\"tit2\">Titulo: </span>"+tituloarticulo+"<br>");
										out.println("<span class=\"tit2\">Titulo alternativo: </span>"+nombrealterna+"<br>");
										out.println("<span class=\"tit2\">Autor: </span>"+autorart+"<br>");
										out.println("<span class=\"tit2\">Duración: </span>"+duracionvid+"<br>");
										rse=stm.executeQuery("SELECT descripcion_formato, extension_formato FROM cformato_video WHERE id_formato="+idformat);
										if(rse.next())
										{
											out.println("<span class=\"tit2\">Formato de imagen: </span>"+rse.getString("descripcion_formato")+"<br>");
											out.println("<span class=\"tit2\">Extensión de imagen: </span>"+rse.getString("extension_formato")+"<br>");
										}
										
										out.println("<span class=\"tit2\">Asunto: </span>"+asuntoart+"<br>");
										out.println("<span class=\"tit2\">Fecha de creación: </span>"+fechcreacion+"<br>");
										out.println("<span class=\"tit2\">Fecha de publicación : </span>"+fecpublicacion+"<br>");
										out.println("<span class=\"tit2\">Descripción: </span>"+descripcionart+"<br><br>");
									}
								break;
								default:
									
								break;
							}
						%>
					</div>
					
					<%
						if(idtipo==4)
						{
							out.println("<div id=\"video\">");
							out.println("<video width=\"320\" height=\"240\" controls=\"controls\"><source src=\""+ruta+"\" type=\"video/mp4\" />Your browser does not support the video tag.</video>");
							out.println("</div>");
						}
					%>
					
					<div class="corte"></div>
					<div id="elemento">
						<%
							switch(idtipo)
							{
								case 1:
									out.println("Para visualizar el libro haga clic en el botón de arriba, el índice del mismo estará dentro del documento.<br><span class=\"naranja\">Importante: Para poder ver el documento necesita tener instalado en su computadora <a href=\"http://get.adobe.com/es/reader/\">Adobe Reader.</a></span>");
								break;
								case 2:
									out.println("Para visualizar la revista haga clic en el botón de arriba, el índice de la misma estará dentro del documento.<br><span class=\"naranja\">Importante: Para poder ver el documento necesita tener instalado en su computadora <a href=\"http://get.adobe.com/es/reader/\">Adobe Reader.</a></span>");
								break;
								case 3:
									out.println("Para visualizar la imagen en tamaño completo haga clic en el botón de arriba.");
								break;
								case 4:
									
								break;
								default:
									
								break;
							}
						%>
					</div>
					<div id="comentario">
						<%
							rs=stm.executeQuery("SELECT des_comentario, fecha_comentario FROM mcomentario WHERE codigo_art='"+parametro+"'");
							if (rs.next())
							{
								out.println("<span class=\"tit2\">Comentarios:</span>");
								rs.beforeFirst();
								while (rs.next())
								{
									out.println("<div class=\"unc\">"+rs.getString("des_comentario")+"<br><div class=\"fechach\">"+rs.getString("fecha_comentario")+"</div></div>");
								}
							}
							else
							{
								out.println("<div class=\"uncn\">No hay ningún comentario aún</div>");
							}
						%>
					</div>
					<form action="Altacomentario" method="post" id="formucom">
						<table id="comentar" cellspacing="0" cellpadding="0">
							<tr><td class="enm"><textarea class="texto" id="tear" name="comentario"></textarea></td></tr>
							<tr><td class="enm"><input type="submit" value="Comentar" name="sad" class="boton"/><input type="reset" value="Limpiar" class="boton"/><input type="hidden" value="<%=parametro%>" name="codart" id="codeart"></form></td></tr>
							<tr><td class="enm">Para hacer una pregunta sobre éste articulo haga clic en el siguiente botón:<input type="button" value="Preguntar" class="boton" id="preg" onclick="redPreg()" /></td></tr>
						</table>
					<div id="respuestas"></div>
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
			<%
				con.cerrarConexion();
				conexion.close();
				stm.close();
				rs.close();
				rse.close();
			%>
		<!--[if gt IE 6]></center><![endif]-->
	</body>
</html>