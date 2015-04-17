function cambiarDiv(divid)
{
	$(".unesub").removeClass("ser");
	$(".divderfor").css("display", "none");
	$("#"+divid).addClass("ser");
	$("#"+divid+"d").css("display", "block");
	$(".respall").css("display", "none");
}

function genThumbnail(ruta)
{
	$("#jcrimg").show();
	$("#target").attr("src","archivo/"+ruta);
	$("#preview").attr("src","archivo/"+ruta);
	$(".esnom").val(ruta);
}

function genThumbnailRev(ruta)
{
	$("#jcrimgrev").show();
	$("#targetrev").attr("src","archivo/"+ruta);
	$("#previewrev").attr("src","archivo/"+ruta);
	$(".esnom").val(ruta);
}

function genThumbnailImg(ruta)
{
	$("#jcrimgimg").show();
	$("#targetimg").attr("src","archivo/"+ruta);
	$("#previewimg").attr("src","archivo/"+ruta);
	$(".esnom").val(ruta);
}

function genThumbnailVid(ruta)
{
	$("#jcrimgvid").show();
	$("#targetvid").attr("src","archivo/"+ruta);
	$("#previewvid").attr("src","archivo/"+ruta);
	$(".esnom").val(ruta);
}

function verm()
{
	var realach=$("#target").width();
	var realalt=$("#target").height();
	while($("#target").width()>728)
	{
		
		$("#target").css
		({
			"width": $("#target").width()-1
		})
	}
	var despach=$("#target").width();
	var despalt=$("#target").height();
	jQuery(function($)
	{
		var jcrop_api, boundx, boundy;
		$('#target').Jcrop(
		{
			onChange: updatePreview,
			onSelect: updatePreview,
			bgColor:'black',
			setSelect:[0,0,200,200],
			bgOpacity:.3,
			allowSelect:false,
			aspectRatio: 1,
			minSize: [200,200]
		}
		,function()
		{
			var bounds = this.getBounds();
			boundx = bounds[0];
			boundy = bounds[1];
			jcrop_api = this;
		});

		function updatePreview(c)
		{
			if (parseInt(c.w) > 0)
			{
				var rx = 200 / c.w;
				var ry = 200 / c.h;

				$('#preview').css(
				{
					width: Math.round(rx * boundx) + 'px',
					height: Math.round(ry * boundy) + 'px',
					marginLeft: '-' + Math.round(rx * c.x) + 'px',
					marginTop: '-' + Math.round(ry * c.y) + 'px'
				});
				setCoords(c, realach, realalt, despach, despalt);
			}
		};
	});
	
	function setCoords(c, realachp, realaltp, despachp, despaltp)
	{
		jQuery('#x1').val(parseInt((realach/despach)*c.x));
		jQuery('#y1').val(parseInt((realalt/despalt)*c.y));
		jQuery('#x2').val(c.x2);
		jQuery('#y2').val(c.y2);
		jQuery('#w').val(parseInt((realach/ despach)*c.w));
		jQuery('#h').val(parseInt((realalt/ despalt)*c.h));
	}
}

function vermrev()
{
	var realach=$("#targetrev").width();
	var realalt=$("#targetrev").height();
	while($("#targetrev").width()>728)
	{
		
		$("#targetrev").css
		({
			"width": $("#targetrev").width()-1
		})
	}
	var despach=$("#targetrev").width();
	var despalt=$("#targetrev").height();
	jQuery(function($)
	{
		var jcrop_api, boundx, boundy;
		$('#targetrev').Jcrop(
		{
			onChange: updatePreview,
			onSelect: updatePreview,
			bgColor:'black',
			setSelect:[0,0,200,200],
			bgOpacity:.3,
			allowSelect:false,
			aspectRatio: 1,
			minSize: [200,200]
		}
		,function()
		{
			var bounds = this.getBounds();
			boundx = bounds[0];
			boundy = bounds[1];
			jcrop_api = this;
		});

		function updatePreview(c)
		{
			if (parseInt(c.w) > 0)
			{
				var rx = 200 / c.w;
				var ry = 200 / c.h;

				$('#previewrev').css(
				{
					width: Math.round(rx * boundx) + 'px',
					height: Math.round(ry * boundy) + 'px',
					marginLeft: '-' + Math.round(rx * c.x) + 'px',
					marginTop: '-' + Math.round(ry * c.y) + 'px'
				});
				setCoordsrev(c, realach, realalt, despach, despalt);
			}
		};
	});
	
	function setCoordsrev(c, realachp, realaltp, despachp, despaltp)
	{
		jQuery('#x1rev').val(parseInt((realach/despach)*c.x));
		jQuery('#y1rev').val(parseInt((realalt/despalt)*c.y));
		jQuery('#x2rev').val(c.x2);
		jQuery('#y2rev').val(c.y2);
		jQuery('#wrev').val(parseInt((realach/ despach)*c.w));
		jQuery('#hrev').val(parseInt((realalt/ despalt)*c.h));
	}
}

