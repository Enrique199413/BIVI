function cambiarDiv(divid)
{
	$(".unesub").removeClass("ser");
	$(".divderfor").css("display", "none");
	$("#"+divid).addClass("ser");
	$("#"+divid+"d").css("display", "block");
	$(".respuesta").css("display", "none");
	if(divid=="articulo")
	{
		cargarArticulo();
	}
}

function cargarArticulo()
{	
	$.ajax
	({
		async:true,
		type:'POST',
		url:'CargarArticuloCambio',
		success: function(datos){
			$('#contenedorarticulod').html(datos);
			otroA()
		}
	})
	return false;
}


function otroA()
{
	$('.camart').submit(function(event){
		$.ajax
		({
			async:true,
			type:'POST',
			url:$(this).attr('action'),
			data:$(this).serialize(),
			success: function(datos){
				$('#contenedorarticulod').html(datos);
				enviarFormulario();
			}
		})
		return false;
	})
}

function enviarFormulario()
{
	$('.camarte').submit(function(event){
		$.ajax
		({
			async:true,
			type:'POST',
			url:$(this).attr('action'),
			data:$(this).serialize(),
			success: function(datos){
				$('#contenedorarticulod').html(datos);
			}
		})
		return false;
	})
}

$(document).ready(function(){
	$('#coia').submit(function(event){
		$.ajax
		({
			async:true,
			type:'POST',
			url:$(this).attr('action'),
			data:$(this).serialize(),
			success: function(datos){
				$('#contenedoridiomad').html(datos);
				cambioReal();
			}
		})
		return false;
	})
})

function cambioReal()
{
	$('.demasd').submit(function(event){
		$.ajax
		({
			async:true,
			type:'POST',
			url:$(this).attr('action'),
			data:$(this).serialize(),
			success: function(datos){
				$(".respuesta").css("display", "block");
				$('.respuesta').html(datos);
			}
		})
		return false;
	})
}