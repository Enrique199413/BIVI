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
		url:'CargarArticulo',
		success: function(datos){
			$('#contenedorarticulod').html(datos);
			otroA()
		}
	})
	return false;
}


function otroA()
{
	$('.boart').submit(function(event){
		$.ajax
		({
			async:true,
			type:'POST',
			url:$(this).attr('action'),
			data:$(this).serialize(),
			success: function(datos){
				$('.respuesta').html(datos);
				cargarArticulo();
			}
		})
		return false;
	})
}

$(document).ready(function(){
	$('.demas').submit(function(event){
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
})