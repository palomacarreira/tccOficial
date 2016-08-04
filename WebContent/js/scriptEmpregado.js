validacao = ""; //// VARIAVEL GLOBAL PARA VALIDAÇÃO DOS CAMPOS
//Verifica se há erros na página
function validar(){ 

	if(validacao == false) 
	{
		alert("Verifique os campos com erros em vermelho na página.");
		return false;
	}
	return true;
}

// Evento para ao selecionar a foto, ela seja mostrada 
function onFileSelected(event) {
	  var selectedFile = event.target.files[0];
	  var reader = new FileReader();

	  var imgtag = document.getElementById("myimage");
	  imgtag.title = selectedFile.name;

	  reader.onload = function(event) {
	    imgtag.src = event.target.result;
	  };

	  reader.readAsDataURL(selectedFile);
	}


$(document).ready(function(){
	
	// ação ao clicar nas imagens(botões) de preencher campos horários
	$(".imgPreenche").click(function() {
        preencheCampos(this.id);
    });
	
	$("#cpf").mask("999.999.999-99");
	$("#dataNasc").mask("99/99/9999");
	$("#dataAdmissao").mask("99/99/9999");
	$("#cnpj").mask("99.999.999/9999-99");
	$("#rg").mask("99.999.999-*");
	$("#cep").mask("99.999-999");
	$("#numeroTelefone").mask("(99)9999-9999");
	
	for ( i = 1; i < 8; i++ ) {
		$("#horaEntrada" + i).mask("99:99");
		$("#horaSaida" + i).mask("99:99");
		$("#horaSaidaAlmoco" + i).mask("99:99");
		$("#horaVoltaAlmoco" + i).mask("99:99");
	}
		
	
	// Ao modificar um campo hora, recalcula o horário total(SEGUNDA COLUNA EM DIANTE)
	// Em caso de dia de meio período
	for ( i = 1; i < 8; i++ ) {
		$("#horaEntrada" + i).blur(function() {calculaHoras();});
		$("#horaSaidaAlmoco" + i).blur(function() {calculaHoras();});
		$("#horaVoltaAlmoco" + i).blur(function() {calculaHoras();});
		$("#horaSaida" + i).blur(function() {calculaHoras();});
	}
	
	verificaCamposHoras(); // CHAMA MÉTODO DE VERIFICAÇÃO 
});

//ação para o evento onblur das horas: verificar se o horário digitado é válido e refazer os cálculos
function verificaCamposHoras(){

	for ( i = 1; i < 8; i++ ) {
		$("#horaEntrada"+ i).blur(function() { 
			if( preencheuHoraCompleta($(this).val()) && ! isHoraValida($(this).val()) ) {
				$("#saldoHoras").html('Horários Inválidos');
		    	$(this).parent().addClass('has-error');
		    	validacao = false;
			}
			else{
				$("#saldoHoras").html('Horários Inválidos');
				$(this).parent().removeClass('has-error');
				validacao = true;
			}
		});
		$("#horaSaidaAlmoco"+ i).blur(function() { 
			if( preencheuHoraCompleta($(this).val()) && ! isHoraValida($(this).val()) ) {
				$("#saldoHoras").html('Horários Inválidos');
		    	$(this).parent().addClass('has-error');
		    	validacao = false;
			}
			else{
				$("#saldoHoras").html('Horários Inválidos');
				$(this).parent().removeClass('has-error');
				validacao = true;
			}
		});
		$("#horaVoltaAlmoco"+ i).blur(function() { 
			if( preencheuHoraCompleta($(this).val()) && ! isHoraValida($(this).val()) ) {
				$("#saldoHoras").html('Horários Inválidos');
		    	$(this).parent().addClass('has-error');
		    	validacao = false;
			}
			else{
				$("#saldoHoras").html('Horários Inválidos');
				$(this).parent().removeClass('has-error');
				validacao = true;
			}
		});
		$("#horaSaida"+ i).blur(function() { 
			if( preencheuHoraCompleta($(this).val()) && ! isHoraValida($(this).val()) ) {
				$("#saldoHoras").html('Horários Inválidos');
		    	$(this).parent().addClass('has-error');
		    	validacao = false;
			}
			else{
				$("#saldoHoras").html('Horários Inválidos');
				$(this).parent().removeClass('has-error');
				validacao = true;
			}
		});
	}
}


