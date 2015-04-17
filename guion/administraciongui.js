$(document).ready(function(){
	$('#somcentral').css("marginTop",parseInt(($(window).height()/2)-($('#somcentral').height()/2)));
});

$(document).ready(function(){
	$('#log').submit(function(event){
		$.ajax
		({
			async:true,
			type:'POST',
			url:$(this).attr('action'),
			data:$(this).serialize(),
			success: function(datos){
				if(parseInt(datos)==1)
				{
					location.href="alta.jsp";
				}
				else
				{
					$('#resp').html(datos);
				}
			}
		})
		return false;
	})
})