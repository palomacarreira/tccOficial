function calculaTotal()
{
	alert("aquii");
	var hora = 0;
	var minuto = 0;
	var tempoTotal = 0;
	
	for( i = 1; i < 8; i++)
	{
		var horaEntrada = document.getElementById("horaEntrada" + i).innerText;
		var horaSaidaAlmoco = document.getElementById("horaSaidaAlmoco" + i).innerText;
		var horaVoltaAlmoco =document.getElementById("horaVoltaAlmoco" + i).innerText;	
		var horaSaida = document.getElementById("horaSaida" + i).innerText;
		
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
