function validar(){	

	var quantidade = 0;
	var form = document.getElementById("dadosPonto");
	for(t = 0; t < form.length; t++)
	{
		resul = form.elements[t].className;
		if(resul == "hora form-control")
		{
			quantidade ++;
		}
	}
	
	// Ao modificar um campo hora, recalcula o horário total(SEGUNDA COLUNA EM DIANTE)
	// Em caso de dia de meio período
	
	for ( i = 1; i <= quantidade; i++ ) 
	{
		if($("#horaEntrada"+ i).parent().hasClass('has-error')){
			   swal({   title: "Cadastro Ponto",   
		  			text: "<span style=\"color:#F8BB86\">Verifique os campos com erros em vermelho na página.<span>",   
		  					html: true 
		  		});
	       		return false;
		}
		
		if($("#horaSaidaAlmoco"+ i).parent().hasClass('has-error')){
			 swal({   title: "Cadastro Ponto",   
		  			text: "<span style=\"color:#F8BB86\">Verifique os campos com erros em vermelho na página.<span>",   
		  					html: true 
		  		});
	       		return false;
		}
		
		if($("#horaVoltaAlmoco"+ i).parent().hasClass('has-error')){
			 swal({   title: "Cadastro Ponto",   
		  			text: "<span style=\"color:#F8BB86\">Verifique os campos com erros em vermelho na página.<span>",   
		  					html: true 
		  		});
	       		return false;
		}
		
		if($("#horaSaida"+ i).parent().hasClass('has-error'))
		{
			 swal({   title: "Cadastro Ponto",   
		  			text: "<span style=\"color:#F8BB86\">Verifique os campos com erros em vermelho na página.<span>",   
		  					html: true 
		  		});
	       		return false;
		}
	
	}		

  return true;
}
	
$(document).ready(function(){

		$("#dadosPonto input[type=text]").mask("99:99");
		
		// VERIFICA QUANTOS INPUTS TEM EM UMA COLUNA DA TABELA
		var quantidade = 0;
		var form = document.getElementById("dadosPonto");

		for(t = 0; t < form.length; t++)
		{
			resul = form.elements[t].className;
			if(resul == "hora form-control")
			{
				quantidade ++;
			}
		}
		
		// Ao modificar um campo hora, recalcula o horário total(SEGUNDA COLUNA EM DIANTE)
		// Em caso de dia de meio período
		
		for ( i = 1; i <= quantidade; i++ ) 
		{
			$("#horaEntrada"+ i).blur(function(e) { 
				var id = e.target.id.substring(11, 12);
				if(verificaCamposHoras($(this).val(), $("#horaSaida" + id).val()) == false){
			    	$(this).parent().addClass('has-error');
				}
				else{
					if(verificaCamposHoras($(this).val(),$("#horaSaidaAlmoco" + id).val()) == false){
				    	$(this).parent().addClass('has-error');
					}
					else{
						$(this).parent().removeClass('has-error');
						$("#horasColuna"+ id).text(calculaColuna(id)); // COLONA O VALOR TOTAL DA COLUNA
						calculaTotal(); // COLOCA O VALOR TOTAL 
					}
				}
			});
			
			$("#horaSaidaAlmoco"+ i).blur(function(e) { 
				var id = e.target.id.substring(15, 16);
				if(verificaCamposHoras($("#horaEntrada" + id).val(), $(this).val()) == false){
			    	$(this).parent().addClass('has-error');
				}
				else{
					if(verificaCamposHoras($(this).val(),$("#horaVoltaAlmoco" + id).val()) == false){
				    	$(this).parent().addClass('has-error');
					}
					else{
						$(this).parent().removeClass('has-error');
						$("#horasColuna"+ id).text(calculaColuna(id)); // COLONA O VALOR TOTAL DA COLUNA
						calculaTotal(); // COLOCA O VALOR TOTAL 
					}
				}
			});
			
			$("#horaVoltaAlmoco"+ i).blur(function(e) { 
				var id = e.target.id.substring(15, 16);
				if(verificaCamposHoras($("#horaSaidaAlmoco" + id).val(),$(this).val()) == false){
			    	$(this).parent().addClass('has-error');
				}
				else{
					if(verificaCamposHoras($(this).val(),$("#horaSaida" + id).val()) == false){
				    	$(this).parent().addClass('has-error');
					}
					else{
						$(this).parent().removeClass('has-error');
						$("#horasColuna"+ id).text(calculaColuna(id)); // COLONA O VALOR TOTAL DA COLUNA
						calculaTotal(); // COLOCA O VALOR TOTAL 
					}
				}
			});
			
			$("#horaSaida"+ i).blur(function(e) { 
				var id = e.target.id.substring(9, 10);			
				if(verificaCamposHoras($("#horaVoltaAlmoco" + id).val(), $(this).val()) == false){
			    	$(this).parent().addClass('has-error');
				}
				else if(verificaCamposHoras($("#horaEntrada" + id).val(), $(this).val()) == false){
			    	$(this).parent().addClass('has-error');
				}
				else if(verificaCamposHoras($("#horaSaida" + id).val(), $(this).val()) == false){
			    	$(this).parent().addClass('has-error');
				}
				else{
					$(this).parent().removeClass('has-error');
					$("#horasColuna"+ id).text(calculaColuna(id)); // COLONA O VALOR TOTAL DA COLUNA
					calculaTotal(); // COLOCA O VALOR TOTAL 
				}
			});
		}		
});

