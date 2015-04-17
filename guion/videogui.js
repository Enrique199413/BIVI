var i=0;
var j=0;
var mod=0;
var nulos=0;
var boton;
var peticion;

function camValBotDerecha()
{
	
	if(ultimoeb.value==""||ultimoeb.value==esc1.value)
	{
		
	}
	else
	{
		$('.nodepag').removeClass('selectpagnum');
		for(i=1; i<=10; ++i)
		{
			if(i==1)
			{
				j = 1+parseInt(ultimoeb.value);
				if(j <= parseInt(esc1.value))
				{
					document.getElementById("primereb").value = 1+parseInt(ultimoeb.value);
				}
				else
				{
					document.getElementById("primereb").value = "";
				}
			}
			if(i>1&&i!=10)
			{
				j = i+parseInt(ultimoeb.value);
				if(j<=esc1.value)
				{
					document.getElementById("pag"+i+"").value = i+parseInt(ultimoeb.value);
				}
				else
				{
					document.getElementById("pag"+i+"").value = "";
				}
			}
			if(i==10)
			{
				j = 9+parseInt(primereb.value);
				if(j<=esc1.value)
				{
					document.getElementById("ultimoeb").value = i+parseInt(ultimoeb.value);
				}
				else
				{
					document.getElementById("ultimoeb").value = "";
				}
			}
		}
	}
}

function camValBotIzquierda()
{
	if(primereb.value!=1||ultimoeb.value!=10)
	{
		$('.nodepag').removeClass('selectpagnum');
		for(i=1; i<=10; ++i)
		{
				if(i==1)
				{
					document.getElementById("primereb").value = parseInt(primereb.value)-10;
					
				}
				if(i<10&&i!=1)
				{
					document.getElementById("pag"+i+"").value = parseInt(primereb.value)+i-1;
				}
				
				if(i==10)
				{
					document.getElementById("ultimoeb").value = parseInt(pag9.value)+1;
				}
		}
	}
}

function camValBotIzquierdaPrin()
{
	if(primereb.value!=1)
	{
		$('.nodepag').removeClass('selectpagnum');
	}
	if(10 <= parseInt(esc1.value))
	{
		for(i=1; i<=10; ++i)
		{
			if(i==1)
			{
				primereb.value=1;
			}
			if(i>1&&i!=10)
			{
				document.getElementById("pag"+i+"").value=i;
			}
			if(i==10)
			{
				ultimoeb.value=i;
			}
		}
	}
	else
	{
		for(i=1; i<=esc1; ++i)
		{
			if(i==1)
			{
				primereb.value=1;
			}
			if(i>1&&i<10)
			{
				document.getElementById("pag"+i+"").value=i;
			}
		}
	}
}

function camValBotDerechaFin()
{
	
	mod = esc1.value%10;
	if(esc1.value-mod+1!=primereb.value)
	{
		$('.nodepag').removeClass('selectpagnum');
	}
	
	if(mod>0 && esc1.value<=10)
	{
		for(i=1; i<=esc1.value; ++i)
		{
			if(i==1)
			{
				primereb.value=1;
			}
			if(i>1&&i<10)
			{
				document.getElementById("pag"+i+"").value=i;
			}
			if(i==10)
			{
				ultimoeb.value=10;
			}
		}
	}
	
	if(mod==0)
	{
		
		for(i=10; i>=1; --i)
		{
			if(i==10)
			{
				document.getElementById("ultimoeb").value = parseInt(esc1.value);
				
			}
			if(i<10&&i!=1)
			{
				document.getElementById("pag"+i+"").value = parseInt(esc1.value)-i;
			}
			
			if(i==1)
			{
				document.getElementById("primereb").value = parseInt(esc1.value)-9;
			}
		}
	}
	
	if(mod!=0)
	{	
		for(i=1; i<=mod; ++i)
		{
			if(i==1)
			{
				document.getElementById("primereb").value = parseInt(esc1.value-(mod-1));
			}
			
			if(i<10&&i>1)
			{
				document.getElementById("pag"+i+"").value = parseInt(esc1.value-mod+(i));
			}
		}
		
		for(i=mod+1; i<=10; ++i)
		{
			if(i==10)
			{
				document.getElementById("ultimoeb").value = "";
			}
			
			if(i<10&&i>1)
			{
				document.getElementById("pag"+i+"").value = "";
			}
		}
	}
}

function despArticulo(valbot, valid)
{
	$('.nodepag').removeClass('selectpagnum');
	$('#'+valid).addClass('selectpagnum');
	
	if (window.XMLHttpRequest)
	{
		// code for IE7+, Firefox, Chrome, Opera, Safari
		peticion=new XMLHttpRequest();
	}
	else
	{
		// code for IE6, IE5
		peticion=new ActiveXObject("Microsoft.XMLHTTP");
	}

	peticion.onreadystatechange=function()
	{
		
		if (peticion.readyState==4 && peticion.status==200)
		{
			document.getElementById("obras").innerHTML=peticion.responseText;
		}
	}
	
	peticion.open("POST","Desplegadoarticulovideo",true);
	peticion.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	peticion.send("pagina="+valbot);
}

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

function moPal(id, id2)
{
	if($('#'+id2).attr('value')=="")
	{
		var div=document.getElementById(id);
		div.style.display='none';
	}
	else
	{
		var div=document.getElementById(id);
		div.style.display='block';
	}
}