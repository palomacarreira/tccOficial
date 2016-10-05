	
   function validar()
	{	
		for (i = 1; i <= 31; i++){
			
			if($("#horaEntrada"+i).parent().hasClass('has-error') || 
				$("#horaSaidaAlmoco"+i).parent().hasClass('has-error') ||
				$("#horaVoltaAlmoco"+i).parent().hasClass('has-error') ||
				$("#horaSaida"+i).parent().hasClass('has-error'))
			{
				 swal({   title: "Cadastro Empregado",   
			  			text: "<span style=\"color:#F8BB86\">Verifique os campos com erros em vermelho na página.<span>",   
			  					html: true 
			  			});
				return false;
			}
		}
		
	}

	$(document).ready(function()
	{
			for(i = 1; i <= 31; i++)
			{
				
				$("#horaEntrada" + i).mask("99:99");
				$("#horaSaida" + i).mask("99:99");
				$("#horaSaidaAlmoco" + i).mask("99:99");
				$("#horaVoltaAlmoco" + i).mask("99:99");
				
				$("#horaEntrada"+ i).blur(function(e){ 
					var id = e.target.id.substring(11, 12);
					if(verificaCamposHoras($("#horaSaida" + id).val(), $(this).val(), $("#horaVoltaAlmoco" + id)) == false){
				    	$(this).parent().addClass('has-error');
					}
					else{
						$(this).parent().removeClass('has-error');
					}
				});
				
				$("#horaSaida"+ i).blur(function(e) { 
						var id = e.target.id.substring(9, 10);
						if(verificaCamposHoras($(this).val(), $("#horaEntrada" + id).val(), $("#horaVoltaAlmoco" + id).val()) == false){
						    $(this).parent().addClass('has-error');
						}
						else{
							$(this).parent().removeClass('has-error');
						}
				});
				
				
				var horaEntrada = $("#horaEntrada" + i).html();
				var horaSaidaAlmoco = $("#horaSaidaAlmoco" + i).html();
				var horaVoltaAlmoco = $("#horaVoltaAlmoco" + i).html();
				var horaSaida = $("#horaSaida" + i).html();
	
				var id = i;
				
				if(horaEntrada != undefined && horaEntrada != ''){
					$("#horasColuna"+ id).text(calculaColuna(id));
					$("#horasExtrasColuna"+ id).text(calculaHorasExtras(id));
					$("#horasExtrasNoturnaColuna"+ id).text(calculaHorasExtrasNoturno(id));
					calculaTotal(calculaColuna(id));
				}
				if(horaSaidaAlmoco != undefined && horaSaidaAlmoco != ''){
					$("#horasColuna"+ id).text(calculaColuna(id));
					$("#horasExtrasColuna"+ id).text(calculaHorasExtras(id));
					$("#horasExtrasNoturnaColuna"+ id).text(calculaHorasExtrasNoturno(id));
					calculaTotal(calculaColuna(id));
				}
				if(horaVoltaAlmoco != undefined && horaVoltaAlmoco != ''){
					$("#horasColuna"+ id).text(calculaColuna(id));
					$("#horasExtrasColuna"+ id).text(calculaHorasExtras(id));
					$("#horasExtrasNoturnaColuna"+ id).text(calculaHorasExtrasNoturno(id));
					calculaTotal(calculaColuna(id));
				}
				if(horaSaida != undefined && horaSaida != ''){
					$("#horasColuna"+ id).text(calculaColuna(id));
					$("#horasExtrasColuna"+ id).text(calculaHorasExtras(id));
					$("#horasExtrasNoturnaColuna"+ id).text(calculaHorasExtrasNoturno(id));
					calculaTotal(calculaColuna(id));
				}
				
			}			  
	});

	
	function verificaCamposHoras(horaSaida, horaEntrada, horaVoltaAlmoco)
	{
		var validacao = true;
		
		if(possuiValor(horaEntrada))
		{	
			if( preencheuHoraCompleta(horaEntrada) && isHoraValida(horaEntrada) == true ) 
			{
				if(possuiValor(horaSaida))
				{
					horainicio = parseInt(horaEntrada.substring(0, 2));
					horapausa = parseInt(horaSaida.substring(0, 2));
					minutoinicio = parseInt(horaEntrada.substring(3, 10));
					minutopausa = parseInt(horaSaida.substring(3, 10));
					horainicio = horainicio * 60 + minutoinicio; 
					horapausa = horapausa * 60 + minutopausa; 
					
					if(possuiValor(horaVoltaAlmoco)) // caso seja um dia de meio periodo
					{
						horaVolta = parseInt(horaVoltaAlmoco.substring(0, 2));
						minutoVolta = parseInt(horaVoltaAlmoco.substring(3, 10));
						horaVolta = horaVolta * 60 + minutoVolta;
						
						if (horapausa > horainicio)
						{
							if(horapausa > horaVolta)
							{
								validacao = true;
							}else{
								validacao = false;
							}
						}
					}
					
				}else{
					validacao = true;
				}
			}else{
				validacao = false;
			}
		}
		else
		{
			validacao = true;
		}
		return validacao;
		
	}

	
	// SABER QUAL É O DIA DA SEMANA - SEG, TERÇ, ..
	function diaDaSemana(dia, mes, ano)
	{
		var mydate = new Date(ano+"/"+mes+"/"+dia);
		var myday = mydate.getDay(); 
		return myday;
	}

	function calculaColuna(id)
	{
		var hora = 0;
		var minuto = 0;
		var tempoTotal = 0;
		var horaFinal = "00:00";
		
		var horaEntrada = document.getElementById("horaEntrada" + id).innerText;
		var horaSaidaAlmoco = document.getElementById("horaSaidaAlmoco" + id).innerText;
		var horaVoltaAlmoco =document.getElementById("horaVoltaAlmoco" + id).innerText;
		var horaSaida = document.getElementById("horaSaida" + id).innerText;
		
		horaFinal = horarios(horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, horaSaida); // chama método
	
		return horaFinal;
	}