function verificaCamposHoras(horaEntrada, horaSaida){
	
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
					
				if (horapausa < horainicio){
					validacao = false;
				}else{
					validacao = true;
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

function calculaColuna(id)
{
	var hora = 0;
	var minuto = 0;
	var tempoTotal = 0;
	var horaFinal = "00:00";
	
	var horaEntrada = document.getElementById("horaEntrada" + id).value;
	var horaSaidaAlmoco = document.getElementById("horaSaidaAlmoco" + id).value;
	var horaVoltaAlmoco =document.getElementById("horaVoltaAlmoco" + id).value;	
	var horaSaida = document.getElementById("horaSaida" + id).value;
	
	if(possuiValor(horaEntrada) && possuiValor(horaSaidaAlmoco) && 
				possuiValor(horaVoltaAlmoco) && possuiValor(horaSaida))
	{
		horaFinal = horarios(horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, horaSaida); // chama método
	}
	
	return horaFinal;
}

function calculaTotal()
{
	var hora = 0;
	var minuto = 0;
	var tempoTotal = 0;
	
	for( i = 1; i < 8; i++)
	{
		var horaEntrada = document.getElementById("horaEntrada" + i).value;
		var horaSaidaAlmoco = document.getElementById("horaSaidaAlmoco" + i).value;
		var horaVoltaAlmoco =document.getElementById("horaVoltaAlmoco" + i).value;	
		var horaSaida = document.getElementById("horaSaida" + i).value;
		
		if(possuiValor(horaEntrada) && possuiValor(horaSaidaAlmoco) && 
				possuiValor(horaVoltaAlmoco) && possuiValor(horaSaida))
		{
			horaFinal = horarios(horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, horaSaida); // chama método
			hora = parseInt(horaFinal.substring(0, 2));
			minuto = parseInt(horaFinal.substring(3, 10));
			tempoTotal += hora * 60 + minuto; 
		}
	}
	
	hora = Math.floor(tempoTotal / 60);
	minuto = tempoTotal % 60;
	horaTotal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto);
	$("#totalDeHoras").text(horaTotal);
}

//RETORNA O CALCULO DE UMA COLUNA DE HORARIOS
function horarios(horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, horaSaida){

	horainicio = parseInt(horaEntrada.substring(0, 2));
	horapausa = parseInt(horaSaidaAlmoco.substring(0, 2));
	horacontinuacao = parseInt(horaVoltaAlmoco.substring(0, 2));
	horafinal = parseInt(horaSaida.substring(0, 2));
	
	minutoinicio = parseInt(horaEntrada.substring(3, 10));
	minutopausa = parseInt(horaSaidaAlmoco.substring(3, 10));
	minutocontinuacao = parseInt(horaVoltaAlmoco.substring(3, 10));
	minutofinal = parseInt(horaSaida.substring(3, 10));
	
	horainicio = horainicio * 60 + minutoinicio; 
	horapausa = horapausa * 60 + minutopausa; 
	horacontinuacao = horacontinuacao * 60 + minutocontinuacao; 
	horafinal = horafinal * 60 + minutofinal; 

	var vlhora = (horapausa - horainicio) + (horafinal - horacontinuacao);
	var hora = Math.floor(vlhora / 60);
	var minuto = vlhora % 60;
	horaFinal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto)
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
