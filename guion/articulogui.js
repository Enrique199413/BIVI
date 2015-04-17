var altnav;
var anchnav;
var anchimg;
var altimg;
var ejecutar;
window.onresize=redimenImagen;

function redimenImagen()
{
	if(validarDes(3))
	{
		var trianc=vReal(1);
		var trialt=vReal(2);
		altnav = $(window).height();
		anchnav = $(window).width();
		
		if(trialt<trianc)
		{
			while($("#laim").height()>altnav||$("#laim").width()>anchnav)
			{
				$("#laim").css
				({
					"width": $("#laim").width()-1
				})
			}
			while($("#laim").height()<altnav && $("#laim").width()<anchnav&&$("#laim").width()<=trianc)
			{
				$("#laim").css
				({
					"width": $("#laim").width()+1
				})
			}
		}
		else
		{
			while($("#laim").height()>altnav||$("#laim").width()>anchnav)
			{
				$("#laim").css
				({
					"width": $("#laim").width()-1
				})
			}
			while($("#laim").height()<altnav && $("#laim").width()<anchnav&&$("#laim").width()<=trianc)
			{
				$("#laim").css
				({
					"width": $("#laim").width()+1
				})
			}
		}	
		posicionarImagen();
	}
}

function vReal(pet)
{	
	$("#sombrat").css
	({
		"width":$(document).width(),
		"height":$(document).height()
	})
	
	$("body").css
	({
		"overflow":"hidden"
	})
	document.getElementById("sombrat").style.display='block';
	
	if(pet==1)
	{
		return anchimg;
	}
	else if(pet==2)
	{
		return altimg;
	}
	else
	{	
	
		anchimg = $("#laim").width();
		altimg = $("#laim").height();
		tamañoImagen(anchimg,altimg);
		validarDes(1);
	}	
}

function tamañoImagen()
{
	altnav = $(window).height();
	anchnav = $(window).width();
	anchimg = vReal(1);
	altimg = vReal(2);
	
	if(altimg>altnav||anchimg>anchnav)
	{
		if(altimg<anchimg)
		{
			while($("#laim").height()>altnav||$("#laim").width()>anchnav)
			{
				$("#laim").css
				({
					"width": $("#laim").width()-1
				})
			}
		}
		else
		{
			while($("#laim").height()>altnav||$("#laim").width()>anchnav)
			{
				$("#laim").css
				({
					"height": $("#laim").height()-1
				})
			}
		}
	}
	posicionarImagen();
	redimenImagen();
}

function posicionarImagen()
{
	$("#laim").css({
	   "position": "absolute",
	   "top": (altnav/2)-($("#laim").height()/2),
	   "left": (anchnav/2)-($("#laim").width()/2)
	})
}

function validarDes(numero)
{
	if(numero==1)
	{
		ejecutar=true;
	}
	if(numero==2)
	{
		ejecutar=false;
	}
	else
	{
		return ejecutar;
	}
}

function ocultarImg()
{	

	
	validarDes(2)
	$("#laim").css
	({
		"width":vReal(1)
	})
	$("#sombrat").css
	({
		"display":"none"
	})
	$("body").css
	({
		"overflow":"auto"
	})
}

$(document).ready(function(){
	$('#formucom').submit(function(event){
		$.ajax
		({
			async:true,
			type:'POST',
			url:$(this).attr('action'),
			data:$(this).serialize(),
			success: function(datos){
			$('#comentario').html(datos);
			}
		})
		return false;
	})
})

function redPreg()
{
	window.location = "pregunta.jsp?codart="+$('#codeart').get(0).value;
}