function vermimg()
{
	var realach=$("#targetimg").width();
	var realalt=$("#targetimg").height();
	while($("#targetimg").width()>728)
	{
		
		$("#targetimg").css
		({
			"width": $("#targetimg").width()-1
		})
	}
	var despach=$("#targetimg").width();
	var despalt=$("#targetimg").height();
	jQuery(function($)
	{
		var jcrop_api, boundx, boundy;
		$('#targetimg').Jcrop(
		{
			onChange: updatePreview,
			onSelect: updatePreview,
			bgColor:'black',
			setSelect:[0,0,200,200],
			bgOpacity:.3,
			allowSelect:false,
			aspectRatio: 1,
			minSize: [200,200]
		}
		,function()
		{
			var bounds = this.getBounds();
			boundx = bounds[0];
			boundy = bounds[1];
			jcrop_api = this;
		});

		function updatePreview(c)
		{
			if (parseInt(c.w) > 0)
			{
				var rx = 200 / c.w;
				var ry = 200 / c.h;

				$('#previewimg').css(
				{
					width: Math.round(rx * boundx) + 'px',
					height: Math.round(ry * boundy) + 'px',
					marginLeft: '-' + Math.round(rx * c.x) + 'px',
					marginTop: '-' + Math.round(ry * c.y) + 'px'
				});
				setCoordsrev(c, realach, realalt, despach, despalt);
			}
		};
	});
	
	function setCoordsrev(c, realachp, realaltp, despachp, despaltp)
	{
		jQuery('#x1img').val(parseInt((realach/despach)*c.x));
		jQuery('#y1img').val(parseInt((realalt/despalt)*c.y));
		jQuery('#x2img').val(c.x2);
		jQuery('#y2img').val(c.y2);
		jQuery('#wimg').val(parseInt((realach/ despach)*c.w));
		jQuery('#himg').val(parseInt((realalt/ despalt)*c.h));
	}
}

function vermvid()
{
	var realach=$("#targetvid").width();
	var realalt=$("#targetvid").height();
	while($("#targetvid").width()>728)
	{
		
		$("#targetvid").css
		({
			"width": $("#targetvid").width()-1
		})
	}
	var despach=$("#targetvid").width();
	var despalt=$("#targetvid").height();
	jQuery(function($)
	{
		var jcrop_api, boundx, boundy;
		$('#targetvid').Jcrop(
		{
			onChange: updatePreview,
			onSelect: updatePreview,
			bgColor:'black',
			setSelect:[0,0,200,200],
			bgOpacity:.3,
			allowSelect:false,
			aspectRatio: 1,
			minSize: [200,200]
		}
		,function()
		{
			var bounds = this.getBounds();
			boundx = bounds[0];
			boundy = bounds[1];
			jcrop_api = this;
		});

		function updatePreview(c)
		{
			if (parseInt(c.w) > 0)
			{
				var rx = 200 / c.w;
				var ry = 200 / c.h;

				$('#previewvid').css(
				{
					width: Math.round(rx * boundx) + 'px',
					height: Math.round(ry * boundy) + 'px',
					marginLeft: '-' + Math.round(rx * c.x) + 'px',
					marginTop: '-' + Math.round(ry * c.y) + 'px'
				});
				setCoordsrev(c, realach, realalt, despach, despalt);
			}
		};
	});
	
	function setCoordsrev(c, realachp, realaltp, despachp, despaltp)
	{
		jQuery('#x1vid').val(parseInt((realach/despach)*c.x));
		jQuery('#y1vid').val(parseInt((realalt/despalt)*c.y));
		jQuery('#x2vid').val(c.x2);
		jQuery('#y2vid').val(c.y2);
		jQuery('#wvid').val(parseInt((realach/ despach)*c.w));
		jQuery('#hvid').val(parseInt((realalt/ despalt)*c.h));
	}
}

$(document).ready(function(){
	$('#cropimg').submit(function(event){
		$.ajax
		({
			async:true,
			type:'POST',
			url:$(this).attr('action'),
			data:$(this).serialize(),
			success: function(datos){
			$('.respcrop').html(datos);
			}
		})
		return false;
	})
})

$(document).ready(function(){
	$('#cropimgrev').submit(function(event){
		$.ajax
		({
			async:true,
			type:'POST',
			url:$(this).attr('action'),
			data:$(this).serialize(),
			success: function(datos){
			$('.respcrop').html(datos);
			}
		})
		return false;
	})
})

$(document).ready(function(){
	$('#cropimgimg').submit(function(event){
		$.ajax
		({
			async:true,
			type:'POST',
			url:$(this).attr('action'),
			data:$(this).serialize(),
			success: function(datos){
			$('.respcrop').html(datos);
			}
		})
		return false;
	})
})

$(document).ready(function(){
	$('#cropimgvid').submit(function(event){
		$.ajax
		({
			async:true,
			type:'POST',
			url:$(this).attr('action'),
			data:$(this).serialize(),
			success: function(datos){
			$('.respcrop').html(datos);
			}
		})
		return false;
	})
})

$(document).ready(function(){
	$('.demasformularios').submit(function(event){
		$.ajax
		({
			async:true,
			type:'POST',
			url:$(this).attr('action'),
			data:$(this).serialize(),
			success: function(datos){
				$(".respall").css("display", "block");
				$('.respall').html(datos);
			}
		})
		return false;
	})
})