//Função chamada quando uma opção regime de trabalho é selecionada
function doalert(checkboxElem) {
    if (checkboxElem.checked) {
    	calculaHoras();
    } else {
    	calculaHoras();
    }
  }
 
 
//Função chamada quando um checkbox de dias de folga é clicado 
//Obrigatório pelo menos 1 dia
function alertaDiaFolga(checkboxElem){
	var cont = 0;
	var x = checkboxElem.id;
	var id = x.substring(8,9); // NÚMERO CHECKBOX

    if (checkboxElem.checked) { // se for selecionado
    	
   			document.getElementById("horaSaida" + id).disabled=true;
			document.getElementById("horaEntrada"+ id).disabled=true;
    		document.getElementById("horaSaidaAlmoco"+ id).disabled=true;
    		document.getElementById("horaVoltaAlmoco"+ id).disabled=true;
    		document.getElementById("diaMeioPeriodo" + id).disabled=true; // DESABILITA CHECKBOX MEIO PERIODO
    		
    		document.getElementById("horaSaida" + id).value='';
			document.getElementById("horaEntrada"+ id).value='';
    		document.getElementById("horaSaidaAlmoco"+ id).value='';
    		document.getElementById("horaVoltaAlmoco"+ id).value='';
    		
    		calculaHoras();
    }
	else 
	{
	//Verifica se há pelo menos 1 dia de folga selecionado
  	for ( i = 1; i < 8; i++ ) {
		    checkBox = document.getElementById("diaFolga" + i);    
		    if (  checkBox.checked == true ) {
		    	cont = 1;
		    	calculaHoras();
		    }	    
		}
  	if(cont != 0)
  	{
  		document.getElementById("horaSaida" + id).disabled=false;
		document.getElementById("horaEntrada"+ id).disabled=false;
		document.getElementById("horaSaidaAlmoco"+ id).disabled=false;
		document.getElementById("horaVoltaAlmoco"+ id).disabled=false;
		document.getElementById("diaMeioPeriodo" + id).disabled=false; // HABILITA CHECKBOX MEIO PERIODO
		
  	}
  	else
  	{
  		alert("A recomendação é que haja um descanso semanal combinado pelo menos uma vez por semana.");
   		checkboxElem.checked = true; 
  	}
  }
}

//Função chamada quando um checkbox de meio período é clicado 
function alertaMeioPeriodo(checkboxElem) {
	
		var x = checkboxElem.id;
		var id = x.substring(14,15); // NÚMERO CHECKBOX
		var idSaidaAlmoco = "horaSaidaAlmoco" + id;
    	var idVoltaAlmoco = "horaVoltaAlmoco" + id;
		
    	if (checkboxElem.checked) { // se for selecionado

    		document.getElementById(idSaidaAlmoco).value='';
    		document.getElementById(idVoltaAlmoco).value='';
    		document.getElementById(idSaidaAlmoco).disabled=true;
    		document.getElementById(idVoltaAlmoco).disabled=true;
    		document.getElementById("diaFolga" + id).disabled=true; // DESABILITA CHECKBOX DIA FOLGA
    
    	} 
    	else // se for tirada a seleção
    	{
    		document.getElementById(idSaidaAlmoco).disabled=false;
    		document.getElementById(idVoltaAlmoco).disabled=false;	
    		document.getElementById("diaFolga" + id).disabled=false; // HABILITA CHECKBOX DIA FOLGA
 		}
    	calculaHoras();
}


//RETORNA O CALCULO DE UMA COLUNA DE HORARIOS
function horarios(id, horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, horaSaida){

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

	if (horapausa < horainicio){
		$("#saldoHoras"+ id).html('Horários Inválidos');
		$("#horaEntrada"+ id).parent().addClass('has-error');
		$("#horaSaidaAlmoco"+ id).parent().addClass('has-error');
		validacao = false;
	}
	else if (horacontinuacao < horapausa){
		$("#saldoHoras"+ id).html('Horários Inválidos');
		$("#horaSaidaAlmoco"+ id).parent().addClass('has-error');
		$("#horaVoltaAlmoco"+ id).parent().addClass('has-error');
		validacao = false;
	}
	else if (horafinal < horacontinuacao){
		$("#saldoHoras"+ id).html('Horários Inválidos');
		$("#horaSaida"+ id).parent().addClass('has-error');
		$("#horaVoltaAlmoco"+ id).parent().addClass('has-error');
		validacao = false;
	}
	else{
		$("#horaVoltaAlmoco"+ id).parent().removeClass('has-error');
		$("#horaSaida" + id).parent().removeClass('has-error');
		$("#horaEntrada" + id).parent().removeClass('has-error');
		$("#horaSaidaAlmoco"+ id).parent().removeClass('has-error');
		validacao = true;
	}

	var vlhora = (horapausa - horainicio) + (horafinal - horacontinuacao);
	var hora = Math.floor(vlhora / 60);
	var minuto = vlhora % 60;
	horaFinal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto)
	return horaFinal;
	
}

