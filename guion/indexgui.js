function mostrar(esto)
{
	var div=document.getElementById(esto);
	div.style.display='block';
}

function ocultar(esto)
{
	var div=document.getElementById(esto);
	div.style.display='none';
}

function verArticulo(codigoArt)
{
	location.href="articulo.jsp?codart="+codigoArt;
}