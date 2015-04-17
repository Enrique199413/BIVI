$(document).ready(function(){
	$('#formupre').submit(function(event){
		$.ajax
		({
			async:true,
			type:'POST',
			url:$(this).attr('action'),
			data:$(this).serialize(),
			success: function(datos){
			$('#comentarior').html(datos);
			$('#formupre')[0].reset();
			}
		})
		return false;
	})
})