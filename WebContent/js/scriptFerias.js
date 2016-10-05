
	function calculaValorFerias()
	{
		var valor = document.getElementById("valor").value;
		var mult =  (valor * 1/3);
		var total = parseFloat(valor) + parseFloat(mult);

		return total;
	}
		
	function calculaDiasVendidos()
	{
		var quantidade = document.getElementById("qtdDiasFerias").value;
		var calculo = quantidade * (1/3);
		
		return calculo;
	}
	
	function adicionaData()
	{
		var data = document.getElementById("dataInicio").value;
		var quantidadeDias = document.getElementById("qtdDiasFerias").value;
		var quantidadeDiasVendidos = document.getElementById("qtdDiasVendidos").value;
		var totalDias = parseInt(quantidadeDias) - parseInt(quantidadeDiasVendidos);
		
		var ano = data.substring(0, 4);
		var mes = data.substring(5, 7);
		var dia = data.substring(8, 10);

		var hoje        = new Date(ano + "/" + mes + "/" + dia);
		var dataVenc    = new Date(hoje.getTime() + (totalDias * 24 * 60 * 60 * 1000));
		
		dia = dataVenc.getDate();
		mes = (dataVenc.getMonth() + 1);
		ano = dataVenc.getFullYear();
			
		if(dia < 10){dia = "0" + dia;}
		if(mes < 10){mes = "0" + mes;}
		return ano+ "-"+ mes +"-" + dia;
		

	}
		

	