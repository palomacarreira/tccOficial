package model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CalculosPagamento {

	EspecialistaContrato contrato = new EspecialistaContrato();
	EspecialistaPonto ponto = new EspecialistaPonto();
	EspecialistaHorasExtras horasExtras = new EspecialistaHorasExtras();
	EspecialistaJornadaTrabalho jornadaTrabalho = new EspecialistaJornadaTrabalho();
	EspecialistaEmpregado empregado = new EspecialistaEmpregado();
	EspecialistaFerias espFerias = new EspecialistaFerias();
	SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
	
	public double totalHoraExtra(String codigoEmpregado,int mesEscolhido, int anoEscolhido, int totalDias)
	{
		ContratoTO contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
		ArrayList<PontoTO> listaDePonto = ponto.pesquisar(codigoEmpregado);
			
		// CALCULAR TOTAL HORA EXTRA
		 CalculosHoraExtra calculos = new CalculosHoraExtra();
		 String qtdHorasSemanais = contratoTO.getDuracaoSemanal();
		 double salario = contratoTO.getSalarioBase();
		 
		 String qtdHorasMensais = calculos.qtdHorasMensais(totalDias, qtdHorasSemanais);
		 
		 HoraExtraTO horaExtraTO = new HoraExtraTO();
		 double valorHoraExtra = 0.0;
		 String acaoSelecionada = "";
		 
		 String somaHoras = "";
		 if(listaDePonto != null)
		 {
			 for(PontoTO pontoTO : listaDePonto)
			 {	 
				String dataPonto = formatador.format(pontoTO.getDataPonto());
				int diaPonto = Integer.parseInt(dataPonto.substring(8,10));
				int mesPonto = Integer.parseInt(dataPonto.substring(5,7));
				int anoPonto = Integer.parseInt(dataPonto.substring(0,4));
				if((mesPonto == mesEscolhido) && (anoPonto == anoEscolhido))
				{
					horaExtraTO = horasExtras.pesquisarPorPonto(pontoTO.getCodigo());
					String totalHorasExtras = horaExtraTO.getTotalDeHorasExtras();
					String totalHorasExtrasNoturno =  horaExtraTO.getTotalDeHorasExtrasNoturno();
					int diaDaSemana = diaPonto;
					acaoSelecionada = pontoTO.getAcao();
					 
					if(contratoTO.getCompensacaoDias().equals("salario"))
					{ 
						valorHoraExtra += calculos.calcularValorHoras(salario, qtdHorasMensais, totalHorasExtras, totalHorasExtrasNoturno, diaDaSemana, acaoSelecionada);						
					}
					else
					{
						// em caso de compensação de horas
						// pagar a mais se no fim do mês tiver horas sobrando
						// descontar em caso de horas negativas
						somaHoras = calculos.somaHoras(somaHoras, totalHorasExtras);
					}
				}
			 }
		 }
		 
		 if(!somaHoras.equals(""))
		 {
			 int horas = Integer.parseInt(qtdHorasMensais.substring(0,2));
			 double valorHoraDiurna = salario / horas;
			 
			 if(somaHoras.substring(0,1).equals("-")){
				 int hE = Integer.parseInt(somaHoras.substring(1,3));
				 valorHoraExtra = -(valorHoraDiurna  *  hE);
			 }else{
				 int hE = Integer.parseInt(somaHoras.substring(0,2));
				 valorHoraExtra = (valorHoraDiurna  *  hE);
			 }
		 }
		 
		 return valorHoraExtra;
	}
	
	public double valorDSR(double valorHoraExtra)
	{
		int diasUteis = 30;
		int diasDom = 4; 
		double dsr = valorHoraExtra / diasUteis * diasDom;
		
		return dsr;
	}
	
	public double totalINSS(double salario){
		// CALCULAR INSS EMPREGADO
		double inss = 0.0;
		if(salario <= 1556.94){ inss = salario * 0.08;}
		else if(salario >= 1556.95 && salario <= 2594.92){ inss = salario * 0.09;}
		else if(salario >= 2594.93 && salario <= 5189.82){ inss = salario * 0.11;}
		else if(salario > 5189.82){inss = 570.88;} // teto de contribuição inss 2016
		
		return inss;
	}
	
	public double totalFGTS(double salario){
		// CALCULAR FGTS
		double fgts = salario * 0.08;
		return fgts;
	}
	
	public double totalIRRF(double salario, String codigoEmpregado, double inss){
		// CALCULAR IRRF
		EmpregadoTO empregadoTO = empregado.pesquisar(codigoEmpregado);
		int numeroDependentes = empregadoTO.getQtdDependentes();
		double valorPorDependente = numeroDependentes * 189.59;
		
		double valorBaseIR = (salario - inss);
		valorBaseIR = (valorBaseIR - valorPorDependente);
		double parcelaDeduzir = 0.0;
		double irrf = 0.0;
		
		if(valorBaseIR <= 1903.98){ 
			irrf = valorBaseIR;
			parcelaDeduzir = 0.0;
		}
		else if(valorBaseIR >= 1903.99 && valorBaseIR <= 2826.65){ 
			irrf = valorBaseIR * 0.075;
			parcelaDeduzir = 142.80;
		}
		else if(valorBaseIR >= 2826.66 && valorBaseIR <= 3715.05){ 
			irrf = valorBaseIR * 0.15;
			parcelaDeduzir = 354.80;
		}
		else if(valorBaseIR >= 3751.06 && valorBaseIR <= 4664.68){ 
			irrf = valorBaseIR * 0.225;
			parcelaDeduzir = 636.13;
		}
		else if(valorBaseIR > 4664.68){ 
			irrf = valorBaseIR * 0.275;
			parcelaDeduzir = 869.36;
		}

		irrf = irrf - parcelaDeduzir;
		
		return irrf;
	}
	
	public double totalValeTransporte(double salario, String codigoEmpregado)
	{
		// CALCULAR DESCONTO VALE TRANSPORTE
		ContratoTO contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
		double valeTransporte = contratoTO.getValeTransporte();
		double parteSalario = salario * 0.06; // 6% DO SALÁRIO
		if(valeTransporte > parteSalario)
		{
			valeTransporte = parteSalario;
		}
		
		return valeTransporte;
	}
	
	public double totalSalario(String codigoEmpregado, double salario, double valeTransporte, 
			double inss, double irrf, double valorHoraExtra, double folgas)
	{
		// CALCULAR SALÁRIO // SE TIVER HORAS NEGATIVAS DESCONTA
		ContratoTO contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
		double salarioLiquido = 0;
		double descontos = contratoTO.getDescontoBeneficios() + valeTransporte + inss + irrf + folgas;
		double dsr = valorDSR(valorHoraExtra); 
		salarioLiquido = (salario - descontos) + valorHoraExtra + dsr;

		return salarioLiquido;
	}
	
	public int quantidadeDeFolgas(String codigoEmpregado)
	{
		ArrayList<PontoTO> lista = ponto.pesquisar(codigoEmpregado);
		HoraExtraTO horaExtraTO = null;
		int quantidadeFolgasCompensatorias = 0;
		int quantidadeFolgas = 0;
		if(lista != null)
		{
			 for(PontoTO pontoTO : lista)
			 {	 
				horaExtraTO = horasExtras.pesquisarPorPonto(pontoTO.getCodigo());
				if(horaExtraTO.getFolgaCompensatoria()){
					quantidadeFolgasCompensatorias += 1;
				}
				if(pontoTO.getAcao().equals("Folga")){
					quantidadeFolgas += 1;
				}
			 }
		}
		
		int totalFolgas = quantidadeFolgas - quantidadeFolgasCompensatorias;
		return totalFolgas;
	}
	
	public double totalFolgas(String codigoEmpregado)
	{
		ContratoTO contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
		double salario = contratoTO.getSalarioBase();
		int totalFolgas = quantidadeDeFolgas(codigoEmpregado);
		
		double valorFolgas= 0.0;
		if(totalFolgas > 0){
			//Para calcular o valor basta dividir o salário registrado na carteira profissional 
			//por 30 e multiplicar pelo número de faltas.	
			valorFolgas = (salario / 30) * totalFolgas;
		}
		return valorFolgas;
	
	}
	
	public int diasNoMes(int mesEscolhido, int anoEscolhido)
	{
		
	 	java.util.Date date = null;
        String data = 1 +"/"+mesEscolhido+"/"+anoEscolhido;
        String formato = "dd/MM/yyyy";
        SimpleDateFormat df = new SimpleDateFormat(formato);
		try {
			date = df.parse(data);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
	 	int ultimoDia = c.getActualMaximum (Calendar.DAY_OF_MONTH);
	 	
	 	return ultimoDia;
	 	
	}
	
	public double calculaValorFerias(double salario)
	{
		return salario + (salario * 1/3);
	}
	
	public double calculaFeriasProporcionais(String codigoEmpregado, double salario, int mesDemissao, int anoDemissao)
	{
		ContratoTO contratoTO = contrato.pesquisarEmpregado(codigoEmpregado); 
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		String dataAdmissao = formatador.format(contratoTO.getDataAdmissao());
		int mesAdmissao = Integer.parseInt(dataAdmissao.substring(5,7));
		
		Calendar atual = Calendar.getInstance();
		Calendar ferias = Calendar.getInstance();
		ferias.set(Calendar.YEAR, anoDemissao - 1);
		ferias.set(Calendar.MONTH, mesAdmissao - 1);
		//ferias.set(Calendar.DAY_OF_MONTH, 01);
		atual.set(Calendar.YEAR, anoDemissao);
		atual.set(Calendar.MONTH, mesDemissao - 1);
		//atual.set(Calendar.DAY_OF_MONTH, 01);
		
		//int diaDiferença = ferias.get(Calendar.DAY_OF_MONTH) - atual.get(Calendar.DAY_OF_MONTH);
		int mesDiferença = (ferias.get(Calendar.MONTH) - atual.get(Calendar.DAY_OF_MONTH)) + 1;
		int anoDiferença = ferias.get(Calendar.YEAR) - atual.get(Calendar.YEAR);
		int total = anoDiferença + mesDiferença;
		
		int mesesTrabalhados = total;

		return salario / 12 * mesesTrabalhados;
	}
	
	public int calculaDiasFerias(String codigoEmpregado, int periodoInicioAno, int periodoInicioMes,
																	int periodoFimAno, int periodoFimMes)
	{
		ContratoTO cont = contrato.pesquisarEmpregado(codigoEmpregado);
		ArrayList<PontoTO> listaDePonto = ponto.pesquisar(cont.getCodigo());
		int diasDeFerias = 0;
		int qtdFolgas = 0;
		if(listaDePonto != null)
		{
			for(PontoTO pontoTO: listaDePonto)
			{
				SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
				String dataPonto = formatador.format(pontoTO.getDataPonto());
				int pontoDataMes = Integer.parseInt(dataPonto.substring(5,7));
				int pontoDataAno = Integer.parseInt(dataPonto.substring(0,4));	  
				
				// verificando os pontos dentro do periodo selecionado		
				if( pontoDataAno == periodoInicioAno || pontoDataAno == periodoFimAno)
				{
					if(pontoDataMes >= periodoInicioMes && pontoDataMes <= periodoFimMes)
					{
						if(pontoTO.getAcao().equals("Falta"))
						{
							qtdFolgas += 1;
						}
					}
				}
			}
	
			String regimeTrabalho = cont.getRegimeDeTrabalho();		
			String duracaoSemanal = cont.getDuracaoSemanal();	
			
			if(regimeTrabalho.equals("Tempo Parcial")){ // CALCULO REGIME DE TEMPO PARCIAL
			int totalSemana = Integer.parseInt((duracaoSemanal.substring(0,2)));
			if(totalSemana > 22)
			{
				if(qtdFolgas > 7){
					diasDeFerias = 9;
				}else{ 
					diasDeFerias = 18;
				}
				}else if(totalSemana > 20 && totalSemana <=22){
					if(qtdFolgas > 7){
						diasDeFerias = 8;
					}else{ 
						diasDeFerias = 16;
					}
				}else if(totalSemana > 15 && totalSemana <=20){
					if(qtdFolgas > 7){
						diasDeFerias = 7;
					}else{ 
						diasDeFerias = 14;
					}
				}else if(totalSemana > 10 && totalSemana <=15){
					if(qtdFolgas > 7){
						diasDeFerias = 6;
					}else{ 
						diasDeFerias = 12;
					}
				}else if(totalSemana > 5 && totalSemana <=10){
					if(qtdFolgas > 7){
						diasDeFerias = 5;
					}else{ 
						diasDeFerias = 10;
					}
				}else if(totalSemana <= 5){
					if(qtdFolgas > 7){
						diasDeFerias = 4;
					}else{ 
						diasDeFerias = 8;
					}
				}
						
			}else{ // CALCULO REGIME DE TEMPO INTEGRAL
				diasDeFerias = 30;
				if(qtdFolgas > 5){
					if(qtdFolgas >= 6 && qtdFolgas <=14){
						diasDeFerias = 24;
					}else if(qtdFolgas >= 15 && qtdFolgas <=23){
						diasDeFerias = 18;
					}else if(qtdFolgas >= 24 && qtdFolgas <=32){
						diasDeFerias = 12;
					}else{
						diasDeFerias = 0;
					}
				}	
			}
		}
		else
		{
			return -1;
		}
		return diasDeFerias;
	}
	
	public double calculaDecimoTerceiro(String codigoEmpregado, int mes, int ano, double salario, double valorHoraExtra, double inss, double irrf)
	{

		ContratoTO cont = contrato.pesquisarEmpregado(codigoEmpregado);
		ArrayList<PontoTO> listaDePonto = ponto.pesquisar(cont.getCodigo());
		int qtdFolgas = 0;
		int qtdDeMesesTrabalhados = mes; 
		int contador = 0;
		for(PontoTO pontoTO: listaDePonto)
		{
			SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
			String dataPonto = formatador.format(pontoTO.getDataPonto());
			int pontoDataMes = Integer.parseInt(dataPonto.substring(5,7));
			int pontoDataAno = Integer.parseInt(dataPonto.substring(0,4));	  
			
			// verificando os pontos dentro do periodo selecionado		
			if( pontoDataAno == ano && pontoDataMes <= mes)
			{
				if(pontoTO.getAcao().equals("Falta"))
				{
					qtdFolgas += 1;
				}
				
				if(qtdFolgas > 15) //+ de 15 faltas no mes deixa de ter direito ao 1/12 avos do mes;
				{
					if(contador != pontoDataMes){
						contador = pontoDataMes;
						qtdDeMesesTrabalhados -= 1;
					}
				}
			}
		}
		double decimoTerceiro = (salario/12 * qtdDeMesesTrabalhados) +  valorHoraExtra - inss - irrf;
		 
		if(decimoTerceiro < 0){
			decimoTerceiro = 0.0;
		}
		return decimoTerceiro;
	}
	
}