//RETORNA O CALCULO DE UMA COLUNA DE HORARIOS
function horarios(horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, horaSaida){
	
	var horaFinal = "00:00";
	if(possuiValor(horaSaidaAlmoco) && possuiValor(horaVoltaAlmoco))
	{
		horainicio = parseInt(horaEntrada.substring(0, 2));
		horapausa = parseInt(horaSaidaAlmoco.substring(0, 2));
		horacontinuacao = parseInt(horaVoltaAlmoco.substring(0, 2));
		horafinal = parseInt(horaSaida.substring(0, 2));
		
		minutoinicio = parseInt(horaEntrada.substring(3, 10));
		minutopausa = parseInt(horaSaidaAlmoco.substring(3, 10));
		minutocontinuacao = parseInt(horaVoltaAlmoco.substring(3, 10));
		minutofinal = parseInt(horaSaida.substring(3, 10));
		
		
		// HORA NOTURNA 
		if(horainicio > horafinal && horafinal >= 0)
		{
			horafinal = horafinal + 24;
		}
		
		horainicio = horainicio * 60 + minutoinicio; 
		horapausa = horapausa * 60 + minutopausa; 
		horacontinuacao = horacontinuacao * 60 + minutocontinuacao; 
		horafinal = horafinal * 60 + minutofinal; 

		var vlhora = (horapausa - horainicio) + (horafinal - horacontinuacao);
		var hora = Math.floor(vlhora / 60);
		var minuto = vlhora % 60;
		horaFinal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto)
	}
	else
	{
		horainicio = parseInt(horaEntrada.substring(0, 2));
		horafinal = parseInt(horaSaida.substring(0, 2));
		minutoinicio = parseInt(horaEntrada.substring(3, 10));
		minutofinal = parseInt(horaSaida.substring(3, 10));
		
		
		// HORA NOTURNA 
		if(horainicio > horafinal && horafinal >= 0)
		{
			horafinal = horafinal + 24;
		}
		
		horainicio = horainicio * 60 + minutoinicio; 
		horafinal = horafinal * 60 + minutofinal; 
		var vlhora = (horafinal - horainicio);
		var hora = Math.floor(vlhora / 60);
		var minuto = vlhora % 60;
	
		horaFinal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto);
	}
	
	return horaFinal;
	
}



//Verifica se o usuário informou todos os campos da hora (hh:mm).
function preencheuHoraCompleta( horario ){
	var hora = horario.replace(/[^0-9:]/g ,''); // deixa só números e o ponto
	return hora.length == 5;
}

//Verifica se a hora é válidas. Exemplo: 12:34 é válido, 03:89 é inválido.
function isHoraValida( horario ) {
	hrs = horario.substring(0,2); 
  min = horario.substring(3,5); 
  
   
  if ((hrs < 00 ) || (hrs > 23) || ( min < 00) ||( min > 59)){ 
      return false;
  } 
return true;
}

//Verifica se um campo está vazio
function possuiValor( valor ){
	return valor != undefined && valor != '';
}

//Completa um número menor que dez com um zero à esquerda.
// Usado aqui para formatar as horas... Exemplo: 3:10 -> 03:10 , 10:5 -> 10:05
function completaZeroEsquerda( numero ){
	return ( numero < 10 ? "0" + numero : numero);
}