// Realiza o cálculo das horas
function calculaHoras(){

	var hora = 0;
	var minuto = 0;
	$("#saldoHoras").html('');
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
			horaFinal = horarios(i, horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, horaSaida); // chama método
			hora = parseInt(horaFinal.substring(0, 2));
			minuto = parseInt(horaFinal.substring(3, 10));
			tempoTotal += hora * 60 + minuto; 
		}
		else{
			$("#saldoHoras").html("Preencha uma coluna ou todas, para ver o total de horas!");
		}
	}
	
	hora = Math.floor(tempoTotal / 60);
	minuto = tempoTotal % 60;
	horaTotal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto);
	$("#totalHoras").html(horaTotal); 
	verificaLimite(horaTotal);// chama método
}



//Verificando se passou do limite determinado pelo Regime de Trabalho
function verificaLimite(horaFinal){
	var x = parseInt(horaFinal.substring(0,3));
	if(x == NaN)
	{
		x = parseInt(horaFinal.substring(0,2))
	}
	if($("#regime44").attr("checked") == true)
	{
   		if(x >= 45){
		$("#saldoHoras").html("Total da semana deve ser menor do que 44 horas, por favor ajuste os horários");
   		validacao = false;
		}
   		else{validacao = true;}
	}
	else if($("#regime25").attr("checked")== true)
	{
		if(x >= 26){
		$("#saldoHoras").html("Total da semana deve ser menor do que 25 horas, por favor ajuste os horários");
		validacao = false;
		}
		else{validacao = true;}
	}
}

//coloca o valor dos campos da primeira coluna , na coluna que foi desabilitada 
function preencheCampos(id)
{
	if(id == 1)
	{
		for(i = 2; i < 8; i++)
		{
			if($("#diaMeioPeriodo" + i).attr("checked")== false)
			{
				if($("#diaFolga" + i).attr("checked") == false)
				{
					$("#horaEntrada"+i).val($("#horaEntrada1").val());
				}
			}
		}
	}
	else if(id == 2)
	{
		for(i = 2; i < 8; i++)
		{
			if($("#diaMeioPeriodo" + i).attr("checked")== false)
			{
				if($("#diaFolga" + i).attr("checked") == false)
				{
					$("#horaSaidaAlmoco"+i).val($("#horaSaidaAlmoco1").val());
				}		
			}
		}
	}
	else if(id == 3)
	{
		for(i = 2; i < 8; i++)
		{
			if($("#diaMeioPeriodo" + i).attr("checked")== false)
			{
				if($("#diaFolga" + i).attr("checked") == false)
				{
					$("#horaVoltaAlmoco" + i).val($("#horaVoltaAlmoco1").val());
				}
			}
		}
	}
	else if(id == 4)
	{
		for(i = 2; i < 8; i++)
		{
			if($("#diaMeioPeriodo" + i).attr("checked")== false)
			{
				if($("#diaFolga" + i).attr("checked") == false)
				{
					$("#horaSaida"+i).val($("#horaSaida1").val());
				}
			}
		}
	}
	calculaHoras();
}

// Completa um número menor que dez com um zero à esquerda.
// Usado aqui para formatar as horas... Exemplo: 3:10 -> 03:10 , 10:5 -> 10:05
function completaZeroEsquerda( numero ){
	return ( numero < 10 ? "0" + numero : numero);
}

//Verifica se o usuário informou todos os campos da hora (hh:mm).
function preencheuHoraCompleta( horario ){
	var hora = horario.replace(/[^0-9:]/g ,''); // deixa só números e o ponto
	return hora.length == 5;
}

// Verifica se a hora é válidas. Exemplo: 12:34 é válido, 03:89 é inválido.
function isHoraValida( horario ) {
	var regex = new RegExp("^([0-1][0-9]|[2][0-3]):([0-5][0-9])$");
	return regex.exec( horario ) != null;
}

// Verifica se um campo está vazio
function possuiValor( valor ){
	return valor != undefined && valor != '';
}

